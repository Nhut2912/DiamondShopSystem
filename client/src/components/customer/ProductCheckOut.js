import React, { useEffect, useState } from 'react'


import '../../theme/customer/ProductCheckOut.css';
import { ICONS } from '../../constants/customer';
import { getDownloadURL, ref } from 'firebase/storage';
import { imageStorage } from '../../config/FirebaseConfig';

const numberFormatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    maximumSignificantDigits: 2
  });

  

function ProductCheckOut({data}) {


 const [imagesProduct,setImagesProduct] = useState();
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
          } catch (error) {
            console.error('Error fetching image URLs:', error);
          }
        };
      
        getImageUrls(); 
      }, [data.images, imageStorage]);
    
      if(imagesProduct === undefined) return <div>Loading</div>;


  return (
    <div className='product-check-out-container'>
        <div>
            <img src={imagesProduct[0]} alt=''/>
        </div>
        <h2>{data.name}</h2>
        <p>CODE: {data.code}</p>
        <ul>
            <li>
                <span>Price</span>
                <span>{numberFormatter.format(data.price)}</span>
            </li>
            <li>
                <span>
                    Size
                </span>
                <span>
                    {data.sizeUser}
                </span>
            </li>
         
        </ul>
        <div>
             <img src={ICONS.icon_delete} alt=''/>
        </div>
    </div>
  )
}

export default ProductCheckOut