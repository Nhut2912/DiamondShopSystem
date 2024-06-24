import React, { useEffect, useState } from 'react'
import HeadAccount from './HeadAccount'

import '../../theme/customer/AccountPurchase.css'
import AccountPurchaseCard from './AccountPurchaseCard';


function AccountPurchase() {
  const statusPurchase = [
    "ALL","PENDING","PREPARE","DELIVERING","COMPLETED","CANCELLED"
  ]
  const [acttiveItem,setActiveItem] = useState("ALL");



  const [orders,setOrders] = useState();




  useEffect(() => {

    const account = JSON.parse(localStorage.getItem("account"));
    const requestOptions = {
      method: "GET",
      redirect: "follow"
    };
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/order/account/${account.id}`, requestOptions)
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
        orders.filter((element) =>{
          if(acttiveItem === "ALL"){
             return element;
          }else if(acttiveItem === "PREPARE"){
            if(element.orderStatus.trim() === "PREPARING" || element.orderStatus.trim() === "PREPARED"){
              return element;
            }
          }else{
            if(element.orderStatus.trim() === acttiveItem){
              return element;
            }
          }
        }).map((item)=> (
          <AccountPurchaseCard 
          orderID={item.id} 
          isDelivery={item.delivery}
          totalPrice={item.totalPrice}
          ordeDate={item.date}
          orderStatus={item.orderStatus}
          />
        ))
       : ""
      }




    </div>
  )
}

export default AccountPurchase