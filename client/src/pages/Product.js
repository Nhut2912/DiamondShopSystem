import React from 'react'

import '../theme/Product.css'
import ProductCard from '../components/ProductCard'
import InputSelectBox from '../components/InputSelectBox'
import IMAGES from '../constants/images'

function Product() {
  const itemsSorted = [
    {name : "Default Sorted"},
    {name : "Ascending Price"},
    {name : "Descending Price"},
    {name : "Newest products"}
  ]
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
                      <div className='input-select'>
                          <InputSelectBox options={itemsSorted} />
                      </div>
                  </div>
                  <div className='category'>
                      <h3>Category</h3>
                      <ul>
                        <li className='isActive'>
                      
                          <span>
                              Ring
                          </span>
                        </li>

                        <li>
                        
                          <span>
                              Earring
                          </span>
                        </li>

                        <li>
                          
                          <span>
                            Pendant
                          </span>
                        </li>

                        <li>
                          
                          <span>
                              Shake
                          </span>
                        </li>

                        <li>
                       
                          <span>
                              Jewelry Set
                          </span>
                        </li>

                      </ul>
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
                            <div className='isActive'><span>01</span></div>
                            <div><span>02</span></div>
                            <div><span>03</span></div>
                            <div><span>04</span></div>
                      </div>
                  </div>
            </div>
      
        </div>
        
      <div className='banner-sale'>
            <img src={IMAGES.images_banner_in_product} />
      </div>
    </div>
    
  )
}

export default Product