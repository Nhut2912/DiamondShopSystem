import React from 'react'

import '../theme/ProductDescription.css';

function ProductDescription() {
  return (
    <div className='product-description-container'>
        <div className='product-description-content'>
            <div className='navigate'>
                <ul>
                    <li className='isActive'>
                        Description and characteristics
                    </li>
                    <li>
                        Diamond certificate
                    </li>
                </ul>
            </div>
            <div className='content'>

            </div>
        </div>
    </div>
  )
}

export default ProductDescription