import React, { useState } from 'react'


import '../../theme/admin/Authentication.css'
import { ICONS } from '../../constants/admin/index';
import { useNavigate } from 'react-router-dom';



const Authentication = () => {

  const navigate = useNavigate();

  const userVirtual = [
    {username : "delivery_staff", password : "1234" , role: "DELIVERY STAFF"},
    {username : "sale_staff", password : "1234" , role: "SALE STAFF"},
    {username : "admin", password : "1234" , role: "ADMIN"},
  ]



  const handleLogin = () => {
    



    userVirtual.map((item) => {
        if(item.username === userName && item.password === password){
          localStorage.setItem('account', JSON.stringify(item));
          if(item.role === "DELIVERY STAFF" || item.role === "SALE STAFF" ){
            navigate("/admin/overview/order")
          }else    navigate("/admin/overview")
       
        }
    })
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