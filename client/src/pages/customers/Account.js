import React from 'react'

import '../../theme/customer/Account.css'
import Navigate from '../../components/customer/Navigate'
import { Outlet } from 'react-router-dom'

function Account() {
  
  

  return (
    <div className='account-container'>
        <Navigate />
        <Outlet />
    </div>
  )
}

export default Account