import React, { useEffect, useState } from 'react'

import '../../theme/admin/WarrantyPrepare.css';
import { ICONS } from '../../constants/admin';

function WarrantyPrepare({orderDetail,warrantyProduct}) {

  const [isActivePolicy,setIsActivePolicy] = useState({
    status: false, index : ""
  });

  const [policyChoose,setPolicyChoose] = useState([]);

  const [policyData,setPolicyData] = useState();

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/warrantypolicy/get`)
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


  const handleCreateWarranty = () => {


      const handleCreateWarranty = () => {

          orderDetail.map((item,index) => {
            
          const Object = {
            "status" : true,
            "userName" : item.order.account.name,
            "phoneNumber" : item.order.account.numberPhone,
            "address" : item.order.address,
            "product" : {
              "id" : item.product.id
            },
            "warrantyPolicies" :{
              "id": policyChoose[index].id
            }
          }



            const myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            const requestOptions = {
              method: "POST",
              headers: myHeaders,
              body: JSON.stringify(Object),
              redirect: "follow"
            };
            
            fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/warranty/create`, requestOptions)
              .then((response) => response.text())
              .then((result) => console.log(result))
              .catch((error) => console.error(error));


          })
      
    }

    handleCreateWarranty();

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
              orderDetail.length > 0 && orderDetail.filter((item) => 
              !warrantyProduct.some((warranty) => warranty.warranty.product.id === item.product.id)
              ).
              map((item,index) => (
              
                <tr>
                  <td>{index+1}</td>
                  <td>{item.product.code}</td>
                  <td>{item.product.name}</td>
                  <td
                     onClick={() => handleClickActive(index)}
                  >
                    <span>
                      {policyChoose !== undefined && policyChoose !== null &&
                      policyChoose.length >0 &&
                      policyChoose[index] !== undefined && policyChoose[index] !== null ?
                      `${ policyChoose[index].name}  ( ${policyChoose[index].warrantyPeriod} month)` : "--Please select policy--"
                      }
                      
                      </span>
                    <img  
                       
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
      
        <div 
          onClick={handleCreateWarranty}
        className='button-create-warranty'>
            CREATE WARRANTY
        </div>
    </div>
  )
}

export default WarrantyPrepare