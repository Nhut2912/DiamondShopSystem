import React from 'react'
import ICONS from '../constants/icons'

import '../theme/ProductCard.css';
import IMAGES from '../constants/images';

function ProductCard() {

  return (
    <div className='product-card-container'>
        <h3>Nhẫn Kim cương Vàng trắng  14K PNJ DDDDW003910</h3>

        {true &&
          (
            <div className='tag-product-card'>
                <h4>On sale</h4>
                <span>30%</span>
            </div>
          )
        }
         
        
      
        <div className='product-card-img'>
            <img src={IMAGES.test_image} alt='' />
        </div>
        <div className='product-card-price'>
          {true && 
             <span>
             $ 1,703.87
           </span>
          }
           
            <div>
              <h3>$ 1,003.87</h3>
              <img src={ICONS.icon_cart} />
            </div>
        </div>
    </div>
  )
}

export default ProductCard