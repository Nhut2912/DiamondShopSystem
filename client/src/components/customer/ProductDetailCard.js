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

const formattedNumber = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  minimumFractionDigits: 2,
})


const getPriceBySize = (userSize,productSize,unitPrice,productPrice) => {

    if(userSize == productSize){
      return productPrice;
    }else if(userSize > productSize){
      return productPrice + (userSize-productSize)*unitPrice;
    }else{
      return productPrice - (productSize-userSize)*unitPrice;
    }
}


function ProductDetailCard({data}) {
  const [imageActive,setImageActive] = useState();
  const [imagesProduct,setImagesProduct] = useState();
  const navigate = useNavigate();
  const cart = useContext(CartContext);
  const [account,setAccount] = useLocalStorage("account",localStorage.getItem("account"));
  const [promtionRate,setPromotionRate] = useState();
  const [isPromotion,setIsPromotion] = useState();

  let size = data.size;
  const sizesProduct = [{value : size-2}, {value : size-1 }, {value :  size}, {value:  size+1}, {value :  size+2}]
  const [userSize,setUserSize] = useState(size);


  useEffect(() => {
    if(data.promotions !== undefined && data.promotions !== null && data.promotions.length > 0){
 
      setIsPromotion(true);
      let promotionRate = 0;
      data.promotions.map((item) => {
        console.log(item);
          if(item.active && ((new Date(item.dateEnd).getTime()) > (new Date().getTime()))){
            promotionRate += item.promotionRate;
          }
      })
      setPromotionRate(promotionRate);
    }else setIsPromotion(false);
  },[data])

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


  const handleAddProduct = () => {
    let beAdded = true;
    ProductCart.code = data.code;
    ProductCart.id = data.id;
    ProductCart.name = data.name;
    ProductCart.sizeUser = userSize;
    ProductCart.price =  getPriceBySize(userSize,data.size,data.sizeUnitPrice,data.price);
    ProductCart.images = data.images;

    account.cart.map((item) => {
      console.log(account.cart);
      console.log(item.id +"- " + ProductCart.id);
      console.log(item.sizeUser+"- " +ProductCart.sizeUser);
        if(item.id === ProductCart.id){
            if(item.sizeUser !== ProductCart.sizeUser){
                item.sizeUser = ProductCart.sizeUser;
                item.price = ProductCart.price;
            }
            beAdded = false;
        }
    })
    if(beAdded){
      let cartUpdated = [...account.cart,ProductCart];
      account.cart = cartUpdated;    
    }
    setAccount(account);
    cart.addToCart();
  }


  const hanleAddTocard  = () => {
     if(account !== null){
      handleAddProduct();
     }else navigate("/login")
  }

  const hanleBuyNow = () => {
    if(account !== null){
      handleAddProduct();
      navigate("/checkout-cart")
     }else navigate("/login")
  }

  console.log(data);

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
              <h5>{isPromotion !== undefined && isPromotion !== null && promtionRate !== undefined
                  && promtionRate !== null && isPromotion ? 
                  formattedNumber.format(data.price) : null
                }</h5>

                {
                  isPromotion !== undefined && isPromotion !== null && isPromotion ? 
                  <h2>{formattedNumber.format(getPriceBySize(userSize,data.size,data.sizeUnitPrice,(data.price-(data.price*promtionRate)  /100)).toFixed(2))}
                
                  </h2>: <h2>{formattedNumber.format(getPriceBySize(userSize,data.size,data.sizeUnitPrice,data.price).toFixed(2))}
                
                </h2>
                }
             
              <div className='choose-size'>
                 {data.category === "Ring" ? 
                  <>
                    <h5>Choose size</h5>
                    <RadioInput items={sizesProduct}
                      setParams={setUserSize}
                      getParams={userSize}
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
                    <div onClick={hanleBuyNow}>
                        <span>Buy Now</span>
                    </div>
              </div>
        </div>
    </div>
  )
}

export default ProductDetailCard