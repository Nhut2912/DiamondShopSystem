import React, { useEffect, useState } from 'react'

import '../../theme/customer/CheckOutCart.css';
import ProductCheckOut from '../../components/customer/ProductCheckOut';
import InputBox from '../../components/customer/InputBox';
import InputSelectBox from '../../components/customer/InputSelectBox';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';
import useLocalStorage from '../../hook/useLocalStorage';


const numberFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    maximumSignificantDigits: 2
  });



function CheckOutCart() {
 const navigate = useNavigate();
 
 const [subTotal,setSubTotal] = useState(0);
 const [total,setTotal] = useState(0);
 const [discount,setDiscount] = useState(0);
 const [cart,setCart] = useState();
 const [account,setAccount] = useLocalStorage("account",localStorage.getItem("account"));

 const [name,setName] = useState();
 const [phone,setPhone] = useState();
 const [email,setEmail] = useState();
 const [birthday,setBirthay] = useState();
 const [province,setProvince] = useState();
 const [district,setDistrict] = useState();
 const [ward,setWard] = useState();
 const [address,setAddress] = useState();

 const Province = [
    {name : "HO CHI MINH"},{name : "HA NOI"}
 ]

 const District = [
    {name : "District 1"},{name : "District 2"},{name : "District 3"},{name : "District 4"},
    {name : "District 5"},{name : "District 6"},{name : "District 7"},{name : "District 8"},
    {name : "THU DUC"}
 ]

 const Ward = [
    {name : "TANG NHON PHU A"},{name : "TANG NHON PHU B"},{name : "LONG THANH MY"}
 ]


 useEffect(()=> {
    if(account === undefined || account === null){
        navigate("/login")
     }else{
        setCart(account.cart);
     }
     
 },[]);

 useEffect(()=> {
    if(cart !== undefined && cart !== null){
        let _subTotal = 0;
        cart.map((item) => {
           _subTotal += item.price;
        })
        console.log(_subTotal);
       setSubTotal(_subTotal);
       setTotal(subTotal + discount);
    }
 },[cart,subTotal])


 const handleOrder = () =>{
    const order = {

        "customer" :{
            "name" : name,
            "phone" : phone,
            "email" : email,
            "birthday" :birthday,
            "address" : address + ", " + ward +", " +district +", " +province
        },
        "cart" : cart
    }
    console.log(order);
 } 


  return (
    <div className='check-out-cart'>

        <div className='continue-shopping'>
            <img src={ICONS.icon_back} alt=''/>
            <span>Continue shopping</span>
        </div>
        <div className='container'>
            <div className='cart-information'>
                  <h3>CART INFORMATION</h3>

                    {
                         cart && cart.length > 0?
                        (
                            account.cart.slice(0,cart.length-1).map((item) => (
                                <>  
                                    <ProductCheckOut
                                        data={item}
                                        cart={setCart}
                                    />
                                    <div className='line'></div>
                                </>
                                
                            ))
                        )
                        :""
                    }
                    {
                       cart && cart !== null && cart.length > 0 &&
                       <>
                         <ProductCheckOut data={cart[cart.length-1]} />
                         <div className='sub-total'>
                                <ul>
                                    <li>
                                    <span>Sub total</span>
                                    <span>{numberFormatter.format(subTotal)}</span>
                                    </li>
                                    <li>
                                    <span>
                                        Discount
                                    </span>
                                    <span>{numberFormatter.format(discount)}</span>
                                    </li>
                                </ul>
                            </div>
                            <div className='total'>
                                <h4>Total</h4>
                                <span>{numberFormatter.format(total)}</span>
                            </div>
                       </>
                    }



                  
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
                            <InputBox
                                setParams={setName}
                               title={"Fullname"} />
                            <InputBox 
                                setParams={setPhone}
                            title={"Number phone"} />
                            <InputBox 
                                setParams={setEmail}
                            title={"Email"} />
                            <InputBox 
                                setParams={setBirthay}
                            title={"Birthday"} />
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
                                        <InputSelectBox 
                                            options={Province}
                                            setParams={setProvince}
                                        title={"Province/City"} />
                                        <InputSelectBox
                                            options={District}
                                            setParams={setDistrict}
                                        title={"District"} />
                                        <InputSelectBox
                                            options={Ward}
                                            setParams={setWard}
                                        title={"Ward/Commune"} />
                                        <InputBox
                                            setParams={setAddress}
                                        title={"Detail Address"}/>
                                        <InputBox title={"Notes"}/>
                                    </div>
                                : 
                               
                                    <div>
                                        <InputSelectBox 
                                             options={Province}
                                        title={"Province/City"} />
                                        <InputSelectBox 
                                             options={District}
                                        title={"District"} />
                                        <InputSelectBox title={"Store"} />
                                        <InputBox title={"Notes"}/>
                                    </div>
                                
                            }
                         
                        </div>
                        <div className='button-continue'
                            onClick={handleOrder}
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