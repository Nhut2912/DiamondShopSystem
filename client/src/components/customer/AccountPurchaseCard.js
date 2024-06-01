import React from 'react'
import '../../theme/customer/AccountPurchaseCard.css'

function AccountPurchaseCard() {
  return (
    <div className='account-purchase-card-container'>
        <p>31-05-2024 10:52pm</p>
        <div className='purchase-card'>
            <div>
                <h3>#ORDER12304</h3>
                <h3>DELIVERY</h3>
            </div>
            <div className='product-purchase'>
                <div>

                </div>
                <div>
                    <h3>Diamond rings white gold Disney JEWELRY</h3>
                    <span>Unit Price : $1034</span>
                </div>
            </div>
            <div className='product-purchase'>
                <div>

                </div>
                <div>
                    <h3>Diamond rings white gold Disney JEWELRY</h3>
                    <span>Unit Price : $1034</span>
                </div>
            </div>
            
            <div className='product-purchase'>
                <div>

                </div>
                <div>
                    <h3>Diamond rings white gold Disney JEWELRY</h3>
                    <span>Unit Price : $1034</span>
                </div>
            </div>
            
            <div>
                <h4>Total : $1034 <span>{"( Paid $100 Of $1034 )"}</span></h4>
                <h4>CANCELED</h4>
            </div>
        </div>
    </div>
  )
}

export default AccountPurchaseCard