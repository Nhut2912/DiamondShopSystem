import React, { useEffect, useState } from 'react'

import '../../theme/admin/PriceListContainer.css';
import {ICONS} from '../../constants/admin'

const convertCarat = (carat) => {

  switch (carat) {
    case 0.1:
        return "(0.1 - 0.4)"
      break;
    case 0.5:
      return "(0.4 - 0.8)"
      break;
    case 1:
      return "(0.8 - 1.5)"
        break;
    case 1.5:
      return "(1.5 - 1.8)"
        break;
    case 2:
      return "(1.8 - 2.0)"
        break;
    default:
      break;
  }

}



function PriceListContainer() {

  const [data,setData] = useState();
 



  useEffect(() => {

    if(data === undefined || data === null){
      fetch("http://localhost:8080/api/diamondpricelist/getAll")
      .then((response) => response.json())
      .then((result) => setData(result))
      .catch((error) => console.error(error));
    }
  },[])


  return (
    <div className='price-list-container'>
      <div className='seach-and-show'>
          <span> Showing {data!== undefined && data !== null ? data.length :0} results</span>
          <div>
              <img src={ICONS.icon_search}  />
              <input placeholder='Search' type='text' />
          </div>
      </div>
        <ul className='head-price-list'>
            <li>No.</li>
            <li>Origin</li>
            <li>Color</li>
            <li>Clarity</li>
            <li>Cut</li>
            <li>Carat</li>
            <li>Effected Date</li>
            <li>Price</li>
            <li>Update</li>
        </ul>
        <div className='container-price-scroll'>
          {
            data !== undefined && data !== null && 
            data.
            map((item,index) => (
                <>
                  <ul>
                    <li>{index+1}</li>
                    <li>{item.origin}</li>
                    <li>{item.color}</li>
                    <li>{item.clarity}</li>
                    <li>{item.cut}</li>
                    <li>
                        {convertCarat(item.carat)}
                    </li>
                    <li>{item.effDate}</li>
                    <li>{item.price}</li>
                    <li>Update</li>
                  </ul>
                  {
                    index <= data.length && <div className='line'></div>
                  }
                </>
            ))
          }
            
            
            <ul>
              <li>No.</li>
              <li>Origin</li>
              <li>Color</li>
              <li>Clarity</li>
              <li>Cut</li>
              <li>Carat</li>
              <li>Effected Date</li>
              <li>Price</li>
              <li>Update</li>
            </ul>
        </div>
    </div>
  )
}

export default PriceListContainer