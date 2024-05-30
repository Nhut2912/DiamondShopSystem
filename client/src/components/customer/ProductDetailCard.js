import React, { useState } from 'react'

import '../../theme/customer/ProductDetailCard.css';
import RadioInput from './RadioInput';
import { IMAGES } from '../../constants/customer';


function ProductDetailCard() {

  const imagesProduct = [
    {image: IMAGES.image_product_1},
    {image: IMAGES.image_product_2},
    {image: IMAGES.image_product_3}
  ]

  const [imageActive,setImageActive] = useState(IMAGES.image_product_1);


  const sizesProduct = [{value : 16}, {value :17 }, {value : 18}, {value: 19}, {value : 20}]

  const handleViewImage = (image) => {
      setImageActive(image)
  }

  return (
    <div className='product-detail-card-container'>
        <div className='image-product-container'>
            <div className='container-view-image'>
                <img src={imageActive} alt='' />
            </div>
            {
              imagesProduct.map((image) => (
                <div onClick={() => handleViewImage(image.image)}
                  className={imageActive === image.image ? 'isActive' : ''}
                >
                    <img src={image.image} alt='' />
                </div>
              ))
            }
        </div>
        <div className='information-product-container'>
              <h1>Diamond rings white gold Disney JEWELRY</h1>
              <p>CODE : P01C01D0102</p>
              <h2>$1,003.87</h2>
              <div className='choose-size'>
                  <h5>Choose size</h5>
                  <RadioInput items={sizesProduct} _width={"60px"} />
                  <div className='questions-size'>
                    <span>How to find out the size ?</span>
                    <span>Don’t have the right size ?</span>
                  </div>
              </div>
              <div className='brief-about-the-product'>
                  <h5>Brief about the product</h5>
                    <ul>
                        <li>
                            <span>Material</span>
                            <span>White gold</span>
                        </li>
                        <li>
                            <span>Main diamond</span>
                            <span>1 - 0.155 carat</span>
                        </li>
                        <li>
                            <span>Weight</span>
                            <span>3.2g</span>
                        </li>
                    </ul>
              </div>
              <div className='button-on-product'>
                    <div>
                          <span>Add To Cart</span>
                    </div>
                    <div>
                        <span>Buy Now</span>
                    </div>
              </div>
        </div>
    </div>
  )
}

export default ProductDetailCard