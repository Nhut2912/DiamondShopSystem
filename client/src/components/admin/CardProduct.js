import React from 'react'

import '../../theme/admin/CardProduct.css';
import { useNavigate } from 'react-router-dom';

function CardProduct({data}) {
  const navigate = useNavigate();
  const productId = data.id;
  const handClick = () => {  
  
    navigate(""+productId);


  }
  return (
    <div className='content-table-container' onClick={handClick} style={{'cursor': 'pointer'}} >
        <ul className='table-content'>
          <li>
              #{data.id}
          </li>
          <li>
              <div className='img-product-table'>

              </div>
          </li>
          <li>
            #{data.code}
          </li>
          <li>
            14K White Gold Diamond Pendant PNJ DDDDW000923
          </li>
          <li>
              Pendant
          </li>
          <li>
              <span>
                  C0001 - 0.5 carat
              </span>
              <br />
              <span>
                  C0002 - 0.1 carat
              </span>
          </li>
          <li>
            14K White Gold 
          </li>
          <li>
            <div className='status-product'>
                <span>
                    Available
                </span>
            </div>
          </li>
        </ul>
    </div>
  )
}

export default CardProduct