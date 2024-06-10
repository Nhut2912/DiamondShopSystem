import React, { useEffect, useState } from 'react'

import '../../theme/admin/OrderCard.css';
import { useNavigate } from 'react-router-dom';
import ConvertLocalDateToFormat from '../../function/ConvertLocalDateToFormat';


const numberFormatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  maximumSignificantDigits: 2
});

function OrderCard({role,orderID,
  Customer,
  Address,
  N_Phone,
  Status,
  Date,
  Total
}) {

  
  const COLORS_STATUS = [
    {name: "PENDING" , color : "#F2C438"},
    {name: "PREPARING" , color : "#F2A20C"},
    {name: "PERPARED", color : "#F2A20C"},
    {name : "DELIVERING", color : "#F25D07"},
    {name : "CANCELED", color : "#D9043D"},
    {name : "COMPLETED", color : "#008F00"}
]

  const [data,setData] = useState();
  const navigate = useNavigate();
  const [statusColor,setStatusColor] = useState();



  useEffect(()=> {
    COLORS_STATUS.map((status) => {
      if(status.name === Status.trim().toUpperCase()){
        setStatusColor(status.color);
      }
    })
  },[])

  useEffect(()=>{
    fetch(`http://localhost:8080/api/payment?order_id=${orderID}`)
    .then((response) => response.json())
    .then((result) => setData(result))
    .catch((error) => console.error(error));
  },[])

  if(data === undefined || data === null) return <div>Loading ...</div>
  if(statusColor === undefined || statusColor === null) return <div>Loading...</div>

  let remainder = ((Total*90)/100).toFixed(2);


  const handleDetailOrder = () => {
    navigate(`/admin/overview/order/${orderID}`);
  }
  
  return (
    <div className='order-card-content' onClick={handleDetailOrder}>
       <div>
            <span>#{orderID}</span>
            <div style={{backgroundColor: statusColor ,border: "none"}}>
                <span
                  style={{color: "#FFFFFF"
                          
                  }}    
                
                >
                    {Status}
                </span>
            </div>
       </div>
       <div>
            <ul>
                <li>
                    <h4>Customer </h4>
                    <span>{Customer}</span>
                </li>

                {
                  role === "DELIVERY STAFF" ? 
                  <>
                      <li>
                          <h4>N.Phone</h4>
                          <span>{N_Phone}</span>
                      </li>
                      <li style={{alignItems: 'start'}}>
                          <h4>Address</h4>
                          <span>{Address}</span>
                      </li>
                  </>: 
                  <>
                     <li className='deposit'>
                        <h4>Deposit</h4>
                        <div>
                          <b> ${(Total*10/100).toFixed(2)} </b>
                          {
                            Status === "PENDING" ?
                            <span 
                              style={{color: "rgba(217, 179, 132, 1)" ,
                                  border: "1px solid rgba(217, 179, 132, 1)",
                                  padding: "2px 20px"
                              }}
                            >PENDING</span> : <span
                            style={{color: "rgba(54, 227, 57, 1)",
                              border: "1px solid rgba(54, 227, 57,1)",
                              padding: "2px 20px"
                            }} 
                            >DONE</span>
                          }
                      
                        </div>
                      </li>
                      <li className='remainder'>
                        <h4>Remainder</h4>
                          <div>
                            <b>$
                            {remainder} </b>
                            <span
                              style={{color: "rgba(0,0,0,0.4)",
                                  border: "1px solid rgba(0,0,0,0.4)",
                                padding: "2px 20px"
                              }}
                            >NOT YET</span>
                          </div>
                      </li>
                  </>
                }
               
              
            </ul>
       </div>
      <div>
          <div>
              <h4>Total </h4> 
              <span>${Total} </span>
              {
                Status !== 'PENDING' ? 
                <span
                style={{fontSize:"12px",
                  color: 'rgba(255,255,255,0.4)',
                  fontWeight: '400'
                }}
              >{"( Paid $100 / $1034 )"}</span> :""
              }
            
          </div>
          <p>
                {ConvertLocalDateToFormat(Date)}
          </p>
      </div>
    </div>
  )
}

export default OrderCard