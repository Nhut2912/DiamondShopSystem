import React, { useState } from 'react';


import '../../theme/admin/AddAccount.css';

import {ICONS} from '../../constants/admin'

function AddAccount() {
  
  const [isDropDown,setIsDropDown] = useState(false);
  const [gender,setGender] = useState("Male")
  const [email,setEmail] = useState();
  const [password,setPassword] = useState();
  const [name,setName] = useState();
  const [phone,setPhone] = useState();
  const [role,setRole] = useState();
  const [birthday,setBirthday] = useState();
  const [address,setAddress] = useState();


  const handleCreate = () => {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");


      const Object = {
        "name" : name,
        "email" : email,
        "password" : password,
        "numberPhone":phone,
        "address" : address,
        "birthDay" : birthday,
        "role" : role,
        "active" : true 
      }

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(Object),
        redirect: "follow"
      };
      
      fetch("${process.env.REACT_APP_API_ENDPOINT}/api/account/create", requestOptions)
        .then((response) => response.text())
        .then((result) => 
          {
            window.location.href = window.location.href
          }
        )
        .catch((error) => console.error(error));
  }


  return (
    <div className='add-account-container'>
      <h3>INFORMATION ACCOUNT</h3>
       <div>
            <label>Gender</label>
            <div>
              <div onClick={() => setGender("Male")} className={gender === "Male" ? 'isActive' : null}>Male</div>
              <div onClick={() => setGender("Female")} className={gender !== "Male" ? 'isActive' : null} >Female</div>
            </div>
        </div>
        <div>
          <div>
                <label>Email</label>
                <input 
                  onChange={(event) => {setEmail(event.target.value)}}
                type='text' />
            </div>
            <div>
                <label>Password</label>
                <input 
                   onChange={(event) => {setPassword(event.target.value)}}
                type='text' />
            </div>
        </div>
       
        <div>
          <div>
              <label>Name</label>
              <input
                onChange={(event) => {setName(event.target.value)}}
              type='text' />
          </div>
          <div>
              <label>Phone</label>
              <input
                onChange={(event) => {setPhone(event.target.value)}}
              type='text' />
          </div>
        </div>
        <div>
            <label>Role</label>
            <div>
               <span>{role !== undefined && role !== null ? role : ""}</span>
               <img 
                onClick={() => setIsDropDown(!isDropDown)}
               src={ICONS.icon_drop_down} />
               <ul className={isDropDown ? 'isActive' : ''}>
                  <li 
                  onClick={(event) => {
                      setRole(event.target.innerText)
                      setIsDropDown(false)
                  }} >ADMIN</li>
                  <li
                    onClick={(event) => {
                      setRole(event.target.innerText)
                      setIsDropDown(false)
                  }}
                  >CUSTOMER</li>
                   <li
                    onClick={(event) => {
                      setRole(event.target.innerText)
                      setIsDropDown(false)
                  }}
                  >MANAGER</li>
                   <li
                    onClick={(event) => {
                      setRole(event.target.innerText)
                      setIsDropDown(false)
                  }}
                  >SALE STAFF</li>
                  <li
                    onClick={(event) => {
                      setRole(event.target.innerText)
                      setIsDropDown(false)
                  }}
                  >DELIVERY STAFF</li>
               </ul>
            </div>
        </div>
        <div>
            <label>Birthday</label>
            <input 
              onChange={(event) => {setBirthday(event.target.value)}}
            type='text' />
        </div>
        <div>
            <label>Address</label>
            <input
              onChange={(event) => {setAddress(event.target.value)}}
            type='text' />
        </div>
        <div onClick={() => handleCreate()} >
            CREATE ACCOUNT
        </div>
    </div>
  );
}

export default AddAccount;