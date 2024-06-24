import React, { useEffect, useState } from 'react'

import '../../theme/admin/ProductPromotion.css'
import PromotionProductCard from './PromotionProductCard'

function ProductPromotion({thisPromotionID,productsPromotion, thisPromotion,thisPromotionRate,
  handleDeleteProduct
}) {

  const [data,setData] = useState([]);


  useEffect(() => {
     if(productsPromotion !== undefined && productsPromotion !== null ){
      if(productsPromotion.length === 0){
        setData(...productsPromotion)
      }else if(data === undefined || data === null || data.length === 0){
    
          const fetchData = async() => {

            const productObject = await Promise.all(
              productsPromotion.map( async (item) => {
               const response = await fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/product/getProduct/${item}`);
               return await response.json();
              })
            )
            if(data === undefined || data === null){
              setData([...productObject.filter(product => product !== null)])
            }else setData([...data,...productObject.filter(product => product !== null)]);

          }
          fetchData();
        }else{
            const fetchData = async() => {
              
              let dataFetch = [];
              if(productsPromotion.length < data.length){
                dataFetch = productsPromotion;
              }else{
                dataFetch = productsPromotion.filter(id => !data.some(item => item.id == id));
              }

              const productObject = await Promise.all(
                dataFetch.map( async (item) => {
                 const response = await fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/product/getProduct/${item}`);
                 return await response.json();
                })
              )
              console.log(productObject);
              if(productsPromotion.length < data.length){
                setData([...productObject.filter(product => product !== null)])
              }else{
                  setData([...data,...productObject.filter(product => product !== null)])
              }
              
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
                productID={item.id}
                handleDeleteProduct={handleDeleteProduct}
            />
          ))
        }
    </div>
  )
}

export default ProductPromotion