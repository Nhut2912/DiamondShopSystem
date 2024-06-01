import React from 'react'

import '../../theme/customer/CheckOutCart.css';
import ProductCheckOut from '../../components/customer/ProductCheckOut';
import InputBox from '../../components/customer/InputBox';
import InputSelectBox from '../../components/customer/InputSelectBox';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';


function CheckOutCart() {
 const navigate = useNavigate();

  return (
    <div className='check-out-cart'>

        <div className='continue-shopping'>
            <img src={ICONS.icon_back} alt=''/>
            <span>Continue shopping</span>
        </div>
        <div className='container'>
            <div className='cart-information'>
                  <h3>CART INFORMATION</h3>
                  <ProductCheckOut />
                  <div className='line'></div>
                  <ProductCheckOut />
                  <div className='sub-total'>
                      <ul>
                        <li>
                          <span>Sub total</span>
                          <span>1.2</span>
                        </li>
                        <li>
                          <span>
                            Discount
                          </span>
                          <span>2.3</span>
                        </li>
                      </ul>
                  </div>
                  <div className='total'>
                      <h4>Total</h4>
                      <span>400</span>
                  </div>
            </div>
            <div className='customer-information'>
                  <div className='status'>
                      
                              <div className='isActive'>
                                  <span>1</span>
                              </div>
                              <div className='isActive'></div>
                              <div>
                                  <span>2</span>
                              </div>
                              <div></div>
                              <div>
                                  <span>3</span>
                              </div>
                              <div></div>
                              <div>
                                  <span>4</span>
                              </div>
                  
                  </div>
                  <div className='buyer-information'>
                        <div className='head-buyer-information'>
                              <div>
                                 <span>1</span>
                              </div>
                              <h2>Buyer information</h2>
                        </div>
                        
                        <div>
                              <ul>
                                  <li>
                                     <div></div>
                                     <span>Male</span>
                                  </li>
                                  <li>
                                      <div>

                                      </div>
                                      <span>Female</span>
                                  </li>
                              </ul>
                        </div>
                        <div>
                            <InputBox title={"Fullname"} />
                            <InputBox title={"Number phone"} />
                            <InputBox title={"Email"} />
                            <InputBox title={"Birthday"} />
                        </div>
                        <div className='button-continue'>
                              <span>Continue</span>
                        </div>
                  </div>
                  <div className='form-of-receipt'>
                        <div className='head-buyer-information'>
                              <div>
                                 <span>2</span>
                              </div>
                              <h2>Form of receipt</h2>
                        </div>
                        <div> 
                            <div className='isActive'>
                               <span>Delivery</span>
                            </div>
                            <div>
                                <span>Pick up in store</span>
                            </div>
                        </div>
                        <div>
                             {true ? 
                                
                                     <div>
                                        <InputSelectBox title={"Province/City"} />
                                        <InputSelectBox title={"District"} />
                                        <InputSelectBox title={"Ward/Commune"} />
                                        <InputBox  title={"Detail Address"}/>
                                        <InputBox title={"Notes"}/>
                                    </div>
                                : 
                               
                                    <div>
                                        <InputSelectBox title={"Province/City"} />
                                        <InputSelectBox title={"District"} />
                                        <InputSelectBox title={"Store"} />
                                        <InputBox title={"Notes"}/>
                                    </div>
                                
                            }
                         
                        </div>
                        <div className='button-continue'
                            onClick={() => 
                                navigate("order")
                            }
                        >
                              <span>Continue</span>
                        </div>
                  </div>
            </div>
        </div>
    </div>
  )
}

export default CheckOutCart;