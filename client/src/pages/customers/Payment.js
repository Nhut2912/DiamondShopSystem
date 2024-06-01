import React from 'react'
import { ICONS } from '../../constants/customer'
import '../../theme/customer/Payment.css'


function Payment() {
  return (
    <div className='payment-container'>
           <div className='continue-shopping'>
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
                <div className='isActive' >
                    <span>3</span>
                </div>
                <div className='isActive' ></div>
                <div>
                    <span>4</span>
                </div>    
            </div>
    </div>
  )
}

export default Payment