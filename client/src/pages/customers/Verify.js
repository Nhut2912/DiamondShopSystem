import React, { useState } from 'react'

import '../../theme/customer/Verify.css';
import { ICONS } from '../../constants/customer';
import { useNavigate, useParams } from 'react-router-dom';

function Verify() {
    const userVirtual = [
        {numberPhone : "0384463039",name :"TranMinhNhut",role : "CUSTOMER"},
        {numberPhone : "0125426890",name :"TranMinhNhut",role : "CUSTOMER"},
      ]
      
  const phoneVerify = localStorage.getItem('phone_verify');
  const verifyCode = "1234";
  const navigate = useNavigate();

 const [OTP,SETOTP] = useState();

  const handleVerify = () => {
        if(OTP === verifyCode){
            localStorage.setItem("account",JSON.stringify(userVirtual[0]));
            navigate("/products");
        }
  }

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
                    <input type='text'
                        onChange={(event) => SETOTP(event.target.value)}
                    />
                </div>
                <div className='button-verify' onClick={handleVerify}>
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