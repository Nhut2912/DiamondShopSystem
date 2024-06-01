import React, { useState } from 'react'

import '../../theme/admin/OrderAdmin.css';
import OrderCard from './OrderCard';

function OrderAdmin() {
 
 const [activeItem,setActiveItem] = useState("ALL");

 const navigateItem = [
    "ALL","PENDING","PREPARING","PREPARED","DELIVERING","COMPLETED","CANCELED"
 ]

 const handleClick = (item) => {
    setActiveItem(item);
 }

  return (
    <div className='order-admin-manage'>
        <ul>
            {navigateItem.map( (item)=> (
                <li
                onClick={() => handleClick(item)}
                className={activeItem === item ? 'isActive' : ''}>{item}</li>
            ))}
        </ul>
        <div className='order-card-container'>
            <OrderCard />
        </div>
    </div>
  )
}

export default OrderAdmin