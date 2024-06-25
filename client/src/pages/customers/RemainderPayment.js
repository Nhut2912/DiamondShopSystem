import React, { useEffect, useState } from 'react'

import '../../theme/customer/RemainderPayment.css';
import { IMAGES } from '../../constants/customer';
import InputFile from '../../components/customer/InputFile';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { imageStorage } from '../../config/FirebaseConfig';
import { ref, uploadBytes } from 'firebase/storage';

const convertUSDToVnd = (usd) => {
    const usdPrice = 25430;
    return Math.ceil(usd*usdPrice);
}  



function RemainderPayment() {

  const params = useParams(); 
  const orderID = params.id;

  const navigate = useNavigate();


  const [payments,setPayments] = useState();
  const [accountDTO,setAccountDTO]  = useState();
  const [deposit,setDeposit] = useState();
  const [numberOfGoods,setNumberOfGoods] = useState();
  const [imagePayment,setImagePayment] = useState();
  const [fileImagePayment,setFileImagePayment] = useState();


  const paymentMethodList = [
    {name : "Bank Transfer", img : '',description  : "Bank Transfer"},
    {name : "MoMo", img : IMAGES.image_momo, description : "( Pay with MOMO wallet )" },
    {name : "VNPAY", img : IMAGES.image_vnpay, description : "( Pay with VNPAY wallet )" },
    {name : "PayPal", img : IMAGES.image_paypal, description : "( Pay with PAYPAL )" },
 ]
 const [paymentMethod,setPaymentMethod] = useState("MoMo");
 const [isBankTransfer,setIsBankTransfer] = useState(false);

 const choosePaymentMethod = (item) => {
    if(item !== "Bank Transfer"){
        setIsBankTransfer(false);
    }
    setPaymentMethod(item);
 }
  
  useEffect(() => {
    const cart = localStorage.getItem("account");
    if(cart !== undefined && cart !== null){
        const cartObject = JSON.parse(cart);
        setAccountDTO(cartObject);
    }
 },[])

 useEffect(() => {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/payment?order_id=${orderID}`)
    .then((response) => response.json())
    .then( ( result ) => {
        if(result !== null){
            setPayments(result);
            setDeposit(result[0].amount);
        }

    }).catch((error) => console.error(error));
 },[])
 
 useEffect(()=> {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/order_detail?order_id=${orderID}`)
    .then((response) => response.json())
    .then( ( result ) => {
        if(result !== null){
            setNumberOfGoods(result.length);
        }

    }).catch((error) => console.error(error));
 },[])


 if(deposit === undefined || deposit === null ) return <div>Loading..</div>
 if(payments === undefined || payments === null) return <div>Loading...</div>



 let remainder = 0;
 if(payments !== undefined && payments !== null && deposit !== undefined && deposit !== null){
       remainder = payments[0].order.totalPrice -deposit
 }


 const handlePayment = async () => {
    
    await fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/paymentRemainder/`+convertUSDToVnd(remainder))
    .then((response) => response.text())
    .then((result) => 
       {
        if(result !== undefined){
            localStorage.setItem("_orderID",JSON.stringify({
                "orderId": orderID,
                "amount" : remainder.toFixed(2),
                "method" : "MOMO"
            }));
            window.location.assign(JSON.parse(result).payUrl)
        }
       }
    )
    .catch((error) => console.error(error));

 }



 const handlePaymentBankTransfer = () => {

    let url ="";
    if(fileImagePayment){
       
        const randomString = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
        const millisecond = new Date().getMilliseconds();
        const filename = `payment_${randomString}_${millisecond}.${fileImagePayment.type.split('/')[1]}`;
        url = `uploads/${filename}`;
        const imageRef = ref(imageStorage,url);
        uploadBytes(imageRef,fileImagePayment);
    }



    const paymentSend = {
        "amount" : remainder.toFixed(2),
        "payTime" : "",
        "transactionCode" : "",
        "image": url,
        "paymentMethodDTO": {
            "method": "BANKTRANSFER"
        }
      }
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
  
  
        const requestOptions = {
           method: "POST",
           headers: myHeaders,
           body: JSON.stringify(paymentSend),
           redirect: "follow"
        };
  
        fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/order/updatePayment?orderId=${orderID}`, requestOptions)
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



 const handleContinue = () => {
    if(paymentMethod === "Bank Transfer"){
        setIsBankTransfer(true);
    }else if(paymentMethod === "MoMo"){
        handlePayment();
    }
 }



  return (
               <div className='remainder-payment'  id="order-payment-page">
               <h1>Remainder Payment</h1>
               <p>Purchase History / <span>Remainder Payment</span> / <span>{orderID}</span></p>
                 <div className='deposit'>
                              <h4>
                                  REMAINDER FEE
                              </h4>
                            <div>
                              <ul>
                                      <li>Name</li>
                                      <li>Number Phone</li>
                                      <li>Quantity Of Goods</li>
                                      <li>Total Price</li>
                                  </ul>
                                  <ul>
                                     
                                      <li>{payments !== undefined && payments !== null &&
                                        payments.length > 0 ?
                                        payments[0].order.account.name : ''
                                      }</li>
                                      <li>{payments !== undefined && payments !== null &&
                                        payments.length > 0 ?
                                        payments[0].order.account.numberPhone : ''
                                      }</li>
                                      <li>{numberOfGoods}</li>
                                      <li>${payments !== undefined && payments !== null &&
                                        payments.length > 0 ?
                                        payments[0].order.totalPrice.toFixed(2) : ''
                                      }</li>
                                  </ul>
                                  <div className='line'></div>
                                      <ul>
                                          <li>
                                              <h5>
                                                  Deposit Fee
                                              </h5>
                                              <h5>
                                                 ${deposit.toFixed(2)}
     
                                                 <span style={{color: "green"}}>DONE</span>
                                              </h5>
                                          </li>
                                          <li>
                                              <h5>
                                                  Remainder Fee
                                              </h5>
                                              <h5>
                                                 ${payments !== undefined && payments !== null &&
                                        payments.length > 0 ?
                                        (payments[0].order.totalPrice -deposit).toFixed(2) : ''
                                      }
                                              </h5>
                                          </li>
                                      </ul>
                            </div>
      
                           <h4>
                             PAYMENT METHOD
                           </h4>
     
                         <div className='payment-method'>
                                
                                      {paymentMethodList.map((item) => (
                                              <div
                                              onClick={() => choosePaymentMethod(item.name)}
                                              className={paymentMethod === item.name ? 'isActive' : ''} >
                                                  <img src={item.img} alt=''/>
                                                  <span>{item.description}</span>
                                              </div>
                                      ))}
                            
                         </div>   
                         {
                            !isBankTransfer ?  <div
                            onClick={handleContinue}
                         
                         className='continue-pay-remainder'>Continue</div> : ""
                            
                         }
                        

                       {

                    isBankTransfer   ? 
                        <div className='remainder-payment-banktransfer' >
                            <h4>BANK TRANSFER METHOD</h4>
                                    <div className='payment-qr-bank-container'>
                                                <div className='qr-bank'>
                                                    <div className='qr-bank-img'>
                                                        <img src={IMAGES.image_qr_bank_pay} alt='' />
                                                    </div>
                                                    <div className='information-bank'>
                                                        <ul>
                                                            <li>
                                                                <h4>BANK NAME</h4>
                                                                <h3>TPBANK</h3>
                                                            </li>
                                                            <li>
                                                                <h4>ACCOUNT OWNER</h4>
                                                                <h3>Tran Minh Nhut</h3>
                                                            </li>
                                                            
                                                            <li>
                                                                <h4>ACCOUNT NUMBER</h4>
                                                                <h3>10384463039</h3>
                                                            </li>
                                                        </ul>
                                                        <p>
                                                        {"( After payment, please take a photo of the transaction and post it on the button below )"}
                                                        </p>
                                                    </div>
                                                </div>

                                                <div className='upload-payment'>
                                                    <div>
                                                        <InputFile
                                                            setImageData={imagePayment}
                                                            setFileImage={fileImagePayment}
                                                        _width="200px" />
                                                        <p>Please upload your reciept here</p>
                                                    </div>
                                                    <div className='receipt'>
                                                        <img src={imagePayment} alt=''/>
                                                    </div>
                                                </div>
                                </div>
                                <div
                        
                                    className='button-complete-your-order'>
                                    <span>Pay Remainder</span>
                                </div>
                    </div>  : ""
                }


            

        </div>
        </div>
                
   
  )
}

export default RemainderPayment


                   

                    