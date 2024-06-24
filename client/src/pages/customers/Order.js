import React, { useEffect, useRef, useState } from 'react'

import '../../theme/customer/Order.css';
import { ICONS, IMAGES } from '../../constants/customer';
import { Link, useNavigate } from 'react-router-dom';
import { imageStorage } from '../../config/FirebaseConfig';
import { getDownloadURL, ref, uploadBytes } from 'firebase/storage';
import InputFile from '../../components/customer/InputFile';



const formattedNumber = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 2,
  })
  

const convertUSDToVnd = (usd) => {
    const usdPrice = 25430;
    return Math.ceil(usd*usdPrice);
}  



function Order() {
 


  const [isPayment,setIsPayment] = useState(false); 
  const navigate = useNavigate();
  
  const [imagePayment,setImagePayment] = useState();
  const [filePayment,setFilePayment] = useState();
  const [accountDTO,setAccountDTO]  = useState();
  const [order,setOrder] = useState();
  const [isBankTransfer,setBankTransfer]  = useState(false);
  const [cart,setCart] = useState();
  const [paymentMethod,setPaymentMethod] = useState("MoMo");
 
 const [imagesProduct,setImagesProduct] = useState();

 const paymentMethodList = [
    {name : "Bank Transfer", img : '',description  : "Bank Transfer"},
    {name : "MoMo", img : IMAGES.image_momo, description : "( Pay with MOMO wallet )" },
    {name : "VNPAY", img : IMAGES.image_vnpay, description : "( Pay with VNPAY wallet )" },
    {name : "PayPal", img : IMAGES.image_paypal, description : "( Pay with PAYPAL )" },
 ]
 const topRef = useRef(null);



 useEffect(() => {
    if(isBankTransfer){
            const paymentELement = document.getElementById("order-payment-page");
            topRef.current = paymentELement;
            topRef.current.scrollIntoView({ behavior: 'smooth' });
    }else{
        const paymentELement = document.getElementById("order-container-customer");
        topRef.current = paymentELement;
        topRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  }, [isBankTransfer]);


 useEffect(() => {
    const order = localStorage.getItem("order");
    if(order !== undefined && order !== null){
        const orderObject = JSON.parse(order);
        setOrder(orderObject);
    }else navigate("/products");
 },[]);

 useEffect(() => {
    const cart = localStorage.getItem("account");
    if(cart !== undefined && cart !== null){
        const cartObject = JSON.parse(cart);
        setAccountDTO(cartObject);
        setCart(cartObject.cart);
    }
 },[])

 useEffect(()=> {
   if(cart !== null && cart !== undefined){
        const getImageUrls = async () => {
            const imageTmpProduct = [];
            const imagesFirebase  = [];
            cart.map((item) => {
                imagesFirebase.push(item.images[0]);
            })
            try {
              for (const image of imagesFirebase) {
                const imageRef = ref(imageStorage, image);
                const url = await getDownloadURL(imageRef);
                imageTmpProduct.push(url);
              }
              setImagesProduct(imageTmpProduct);
            } catch (error) {
              console.error('Error fetching image URLs:', error);
            }
          };
          getImageUrls(); 
   }
 },[cart])

 

 
 if((order === undefined && order === null) || (cart === undefined
    && cart === null) || (imagesProduct === undefined && imagesProduct === null) 
 ) return <div>Loading</div>





 const choosePaymentMethod = (item) => {
    setPaymentMethod(item);
 }
  
 const handlePrevious = () => {
    navigate("/checkout-cart");
 }


 const handlePayment = async () => {
    
    await fetch("${process.env.REACT_APP_API_ENDPOINT}/api/payment/"+convertUSDToVnd(order.totalPrice*10/100))
    .then((response) => response.text())
    .then((result) => 
        window.location.assign(JSON.parse(result).payUrl)
    )
    .catch((error) => console.error(error));

 }
 
 const handleOrder = () => {

    const cartItem = {
        "address": order.address,
        "totalPrice": order.totalPrice,
        "accountDTO": {
            "id": accountDTO.id,
            "name": order.accountDTO.name,
            "email": order.accountDTO.email,
            "gender" :order.accountDTO.gender === "Male" ? true : false,
            "numberPhone": order.accountDTO.numberPhone,
            "address": order.accountDTO.address,
            "birthDay": order.accountDTO.birthDay
        },
        "orderDetailDTOS": order.orderDetailDTOS,
        "paymentDTOS": {
            "amount": order.totalPrice*10/100,
            "image": "",
            "transactionCode" : "",
            "payTime" : "",
            "paymentMethodDTO": {
                "method": paymentMethod
            }
        },
        "delivery": true
    }

    if(paymentMethod === "Bank Transfer"){
        setBankTransfer(true);
    }else{   
        localStorage.setItem("order",JSON.stringify(cartItem));
        handlePayment();
    }
    console.log(cartItem);
 }

 const handleCompleteOrder = () => {

    let url = "";
    if(filePayment === undefined || filePayment === null){
        
        return;
    } 


    if(filePayment){
       
        const randomString = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
        const millisecond = new Date().getMilliseconds();
        const filename = `payment_${randomString}_${millisecond}.${filePayment.type.split('/')[1]}`;
        url = `uploads/${filename}`;
        const imageRef = ref(imageStorage,url);
        uploadBytes(imageRef,filePayment);
    }

    const cartItem = {
        "address": order.address,
        "totalPrice": order.totalPrice,
        "accountDTO": {
            "id":  accountDTO.id,
            "name": order.accountDTO.name,
            "email": order.accountDTO.email,
            "gender" :order.accountDTO.gender === "Male" ? true : false,
            "numberPhone": order.accountDTO.numberPhone,
            "address": order.accountDTO.address,
            "birthDay": order.accountDTO.birthDay
        },
        "orderDetailDTOS": order.orderDetailDTOS,
        "paymentDTOS": {
            "amount": order.totalPrice*10/100,
            "image": url,
            "paymentMethodDTO": {
                "method": "BANKTRANSFER"
            }
        },
        "delivery": true
    }   

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
 
    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(cartItem),
      redirect: "follow"
    };
    
    fetch("${process.env.REACT_APP_API_ENDPOINT}/api/order/buy", requestOptions)
      .then((response) => response.text())
      .then((result) => 
        {
            if(result === "true")navigate("/checkout-cart/complete");
        }
        )
      .catch((error) => console.error(error));
 }
 
  return (
    <>
        {!isBankTransfer ?
         <div className='order-container-customer'  id="order-container-customer">
         <div className='continue-shopping' 
             onClick={handlePrevious}
         >
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
               <span className='type-order'>{order !== undefined && order !== null &&
                                             order.delivery ? "DELIVERY" :"PICK UP IN STORE"
                 
               }</span>
               <div className='customer-information'>
                   <ul>
                       <li>
                           <h5>Full Name</h5>
                           <span>
                             {   
                                 order !== undefined && order !== null 
                                 && order.accountDTO.name !== undefined && order.accountDTO.name !== "" ? 
                                 order.accountDTO.name : ""
                             }
                           </span>
                       </li>
                       <li>
                         <h5>Phone Number</h5>
                         <span>
                         {
                                 order !== undefined && order !== null 
                                 && order.accountDTO.numberPhone !== undefined && order.accountDTO.numberPhone !== "" ? 
                                 order.accountDTO.numberPhone : ""
                             }
                         </span>
                       </li>
                       <li>
                           <h5>Email</h5>
                           <span>
                           {
                                 order !== undefined && order !== null 
                                 && order.accountDTO.email !== undefined && order.accountDTO.email !== "" ? 
                                 order.accountDTO.email : ""
                             }
                           </span>
                       </li>
                       <li>
                           <h5>Address</h5>
                           <span>
                           {
                                 order !== undefined && order !== null 
                                 && order.address !== undefined && order.address !== "" ? 
                                 order.address : ""
                             }
                           </span>
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
                     {   imagesProduct !== undefined &&
                          cart !== undefined && cart !== null 
                          && cart !== "" && cart.length > 0 ?
                          cart.map((item,index) => (
                             <ul>
                 
                                 <li>
                                     <div>
                                         <img src={imagesProduct[index]}  alt='' /> 
                                     </div>
                                 </li>
                                 <li>
                                     <div>
                                         <h4>{item.name}</h4>
                                         <p>CODE : {item.code} </p>
                                         <span> x1 </span>
                                     </div>
                                 </li>
                                 <li>
                                     {item.sizeUser}
                                 </li>
                                 <li>{formattedNumber.format(item.price)}
                                 </li>
                           </ul>
       
                          ))
                          :""
                     }
                  
 
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
                                           <h4>{formattedNumber.format( order !== undefined &&
                                             order !== null ? 
                                             order.subTotal :"" )}</h4>
                                       </li>
                                       <li>
                                           <h5>
                                             Discount
                                           </h5>
                                           <h4>{formattedNumber.format(
                                             order !== undefined &&
                                             order !== null ? 
                                             order.discount : "")}</h4>
                                       </li>
                                       <li>
                                           <h5>Total</h5>
                                           <h4>{formattedNumber.format( order !== undefined &&
                                             order !== null ? 
                                             order.totalPrice : "")}</h4>
                                       </li>
                             </ul>
                             
                     </div>
 
               </div>
         </div>
         <div className='button-payment' onClick={() => setIsPayment(true)}>
             <span>Confirm</span>
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
                                 <li>Name</li>
                                 <li>Number Phone</li>
                                 <li>Quantity Of Goods</li>
                                 <li>Total Price</li>
                             </ul>
                             <ul>
                                
                                 <li> {   
                                 order !== undefined && order !== null 
                                 && order.accountDTO.name !== undefined && order.accountDTO.name !== "" ? 
                                 order.accountDTO.name : ""
                             }</li>
                                 <li>{   
                                 order !== undefined && order !== null 
                                 && order.accountDTO.numberPhone !== undefined && order.accountDTO.numberPhone !== "" ? 
                                 order.accountDTO.numberPhone : ""
                             }</li>
                                 <li>{cart !== undefined && cart !== null ? cart.length : 0}</li>
                                 <li>{formattedNumber.format(order !== undefined && order !== null ? order.totalPrice : 0)}</li>
                             </ul>
                             <div className='line'></div>
                                 <ul>
                                     <li>
                                         <h5>
                                             Deposit Fee
                                         </h5>
                                         <h5>
                                         {formattedNumber.format(order !== undefined && order !== null ?  order.totalPrice*10/100 : 0)}
                                         </h5>
                                     </li>
                                 </ul>
                             
                       </div>
 
                       <div className='choose-payment-method'>
                             <h4>
                                DEPOSIT PAYMENT
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
                             
                       <div className='button-order'
                         onClick={handleOrder}
                       >
                            <p>{"( Please select a payment method and pay to complete your order )"}</p>
                             <span>Payment</span>
                       </div>
                 </div>
             </div>  
         }
     </div> :
     
     <div className='order-payment-page'  id="order-payment-page">
            <div className='order-payment-container'>
            <div className='deposit'>
                         <h4>
                             DEPOSIT FEE
                         </h4>
                       <div>
                         <ul>
                                 <li>Name</li>
                                 <li>Number Phone</li>
                                 <li>Quantity Of Goods</li>
                                 <li>Total Price</li>
                             </ul>
                             <ul>
                                
                                 <li> {   
                                 order !== undefined && order !== null 
                                 && order.accountDTO.name !== undefined && order.accountDTO.name !== "" ? 
                                 order.accountDTO.name : ""
                             }</li>
                                 <li>{   
                                 order !== undefined && order !== null 
                                 && order.accountDTO.numberPhone !== undefined && order.accountDTO.numberPhone !== "" ? 
                                 order.accountDTO.numberPhone : ""
                             }</li>
                                 <li>{cart !== undefined && cart !== null ? cart.length : 0}</li>
                                 <li>{formattedNumber.format( order !== undefined && order !== null ? order.totalPrice : 0)}</li>
                             </ul>
                             <div className='line'></div>
                                 <ul>
                                     <li>
                                         <h5>
                                             Deposit Fee
                                         </h5>
                                         <h5>
                                         {formattedNumber.format(order !== undefined && order !== null ?  order.totalPrice*10/100 : 0)}
                                         </h5>
                                     </li>
                                 </ul>
                       </div>
 
                      <h4>
                        PAYMENT
                      </h4>
                     
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
                                        setImageData={setImagePayment}
                                        setFileImage={setFilePayment}
                                    _width="200px" />
                                    <p>Please upload your reciept here</p>
                                </div>
                                <div className='receipt'>
                                    <img src={imagePayment} alt=''/>
                                </div>
                            </div>
                      </div>
                     

                     <div
                        onClick={handleCompleteOrder}
                     className='button-complete-your-order'>
                             <span>Complete Order</span>
                     </div>
                    
                 </div>
            </div>
     </div>
        }
    </>
  )
}

export default Order;