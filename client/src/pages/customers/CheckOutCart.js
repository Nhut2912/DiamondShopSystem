import React, { useContext, useEffect, useState } from 'react'

import '../../theme/customer/CheckOutCart.css';
import ProductCheckOut from '../../components/customer/ProductCheckOut';
import InputBox from '../../components/customer/InputBox';
import InputSelectBox from '../../components/customer/InputSelectBox';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';
import useLocalStorage from '../../hook/useLocalStorage';
import { CartContext } from '../../context/CartContext';


const numberFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    maximumSignificantDigits: 2
  });



function CheckOutCart() {
 const navigate = useNavigate();
 const cartContext = useContext(CartContext);


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
const [gender,setGender] = useState("Male");



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

 const Gender = [
    "Male","Female"
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
   
    let productsOrder = [];
    cart.map((item) => {
        const productOrder =  {
            "priceAfterSizeAdjustment": item.price,
            "size":item.sizeUser ,
            "productID": item.id
        }
        let productsOrderUpdate = [...productsOrder,productOrder];
        productsOrder = productsOrderUpdate;
    })


    const order = {
        "address": province +", " +district +", " + ward +", "+address,
        "totalPrice": total,
        "accountDTO": {
                  "id": 2,
                  "name": name,
                  "email": email,
                  "numberPhone": phone,
                  "address": province +", " +district +", " + ward +", "+address,
                  "birthDay": birthday
           
        },
        "orderDetailDTOS" :productsOrder,
        "delivery" : true
    }


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
        .then( (result) => {
            if(result === "true"){
                navigate("order")
            }
        }
        )
        .catch((error) => console.error(error));
 } 


 const handleDeleteProduct = (id) => {
    const cartUpdate = cart.filter(item => item.id !== id);
    setCart(cartUpdate);
    let accountUpdate = account;
    accountUpdate.cart = cartUpdate;
    setAccount(accountUpdate);
    cartContext.addToCart();
 }

 const handleRadioGender = (item) => {
    setGender(item);
 }

 const [statusInformationCustomer,setStatusInformationCustomer] = useState([
    {name : "Buyer Information", status : false},
    {name : "Form Of Receipt", status : false}
 ])




  return (
    <div className='check-out-cart'>

        <div className='continue-shopping'
            onClick={() => {
                navigate("/products");
            }}
            style={{cursor:'pointer'}}
        >
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
                                        handleDeleteProduct={handleDeleteProduct}
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
                         <ProductCheckOut
                               handleDeleteProduct={handleDeleteProduct}
                         data={cart[cart.length-1]} />
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
                      
                              <div className={statusInformationCustomer[0].status ? 'isActive' : ''}>
                                  <span>1</span>
                              </div>
                              <div  className={statusInformationCustomer[0].status ? 'isActive' : ''} ></div>
                              <div  className={statusInformationCustomer[1].status ? 'isActive' : ''} >
                                  <span>2</span>
                              </div>
                              <div  className={statusInformationCustomer[1].status ? 'isActive' : ''}></div>
                              <div>
                                  <span>3</span>
                              </div>
                              <div></div>
                              <div>
                                  <span>4</span>
                              </div>
                  
                  </div>
                  <div className='buyer-information'>
                        <div className={ statusInformationCustomer[0].status ? 'head-buyer-information isActive' :'head-buyer-information' }>
                              <div>
                                 <span>1</span>
                              </div>
                              <h2>Buyer information</h2>
                        </div>
                        
                        {
                            !statusInformationCustomer[0].status ? 
                            <>
                            <div>
                              <ul>

                                {
                                    Gender.map((item) => (
                                        <li 
                                        onClick={() => handleRadioGender(item)}
                                        className={gender === item ? "isActive" : ""}>
                                            <div></div>
                                            <span>{item}</span>
                                        </li>
                                    ))
                                }
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
                            <div className='button-continue'
                                onClick={() => {
                                    let status = [...statusInformationCustomer];
                                    status[0].status = true;
                                    setStatusInformationCustomer(status);
                                }}
                            >
                                <span>Continue</span>
                            </div>
                            </> : <div
                                  onClick={() => {
                                    let status = [...statusInformationCustomer];
                                    status[0].status = false;
                                    setStatusInformationCustomer(status);
                                }}
                            
                            className='edit'> Edit </div>
                        }
                        
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
                              <span
                                 onClick={() => {
                                    let status = [...statusInformationCustomer];
                                    status[1].status = true;
                                    setStatusInformationCustomer(status);
                                }}
                              >Continue</span>
                        </div>
                  </div>
            </div>
        </div>
    </div>
  )
}

export default CheckOutCart;