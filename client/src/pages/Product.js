import React from 'react'

import '../theme/Product.css'
import ProductCard from '../components/ProductCard'

function Product() {
  return (
    <div className='product-page-container'>
        <p>
           Home / <span>Product</span>
        </p>
        <div className='on-sale'>
            <h3>
              On sale
            </h3>
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
        </div>
        <div className='all-product'>
              <div className='search-product'>
                  Search
              </div>
              <div className='sorted-products'>
                  Sorted
              </div>
              <div className='category'>
                  category
              </div>
              <div className='container-all-product'>
                alll
              </div>
        </div>
    </div>
  )
}

export default Product