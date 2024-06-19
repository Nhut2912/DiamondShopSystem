import React from 'react'
import '../../theme/admin/HeadTableAccount.css'

function HeadTableCardAccount(){
   return(
    <div className='head-table-account-container'>
       <ul className='head-table-account-content'>
          <li>ID</li>
          <li>Email</li>
          <li>Name</li>
          <li>Phone</li>
          <li>Status</li>
          <li>Role</li>
       </ul>
    </div>
   )
}
export default HeadTableCardAccount