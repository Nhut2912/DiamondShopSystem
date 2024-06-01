import React, { useEffect, useState } from 'react'
import HeadAccount from './HeadAccount'

import '../../theme/customer/AccountPurchase.css'
import AccountPurchaseCard from './AccountPurchaseCard';


function AccountPurchase() {
  const statusPurchase = [
    "All","Pending","Prepare","Delivering","Completed","Canceled"
  ]
  const [acttiveItem,setActiveItem] = useState("All");



  const handleChangeStatus = (item) => {
    setActiveItem(item);
  }

  return (
    <div className='account-purchase-container'>
       <HeadAccount />
       <p>
          Home / <span> Purchase History </span>
       </p>
       <h3>Purchase History</h3>
       <ul className='status-purchase'>
          {statusPurchase.map((item) => (
            <li 
            onClick={() => handleChangeStatus(item)}
            className={acttiveItem === item ? 'isActive' : ''}>
                {item}
            </li>
          ))}
       </ul>

      <AccountPurchaseCard />


    </div>
  )
}

export default AccountPurchase