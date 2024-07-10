import React, { useEffect, useState } from 'react'


import '../../theme/admin/ViewBlog.css'


function ViewBlog() {
  const [data,setData] = useState();

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/blog/get`)
    .then((response) => response.json())
    .then((result) => setData(result))
    .catch((error) => console.error(error));
  },[])


  return (
    <div className='view-blog-admin-container'>
        <ul>
          <li>
            Id
          </li>
          <li>Tag Name</li>
          <li>
              Title
          </li>
          <li>
              Description
          </li>
          <li>
              Active
          </li>
        </ul>
        <div className='container-content-blog'>

          {
            data != undefined && data != null && 
            data.map((item) => (
              <ul>
                <li>
                  {item.id}
                </li>
                <li>{item.tagName}</li>
                <li>
                  {item.title}
                </li>
                <li>
                  {item.description}
                </li>
                <li className={item.active ? 'isActive' : ''}>
                   <span>
                      Active
                   </span>
                </li>
              </ul>
            ))
          }
        
       
        </div>
    </div>
  )
}

export default ViewBlog