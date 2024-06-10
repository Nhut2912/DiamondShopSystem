import React, { useEffect, useState } from 'react'

import '../../theme/customer/RemainderPayment.css';
import { IMAGES } from '../../constants/customer';
import InputFile from '../../components/customer/InputFile';


function RemainderPayment() {

  const [accountDTO,setAccountDTO]  = useState();
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


 const handleContinue = () => {
    if(paymentMethod === "Bank Transfer"){
        setIsBankTransfer(true);
    }else{

    }
 }




  return (
               <div className='remainder-payment'  id="order-payment-page">
               <h1>Remainder Payment</h1>
               <p>Purchase History / <span>Remainder Payment</span> / <span>1</span></p>
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
                                     
                                      <li>Tran Minh Nhut</li>
                                      <li>0384463039</li>
                                      <li>8</li>
                                      <li>$1000</li>
                                  </ul>
                                  <div className='line'></div>
                                      <ul>
                                          <li>
                                              <h5>
                                                  Deposit Fee
                                              </h5>
                                              <h5>
                                                 $100
     
                                                 <span style={{color: "green"}}>DONE</span>
                                              </h5>
                                          </li>
                                          <li>
                                              <h5>
                                                  Remainder Fee
                                              </h5>
                                              <h5>
                                                 $900
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
                                                            
                                                        _width="200px" />
                                                        <p>Please upload your reciept here</p>
                                                    </div>
                                                    <div className='receipt'>
                                                        <img  alt=''/>
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


                   

                    