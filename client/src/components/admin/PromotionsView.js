import React from 'react'

import '../../theme/admin/PromotionsView.css'
import PromotionCard from './PromotionCard'


function PromotionsView() {
  return (
    <div className='promotions-view-container'>
         <ul className='headtable-promotions-view'>
                <li>ID</li>
                <li>Name</li>
                <li>Promotion Rate</li>
                <li>Date Start</li>
                <li>Date End</li>
                <li>Active</li>
         </ul>
         <PromotionCard />
         <PromotionCard />
         <PromotionCard />
    </div>
  )
}

export default PromotionsView