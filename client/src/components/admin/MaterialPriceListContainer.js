import React from 'react'
import { ICONS } from '../../constants/admin'

import "../../theme/admin/MaterialPriceListContainer.css"


function MaterialPriceListContainer() {
  return (
    <div className='material-price-list-container'>
        <div className='seach-and-show'>
          <span> Showing 50 results</span>
          <div>
              <img src={ICONS.icon_search}  />
              <input placeholder='Search' type='text' />
          </div>
      </div>
        <ul className='head-price-list'>
            <li>No.</li>
            <li>Material</li>
            <li>Sell Price</li>
            <li>Effected Date</li>
            <li>Update</li>
        </ul>
        <div className='container-price-scroll' >
            <ul >
                <li>No.</li>
                <li>Material</li>
                <li>Sell Price</li>
                <li>Effected Date</li>
                <li>Update</li>
            </ul>
        </div>
    </div>
  )
}

export default MaterialPriceListContainer