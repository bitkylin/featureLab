import React, { useState } from 'react'
import axios from 'axios'
import './App.css'

// 配置axios基础URL
const API_BASE_URL = 'http://localhost:8888'

// 统一错误处理函数
const handleApiError = async (err) => {
  // 网络错误处理
  if (!err.response) {
    if (err.request) {
      // 请求已发送但没有收到响应
      return '网络连接异常，请检查服务器是否正常运行'
    } else {
      // 请求配置出错
      return '请求配置错误'
    }
  }
  
  // 对于blob类型的响应，需要特殊处理错误
  let errorMessage = '操作失败'
  if (err.response.data instanceof Blob) {
    // 尝试将blob转换为文本以获取错误信息
    try {
      errorMessage = await new Response(err.response.data).text()
    } catch (e) {
      // 如果无法解析blob，则使用默认错误信息
      errorMessage = `请求失败，状态码: ${err.response.status}`
    }
  } else {
    // 对于普通文本响应，直接使用data
    errorMessage = err.response.data || '操作失败'
  }
  
  // 根据错误状态码提供更具体的错误信息
  if (err.response.status === 400) {
    return errorMessage // 如果没有匹配的映射，则使用原始错误信息
  } else {
    return `请求失败，状态码: ${err.response.status}`
  }
}

function App() {
  // 状态管理
  const [guyName, setGuyName] = useState('')
  const [dirPath, setDirPath] = useState('')
  const [fileList, setFileList] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const [activeTab, setActiveTab] = useState('list')

  // 列出目录下的所有文件
  const handleListFiles = async () => {
    setLoading(true)
    setError('')
    try {
      const response = await axios.get(`${API_BASE_URL}/spy/list`, {
        params: dirPath ? { dir: dirPath } : {}
      })
      setFileList(response.data)
    } catch (err) {
      const errorMsg = await handleApiError(err)
      setError(errorMsg)
    } finally {
      setLoading(false)
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
      // 由于这个API是文件下载，我们需要使用blob响应类型
      const response = await axios.get(`${API_BASE_URL}/spyGuy/**`, {
        params: { guy: guyName },
        responseType: 'blob'
      })
      // 创建下载链接
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      // 从响应头中提取文件名
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
      // 创建下载链接
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
      // 创建下载链接
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
            className={`tab ${activeTab === 'list' ? 'active' : ''}`} 
            onClick={() => setActiveTab('list')}
          >
            文件列表
          </button>
          <button 
            className={`tab ${activeTab === 'spy' ? 'active' : ''}`} 
            onClick={() => setActiveTab('spy')}
          >
            刺探工具
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

        {/* 文件列表标签页 */}
        {activeTab === 'list' && (
          <div className="tab-content">
            <div className="input-group">
              <input
                type="text"
                placeholder="输入目录路径（可选）"
                value={dirPath}
                onChange={(e) => setDirPath(e.target.value)}
              />
              <button onClick={handleListFiles} disabled={loading}>
                {loading ? '加载中...' : '获取文件列表'}
              </button>
            </div>
            
            {fileList.length > 0 && (
              <div className="file-list">
                <h3>文件列表:</h3>
                <ul>
                  {fileList.map((file, index) => (
                    <li key={index}>{file}</li>
                  ))}
                </ul>
              </div>
            )}
          </div>
        )}

        {/* 刺探工具标签页 */}
        {activeTab === 'spy' && (
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
                  {loading ? '刺探中...' : '刺探医生'}
                </button>
                <button 
                  className="quick-btn"
                  onClick={handleSpyDev} 
                  disabled={loading}
                >
                  {loading ? '刺探中...' : '刺探德芙'}
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
