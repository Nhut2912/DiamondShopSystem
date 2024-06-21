import React from 'react'
import FilterMaterialPriceList from './FilterMaterialPriceList'
import MaterialPriceListContainer from './MaterialPriceListContainer'

import "../../theme/admin/MaterialPriceList.css"

function MaterialPriceList() {
  return (
    <div className='material-price-list-content-container'>
        <FilterMaterialPriceList />
        <MaterialPriceListContainer />
    </div>
  )
}

export default MaterialPriceList