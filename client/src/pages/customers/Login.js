import React from 'react'

import '../../theme/customer/Login.css'
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

function Login() {
 
  const navigate = useNavigate();  


  const handleBack = () => {
    navigate("/");
  }  

  const handleVerify = () => {
    navigate("verify")
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
                    <label>Number Phone or Email </label>
                    <input type='text' />
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