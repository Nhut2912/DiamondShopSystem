import React from 'react'
import { IMAGES } from '../../constants/customer';

import '../../theme/customer/ProductSaleBanner.css'

function ProductSaleBanner() {
  return (
    <div className='product-banner-sale-container'>
      
                <div>
                    <img src={IMAGES.image_banner_sale} alt='' /> 
                </div>
                <div>
                    <img src={IMAGES.image_banner_sale_1} alt='' /> 
                </div>
                <div>
                    <img src={IMAGES.image_banner_sale_2} alt='' /> 
                </div>
    </div>
  )
}

export default ProductSaleBanner