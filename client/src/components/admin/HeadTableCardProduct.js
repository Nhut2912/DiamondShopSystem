import React from 'react'

import '../../theme/admin/HeadTableCardProduct.css'

function HeadTableCardProduct() {
  return (
    <div className='head-table-container'>
        <ul className='head-table-content'>
          <li>
            No.
          </li>
          <li>
              Image
          </li>
          <li>
            Code
          </li>
          <li>
              Name
          </li>
          <li>
              Category
          </li>
          <li>
            Diamond Code
          </li>
          <li>
            Material
          </li>
          <li>
            Status
          </li>
        </ul>
    </div>
  )
}

export default HeadTableCardProduct