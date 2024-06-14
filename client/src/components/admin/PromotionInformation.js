import React from 'react'

import '../../theme/admin/PromotionInformation.css'
import ProductPromotion from './ProductPromotion'
import ICONS from '../../constants/admin/icons'

function PromotionInformation() {
  return (
    <div className='promotion-information-container'>
        <h1>PROMOTION DETAILS</h1>
        <div className='edit-promotion'>Edit</div>
         <div className='information-promotion'>
                
                <div className='head-information'>
                    <span>
                       Promotion ID : #00001
                    </span>
                    <div>
                        Available
                    </div>
                </div>
                <div className='primary-information'>
                    <div>
                        <div>
                            <label>Name</label>
                            <input value={"Tran Minh Nhut"} type='text'  />
                        </div>
                        <div>
                            <label>Promotion Rate</label>
                            <input  type='text' />
                        </div>
                    </div>
                    <div>
                        <div>
                            <label>Date Start</label>
                            <input type='text' />
                        </div>
                        <div>
                            <label>Date End</label>
                            <input type='text' />
                        </div>
                    </div>
                </div>
                <h4>Products are on this promotion</h4>
                <div className='search-product-on-promotion'>
                    <span>50 products on this promotions</span>
                  
                    <div className='search-product-promotion'>
                        <input placeholder='Search' type='text' />
                        <img src={ICONS.icon_search} />
                    </div> 

                    {
                        true ?<div className='add-product-promotion'>Add Product</div> : null
                    }
                    
                    
                </div>
                <ProductPromotion />
         </div>
    </div>
  )
}

export default PromotionInformation