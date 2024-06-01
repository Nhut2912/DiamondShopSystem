import React from 'react'

import '../../theme/admin/OrderCard.css';
import { useNavigate } from 'react-router-dom';

function OrderCard({role,orderID,
  Customer,
  Address,
  N_Phone,
  Status,
  Date,
  Total
}) {

  const navigate = useNavigate();
  const handleDetailOrder = () => {
    navigate(orderID);
  }



  return (
    <div className='order-card-content' onClick={handleDetailOrder}>
       <div>
            <span>#{orderID}</span>
            <div>
                <span>
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
                          <b> $100 </b>
                          {
                            Status === "PENDING" ?
                            <span>PENDING</span> : <span>DONE</span>
                          }
                      
                        </div>
                      </li>
                      <li className='remainder'>
                        <h4>Remainder</h4>
                          <div>
                            <b> $100 </b>
                            <span>NOT YET</span>
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
                {Date}
          </p>
      </div>
    </div>
  )
}

export default OrderCard