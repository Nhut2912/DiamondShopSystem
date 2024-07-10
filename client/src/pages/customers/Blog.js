import React, { useEffect, useState } from 'react'

import '../../theme/customer/Blog.css'
import BlogCard from '../../components/customer/BlogCard'

function Blog() {

  const [data,setData] = useState();

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/blog/get`)
    .then((response) => response.json())
    .then((result) => setData(result))
    .catch((error) => console.error(error));
  },[])



  return (
    <div className='blog-customer-container'>
          <p>
            Home / <span>Blog</span>
          </p>

          <div className='blog-content-container'>
            {data !== undefined && data !== null && 
              data.map((item) => (
                <BlogCard 
                  data={item}
                />
              ))
            }
              
             
          </div>
    </div>
  )
}

export default Blog