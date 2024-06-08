import React, { useEffect, useState } from 'react'
import HeadAccount from './HeadAccount'

import '../../theme/customer/AccountPurchase.css'
import AccountPurchaseCard from './AccountPurchaseCard';


function AccountPurchase() {
  const statusPurchase = [
    "All","PENDING","Prepare","Delivering","Completed","Canceled"
  ]
  const [acttiveItem,setActiveItem] = useState("All");


  const [orders,setOrders] = useState();

  useEffect(() => {
    const requestOptions = {
      method: "GET",
      redirect: "follow"
    };
    fetch("http://localhost:8080/api/order/account/2", requestOptions)
      .then((response) => response.text())
      .then(result => setOrders(JSON.parse(result)))
      .catch((error) => console.error(error));
  },[])




console.log(orders);

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

      {
        orders !== undefined && orders !== null ?
        orders.map((item) => (
          <AccountPurchaseCard 
          orderID={item.id} 
          isDelivery={item.delivery}
          totalPrice={item.totalPrice}
          ordeDate={item.date}
          orderStatus={item.orderStatus}
          />
      )) : ""
      }




    </div>
  )
}

export default AccountPurchase