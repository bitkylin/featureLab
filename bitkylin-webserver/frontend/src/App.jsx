import React, { useState, useEffect, useCallback } from 'react'
import axios from 'axios'
import './App.css'

// 配置axios基础URL
const API_BASE_URL = 'http://localhost:8080'

// 统一错误处理函数
const handleApiError = async (err) => {
  if (!err.response) {
    if (err.request) {
      return '网络连接异常，请检查服务器是否正常运行'
    } else {
      return '请求配置错误'
    }
  }

  let errorMessage = '操作失败'
  if (err.response.data instanceof Blob) {
    try {
      errorMessage = await new Response(err.response.data).text()
    } catch (e) {
      errorMessage = `请求失败，状态码: ${err.response.status}`
    }
  } else {
    errorMessage = err.response.data || '操作失败'
  }

  if (err.response.status === 400) {
    return errorMessage
  } else {
    return `请求失败，状态码: ${err.response.status}`
  }
}

// 格式化文件大小
const formatFileSize = (bytes) => {
  if (bytes === 0) return '-'
  const k = 1024
  const sizes = ['B', 'KB', 'MB', 'GB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i]
}

// 格式化时间
const formatTime = (timestamp) => {
  if (!timestamp) return '-'
  return new Date(timestamp).toLocaleString()
}

function App() {
  // 状态管理
  const [guyName, setGuyName] = useState('')
  const [currentPath, setCurrentPath] = useState('')
  const [rootPath, setRootPath] = useState('')
  const [fileList, setFileList] = useState([])
  const [selectedFiles, setSelectedFiles] = useState(new Set())
  const [loading, setLoading] = useState(false)
  const [packLoading, setPackLoading] = useState(false)
  const [error, setError] = useState('')
  const [warning, setWarning] = useState('')
  const [activeTab, setActiveTab] = useState('browser')

  // 初始化加载当前路径
  useEffect(() => {
    fetchCurrentPath()
  }, [])

  // 获取当前路径
  const fetchCurrentPath = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/spy/current`)
      const path = response.data.path
      setRootPath(path)
      loadFileList(path)
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    }
  }

  // 加载文件列表
  const loadFileList = async (dir) => {
    setLoading(true)
    setError('')
    setCurrentPath(dir)
    try {
      const response = await axios.get(`${API_BASE_URL}/spy/list`, {
        params: { dir }
      })
      setFileList(response.data)
      setSelectedFiles(new Set())
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    } finally {
      setLoading(false)
    }
  }

  // 点击目录进入
  const handleDirectoryClick = (file) => {
    if (file.directory) {
      loadFileList(file.path)
    }
  }

  // 点击返回上一级
  const handleUpOneLevel = () => {
    // 处理 Windows 和 Unix 路径分隔符
    let lastSeparatorIndex = currentPath.lastIndexOf('/')
    if (lastSeparatorIndex === -1) {
      lastSeparatorIndex = currentPath.lastIndexOf('\\')
    }
    // 如果找不到分隔符或者分隔符在末尾（根目录情况），则不能继续返回
    if (lastSeparatorIndex <= 0) {
      return
    }
    const parentPath = currentPath.substring(0, lastSeparatorIndex)
    loadFileList(parentPath)
  }

  // 点击返回根目录
  const handleBackToRoot = () => {
    if (rootPath) {
      loadFileList(rootPath)
    }
  }

  // 切换文件选中状态
  const handleFileSelect = (file, event) => {
    event.stopPropagation()
    const newSelected = new Set(selectedFiles)
    if (newSelected.has(file.path)) {
      newSelected.delete(file.path)
    } else {
      newSelected.add(file.path)
    }
    setSelectedFiles(newSelected)
  }

  // 全选/取消全选
  const handleSelectAll = (event) => {
    if (event.target.checked) {
      const allPaths = fileList.map(f => f.path)
      setSelectedFiles(new Set(allPaths))
    } else {
      setSelectedFiles(new Set())
    }
  }

  // 打包下载选中文件
  const handlePackDownload = async () => {
    if (selectedFiles.size === 0) {
      setError('请先选择要下载的文件或文件夹')
      return
    }

    setPackLoading(true)
    setError('')
    setWarning('')
    try {
      const paths = Array.from(selectedFiles)
      const response = await axios.post(`${API_BASE_URL}/spy/pack`, { paths }, {
        responseType: 'blob'
      })

      // 检查是否使用默认密码
      const usingDefaultPassword = response.headers['x-using-default-password'] === 'true'
      if (usingDefaultPassword) {
        setWarning('当前使用默认密码进行加密，建议配置环境变量 ZIP_DOWNLOAD_PASSWORD')
      }

      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      const contentDisposition = response.headers['content-disposition']
      let fileName = 'packed_files.zip'
      if (contentDisposition) {
        const match = contentDisposition.match(/filename="([^"]+)"/)
        if (match && match[1]) {
          fileName = match[1]
        }
      }
      link.setAttribute('download', fileName)
      document.body.appendChild(link)
      link.click()
      link.remove()
      setSelectedFiles(new Set())
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    } finally {
      setPackLoading(false)
    }
  }

  // 刺探特定伙计
  const handleSpyGuy = async () => {
    if (!guyName.trim()) {
      setError('请输入伙计名称')
      return
    }

    setLoading(true)
    setError('')
    try {
      const response = await axios.get(`${API_BASE_URL}/spyGuy/**`, {
        params: { guy: guyName },
        responseType: 'blob'
      })
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      const contentDisposition = response.headers['content-disposition']
      let fileName = guyName
      if (contentDisposition) {
        const match = contentDisposition.match(/filename="([^"]+)"/)
        if (match && match[1]) {
          fileName = match[1]
        }
      }
      link.setAttribute('download', fileName)
      document.body.appendChild(link)
      link.click()
      link.remove()
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    } finally {
      setLoading(false)
    }
  }

  // 刺探医生
  const handleSpyDoc = async () => {
    setLoading(true)
    setError('')
    try {
      const response = await axios.get(`${API_BASE_URL}/spy/doc`, {
        responseType: 'blob'
      })
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', 'doc')
      document.body.appendChild(link)
      link.click()
      link.remove()
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    } finally {
      setLoading(false)
    }
  }

  // 刺探德芙
  const handleSpyDev = async () => {
    setLoading(true)
    setError('')
    try {
      const response = await axios.get(`${API_BASE_URL}/spy/dev`, {
        responseType: 'blob'
      })
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', 'dev')
      document.body.appendChild(link)
      link.click()
      link.remove()
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="app">
      <header className="app-header">
        <h1>假想敌服务 - 前端界面</h1>
      </header>

      <div className="app-content">
        {/* 标签页导航 */}
        <div className="tabs">
          <button
            className={`tab ${activeTab === 'browser' ? 'active' : ''}`}
            onClick={() => setActiveTab('browser')}
          >
            文件浏览
          </button>
          <button
            className={`tab ${activeTab === 'tools' ? 'active' : ''}`}
            onClick={() => setActiveTab('tools')}
          >
            快捷工具
          </button>
        </div>

        {/* 错误信息显示 */}
        {error && (
          <div className="error-message">
            <span className="error-icon">⚠️</span>
            {error}
            <button
              className="error-close-btn"
              onClick={() => setError('')}
              title="关闭错误提示"
            >
              ×
            </button>
          </div>
        )}

        {/* 警告信息显示 */}
        {warning && (
          <div className="warning-message">
            <span className="warning-icon">ℹ️</span>
            {warning}
            <button
              className="error-close-btn"
              onClick={() => setWarning('')}
              title="关闭提示"
            >
              ×
            </button>
          </div>
        )}

        {/* 文件浏览标签页 */}
        {activeTab === 'browser' && (
          <div className="tab-content">
            {/* 路径导航栏 */}
            <div className="path-navigation">
              <div className="path-nav-buttons">
                <button
                  className="nav-btn"
                  onClick={handleBackToRoot}
                  title="回到当前根目录"
                >
                  🏠 首页
                </button>
                <button
                  className="nav-btn"
                  onClick={handleUpOneLevel}
                  disabled={!currentPath}
                  title="返回上一级"
                >
                  ⬆️ 上级
                </button>
              </div>
              <div className="current-path">
                <span className="path-label">当前目录：</span>
                <span className="path-value" title={currentPath}>
                  {currentPath || '加载中...'}
                </span>
              </div>
            </div>

            {/* 文件表格 */}
            <div className="file-table-container">
              {loading ? (
                <div className="loading">加载中...</div>
              ) : fileList.length === 0 ? (
                <div className="empty">目录为空</div>
              ) : (
                <table className="file-table">
                  <thead>
                    <tr>
                      <th className="checkbox-col">
                        <input
                          type="checkbox"
                          checked={selectedFiles.size === fileList.length && fileList.length > 0}
                          onChange={handleSelectAll}
                        />
                      </th>
                      <th>名称</th>
                      <th className="size-col">大小</th>
                      <th className="date-col">修改时间</th>
                    </tr>
                  </thead>
                  <tbody>
                    {fileList.map((file) => (
                      <tr
                        key={file.path}
                        className={`
                          ${file.directory ? 'is-directory' : ''}
                          ${selectedFiles.has(file.path) ? 'selected' : ''}
                        `}
                        onClick={() => handleDirectoryClick(file)}
                      >
                        <td className="checkbox-col" onClick={(e) => e.stopPropagation()}>
                          <input
                            type="checkbox"
                            checked={selectedFiles.has(file.path)}
                            onChange={(e) => handleFileSelect(file, e)}
                          />
                        </td>
                        <td className="name-col">
                          <span className="file-icon">
                            {file.directory ? '📁' : '📄'}
                          </span>
                          <span className="file-name">{file.name}</span>
                        </td>
                        <td className="size-col">
                          {formatFileSize(file.size)}
                        </td>
                        <td className="date-col">
                          {formatTime(file.lastModified)}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>

            {/* 浮动操作弹窗 */}
            {selectedFiles.size > 0 && (
              <div className="action-popup">
                <span className="selected-count">已选择 {selectedFiles.size} 项</span>
                <button
                  className="pack-btn"
                  onClick={handlePackDownload}
                  disabled={packLoading}
                >
                  {packLoading ? '打包中...' : '📦 打包下载'}
                </button>
                <button
                  className="clear-btn"
                  onClick={() => setSelectedFiles(new Set())}
                >
                  取消选择
                </button>
              </div>
            )}
          </div>
        )}

        {/* 快捷工具标签页 */}
        {activeTab === 'tools' && (
          <div className="tab-content">
            <div className="spy-tools">
              {/* 刺探特定伙计 */}
              <div className="tool-group">
                <h3>刺探特定伙计</h3>
                <div className="input-group">
                  <input
                    type="text"
                    placeholder="输入伙计名称"
                    value={guyName}
                    onChange={(e) => setGuyName(e.target.value)}
                  />
                  <button onClick={handleSpyGuy} disabled={loading}>
                    {loading ? '刺探中...' : '开始刺探'}
                  </button>
                </div>
              </div>

              {/* 快速刺探按钮 */}
              <div className="quick-spy">
                <h3>快速刺探</h3>
                <button
                  className="quick-btn"
                  onClick={handleSpyDoc}
                  disabled={loading}
                >
                  {loading ? '刺探中...' : '📄 刺探医生'}
                </button>
                <button
                  className="quick-btn"
                  onClick={handleSpyDev}
                  disabled={loading}
                >
                  {loading ? '刺探中...' : '📦 刺探德芙'}
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

export default App
