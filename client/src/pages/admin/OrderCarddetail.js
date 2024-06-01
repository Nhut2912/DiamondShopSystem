import React from 'react'
import { useNavigate, useParams } from 'react-router-dom'

import '../../theme/admin/OrderCardDetail.css';
import {ICONS} from '../../constants/admin'
import PaymentInformation from '../../components/admin/PaymentInformation';

function OrderCarddetail() {
 const order = useParams();
 const orderID = order.id;

 const userRole = "SALE STAFF"
 const statusOrder = "PENDING";


 const navigate = useNavigate();
  return (
    <div className='order-detail-container'>
        <h1>Orders</h1>
        <p>
          Admin / <span>Manage Orders</span> / <span>ID0001</span>
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
            <PaymentInformation
                paymentTitle={"DEPOSIT PAYMENT"}
                statusPayment={"PENDING"}
                paymentId={""}
                paymentMethod={"BANK TRASNFER"}
                paymentDate={""}
                paymentAmount={""}
                paymentFEE={"100"}
                statusOrder={statusOrder}
            />
             <PaymentInformation
                paymentTitle={"REMAINDER PAYMENT"}
                statusPayment={"NOT YET"}
                paymentId={""}
                paymentMethod={""}
                paymentDate={""}
                paymentAmount={""}
                paymentFEE={"800"}
                statusOrder={statusOrder}
            />


           {
            userRole === "SALE STAFF" && 
            statusOrder === "PREPARING" ?
            <div className='button-prepared'>
                <div>PREPARED</div>
                <div>CANCEL</div>
            </div> : ""
           }
           {
            userRole === "DELIVERY STAFF" && 
            statusOrder === "PREPARED" ?
            <div className='button-prepared'>
                <div>DELIVERING</div>
            </div> : ""
           }
           {
            userRole === "DELIVERY STAFF" && 
            statusOrder === "DELIVERING" ?
            <div className='button-prepared'>
                <div>COMPLETE</div>
                <div>CANCEL</div>
            </div> : ""
           }
        </div>


        

    </div>
  )
}

export default OrderCarddetail