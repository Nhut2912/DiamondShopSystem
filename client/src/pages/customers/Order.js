import React, { useState } from 'react'

import '../../theme/customer/Order.css';
import { ICONS, IMAGES } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';


function Order() {
  const [isPayment,setIsPayment] = useState(false); 
  const navigate = useNavigate();


  const [paymentMethod,setPaymentMethod] = useState("MoMo");

 const paymentMethodList = [
    {name : "Bank Transfer", img : '',description  : "Bank Transfer"},
    {name : "MoMo", img : IMAGES.image_momo, description : "( Pay with MOMO wallet )" },
    {name : "VN PAY", img : IMAGES.image_vnpay, description : "( Pay with VNPAY wallet )" },
    {name : "PayPal", img : IMAGES.image_paypal, description : "( Pay with PAYPAL )" },
 ]

 const choosePaymentMethod = (item) => {
    setPaymentMethod(item);
 }
  
  return (
    <div className='order-container-customer'>
        <div className='continue-shopping'>
            <img src={ICONS.icon_back} alt=''/>
            <span>Previous</span>
        </div>
        <div className='status'>
            <div className='isActive'>
                <span>1</span>
            </div>
            <div className='isActive'></div>
            <div className='isActive' >
                <span>2</span>
            </div>
            <div className='isActive' ></div>
            <div className={isPayment ? 'isActive' : ''}>
                <span>3</span>
            </div>
            <div className={isPayment ? 'isActive' : ''} ></div>
            <div>
                <span>4</span>
            </div>    
        </div>

        <div className='head-order-information'>
            <div>
                <span>3</span>
            </div>
            <h2>Order Information</h2>
        </div>

        <div className='information-order'>
              <h3>ORDER INFORMATION</h3>
              <span className='type-order'>PICK UP IN STORE</span>
              <div className='customer-information'>
                  <ul>
                      <li>
                          <h5>Full Name</h5>
                          <span>Tran Minh Nhut</span>
                      </li>
                      <li>
                        <h5>Phone Number</h5>
                        <span>0384463039</span>
                      </li>
                      <li>
                          <h5>Email</h5>
                          <span>nhutminhtran05@gmail.com</span>
                      </li>
                      <li>
                          <h5>Address</h5>
                          <span>Lot E2a-7, Street D1, D. D1, Long Thanh My, Thu Duc City, Ho Chi Minh City 700000</span>
                      </li>
                  </ul>
              </div>
              <div className='products-information'>
                    <ul>
                        <li>
                            Image
                        </li>
                        <li>
                            Product
                        </li>
                        <li>
                            Size
                        </li>
                        <li>Price</li>
                    </ul>

                    <ul>
                      <li>
                          <div>

                          </div>
                      </li>
                      <li>
                          <div>
                              <h4>Diamond rings white gold Disney JEWELRY</h4>
                              <p>CODE : P01C01D0102 </p>
                              <span> x1 </span>
                          </div>
                      </li>
                      <li>
                          17
                      </li>
                      <li>
                          $1,003.87
                      </li>
                    </ul>


                    <div className='line'></div>
                    <div className='total'>
                            <div className=''>
                                  <p>
                                  Thank you for trusting our store's service. 
                                  Due to the high value of the goods, we would like to collect
                                   a <b>10% deposit fee on order</b>. You will be provided
                                    with detailed information when you click through to the payment section
                                  </p>
                            </div>
                            
                            <ul>
                                      <li>
                                          <h5>Sub total</h5>
                                          <h4>$1092</h4>
                                      </li>
                                      <li>
                                          <h5>
                                            Discount
                                          </h5>
                                          <h4>$103</h4>
                                      </li>
                                      <li>
                                          <h5>Total</h5>
                                          <h4>$989</h4>
                                      </li>
                            </ul>
                            
                    </div>

              </div>
        </div>
        <div className='button-payment' onClick={() => setIsPayment(true)}>
            <span>Payment</span>
        </div>



        {
            isPayment && 
            <div className='payment-method'>
                 <div className='head-payment-information'>
                    <div>
                        <span>4</span>
                    </div>
                    <h2>Payment</h2>
                </div>
                <div className='deposit'>
                        <h4>
                            DEPOSIT FEE
                        </h4>
                      <div>
                        <ul>
                                <li>Order ID</li>
                                <li>Orderer</li>
                                <li>Number Phone</li>
                                <li>Quantity Of Goods</li>
                                <li>Total Price</li>
                            </ul>
                            <ul>
                                <li>1234587</li>
                                <li>Tran Minh Nhut</li>
                                <li>0384463039</li>
                                <li>1</li>
                                <li>$989</li>
                            </ul>
                            <div className='line'></div>
                                <ul>
                                    <li>
                                        <h5>
                                            Deposit Fee
                                        </h5>
                                        <h5>
                                            $100
                                        </h5>
                                    </li>
                                </ul>
                            
                      </div>

                      <div className='choose-payment-method'>
                            <h4>
                                PAYMENT METHOD
                            </h4>
                            <div>
                                {paymentMethodList.map((item) => (
                                        <div
                                        onClick={() => choosePaymentMethod(item.name)}
                                        className={paymentMethod === item.name ? 'isActive' : ''} >
                                            <img src={item.img} alt=''/>
                                            <span>{item.description}</span>
                                        </div>
                                ))}
                            </div>
                      </div>

                      <div className='button-order'>
                            <span>Order</span>
                      </div>
                </div>
            </div>  
        }
    </div>
  )
}

export default Order;