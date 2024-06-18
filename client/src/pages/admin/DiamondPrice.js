import React from 'react'

import '../../theme/admin/DiamondPrice.css';
import DiamondPriceList from '../../components/admin/DiamondPriceList';

function DiamondPrice() {
  return (
    <div className='diamond-price-container'>
       <h1>Diamond Prices</h1>
        <p>
          Admin / <span>Diamond Prices</span>
        </p>
        <DiamondPriceList />
    </div>
  )
}

export default DiamondPrice