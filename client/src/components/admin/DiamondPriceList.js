import React, { useEffect, useState } from 'react'

import "../../theme/admin/DiamondPriceList.css"
import FilterDiamond from './FilterDiamond'
import PriceListContainer from './PriceListContainer'
 

function DiamondPriceList() {
 

 const [originFilter,setOriginFilter] = useState([]);
 const [clarityFilter,setClarityFilter] = useState([]);
 const [colorFilter,setColorFilter] = useState([]);
 const [cutFilter,setCutFilter] = useState([]);


 useEffect(() => {
  const params =  new URLSearchParams(window.location.search);

  if(params.get("origin") !== null && params.get("clarity") !== null && params.get("color") && params.get("cut") ){
    const originUpdate = [...originFilter,params.get("origin")];
    setOriginFilter([...new Set(originUpdate)])
  
    const clarityUpdate = [...clarityFilter,params.get("clarity")];
    setClarityFilter([...new Set(clarityUpdate)])
  
    const colorUpdate = [...colorFilter,params.get("color")];
    setColorFilter([...new Set(colorUpdate)])
  
    const cutUpdate = [...cutFilter,params.get("cut")];
    setCutFilter([...new Set(cutUpdate)])
  }


 },[])

 
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