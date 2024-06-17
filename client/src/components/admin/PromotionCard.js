import React from 'react'

import '../../theme/admin/PromotionCard.css'
import { useNavigate } from 'react-router-dom'

function PromotionCard({promotion,index}) {

  const navigate = useNavigate();  
  

  const handleClickDetail = () => {
    navigate("promtion-details",{state : promotion})
  }

  return (
    <ul
    onClick={handleClickDetail}
    className='promotion-card-container'>
        <li>{index+1}</li>
         <li>#{promotion.idPromotion}</li>
         <li>{promotion.namePromotion}</li>
         <li>{promotion.promotionRate}%</li>
         <li>{promotion.dateStart}</li>
         <li>{promotion.dateEnd}</li>
         <li>{promotion.productIds.length}</li>
         <li>
            <div className=  {promotion.active ? "isAvailable" : "isUnAvailable"}>
                {promotion.active ? "AVAILABLE" : "UNAVAILABLE"}
            </div>
         </li>
    </ul>
  )
}

export default PromotionCard