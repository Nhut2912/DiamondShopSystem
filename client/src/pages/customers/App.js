import React from 'react'
import Header from '../../components/customer/Header'
import { Outlet } from 'react-router-dom'
import Footer from '../../components/customer/Footer'
import { CartProvider } from '../../context/CartContext'


function App() {
  return (
    <CartProvider>
       <div style={{"width": "100%"}}>
          <Header />
          <Outlet />
          <Footer />
      </div>
    </CartProvider>
   
  )
}

export default App