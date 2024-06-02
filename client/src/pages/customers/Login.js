import React, { useState } from 'react'

import '../../theme/customer/Login.css'
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

function Login() {
  
  const userVirtual = [
    {numberPhone : "0384463039",name :"TranMinhNhut",role : "CUSTOMER"

    },
    {numberPhone : "0125426890",name :"TranMinhNhut",role : "CUSTOMER"},
  ]


  const navigate = useNavigate();  

  const [phoneNumber,setPhoneNumber] = useState();

  const handleBack = () => {
    navigate("/");
  }  

  const handleVerify = () => {
    userVirtual.map((item) => {
      if(item.numberPhone === phoneNumber){
        localStorage.setItem('phone_verify',item.numberPhone);
        navigate("verify")
      }
    })
  
  }

  return (
    <div className='login-container'>
        <div className='login-content'>
            <div onClick={handleBack}>
                <img src={ICONS.icon_back_arrow} alt='' />
                <span>Back</span>
            </div>
            <div>
                <img src={ICONS.icon_logo_no_text} alt='' />
            </div>
            <div>
                <h2>Welcome</h2>
                <div>
                    <label>Number Phone </label>
                    <input 
                      onChange={(event) => setPhoneNumber(event.target.value)}
                    type='text' />
                </div>
                <div 
                onClick={handleVerify}
                className='button-login'>
                    Login
                </div>
            </div>
        </div>
    </div>
  )
}

export default Login;