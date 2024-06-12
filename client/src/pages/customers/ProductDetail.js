import React, { useEffect, useState } from 'react'

import '../../theme/customer/ProductDetail.css';
import { useNavigate, useParams } from 'react-router-dom';
import ProductDetailCard from '../../components/customer/ProductDetailCard';
import ProductDescription from '../../components/customer/ProductDescription';
import Question from '../../components/customer/Question';
import ProductCard from '../../components/customer/ProductCard';

function ProductDetail() {
  const navigate = useNavigate();
  const param = useParams();
  const [data,setData] = useState();

  console.log(param);
  useEffect(() => {
      fetch('http://localhost:8080/api/product/getProduct/'+param.id)
        .then(response => response.json())
        .then(data => setData(data))
        .catch((error) => console.error(error));
  },[])
 
  if(data === undefined) return <div>Loading</div>
 

  console.log(data);

  return (
    <div>
 
      <div className='product-detail-container'>
          <p><span
            onClick={() => navigate("/home")}
          >Home</span> / <span
            onClick={() => navigate("/products")}
          >Product</span> / <span
          >{data.id}</span></p>

          <ProductDetailCard data = {data} />
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