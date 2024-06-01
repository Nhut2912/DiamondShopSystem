import React from 'react'

import '../../theme/customer/OrderComplete.css'
import {ICONS} from '../../constants/customer/index'

function OrderComplete() {
  return (
    <div className='order-complete-container'>
         <div className='continue-shopping'>
            <img src={ICONS.icon_back_white} alt=''/>
            <span>Shopping</span>
        </div>
       <div>
        <div className='thank-you-container'>
                        <h1>Thank You</h1>
                        <h4>FOR YOUR PURCHASE</h4>
                        <p>
                        It is an honor and happiness that
                        JEWELRY is trusted and chosen by you.
                        We hope you are satisfied with your purchase and that you like it.
                        Your order is being prepared for delivery to you. 
                        You can track your order through the <b>purchase history section</b>. 
                        If you have any questions, please contact us. We are here to help!
                        </p>
            </div>
       </div>
    </div>
  )
}

export default OrderComplete