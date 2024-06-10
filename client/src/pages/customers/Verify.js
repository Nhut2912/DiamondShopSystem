import React, { useState } from 'react'

import '../../theme/customer/Verify.css';
import { ICONS } from '../../constants/customer';
import { useNavigate, useParams  } from 'react-router-dom';
import useLocalStorage from '../../hook/useLocalStorage';
import { signOut } from 'firebase/auth';



function Verify() {

  const params = useParams();
  console.log(params.id);

  const navigate = useNavigate();
  const [OTP,SETOTP] = useState();

  const handleVerify = async () => {
    const accountDTO = localStorage.getItem("accountDTO");
    localStorage.removeItem("accountDTO");

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: accountDTO,
    redirect: "follow"
    };

    console.log(OTP);
    fetch(`http://localhost:8080/api/account/verifyOtp?otp=${OTP}`, requestOptions)
    .then((response) => response.text())
    .then((result) =>{
      if(result !== null && result !== undefined){
          const accountDTO = JSON.parse(result);

          const account = {
            "id" : accountDTO.id,
            "name" : accountDTO.name,
            "email" : accountDTO.email,
            "numberPhone" : accountDTO.numberPhone,
            "birthDay" : accountDTO.birthDay,
            "role" : accountDTO.role,
            "cart" : []
          }
          localStorage.setItem("account",JSON.stringify(account));
          navigate("/products");
      }
    }
    )
    .catch((error) => console.error(error));

  };

  const handleChange = (e)=> {
    SETOTP(e.target.value);
    console.log(e.target.value);
  }



  return (
    <div className='verify-container'>
        <div className='verify-content'>
            <div>
                <img src={ICONS.icon_logo_no_text}   alt=''/>
            </div>
            <div >
                <h2>Verify</h2>
                <p>Your verification code has been sent to your email {params.id} <br />
                <span></span>
                </p>
                
                <div>
                    <label>OTP</label>
                    <input type='text'
                        onChange={(e) => handleChange(e)}
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