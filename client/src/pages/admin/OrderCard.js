import React from 'react'

import '../../theme/admin/OrderCard.css';

function OrderCard() {
  return (
    <div className='order-card-container'>
       <div>
            <span>#ID00001</span>
            <span>Date : 01/06/2024 9:36am</span>
       </div>
       <div>
            <ul>
                <li>
                    <h4>Customer </h4>
                    <span>Tran Minh Nhut</span>
                </li>
                <li>
                    
                </li>
                <li>
                    <h4>Number Of Product</h4>
                    <span>12</span>
                </li>
                
            </ul>
       </div>
    </div>
  )
}

export default OrderCard