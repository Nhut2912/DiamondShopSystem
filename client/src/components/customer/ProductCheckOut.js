import React from 'react'


import '../../theme/customer/ProductCheckOut.css';
import { ICONS } from '../../constants/customer';

function ProductCheckOut() {
  return (
    <div className='product-check-out-container'>
        <div>

        </div>
        <h2>14K White Gold Diamond Ring PNJ DDDDW003910</h2>
        <p>CODE: GNDDDDW003910</p>
        <ul>
            <li>
                <span>Price</span>
                <span>1</span>
            </li>
            <li>
                <span>
                    Size
                </span>
                <span>
                    1
                </span>
            </li>
            <li>
                <span>
                    Quantity
                </span>
                <span>
                    1
                </span>
            </li>
        </ul>
        <div>
             <img src={ICONS.icon_delete} alt=''/>
        </div>
    </div>
  )
}

export default ProductCheckOut