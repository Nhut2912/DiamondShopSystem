import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';

function CheckRemainder() {

  const [payment,setPayment] = useState(); 
  const [resultCode,setResultCode] = useState();
  const [responseTime,setResponseTime] = useState();
  const [transaction,setTransaction] = useState();
  const navigate = useNavigate();


  useEffect(()=> {
    const params =  new URLSearchParams(window.location.search);
    const resultCode =  params.get('resultCode');
    const transId = params.get('transId');
    const responseTime = params.get('responseTime');
    const paymentObject = JSON.parse(localStorage.getItem("_orderID"));
    localStorage.removeItem("_orderID");

    if(resultCode !== null && resultCode !== undefined && resultCode !== "" &&
      transId !== null && transId !== undefined && transId !== "" &&
      responseTime !== null && responseTime !== undefined && responseTime !== "" &&
      paymentObject !== null && paymentObject !== undefined && paymentObject !== ""
    ){
      setResponseTime(responseTime);
      setResultCode(resultCode);
      setPayment(paymentObject);
      setTransaction(transId);
    }
 },[]);
 
 useEffect(()=> {
      if(payment !== undefined && payment !== null &&
        resultCode !== undefined && resultCode !== null
    ){

        const responseTimeInMilliseconds = parseInt(responseTime, 10);
        const dateTime = new Date(responseTimeInMilliseconds);

        const paymentSend = {
          "amount" : payment.amount,
          "payTime" : dateTime,
          "transactionCode" : transaction,
          "image": "",
          "paymentMethodDTO": {
              "method": payment.method
          }
        }
      
        const callPaymentAPi = async() => {
          const myHeaders = new Headers();
          myHeaders.append("Content-Type", "application/json");
    
    
          const requestOptions = {
             method: "POST",
             headers: myHeaders,
             body: JSON.stringify(paymentSend),
             redirect: "follow"
          };
    
          await fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/order/updatePayment?orderId=${payment.orderId}`, requestOptions)
             .then((response) => response.text())
             .then((result) => 
                {
                   if(result){
                      navigate("/purchase/remainder/status")
                   }
                }
             )
             .catch((error) => console.error(error));
 
       }
       
       callPaymentAPi();

    }
 },[payment,resultCode,responseTime])


  return (
    <div>



    </div>
  )
}

export default CheckRemainder;