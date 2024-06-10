import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'

import '../../theme/admin/OrderCardDetail.css';
import {ICONS} from '../../constants/admin'
import PaymentInformation from '../../components/admin/PaymentInformation';
import { imageStorage } from '../../config/FirebaseConfig';
import { getDownloadURL, ref } from 'firebase/storage';

function OrderCarddetail() {
 const order = useParams();
 const orderID = order.id;


 const [userRole,setUserRole] = useState();
 const [orderDetail,setOrderDetail] = useState();
 const navigate = useNavigate();
 const [imagesProduct,setImagesProduct] = useState();
 const [payments,setPayments] = useState();

 const [paymentDeposit,setPaymentDeposit] = useState();
 const [paymentRemainder,setPaymentRemainder] = useState(); 
 const [statusDeposit,setStatusDeposit] = useState();
 const [statusRemainder,setStatusRemainder] = useState();

 useEffect(() => {
    const loggedAccount = localStorage.getItem('account');
    if(loggedAccount){
        const account = JSON.parse(loggedAccount);
        setUserRole(account.role);
    }else navigate("/admin")
 },[])

 useEffect(() => {
        fetch(`http://localhost:8080/api/order_detail?order_id=${orderID}`)
        .then((response) => response.json())
        .then((result) => {
            if(result !== null){
                setOrderDetail(result);
            }
        })
        .catch((error) => console.error(error));
 },[])


 useEffect(() => {
    fetch(`http://localhost:8080/api/payment?order_id=${orderID}`)
  .then((response) => response.json())
  .then((result) => setPayments(result))
  .catch((error) => console.error(error));
 },[])


 useEffect(()=> {
    if(orderDetail !== undefined && orderDetail !== null){
        
        let getImageProduct =[];
        orderDetail.forEach(element => {
            getImageProduct = [...getImageProduct,element.product.images[0].url];
        });

        const getImageUrls = async () => {
              const imageTmpProduct = [];
              try {
                for (const image of getImageProduct) {
                  const imageRef = ref(imageStorage, image);
                  const url = await getDownloadURL(imageRef);
                  imageTmpProduct.push(url);
                }
              setImagesProduct(imageTmpProduct)
              } catch (error) {
                console.error('Error fetching image URLs:', error);
              }
        };
          
         getImageUrls(); 
          
    }
  },[orderDetail])

  useEffect(()=>{
        if(payments !== undefined){
            if(payments !== null && payments.length > 0){
                if(payments[0].order.orderStatus === "PENDING"){
                    setStatusDeposit("PENDING");
                }else{
                    setStatusDeposit("DONE");
                }
            }

            // if(payments.length > 1){
            //     payments.slice(0,payments.length-1).map(())
            // }else setStatusRemainder("NOT YET");
        }   
  },[payments])

  if(statusDeposit === undefined || statusDeposit === null) return <div>Loadding</div> 
 if(orderDetail === undefined || orderDetail === null) return <div>Loading</div>;



let count = 0;
let subTotal = 0;
orderDetail.map((item) =>{

        subTotal += item.priceAfterSizeAdjustment;
})

const handlePreparedOrder = () => {
        const requestOptions = {
            method: "POST",
            redirect: "follow"
          };
          
          fetch(`http://localhost:8080/api/order/Prepared/${orderID}`, requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
            .catch((error) => console.error(error));
    
  }

  const handleDeliveringOrder = () => {
    const requestOptions = {
        method: "POST",
        redirect: "follow"
      };
      
      fetch(`http://localhost:8080/api/order/Delivering/${orderID}`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));

 }


  const handleCanceledPayment = () => {
    const requestOptions = {
        method: "POST",
        redirect: "follow"
      };
      
      fetch(`http://localhost:8080/api/order/Canceled/${orderID}`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
  }


  const handleCompletedOrder = () => {
    const requestOptions = {
        method: "POST",
        redirect: "follow"
      };
      
      fetch(`http://localhost:8080/api/order/Completed/${orderID}`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));

 }



  return (
    <div className='order-detail-container'>
        <h1>Orders</h1>
        <p>
          Admin / <span>Manage Orders</span> / <span>{orderID}</span>
        </p>
        <div className='back-to-orders'
            onClick={() => navigate("/admin/overview/order")}
        >
            <img src={ICONS.icon_back_arrow} />
            <span>Back</span>
        </div>
        <div className='order-detail-content'>
            <div className='information-order'>
                <h3>ORDER INFORMATION</h3>
                <span className='type-order'>PICK UP IN STORE</span>
                <div className='customer-information'>
                    <ul>
                        <li>
                            <h5>Full Name</h5>
                            <span>
                                {
                                    orderDetail !== undefined && orderDetail !== undefined ?
                                    orderDetail[0].order.account.name : ""
                                }
                            </span>
                        </li>
                        <li>
                            <h5>Phone Number</h5>
                            <span>
                                {
                                    orderDetail !== undefined && orderDetail !== undefined ?
                                    orderDetail[0].order.account.numberPhone : ""
                                }
                            </span>
                        </li>
                        <li>
                            <h5>Email</h5>
                            <span>
                                {
                                    orderDetail !== undefined && orderDetail !== undefined ?
                                    orderDetail[0].order.account.email : ""
                                }
                            </span>
                        </li>
                        <li>
                            <h5>Address</h5>
                            <span>
                                {
                                    orderDetail !== undefined && orderDetail !== undefined ?
                                    orderDetail[0].order.address : ""
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

                        {
                            orderDetail !== undefined && orderDetail !== null &&
                            orderDetail.map((item) => (
                                
                                <ul>
                                    <li>
                                        <div>
                                            <img src={imagesProduct !== undefined && imagesProduct !== null ?
                                                 imagesProduct[count++] : null} alt='' />
                                        </div>
                                    </li>
                                    <li>
                                        <div>
                                            <h4>{item.product.name}</h4>
                                            <p>CODE : {item.product.code} </p>
                                            <span> x1 </span>
                                        </div>
                                    </li>
                                    <li>
                                        {item.size}
                                    </li>
                                    <li> 
                                        ${item.priceAfterSizeAdjustment.toFixed(2)}
                                    </li>
                                    </ul>
                         
                            ))
                        }

                        
                         

                        <div className='line'></div>
                        <div className='total'>
                                <div className=''>
                                </div>
                                
                                <ul>
                                        <li>
                                            <h5>Sub total</h5>
                                            <h4>${subTotal.toFixed(2)}</h4>
                                        </li>
                                        <li>
                                            <h5>
                                                Discount
                                            </h5>
                                            <h4>$0</h4>
                                        </li>
                                        <li>
                                            <h5>Total</h5>
                                            <h4>$ {
                                                orderDetail !== undefined && orderDetail !== undefined ?
                                                orderDetail[0].order.totalPrice.toFixed(2) : ""
                                            }</h4>
                                        </li>
                                </ul>
                                
                        </div>

                </div>
            </div>
            

            <PaymentInformation
                paymentTitle={"DEPOSIT PAYMENT"}
                statusPayment={statusDeposit !== undefined && statusDeposit !== null ? statusDeposit : "NOT YET"}
                paymentId={payments !== undefined && payments !== null &&
                    payments.length > 0 ? payments[0].id : null}
                paymentMethod={payments !== undefined && payments !== null &&
                    payments.length > 0 ? payments[0].paymentMethod.method.toUpperCase() : null
                }
                paymentDate={payments !== undefined && payments !== null &&
                    payments.length > 0 ? payments[0].payTime : null}
                paymentAmount={payments !== undefined && payments !== null &&
                    payments.length > 0 ? payments[0].amount : null}
                paymentFEE={
                    orderDetail !== undefined && orderDetail !== undefined ?
                    orderDetail[0].order.totalPrice*10/100 : ""
                }
                statusOrder={orderDetail !== undefined && orderDetail !== undefined ? 
                    orderDetail[0].order.orderStatus : ""}

                paymentImage={payments !== undefined && payments !== null &&
                    payments.length > 0 ? payments[0].image : null}
                orderID={orderID}
            />


             <PaymentInformation
                paymentTitle={"REMAINDER PAYMENT"}
                statusPayment={"NOT YET"}
                paymentId={""}
                paymentMethod={""}
                paymentDate={""}
                paymentAmount={""}
                paymentFEE={
                    orderDetail !== undefined && orderDetail !== undefined ?
                    orderDetail[0].order.totalPrice*90/100 : ""
                }
                statusOrder={orderDetail !== undefined && orderDetail !== undefined ? 
                    orderDetail[0].order.orderStatus : ""}
            />


           {
            userRole !== "DELIVERY STAFF" && 
            orderDetail !== undefined && orderDetail !== undefined && 
                    orderDetail[0].order.orderStatus  === "PREPARING" ?
            <div className='button-prepared'>
                <div
                    onClick={handlePreparedOrder}
                >PREPARED</div>
                <div 
                    onClick={handleCanceledPayment}
                >CANCEL</div>
            </div> : ""
           }
           {
            userRole !== "SALE STAFF" && 
            orderDetail !== undefined && orderDetail !== undefined && 
                    orderDetail[0].order.orderStatus  === "PREPARED" ?
            <div className='button-prepared'>
                <div
                    onClick={handleDeliveringOrder}
                >DELIVERING</div>
            </div> : ""
           }
           {
            userRole !== "SALE STAFF" && 
            orderDetail !== undefined && orderDetail !== undefined && 
                    orderDetail[0].order.orderStatus  === "DELIVERING" ?
            <div className='button-prepared'>
                <div
                    onClick={handleCompletedOrder}
                >COMPLETE</div>
                <div
                     onClick={handleCanceledPayment}
                >CANCEL</div>
            </div> : ""
           }
        </div>


        

    </div>
  )
}

export default OrderCarddetail