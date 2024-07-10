import React, { useEffect, useState } from 'react'

import '../../theme/customer/BlogCard.css'
import {ICONS, IMAGES} from '../../constants/customer'
import { useNavigate } from 'react-router-dom'
import { getDownloadURL, ref } from 'firebase/storage';
import { imageStorage } from '../../config/FirebaseConfig';

function BlogCard({data}) {

  const navigate = useNavigate();
  const [imageBlog,setImagesdBlog] = useState();
  const handleClick = () =>{
    navigate(`${data.title}`, {state : data})
  }

  
  useEffect(() => {
    const getImageUrls = async () => {
     
      try {
        const imageRef = ref(imageStorage, data.image);
        const url = await getDownloadURL(imageRef);   
        setImagesdBlog(url);
      } catch (error) {
        console.error('Error fetching image URLs:', error);
      }
    };
  
    getImageUrls(); 
  }, [data.image, imageStorage]);

  return (
    <div 
      onClick={handleClick}
    className='blog-card-container'>
        <div className='container-img'>
            <img src={imageBlog} alt=''/>
        </div>
        <h5>{data.tagName}</h5>
        <h3>{data.title}</h3>
        <p>{data.description}</p>
        <div className='img-icon'>
              <img  src={ICONS.icon_arrow_blog} alt='' />
        </div>
    </div>
  )
}

export default BlogCard