import React, { useEffect, useState } from 'react'

import "../../theme/admin/WarrantyView.css"
import { ICONS } from '../../constants/admin';

function WarrantyView() {

  const [isEdit,setIsEdit] = useState(false);
  const [isDropDown,setIsDropDown] = useState(false);

  const [status,setStatus] = useState("ACTIVE");
  const [data,setData] = useState();

  useEffect(() =>{
      fetch("http://localhost:8080/api/warranty/get")
      .then((response) => response.json())
      .then((result) => setData(result))
      .catch((error) => console.error(error));
  },[])


  const handleUpdateWarranty =() => {
    setIsEdit(false);
  }

  return (
    <div className='warranty-view-container'>
        <ul>
     
            <li>ID</li>
            <li>Customer</li>
            <li>Phone</li>
            <li>Address</li>
            <li>Date Start</li>
            <li>Date End</li>
            <li>Status</li>
            <li>Update</li>
        </ul>
        <ul className={ isEdit ? 'isUpdate' :null}>
            <li>ID</li>
            <li>Customer</li>
            <li>Phone</li>
            <li>Address</li>
            <li>
              {
                !isEdit ? "Date Start" :
                <input type='text' />
              }   
            </li>
            <li>
              {
                !isEdit ? "Date End" :
                <input type='text' />
              }   
            </li>
            <li 
              style={
                {
                  color : status === 'ACTIVE' ? 'rgba(54, 227, 57, 1)' :'rgba(217, 4, 61, 1)' 
                }
              }
              onClick={() => setIsDropDown(!isDropDown)}
            className={isEdit ? 'isUpdate' : null }>
              {status}
              {
                isEdit ? <>
                     <ul className={isDropDown ? 'isActive' : null}>
                        <li
                          onClick={() => setStatus("ACTIVE")}
                        className='isActive' >ACTIVE</li>
                        <li
                           onClick={() => setStatus("INACTIVE")}
                        className='isInActive' >INACTIVE</li>
                    </ul>  
                    <img 
                           onClick={() => setIsDropDown(!isDropDown)}
                    src={ICONS.icon_drop_down} />
                </> : null
              }
           
            </li>
            <li className={ isEdit ? 'isUpdate' :null}>
              {
                !isEdit ?  <span
                  onClick={() => setIsEdit(true)}
                >Edit</span>: <span
                  onClick={() => handleUpdateWarranty()}
                >Update</span>
              }
             
              </li>
        </ul>
    </div>
  )
}

export default WarrantyView