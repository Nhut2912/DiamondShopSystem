import React, { useState } from 'react';


import '../../theme/admin/AddAccount.css';

import {ICONS} from '../../constants/admin'

function AddAccount() {
  
  const [isDropDown,setIsDropDown] = useState(false);
  const [gender,setGender] = useState("Male")


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
                <input type='text' />
            </div>
            <div>
                <label>Password</label>
                <input type='text' />
            </div>
        </div>
       
        <div>
          <div>
              <label>Name</label>
              <input type='text' />
          </div>
          <div>
              <label>Phone</label>
              <input type='text' />
          </div>
        </div>
        <div>
            <label>Role</label>
            <div>
               <span>CUSTOMER</span>
               <img 
                onClick={() => setIsDropDown(!isDropDown)}
               src={ICONS.icon_drop_down} />
               <ul className={isDropDown ? 'isActive' : ''}>
                  <li>ADMIN</li>
                  <li>CUSTOMER</li>
               </ul>
            </div>
        </div>
        <div>
            <label>Birthday</label>
            <input type='text' />
        </div>
        <div>
            <label>Address</label>
            <input type='text' />
        </div>
        <div>
            CREATE ACCOUNT
        </div>
    </div>
  );
}

export default AddAccount;