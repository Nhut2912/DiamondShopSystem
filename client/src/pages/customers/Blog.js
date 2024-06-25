import React from 'react'

import '../../theme/customer/Blog.css'
import BlogCard from '../../components/customer/BlogCard'

function Blog() {
  return (
    <div className='blog-customer-container'>
          <p>
            Home / <span>Blog</span>
          </p>

          <div className='blog-content-container'>
              <BlogCard />
              <BlogCard />
              <BlogCard />
              <BlogCard />
              <BlogCard />
              <BlogCard />
          </div>
    </div>
  )
}

export default Blog