import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';


function CheckPaymentMomo() {
 
 const navigate =  useNavigate();
 const [resultCode,setResultCode] = useState();
 const [transactionCode,setTransactionCode] = useState();
 const [responseTime,setResponseTime] = useState();
 const [order,setOrder] = useState();


 useEffect(()=> {
    const params =  new URLSearchParams(window.location.search);
    const resultCode =  params.get('resultCode');
    const transId = params.get('transId');
    const responseTime = params.get('responseTime');
    setResultCode(resultCode);
    setTransactionCode(transId);
    setResponseTime(responseTime);

    const order = JSON.parse(localStorage.getItem("order"));
    setOrder(order);
 },[]);

 if(resultCode !== undefined && resultCode !== null 
    && order !== null && order !== undefined){
    if(resultCode !== "0"){
        navigate("/checkout-cart/order");    
     }else{

        const responseTimeInMilliseconds = parseInt(responseTime, 10);
        const dateTime = new Date(responseTimeInMilliseconds);
        order.paymentDTOS.payTime = dateTime;
        order.paymentDTOS.transactionCode = transactionCode;


        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        const requestOptions = {
         method: "POST",
         headers: myHeaders,
         body: JSON.stringify(order),
         redirect: "follow"
       };
       
       fetch("http://localhost:8080/api/order/buy", requestOptions)
         .then((response) => response.text())
         .then((result) => 
            {
               if(result){
                  navigate("/checkout-cart/complete")
               }
            }
         )
         .catch((error) => console.error(error));

     }
    
 }
 

  return (
    <div>
   
    </div>
  )
}

export default CheckPaymentMomo