import React, { useState } from 'react'

import "../../theme/admin/WarrantyPolicyAdd.css"


function WarrantyPolicyAdd() {
 
 const [name,setName] = useState();
 const [period,setPeriod] = useState();
 
 const handleChangeName = (event) => {
    const value = event.target.value;
    setName(value);
 }

 const handleChangePeriod = (event) => {
    const value = event.target.value;
    setPeriod(value);
 }


 const handleAddWarrantyPolicy = () => {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const warrantyPolicy = {
        "name": name.trim(),
        "warrantyPeriod": period,
        "active": true
    }

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(warrantyPolicy),
        redirect: "follow"
      };
      
      fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/warrantypolicy/create`, requestOptions)
        .then((response) => response.text())
        .then((result) => {
            if(result === "true"){
                window.location.href = window.location.href
            }
        })
        .catch((error) => console.error(error));

 }

  return (
    <div className='warranty-policy-add-container'>
        <h4>Add Warranty Policy</h4>
        <div>
            <label>Name</label>
            <input 
                onChange={handleChangeName}
            type='text' />
        </div>
        <div>
            <label>Period {"(Month)"}</label>
            <input 
                 onChange={handleChangePeriod}
            type='text' />
        </div>
        <div onClick={handleAddWarrantyPolicy}>
            Add Policy
        </div>
    </div>
  )
}

export default WarrantyPolicyAdd