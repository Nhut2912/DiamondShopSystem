import React, { useState } from 'react'

import '../../theme/admin/Promotions.css';
import InputBox from '../../components/admin/InputBox';
import ICONS from '../../constants/admin/icons';
import PromotionsView from '../../components/admin/PromotionsView';
import AddPromotions from '../../components/admin/AddPromotions';


function Promotions() {

  const navigateItems = [
    {name : "View Promotions"},
    {name : "Add Promotion"}
  ]
  const [activeItem,setActiveItem] = useState("View Promotions");




  const handleClickNavigate = (item) => {
    setActiveItem(item);
  }

  return (
    <div className='promotions-admin-container'>
        <h1>Promotions</h1>
        <p>
          Admin / <span>Promotions</span>
        </p>
        <div className='navigate'>
            <ul>
                {
                  navigateItems.map((item) => (
                    <li
                    onClick={() => handleClickNavigate(item.name)}
                      className={activeItem === item.name ? "isActive" : null}
                    >{item.name}</li>
                  ))
                }
            </ul>
            {
              activeItem === "View Promotions" ? 
              <div className='search-promotions'>
                  <input placeholder='Search' type="text" />
                  <img src={ICONS.icon_search} alt=''/>
            </div> : ""
            }
            
        </div>

        <div className='promotion-control'>
        {
              activeItem === "View Promotions" ? 
              <PromotionsView />: <AddPromotions />
            }
              
        </div>
    </div>
  )
}

export default Promotions