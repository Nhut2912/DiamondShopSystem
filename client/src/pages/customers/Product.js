import React, { useEffect, useState } from 'react'

import '../../theme/customer/Product.css'
import ProductCard from '../../components/customer/ProductCard'
import InputSelectBox from '../../components/customer/InputSelectBox'
import { IMAGES } from '../../constants/customer'

function Product() {


  const [data,setData] = useState();

  useEffect(() => {
        fetch('http://localhost:8080/api/product/getProducts')
          .then(response => response.json())
          .then(data => setData(data))
          .catch((error) => console.error(error));
  },[])
  console.log(data);
  if(data === undefined) return <div>Loading</div>

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
                    {
                            data.map((product) => (
                              <ProductCard 
                              name={product.name}
                              images={product.images}
                              id={product.id}
                              />
                            ))
                          }
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
                          {
                            data.map((product) => (
                              <ProductCard 
                              name={product.name}
                              images={product.images}
                              id={product.id}
                              />
                            ))
                          }
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
            <img src={IMAGES.images_banner_in_product} alt=''/>
      </div>
    </div>
    
  )
}

export default Product