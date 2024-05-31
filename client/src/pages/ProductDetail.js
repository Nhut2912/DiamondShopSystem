import React from 'react'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../theme/ProductDetail.css';
import { useNavigate } from 'react-router-dom';
import ProductDetailCard from '../components/ProductDetailCard';
import ProductDescription from '../components/ProductDescription';
import Question from '../components/Question';
import ProductCard from '../components/ProductCard';

function ProductDetail() {
  const navigate = useNavigate();
  return (
    <div>
      <Header />
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

      <Footer />
    </div>
  )
}

export default ProductDetail