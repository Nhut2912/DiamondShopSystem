import React from 'react'

import "../../theme/admin/WarrantyPolicyAdd.css"


function WarrantyPolicyAdd() {
  return (
    <div className='warranty-policy-add-container'>
        <h4>Add Warranty Policy</h4>
        <div>
            <label>Name</label>
            <input type='text' />
        </div>
        <div>
            <label>Period {"(Month)"}</label>
            <input type='text' />
        </div>
        <div>
            Add Policy
        </div>
    </div>
  )
}

export default WarrantyPolicyAdd