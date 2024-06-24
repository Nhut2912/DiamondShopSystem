import { wait } from '@testing-library/user-event/dist/utils';
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';


function CheckPaymentMomo() {
 
 const navigate =  useNavigate();

let count =0;
 
const [orderDTO,setOrderDTO] = useState();
const [resultCode,setResultCode] = useState();
const [responseTime,setResponseTime] = useState();
const [transaction,setTransaction] = useState();
 useEffect(()=> {
    const params =  new URLSearchParams(window.location.search);
    const resultCode =  params.get('resultCode');
    const transId = params.get('transId');
    const responseTime = params.get('responseTime');
    const orderObject = JSON.parse(localStorage.getItem("order"));


    if(resultCode !== null && resultCode !== undefined && resultCode !== "" &&
      transId !== null && transId !== undefined && transId !== "" &&
      responseTime !== null && responseTime !== undefined && responseTime !== "" &&
      orderObject !== null && orderObject !== undefined && orderObject !== ""
    ){
      setResponseTime(responseTime);
      setResultCode(resultCode);
      setOrderDTO(orderObject);
      setTransaction(transId);
    }


 },[]);




useEffect(() => {
   if(orderDTO !== undefined && orderDTO !== null &&
      resultCode !== undefined && resultCode !== null
   ){

      
         const responseTimeInMilliseconds = parseInt(responseTime, 10);
         const dateTime = new Date(responseTimeInMilliseconds);
         
         if(resultCode !== "0"){
            navigate("/checkout-cart/order");    
         }else{
            
            const orderSend = {
               "address": orderDTO.address,
               "totalPrice": orderDTO.totalPrice,
               "accountDTO": {
                   "id": orderDTO.accountDTO.id,
                   "name":  orderDTO.accountDTO.name,
                   "email": orderDTO.accountDTO.email,
                   "gender": orderDTO.accountDTO.gender,
                   "numberPhone": orderDTO.accountDTO.numberPhone,
                   "address": orderDTO.accountDTO.address,
                   "birthDay": orderDTO.accountDTO.birthDay
               },
               "orderDetailDTOS": orderDTO.orderDetailDTOS,
               "paymentDTOS": {
                   "amount": orderDTO.paymentDTOS.amount,
                   "image": orderDTO.paymentDTOS.image,
                   "transactionCode": transaction,
                   "payTime":dateTime ,
                   "paymentMethodDTO": {
                       "method": orderDTO.paymentDTOS.paymentMethodDTO.method
                   }
               },
               "delivery": true
           }


           const callPaymentAPi = async() => {
               const myHeaders = new Headers();
               myHeaders.append("Content-Type", "application/json");
         
         
               const requestOptions = {
                  method: "POST",
                  headers: myHeaders,
                  body: JSON.stringify(orderSend),
                  redirect: "follow"
               };
         
               await fetch("${process.env.REACT_APP_API_ENDPOINT}/api/order/buy", requestOptions)
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
            
            callPaymentAPi();
         
         }
      
   }
},[orderDTO])



  return (
    <div>
   
    </div>
  )
}

export default CheckPaymentMomo