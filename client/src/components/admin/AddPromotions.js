import React from 'react'

import '../../theme/admin/AddPromotions.css';
import ICONS from '../../constants/admin/icons';
import ProductPromotion from './ProductPromotion';


function AddPromotions() {
  return (
    <div className='add-promotions-container'>
         <div className='add-promotion-information'>
                <h2>Promotion Information</h2>
                <div className='save-promotion'> Save Promotion</div>
                <div className='primary-add-information'>
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

                <h2>Products on this promotions</h2>
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

export default AddPromotions