import React, { useEffect, useState } from 'react'

import '../../theme/customer/Order.css';
import { ICONS, IMAGES } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';
import { imageStorage } from '../../config/FirebaseConfig';
import { getDownloadURL, ref } from 'firebase/storage';


const numberFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    maximumSignificantDigits: 2
  });

function Order() {
  const [isPayment,setIsPayment] = useState(false); 
  const navigate = useNavigate();
  

  
  const [order,setOrder] = useState();
  
  const [cart,setCart] = useState();
  const [paymentMethod,setPaymentMethod] = useState("MoMo");
 
 const [imagesProduct,setImagesProduct] = useState();

 const paymentMethodList = [
    {name : "Bank Transfer", img : '',description  : "Bank Transfer"},
    {name : "MoMo", img : IMAGES.image_momo, description : "( Pay with MOMO wallet )" },
    {name : "VN PAY", img : IMAGES.image_vnpay, description : "( Pay with VNPAY wallet )" },
    {name : "PayPal", img : IMAGES.image_paypal, description : "( Pay with PAYPAL )" },
 ]


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
    && cart !== undefined) || (imagesProduct === undefined && imagesProduct === null)
 ) return <div>Loading</div>





 const choosePaymentMethod = (item) => {
    setPaymentMethod(item);
 }
  
 const handlePrevious = () => {
    navigate("/checkout-cart");
 }

 


 
  return (
    <div className='order-container-customer'>
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
                                <li>{numberFormatter.format(item.price)}
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
                                {console.log(order)}
                                      <li>
                                          <h5>Sub total</h5>
                                          <h4>{numberFormatter.format( order !== undefined &&
                                            order !== null ? 
                                            order.subTotal :"" )}</h4>
                                      </li>
                                      <li>
                                          <h5>
                                            Discount
                                          </h5>
                                          <h4>{numberFormatter.format(
                                            order !== undefined &&
                                            order !== null ? 
                                            order.discount : "")}</h4>
                                      </li>
                                      <li>
                                          <h5>Total</h5>
                                          <h4>{numberFormatter.format( order !== undefined &&
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
                                <li>{cart.length}</li>
                                <li>{numberFormatter.format(order.totalPrice)}</li>
                            </ul>
                            <div className='line'></div>
                                <ul>
                                    <li>
                                        <h5>
                                            Deposit Fee
                                        </h5>
                                        <h5>
                                        {numberFormatter.format(order.totalPrice*10/100)}
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
                            
                      <div className='button-order'>
                           <p>{"( Please select a payment method and pay to complete your order )"}</p>
                            <span>Payment</span>
                      </div>
                </div>
            </div>  
        }
    </div>
  )
}

export default Order;