import React, { useEffect, useState } from 'react'

import '../../theme/admin/ProductPromotion.css'
import PromotionProductCard from './PromotionProductCard'

function ProductPromotion({thisPromotionID,productsPromotion, thisPromotion,thisPromotionRate}) {

  const [data,setData] = useState([]);

  useEffect(() => {
     if(productsPromotion !== undefined && productsPromotion !== null && productsPromotion.length > 0){
     
      if(data === undefined || data === null || data.length === 0){
    
          const fetchData = async() => {

            const productObject = await Promise.all(
              productsPromotion.map( async (item) => {
               const response = await fetch(`http://localhost:8080/api/product/getProduct/${item}`);
               return await response.json();
              })
            )
            console.log(productObject);
            setData([...data,...productObject.filter(product => product !== null)])

          }
          fetchData();



        }else{

            const fetchData = async() => {
              
              let dataFetch = productsPromotion.filter(id => !data.some(item => item.id == id));

              const productObject = await Promise.all(
                dataFetch.map( async (item) => {
                 const response = await fetch(`http://localhost:8080/api/product/getProduct/${item}`);
                 return await response.json();
                })
              )
              console.log(productObject);
              setData([...data,...productObject.filter(product => product !== null)])

            }
            fetchData();
        }
     }
  },[productsPromotion])
  
  



  return (
    <div className='product-promotion-container'>
        <ul>
            <li>No.</li>
            <li>Code</li>
            <li>Name</li>
            <li>Price</li>
            <li>Promotions</li>
            <li>Price On Promotions</li>
        </ul>
        {
          data !== undefined && data !== null && data.length > 0 &&
          data.map((item,index) => (
            <PromotionProductCard 
                index={index+1}
                Code={item.code}
                Name={item.name}
                Price={item.price}
                thisPromotion={thisPromotion}
                thisPromotionRate={thisPromotionRate}
                promotionsAvailable={item.promotions}
                thisPromotionID={thisPromotionID}
            />
          ))
        }
    </div>
  )
}

export default ProductPromotion