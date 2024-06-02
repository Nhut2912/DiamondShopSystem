import React, { useEffect, useState } from 'react'

import '../../theme/admin/OrderAdmin.css';
import OrderCard from './OrderCard';
import { useNavigate } from 'react-router-dom';

function OrderAdmin() {
    const navigate = useNavigate();
    const [account,setAccount] = useState(null);
    const [activeItem,setActiveItem] = useState("ALL");
    const [navigateItem,setNavigateItem] = useState(null);


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

     if(account === null) return <div>Loading.......</div>

 const orderItems = [
    {OrderId: "ID0001",Customer: "Tran Minh Nhut",N_Phone :"0384463039", Address: "Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh 700000", status: "PENDING", total: "1034", date: "8:32pm 01/06/2024" },
    {OrderId: "ID0002",Customer: "Tran Minh Nhut",N_Phone :"0384463039", Address: "Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh 700000", status: "PREPARING", total: "1034", date: "8:32pm 01/06/2024" },
    {OrderId: "ID0003",Customer: "Tran Minh Nhut",N_Phone :"0384463039", Address: "Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh 700000", status: "PREPARED", total: "1034", date: "8:32pm 01/06/2024" },
    {OrderId: "ID0004",Customer: "Tran Minh Nhut",N_Phone :"0384463039", Address: "Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh 700000", status: "DELIVERING", total: "1034", date: "8:32pm 01/06/2024" },
    {OrderId: "ID0005",Customer: "Tran Minh Nhut",N_Phone :"0384463039", Address: "Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh 700000", status: "COMPLETED", total: "1034", date: "8:32pm 01/06/2024" },
    { OrderId: "ID0006",Customer: "Tran Minh Nhut",N_Phone :"0384463039", Address: "Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh 700000", status: "CANCELED", total: "1034", date: "8:32pm 01/06/2024" },
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
           {    account &&
                orderItems.filter((item) => {
                    if(activeItem !== "ALL"){
                        if(item.status === activeItem){
                            return item;
                        }
                    }else if(activeItem === "ALL" && account.role === "DELIVERY STAFF"){
                        if(item.status === "PREPARED" ||  item.status === "DELIVERING"
                        ||  item.status === "COMPLETED" ||  item.status === "CANCELED"
                         ){
                            return item;
                         }
                    }else{
                        return item;
                    }
                    
                }).map((item) => (
                    <OrderCard 
                     role={account.role}
                     orderID={item.OrderId}
                     Customer={item.Customer}
                     Address={item.Address}
                     N_Phone={item.N_Phone}
                     Status={item.status}
                     Date={item.date}
                     Total={item.total}
                     />
                ))
           }
        </div>
    </div>
  )
}

export default OrderAdmin