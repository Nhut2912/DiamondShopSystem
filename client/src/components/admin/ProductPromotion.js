import React from 'react'

import '../../theme/admin/ProductPromotion.css'
import PromotionProductCard from './PromotionProductCard'

function ProductPromotion() {
  return (
    <div className='product-promotion-container'>
        <ul>
            <li>No.</li>
            <li>Code</li>
            <li>Name</li>
            <li>Price</li>
            <li>Promotions</li>
            <li>Price On Promotions</li>
        </ul>
        <PromotionProductCard />
        <PromotionProductCard />
        <PromotionProductCard />
        <PromotionProductCard />
        <PromotionProductCard />
        <PromotionProductCard />
    </div>
  )
}

export default ProductPromotion