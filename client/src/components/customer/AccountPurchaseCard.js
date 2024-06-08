import React from 'react'
import '../../theme/customer/AccountPurchaseCard.css'

const numberFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    maximumSignificantDigits: 2
  });


function AccountPurchaseCard({
        orderID,isDelivery,totalPrice,ordeDate,orderStatus
    }) {



  return (
    <div className='account-purchase-card-container'>
        <p>{
        ordeDate !== undefined && 
        ordeDate !== null ? 
        ordeDate : null}
        </p>
        <div className='purchase-card'>
            <div>
                <h3>#{orderID !== undefined && orderID !== null ?
                orderID : null }</h3>
                <h3>
                {isDelivery !== undefined && isDelivery !== null && 
                isDelivery === true ? "DELIVERY" : "PICK UP IN STORE" }
                </h3>
            </div>
            <div className='product-purchase'>
                <div>

                </div>
                <div>
                    <h3>Diamond rings white gold Disney JEWELRY</h3>
                    <span>Size : 17</span>
                    <span>Price : $1034</span>
                </div>
            </div>
    
            
            <div>
                <h4>Total : {numberFormatter.format(totalPrice)}  <span>{"( Paid $100 Of $1034 )"}</span></h4>
                <h4>
                {orderStatus !== undefined && orderStatus !== null ? 
                orderStatus : null }
                </h4>
            </div>
        </div>
    </div>
  )
}

export default AccountPurchaseCard