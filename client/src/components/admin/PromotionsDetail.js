import React, { useEffect, useState } from 'react'

import '../../theme/admin/PromotionsDetail.css'
import { ICONS } from '../../constants/admin'
import { useLocation, useNavigate } from 'react-router-dom'
import PromotionInformation from './PromotionInformation';


function PromotionsDetail() {

 const location = useLocation();   
 const navigate = useNavigate();
 const [data,setData] = useState();



useEffect(() => {
        if(location.state){
            setData(location.state);
         }
        
},[])



  return (
    <div className='promotions-detail-container'>
         <h1>Promotions</h1>
        <p>
            Admin / <span>Promotions</span> / <span>{data !== undefined && data !== null ? data.idPromotion: 0}</span>
        </p>

        <div className='back-to-promotions'
            onClick={() => navigate("/admin/overview/promotions")}
        >
            <img src={ICONS.icon_back_arrow} />
            <span>Back</span>
        </div>

        <PromotionInformation
            data={data}
        />
    </div>
  )
}

export default PromotionsDetail