import React, { useEffect, useState } from 'react'

import '../../theme/customer/Navigate.css';

import  { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

function Navigate() {
  const navigate = useNavigate();
  const url = window.location.href;


  const [activeItem, setActiveItem] = useState("Your Profile");


  const navigateItems = [
    {name : "Your Profile", image : ICONS.icon_user, path:"/account"},
    {name : "Purchase History", image : ICONS.icon_purchase, path: "purchase"}
  ]

  useEffect(()=>{
    navigateItems.map((item) => {
      if(url.endsWith(item.path)){
        setActiveItem(item.name);
      }
    })
  },[])

  const handleNavigate = (item) => {
    navigate(item.path)
    setActiveItem(item.name);
  }

  return (
    <div className='container-navigate-account'>
          <div className="head-navigation">
                <img src={ICONS.icon_logo_no_text} width="50px" alt="" />
          </div>
          <ul>
            {
              navigateItems.map((item) => (
                <li 
                onClick={()=>handleNavigate(item)}
                className={activeItem === item.name ? "isActive" : ''}>
                    <div>
                        <img src={item.image} alt='' />
                    </div>
                    <span>{item.name}</span>
                </li>
              ))
            }
              <li >
                    <div>
                        <img src={ICONS.icon_logout} alt='' />
                    </div>
                    <span>Log Out</span>
                </li>
          </ul>
    </div>  
  )
}

export default Navigate