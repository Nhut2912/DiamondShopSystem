import React from 'react'

import '../../theme/admin/PromotionsDetail.css'
import { ICONS } from '../../constants/admin'
import { useNavigate } from 'react-router-dom'
import PromotionInformation from './PromotionInformation';


function PromotionsDetail() {
  
 const navigate = useNavigate();


  return (
    <div className='promotions-detail-container'>
         <h1>Promotions</h1>
        <p>
            Admin / <span>Promotions</span> / <span>1</span>
        </p>

        <div className='back-to-promotions'
            onClick={() => navigate("/admin/overview/promotions")}
        >
            <img src={ICONS.icon_back_arrow} />
            <span>Back</span>
        </div>

        <PromotionInformation />
    </div>
  )
}

export default PromotionsDetail