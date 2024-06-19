import React from 'react'

import "../../theme/admin/WarrantyPolicyViews.css"

function WarrantyPolicyViews() {
  return (
    <div className='warranty-policy-views-container'>
       <ul>
            <li>No.</li>
            <li>ID</li>
            <li>Name</li>
            <li>Period {"(Month)"}</li>
            <li>Active</li>
       </ul>
       <ul>
            <li>No.</li>
            <li>ID</li>
            <li>Name</li>
            <li>Period {"(Month)"}</li>
            <li>Active</li>
       </ul>
       <div className='line'></div>
    </div>
  )
}

export default WarrantyPolicyViews