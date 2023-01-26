import './App.css'
import axios from 'axios'
import {useState, useEffect} from 'react'

function App() {

const baseUrl = 'http://localhost:8080/api/map'
const [map, setMap] = useState("")

useEffect(() => {
  axios.get(baseUrl).then(res=>setMap(res.data))
}, [])

console.log(map)
  return (
    <div className="App">
      <header className="App-header">
        <p>Map: {map}</p>
      </header>
    </div>
  );
}

export default App;
