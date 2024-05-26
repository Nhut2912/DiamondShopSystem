import React from 'react'
import Header from '../components/Header'
import { Outlet } from 'react-router-dom'
import Footer from '../components/Footer'

function App() {
  return (
    <div style={{"width": "100%"}}>
        <Header />
        <Outlet />
        <Footer />
    </div>
  )
}

export default App