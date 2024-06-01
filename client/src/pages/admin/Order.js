import React from 'react'

import '../../theme/admin/Order.css';
import OrderAdmin from './OrderAdmin';

function Order() {
  return (
    <div className='order-container'>
        <h1>Orders</h1>
        <p>
          Admin / <span>Manage Orders</span>
        </p>
        <OrderAdmin />
    </div>
  )
}

export default Order