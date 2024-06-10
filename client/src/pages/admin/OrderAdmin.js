import React, { useContext, useEffect, useState } from 'react'

import '../../theme/admin/OrderAdmin.css';
import OrderCard from './OrderCard';
import { useNavigate } from 'react-router-dom';


function OrderAdmin() {
    const navigate = useNavigate();
    const [account,setAccount] = useState(null);
    const [activeItem,setActiveItem] = useState("ALL");
    const [navigateItem,setNavigateItem] = useState(null);
    const [data,setData] = useState();


     useEffect(() => {
        const loggedAccount = localStorage.getItem('account');
        if(loggedAccount){
            const account = JSON.parse(loggedAccount);
            setAccount(account);
            if(account.role === "DELIVERY STAFF" ){
                setNavigateItem (["ALL","PREPARED","DELIVERING","COMPLETED","CANCELED"]);
            }else {
                setNavigateItem([
                    "ALL","PENDING","PREPARING","PREPARED","DELIVERING","COMPLETED","CANCELED"
                 ]
            );
            }
        }else navigate("/admin")
     },[])

     useEffect(() => {
        fetch("http://localhost:8080/api/order/getAll")
        .then((response) => response.json())
        .then((result) => setData(result))
        .catch((error) => console.error(error));
     },[])



     if(account === null || data === undefined || data === null) return <div>Loading.......</div>

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

           {    account && data !== undefined && data !== null && 
                data.filter((item) => {
                    if(activeItem !== "ALL" ){
                        if(item.orderStatus === activeItem){
                            return item;
                        }
                    }else if(activeItem === "ALL" && account.role === "DELIVERY STAFF"){
                        if(item.orderStatus === "PREPARED" ||  item.orderStatus === "DELIVERING"
                        ||  item.orderStatus === "COMPLETED" ||  item.orderStatus === "CANCELED"
                         ){
                            return item;
                         }
                    }else{
                        return item;
                    }
                    
                }).map((item) => (
                    <OrderCard 
                     role={account.role}
                     orderID={item.id}
                     Customer={item.account.name}
                     Address={item.address}
                     N_Phone={item.account.numberPhone}
                     Status={item.orderStatus}
                     Date={item.date}
                     Total={item.totalPrice}
                     />
                ))
           }

        </div>
    </div>
  )
}

export default OrderAdmin