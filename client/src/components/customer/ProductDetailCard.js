import React, { useEffect, useState } from 'react'

import '../../theme/customer/ProductDetailCard.css';
import RadioInput from './RadioInput';

import { getDownloadURL, ref } from 'firebase/storage';
import { imageStorage } from '../../config/FirebaseConfig';
import { useNavigate } from 'react-router-dom';
import useLocalStorage from '../../hook/useLocalStorage';
import { CartContext } from '../../context/CartContext';
import { useContext } from 'react';
import { ProductCart } from '../../constants/customer/ProductCart';

const numberFormatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  maximumSignificantDigits: 2
});

const getPriceBySize = (userSize,productSize,unitPrice,productPrice) =>{
    if(userSize > productSize){
        return productPrice + (userSize - productSize)*unitPrice;
    }else{
      return productPrice - (userSize - productSize)*unitPrice;
    }
}


function ProductDetailCard({data}) {
  const [imageActive,setImageActive] = useState();
  const [imagesProduct,setImagesProduct] = useState();
  const navigate = useNavigate();
  const cart = useContext(CartContext);
  const [account,setAccount] = useLocalStorage("account",localStorage.getItem("account"));

  let size = data.size;
  const sizesProduct = [{value : size-2}, {value : size-1 }, {value :  size}, {value:  size+1}, {value :  size+2}]
  const [userSize,setUserSize] = useState(size-2);


  useEffect(() => {
    const getImageUrls = async () => {
      const imageTmpProduct = [];
      try {
        for (const image of data.images) {
          const imageRef = ref(imageStorage, image);
          const url = await getDownloadURL(imageRef);
          imageTmpProduct.push(url);
        }
        setImagesProduct(imageTmpProduct);
        setImageActive(imageTmpProduct[0]);
      } catch (error) {
        console.error('Error fetching image URLs:', error);
      }
    };
  
    getImageUrls(); 
  }, [data.images, imageStorage]);
  if(imagesProduct === undefined) return <div>Loading</div>;

 

  const handleViewImage = (image) => {
      setImageActive(image)
  }

  let weight = 0;
  data.materials.map((item) =>{
    weight += item.weight;
  })





  const hanleAddTocard  = () => {
     if(account !== null){
        let beAdded = true;

        ProductCart.code = data.code;
        ProductCart.id = data.id;
        ProductCart.name = data.name;
        ProductCart.sizeUser = userSize;
        ProductCart.price =  getPriceBySize(userSize,data.size,data.sizeUnitPrice,data.price);
        ProductCart.images = data.images;

        account.cart.map((item) => {
            if(item.id === ProductCart.id){
                if(item.sizeUser !== ProductCart.sizeUser){
                    item.sizeUser = ProductCart.sizeUser;
                    item.price = ProductCart.price;
                }else{
                  beAdded = false;
                }
            }
        })

        
        if(beAdded){
          let cartUpdated = [...account.cart,ProductCart];
          account.cart = cartUpdated;    
        }
        setAccount(account);
        cart.addToCart();
     }else navigate("/login")
  }

  const hanleBuyNow = () => {

  }

  return (
    <div className='product-detail-card-container'>
        <div className='image-product-container'>
            <div className='container-view-image'>
                <img src={imageActive} alt='' />
            </div>
            {
              imagesProduct.map((image) => (
                <div onClick={() => handleViewImage(image)}
                  className={imageActive === image ? 'isActive' : ''}
                >
                    <img src={image} alt='' />
                </div>
              ))
            }
        </div>
        <div className='information-product-container'>
              <h1>{data.name}</h1>
              <p>CODE : {data.code}</p>
              <h2>{numberFormatter.format(getPriceBySize(userSize,data.size,data.sizeUnitPrice,data.price))}
                </h2>
              <div className='choose-size'>
                 {data.category === "Ring" ? 
                  <>
                    <h5>Choose size</h5>
                    <RadioInput items={sizesProduct}
                      setParams={setUserSize}
                    _width={"60px"} />
                    <div className='questions-size'>
                      <span>How to find out the size ?</span>
                      <span>Don’t have the right size ?</span>
                    </div>
                  </> : ""
                }
              </div>
              <div className='brief-about-the-product'>
                  <h5>Brief about the product</h5>
                    <ul>
                        <li>
                            <span>Material</span>
                            <span>
                              {data.materials.map((item) => (
                                item.name +"; "
                              ))}
                              
                            </span>
                        </li>
                        <li>
                            <span>Main diamond</span>
                            <span>
                              {
                                data.diamonds.map((item) => (
                                  item.carat +" carat; "
                                ))
                              }
                            
                            
                            </span>
                        </li>
                        <li>
                            <span>Weight</span>
                            <span>{
                                weight+"g"
                            }</span>
                        </li>
                    </ul>
              </div>
              <div className='button-on-product'>
                    <div onClick={hanleAddTocard}>
                          <span >Add To Cart</span>
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