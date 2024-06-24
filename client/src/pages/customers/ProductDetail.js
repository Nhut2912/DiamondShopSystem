import React, { useEffect, useState } from 'react'

import '../../theme/customer/ProductDetail.css';
import { useNavigate, useParams } from 'react-router-dom';
import ProductDetailCard from '../../components/customer/ProductDetailCard';
import ProductDescription from '../../components/customer/ProductDescription';
import Question from '../../components/customer/Question';
import ProductCard from '../../components/customer/ProductCard';
import { IMAGES } from '../../constants/customer';

function ProductDetail() {
  const navigate = useNavigate();
  const param = useParams();
  const [data,setData] = useState();
  const [productSimilar,setProductSimilar] = useState();
  

  console.log(param);
  useEffect(() => {
      fetch('${process.env.REACT_APP_API_ENDPOINT}/api/product/getProduct/'+param.id)
        .then(response => response.json())
        .then(data => setData(data))
        .catch((error) => console.error(error));
  },[])
  
  useEffect(() => {
    fetch('${process.env.REACT_APP_API_ENDPOINT}/api/product/similar/'+param.id)
    .then(response => response.json())
    .then(data => setProductSimilar(data))
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
          >{data.code}</span></p>

          <ProductDetailCard data = {data} />
          <ProductDescription data={data} />
          <Question />

          <div className='similar-product'>
                <h3>
                  Similar products
                </h3>
                <div className='slider'>
                   <div className='slider-container' id="slider-container">
                    {productSimilar !== undefined && productSimilar !== null &&
                      productSimilar.map((item) => (
                          <ProductCard 
                          name={item.name}
                          images={item.images}
                          id={item.id}
                          price={item.price}
                          promotions={item.promotions}
                          />
                      ))
                    }
                      
                   </div>
                </div>
                
            </div>

          <div className='banner-on-product-detail'>
                <img src={IMAGES.image_banner} />
          </div>
      </div>

    </div>
  )
}

export default ProductDetail