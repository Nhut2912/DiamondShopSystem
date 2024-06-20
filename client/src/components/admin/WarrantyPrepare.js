import React, { useEffect, useState } from 'react'

import '../../theme/admin/WarrantyPrepare.css';
import { ICONS } from '../../constants/admin';

function WarrantyPrepare({orderDetail}) {

  const [isActivePolicy,setIsActivePolicy] = useState({
    status: false, index : ""
  });

  const [policyChoose,setPolicyChoose] = useState([]);

  const [policyData,setPolicyData] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/api/warrantypolicy/get")
    .then((response) => response.json())
    .then((result) => setPolicyData(result))
    .catch((error) => console.error(error));
  },[])

  const handleClickActive = (index) => {
    setIsActivePolicy({
      status: !isActivePolicy.status, index : index
    })
  }

  const handleChoosePolicy = (index,value) => {
    let policyChooseUpdate = policyChoose;
    policyChooseUpdate[index] = value;
    setPolicyChoose(policyChooseUpdate);
    setIsActivePolicy(false);
  }

  return (
    <div className='warranty-prepare-container'>
        <h3>WARRANTY</h3>
        <table>
            <tr>
                <th>No.</th>
                <th>Code</th>
                <th>Name</th>
                <th>Warranty Policy</th>
            </tr>
            {
              orderDetail !== undefined && orderDetail !== null &&
              orderDetail.length > 0 && orderDetail.map((item,index) => (
              
                <tr>
                  <td>{index+1}</td>
                  <td>{item.product.code}</td>
                  <td>{item.product.name}</td>
                  <td>
                    <span>
                      {policyChoose !== undefined && policyChoose !== null &&
                      policyChoose.length >0 &&
                      policyChoose[index] !== undefined && policyChoose[index] !== null ?
                      `${ policyChoose[index].name}  ( ${policyChoose[index].warrantyPeriod} month)` : "--Please select policy--"
                      }
                      
                      </span>
                    <img  
                        onClick={() => handleClickActive(index)}
                        src={ICONS.icon_drop_down} />

                    <ul 

                    className={isActivePolicy.index === index && isActivePolicy.status ? "isActive" : null}>
                        {
                          policyData !== undefined && policyData !== null &&
                          policyData.map((policy) => (
                            <li
                              onClick={() => handleChoosePolicy(index,policy)}
                            >{policy.name}</li>
                          ))
                        }
                    </ul>  
                  </td>
              </tr>
              ))
            }
 
        </table>
      
        <div className='button-create-warranty'>
            CREATE WARRANTY
        </div>
    </div>
  )
}

export default WarrantyPrepare