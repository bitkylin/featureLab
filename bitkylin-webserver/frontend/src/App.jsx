import React, { useState } from 'react'
import axios from 'axios'
import './App.css'

// 配置axios基础URL
const API_BASE_URL = 'http://localhost:8888'

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
      setError(err.response?.data || '获取文件列表失败')
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
      setError(err.response?.data || '刺探伙计失败')
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
      setError(err.response?.data || '刺探医生失败')
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
      setError(err.response?.data || '刺探德芙失败')
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
            {error}
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
