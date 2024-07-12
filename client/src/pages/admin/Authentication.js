import React, { useState } from 'react'


import '../../theme/admin/Authentication.css'
import { ICONS } from '../../constants/admin/index';
import { useNavigate } from 'react-router-dom';



const Authentication = () => {

  const navigate = useNavigate();

  const handleLogin = () => {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    
    const raw = JSON.stringify({
      "email": userName,
      "password":password
    });
    
    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow"
    };
    
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/account/checkUserNameAndPassword`, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        if(result !== "Not Valid"){
          const user = {
            "email": userName,
            "role": result
          }
          localStorage.setItem("_acount_manager",JSON.stringify(user));
          if(user.role === "ADMIN" || user.role === "MANAGER"){
            navigate("/admin/overview");
          }else navigate("/admin/overview/order");
          
        }
      })
      .catch((error) => console.error(error));


  }

  

  

   const [userName,setUserName] = useState("");
   const [password,setPassword] = useState("");
   
   const getUserNameHandle = ( event ) => {
        let value = event.target.value;
        setUserName(value);
   }

   const getPasswordHandle = ( event ) => {
    let value = event.target.value;
    setPassword(value);
  }


  return (
    <div className='container-admin'>
        <div className='login-form'>
            <div className='welcome'> 
                <img src={ICONS.icon_logo} className='logo' alt="" width='50%' height='50%'/>
            </div>
            <div className='login-information'>
                  <h1>Login</h1>
                  <div className='input-box' >
                      <label>Username</label>
                      <input type='text' onBlur={event => getUserNameHandle(event)} placeholder='Email or phone number' />
                  </div>
                  <div className='input-box' >
                      <label>Password</label>
                      <input type='password' onBlur={getPasswordHandle} />
                      <label>Forgot password ?</label>
                  </div>
                  <div className='button-login' onClick={handleLogin}>
                      <span>
                        Login
                      </span>
                  </div>
            </div>
        </div>
    </div>
  )
}

export default Authentication;