import React, { useState } from 'react'

import "../../theme/admin/DiamondPriceList.css"
import FilterDiamond from './FilterDiamond'
import PriceListContainer from './PriceListContainer'


function DiamondPriceList() {

 const [originFilter,setOriginFilter] = useState([]);
 const [clarityFilter,setClarityFilter] = useState([]);
 const [colorFilter,setColorFilter] = useState([]);
 const [cutFilter,setCutFilter] = useState([]);

  return (  
    <div className='diamond-price-list-container'>
        <FilterDiamond
            setClarityFilter={setClarityFilter}
            setColorFilter={setColorFilter}
            setCutFilter={setCutFilter}
            setOriginFilter={setOriginFilter}
            originFilter={originFilter}
            clarityFilter={clarityFilter}
            colorFilter={colorFilter}
            cutFilter={cutFilter}
        />
        <PriceListContainer
            originFilter={originFilter}
            clarityFilter={clarityFilter}
            colorFilter={colorFilter}
            cutFilter={cutFilter}
        />
    </div>
  )
}

export default DiamondPriceList