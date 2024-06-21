import React, {useState} from 'react' 

import '../../theme/admin/GoldPrice.css'
import MaterialPriceList from '../../components/admin/MaterialPriceList'


function GoldPrice(){
    


   return(
       <div className='gold-price-container'>
            <h1>Material Prices</h1>
            <p>
              Admin / <span>Material Prices</span>
            </p>
            <MaterialPriceList />

       </div>
   )
}
export default GoldPrice