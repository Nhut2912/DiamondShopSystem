import React, { useEffect, useState } from 'react'

import '../../theme/admin/PromotionProductCard.css'

function PromotionProductCard({
  index,Code,Name,Price,thisPromotion,
  thisPromotionRate,promotionsAvailable,thisPromotionID,handleDeleteProduct,productID
}) {

  const [promotionsRate,setPromotionsRate] = useState();

  useEffect(() => {
    let promotionRate = Number(thisPromotionRate) | 0;
    if(promotionsAvailable !== undefined && promotionsAvailable !== null && promotionsAvailable.length >0 
    ){
      promotionsAvailable.filter((item) => item.idPromotion !== thisPromotionID).
      map((item) => {
        if(item.promotionRate !== null){
          promotionRate += Number(item.promotionRate);
        }
      })
      
    }
    setPromotionsRate(promotionRate)
  },[thisPromotionRate])

  const handlePressDeleteProduct = () => {
    handleDeleteProduct(productID)
  }


  return (
    <ul className='promotion-product-card' onClick={handlePressDeleteProduct}>
        <li>
            {index}
        </li>
        <li>#{Code}</li>
        <li>{Name}</li>
        <li>${Price !== undefined && Price !== null ? Price.toFixed(2) :0}</li>
        <li>
            {
               promotionsAvailable !== undefined && promotionsAvailable !==null && promotionsAvailable.length > 0 &&
               !promotionsAvailable.some((item) => item.idPromotion === thisPromotionID) ? 
               <span className={"isActive"}>{thisPromotion}</span>:""
            }


            {
              promotionsAvailable !== undefined && promotionsAvailable !==null && promotionsAvailable.length > 0 &&
              promotionsAvailable.map((item) => (
                <span className={item.idPromotion === thisPromotionID ?"isActive" : null}>{item.idPromotion === thisPromotionID ?
                  thisPromotion :item.namePromotion}</span>
              ))
            }

        </li>

        <li
        >${promotionsRate !== undefined && 
          promotionsRate !== null ?
            (((100-promotionsRate)*Price)/100).toFixed(2): Price}   {`(-${
              promotionsRate !== undefined && 
              promotionsRate !== null ?
              promotionsRate : 0}%)`}</li>
    </ul>
  )
}

export default PromotionProductCard