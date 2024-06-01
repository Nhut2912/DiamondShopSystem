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



  // const [data,setData] = useState("");

  // useEffect(() => {
  //   fetch('http://localhost:8080/api/product/getProducts')
  //     .then(response => response.json())
  //     .then(data => setData(data));
  // }, []); 


  const handleLogin = () => {
    
    // const myHeaders = new Headers();
    // myHeaders.append("Content-Type","application/json")
    // const raw = JSON.stringify({
    //   "name": "Add your name in the body"
    // });
    // const requestOptions = {
    //   method: "POST",
    //   headers: myHeaders,
    //   body: raw,
    //   redirect: "follow"
    // };
    //   fetch('http://localhost:8080/api/product/save', requestOptions)
    //   .then(response => response.text())
    //   .then( data => setData(data));
    //   console.log(data);
    //   if(data === "true"){
    //     window.location.href ="/admin"
    //   }


    userVirtual.map((item) => {
        if(item.username === userName && item.password === password){
          localStorage.setItem('account', JSON.stringify(item));
          if(item.role === "DELIVERY STAFF" || item.role === "SALE STAFF" ){
            navigate("/admin/overview/order")
          }else    navigate("/admin/overview")
       
        }
    })
  }

  /**
   * author : Tran Minh Nhut
   * date : 20/5/2024
   * purpose : thu thap thong tin ve username va password
   */

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