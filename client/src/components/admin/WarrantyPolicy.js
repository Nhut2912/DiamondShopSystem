import React from 'react'

import '../../theme/admin/WarrantyPolicy.css';
import WarrantyPolicyViews from './WarrantyPolicyViews';
import WarrantyPolicyAdd from './WarrantyPolicyAdd';

function WarrantyPolicy() {

  return (
    <div className='warranty-policy-container'>
        <WarrantyPolicyViews />
        <WarrantyPolicyAdd />
    </div>
  )
}

export default WarrantyPolicy