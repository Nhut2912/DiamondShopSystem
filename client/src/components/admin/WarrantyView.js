import React from 'react'

import "../../theme/admin/WarrantyView.css"

function WarrantyView() {

  return (
    <div className='warranty-view-container'>
        <ul>
     
            <li>ID</li>
            <li>Customer</li>
            <li>Phone</li>
            <li>Address</li>
            <li>Date Start</li>
            <li>Date End</li>
            <li>Status</li>
            <li>Update</li>
        </ul>
        <ul className='isUpdate'>
            <li>ID</li>
            <li>Customer</li>
            <li>Phone</li>
            <li>Address</li>
            <li>Date Start</li>
            <li>Date End</li>
            <li className='isActive'>ACTIVE</li>
            <li className='isUpdate'>
              {
                true ?  <span>Edit</span>: <span>Update</span>
              }
             
              </li>
        </ul>
    </div>
  )
}

export default WarrantyView