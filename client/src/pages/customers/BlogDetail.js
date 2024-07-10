import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';

import '../../theme/customer/BlogDetail.css'


function BlogDetail() {

  const location = useLocation();   
  const [data,setData] = useState();
 
 
 
 useEffect(() => {
         if(location.state){
            const content = document.getElementById('content-blog');
             setData(location.state);
             content.innerHTML =location.state.contentDetail; 
             console.log(data);
          }
         
 },[])
 
  return (
    <div className='container-blog-content-customer' >
        <p>
            Home / <span>Blog</span> / <span>{data !== undefined &&
              data !== null ? data.tagName : '' 
              }</span>
          </p>  
        <div id='content-blog' >
    
        </div>
    </div>
    
  )
}

export default BlogDetail