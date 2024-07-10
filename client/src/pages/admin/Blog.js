import React, { useState } from 'react'

import '../../theme/admin/Blog.css'
import ViewBlog from '../../components/admin/ViewBlog';
import AddBlog from '../../components/admin/AddBlog';

function Blog() {

  const [activeItems,setActiveItems] = useState("View Blogs");

  const navigation = [
    "View Blogs","Add Blog"
  ]


  return (
    <div className='blog-admin-container'>
        <h1>Blogs</h1>
        <p>
          Admin / <span>Manage Blogs</span>
        </p>
        <ul 
        className='naviagtion-blog'>
            {
              navigation.map((item) => (
                <li
                onClick={() => setActiveItems(item)}
                className={activeItems === item ? 'isActive' : null}
                >{item}</li>
              ))
            }
        </ul>
        <div className='container-content-navigation'>
            {
              activeItems === "View Blogs" ? 
              <ViewBlog /> : <AddBlog />
            }
        </div>
    </div>
  )
}

export default Blog