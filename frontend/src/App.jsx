import { useEffect, useState } from 'react'
import './App.css'

function App() {

  const API_URL = "/epigrams"

  const [epigram, setEpigram] = useState('')
  const [customEpigram, setCustomEpigram] = useState('')
  const [loading, setLoading] = useState(false)
  const [autoLoad, setAutoLoad] = useState(() => {
    return localStorage.getItem('autoLoad') !== 'false'
  })

  function toggleAutoLoad(loadState) {
    console.log(loadState)
    localStorage.setItem('autoLoad', String(loadState))
    setAutoLoad(loadState)
  }

  async function fetchEpigram() {
    setLoading(true)
    try {
      const res = await fetch(API_URL + "/random")
      if (res.ok) {
        const data = await res.json()
        setEpigram(data.epigram)
        localStorage.setItem('epigram', data.epigram)
      }
    } catch (error) {
      console.error('Error fetching epigram:', error)
    } finally {
      setLoading(false)
    }
  }

  async function submitEpigram() {
    try {
      const res = await fetch(API_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          epigram: customEpigram
        })
      })
      if (res.ok) {
        setCustomEpigram('')
      }
    } catch (error) {
      console.error('Error submitting epigram:', error)
    }
  }


  useEffect(() => {
    const localEpigram = localStorage.getItem('epigram')
    if (!autoLoad && localEpigram) {
      setEpigram(localEpigram)
    } else {
      fetchEpigram()
    }
  }, [])

  return (
    <div className='epigram-container'>
      <div className='epigram-content'>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <h1>{epigram}</h1>
        )}
      </div>
      <button onClick={fetchEpigram}>Load New Epigram</button>
      <button onClick={() => toggleAutoLoad(!autoLoad)}>{autoLoad ? 'Disable' : 'Enable'} Auto Load</button>
      <br />
      <input id='e-input' type="text" placeholder='Your new epigram' value={customEpigram} onChange={(e) => setCustomEpigram(e.target.value)} />
      <button onClick={submitEpigram}>Submit</button>
    </div>
  )
}

export default App
