import React from 'react'

import '../theme/Product.css'
import ProductCard from '../components/ProductCard'

function Product() {
  
  return (
    <div className='product-page'>
            <div className='product-page-container'>
            <p>
              Home / <span>Product</span>
            </p>
            <div className='on-sale'>
                <h3>
                  On sale
                </h3>
                <div className='slider'>
                   <div className='slider-container' id="slider-container">
                      <ProductCard />
                      <ProductCard />
                      <ProductCard />
                      <ProductCard />
                      <ProductCard />
                      <ProductCard />
                      <ProductCard />
                   </div>
                
                </div>
                
            </div>
            <div className='all-product'>
                  <div className='search-product'>
                      <input type='text' placeholder='Search' />
                  </div>
                  <div className='sorted-products'>
                      <p>Showing 12 of 90 result</p>
                      <div>
                        Default Sorted
                      </div>
                  </div>
                  <div className='category'>
                      category
                  </div>
                  <div className='container-all-product'>
                      <div className='products'>
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                            <ProductCard />
                      </div>
                      <div>

                      </div>
                  </div>
            </div>
      
        </div>
        
      <div className='banner-sale'>

      </div>
    </div>
    
  )
}

export default Product