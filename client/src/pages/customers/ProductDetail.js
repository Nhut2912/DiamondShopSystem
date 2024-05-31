import React from 'react'

import '../../theme/customer/ProductDetail.css';
import { useNavigate } from 'react-router-dom';
import ProductDetailCard from '../../components/customer/ProductDetailCard';
import ProductDescription from '../../components/customer/ProductDescription';
import Question from '../../components/customer/Question';
import ProductCard from '../../components/customer/ProductCard';

function ProductDetail() {
  const navigate = useNavigate();
  return (
    <div>
 
      <div className='product-detail-container'>
          <p><span
            onClick={() => navigate("/home")}
          >Home</span> / <span
            onClick={() => navigate("/products")}
          >Product</span> / <span
          >123</span></p>

          <ProductDetailCard />
          <ProductDescription />
          <Question />

          <div className='similar-product'>
                <h3>
                  Similar products
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

          <div className='banner-on-product-detail'>

          </div>
      </div>

    </div>
  )
}

export default ProductDetail