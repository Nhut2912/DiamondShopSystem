import React, { useState } from 'react'

import '../../theme/customer/Login.css'
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

function Login() {
  
  // const userVirtual = [
  //   {numberPhone : "0384463039",name :"TranMinhNhut",role : "CUSTOMER"
  //   },
  //   {numberPhone : "0125426890",name :"TranMinhNhut",role : "CUSTOMER"},
  // ]


  const navigate = useNavigate();  

  const [email,setEmail] = useState();

  const handleBack = () => {
    navigate("/");
  }  

  const handleVerify = () => {
      const accountDTO ={
        "email": email,
        "role": "CUSTOMER"
      }

      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
      
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(accountDTO),
        redirect: "follow"
      };
      
      fetch("http://localhost:8080/api/account/register", requestOptions)
        .then((response) => 
          response.text()
        )
        .then((result) => 
          { 
            if(result === "true"){
              localStorage.setItem("accountDTO",JSON.stringify(accountDTO));
              navigate("verify/"+email);
            }
          }
        )
        .catch((error) => console.error(error));

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
                    <label>Email</label>
                    <input 
                      onChange={(event) => setEmail(event.target.value)}
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