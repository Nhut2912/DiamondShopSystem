import React, { useEffect, useState } from 'react'

import '../../theme/admin/CardProduct.css';
import { useNavigate } from 'react-router-dom';
import { imageStorage } from '../../config/FirebaseConfig';
import { getDownloadURL, ref } from 'firebase/storage';

function CardProduct({data}) {
  const navigate = useNavigate();
  const productId = data.id;
  console.log(data)
  const handClick = () => {  
    navigate(""+productId,{state: data});
  }
  const [images,setImages] = useState();

  useEffect(() => {
    const getImageUrls = async () => {
      const imageTmpProduct = [];
      try {
        for (const image of data.images) {
          const imageRef = ref(imageStorage, image);
          const url = await getDownloadURL(imageRef);
          imageTmpProduct.push(url);
        }
        setImages(imageTmpProduct);
      } catch (error) {
        console.error('Error fetching image URLs:', error);
      }
    };
  
    getImageUrls(); 
  }, [data.images, imageStorage]);


  return (
    <div className='content-table-container' onClick={handClick} style={{'cursor': 'pointer'}} >
        <ul className='table-content'>
          <li>
              #{data.id}
          </li>
          <li>
              <div className='img-product-table'>
                  <img src={images !== undefined && images !== null ? images[0] : null} alt=''/>
              </div>
          </li>
          <li>
            #{data.code}
          </li>
          <li>
            {data.name}
          </li>
          <li>
          {data.category}
          </li>
          <li>
            {data.diamonds.map((item,index) => (
                <>
                  <span>
                      {item.code} - {item.carat} carat
                  </span>
                  {index < data.diamonds.length -1 ? <br /> : null}
                </>
            ))}
          </li>
          <li>
            {data.materials.map((item,index) => (
              index < data.materials.length-1 ? `${item.name};  ` : `${item.name}`
            ))}
          </li>
          <li>
            <div className={data.active ? 'status-product available' : 'status-product unAvailable'}>
                <span>
                {data.active ? 'ACTIVE' : 'INACTIVE'}
                </span>
            </div>
          </li>
        </ul>
    </div>
  )
}

export default CardProduct