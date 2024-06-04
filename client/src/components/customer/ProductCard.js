import React, { useEffect, useState } from 'react'

import '../../theme/customer/ProductCard.css';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

import { getDownloadURL, listAll, ref } from 'firebase/storage';
import { imageStorage } from '../../config/FirebaseConfig';

function ProductCard({name,images,id}) {

  const [imagesProduct,setImagesProduct] = useState();
  const navigate = useNavigate();
  const viewDetailProduct = () => {
      if(id !== undefined){
        navigate("/products/"+id);
      }
  }
  


  useEffect(() => {
    const getImageUrls = async () => {
      const imageTmpProduct = [];
      try {
        for (const image of images) {
          const imageRef = ref(imageStorage, image);
          const url = await getDownloadURL(imageRef);
          imageTmpProduct.push(url);
        }
        setImagesProduct(imageTmpProduct);
      } catch (error) {
        console.error('Error fetching image URLs:', error);
      }
    };
  
    getImageUrls(); 
  }, [images, imageStorage]);

  if(imagesProduct === undefined) return <div>Loading</div>;

  
  return (
    <div className='product-card-container'
      onClick={viewDetailProduct}
    >
        <h3>{name === undefined ? "" : name}</h3>

        {false &&
          (
            <div className='tag-product-card'>
                <h4>On sale</h4>
                <span>30%</span>
            </div>
          )
        }
         
        
      
        <div className='product-card-img'>
            <img src={imagesProduct[0] === null ? "" : imagesProduct[0]} alt='' />
        </div>
        <div className='product-card-price'>
          {true && 
             <span>
             $ 1,703.87
           </span>
          }
           
            <div>
              <h3>$ 1,003.87</h3>
              <img src={ICONS.icon_cart} alt=''/>
            </div>
        </div>
    </div>
  )
}

export default ProductCard