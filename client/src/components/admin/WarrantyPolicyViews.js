import React, { useEffect, useState } from 'react'

import "../../theme/admin/WarrantyPolicyViews.css"

function WarrantyPolicyViews() {
 

 const [data,setData] = useState();

 useEffect(() => {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/warrantypolicy/get`)
    .then((response) => response.json())
    .then((result) => {
        if(result !== null ){
            setData(result);
        }
    })
    .catch((error) => console.error(error));
 },[])

  return (
    <div className='warranty-policy-views-container'>
       <ul>
            <li>No.</li>
            <li>ID</li>
            <li>Name</li>
            <li>Period {"(Month)"}</li>
            <li>
                Active
            </li>
       </ul>
      <div className='container-warranty-policy-card'>
      {
        data !== undefined && data !== null && data.length > 0 &&
        data.map((item,index) => (
            <>
                 <ul>
                        <li>{index+1}</li>
                        <li>#{item.id}</li>
                        <li>{item.name}</li>
                        <li>{item.warrantyPeriod}</li>
                        <li
                            className='isActive'
                        >Active</li>
                </ul>
                <div className='line'></div>
            </>
        ))
       }
      </div>
      
    </div>
  )
}

export default WarrantyPolicyViews