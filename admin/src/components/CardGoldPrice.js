import React from 'react'

import '../../theme/admin/CardGoldPrice.css';
import { useNavigate } from 'react-router-dom';

function CardGoldPrice(){
     const navigate = useNavigate();
     const goldPriceId = 123;
     const handClick = () => {

        console.log("...");
        navigate(""+goldPriceId);
     }


    return(
        <div className='content-table-gold-price-container'  onClick={handClick} style={{'cursor':'pointer'}}>
           <ul className='table-gold-price-content'>
              <li>M001</li>
              <li>100</li>
              <li>200</li>
           </ul>
        </div>
    )
}
export default CardGoldPrice
