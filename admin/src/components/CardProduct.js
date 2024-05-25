import React from 'react'

import '../theme/CardProduct.css';
import { useNavigate } from 'react-router-dom';

function CardProduct() {
  const navigate = useNavigate();
  const productId = 123;
  const handClick = (event) => {  
   
    console.log(".....");
    navigate(""+productId);


  }
  return (
    <div className='content-table-container' onClick={handClick} style={{'cursor': 'pointer'}} >
        <ul className='table-content'>
          <li>
              1
          </li>
          <li>
              <div className='img-product-table'>

              </div>
          </li>
          <li>
            #DDDDW000923
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