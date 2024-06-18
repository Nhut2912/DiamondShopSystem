import React, { useEffect, useState } from 'react'

import '../../theme/admin/PromotionsView.css'
import PromotionCard from './PromotionCard'


function PromotionsView() {



  const [promotions,setPromotions] = useState();


  useEffect(() => {
    fetch("http://localhost:8080/Promotion/getPromotions")
    .then((response) => response.json())
    .then((result) => setPromotions(result))
    .catch((error) => console.error(error));
  },[])


  return (
    <div className='promotions-view-container'>
         <ul className='headtable-promotions-view'>
                <li>No.</li>
                <li>ID</li>
                <li>Name</li>
                <li>Promotion Rate</li>
                <li>Date Start</li>
                <li>Date End</li>
                <li>Number Of Product</li>
                <li>Active</li>
         </ul>
          {  promotions !== undefined && promotions !== null && promotions.length >0 &&
              promotions.map((item,index) => (
                <PromotionCard 
                  promotion={item}
                  index = {index}
                 
                />
              ))     
          }
    </div>
  )
}

export default PromotionsView