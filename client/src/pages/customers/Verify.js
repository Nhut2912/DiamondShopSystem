import React from 'react'

import '../../theme/customer/Verify.css';
import { ICONS } from '../../constants/customer';

function Verify() {
  return (
    <div className='verify-container'>
        <div className='verify-content'>
            <div>
                <img src={ICONS.icon_logo_no_text}   alt=''/>
            </div>
            <div >
                <h2>Verify</h2>
                <p>Your verification code has been sent to your phone number <br />
                <span>0384463039</span>
                </p>
                
                <div>
                    <label>OTP</label>
                    <input type='text' />
                </div>
                <div className='button-verify'>
                    Verify
                </div>
                <div className='button-send-again'>
                    Send Again
                </div>
            </div>
        </div>
    </div>
  )
}

export default Verify