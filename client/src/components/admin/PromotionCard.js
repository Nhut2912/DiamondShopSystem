import React from 'react'

import '../../theme/admin/PromotionCard.css'
import { useNavigate } from 'react-router-dom'

function PromotionCard() {

  const navigate = useNavigate();  
  
  const promotionsID = 1;

  const handleClickDetail = () => {
    navigate(""+promotionsID)
  }

  return (
    <ul
    onClick={handleClickDetail}
    className='promotion-card-container'>
         <li>#00001</li>
         <li>SALE DOUBLE DAY 6.6</li>
         <li>30%</li>
         <li>12/06/2024</li>
         <li>18/06/2024</li>
         <li>
            <div className='isAvailable'>
                Available
            </div>
         </li>
    </ul>
  )
}

export default PromotionCard