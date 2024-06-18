import React, { useState } from 'react'

import '../../theme/admin/FilterDiamond.css';

function FilterDiamond({
    setClarityFilter,setColorFilter,setCutFilter,setOriginFilter,
    originFilter,clarityFilter,colorFilter,cutFilter
}) {


      const origin = [ {value : "NATURAL" },   {value : "LAB GROWN" }  ]
    
      const color = [{value : "K"},{value : "J"},{value : "I"},{value : "H"},{value : "G"}, {value : "F"},{value : "E"}, {value : "D"}
      ]
    
      const clarity = [ {value : "SI2"}, {value : "SI1"}, {value : "VS2"}, {value : "VS1"}, {value : "VVS2"}, {value : "VVS1"}, {value : "IF"}, {value : "FL"}
      ]
    
      const cut = [ {value : "FAIR"},  {value : "GOOD"}, {value : "V.GOOD"}, {value : "EX."},
      ]
 
    const handleFillterOrigin = (value) => {
        console.log(value);
        let isValid = false;
        originFilter.map((item) => {
            if(item === value) {
                isValid = true;
            }
        })
      
        if(isValid){
          let originActiveUpdate = originFilter.filter((item) => 
            item !== value
          )
          setOriginFilter(originActiveUpdate);
        }else{
          let originActiveUpdate = [...originFilter,value];
          setOriginFilter(originActiveUpdate);
        }
    }   

  return (
    <div className='filter-diamond-container'>
        <h3>Filter</h3>
        <div className='filter-content'>
            <div className='origin'>
                <h4>Origin</h4>
                <ul>
                    {origin.map((item) =>(<li
                        onClick={() => handleFillterOrigin(item.value)}
                    className={originFilter.includes(item.value) ? "isActive" : null}>
                        {item.value}
                        </li>))}
                </ul>
            </div>
            <div className='origin'>
                <h4>Color</h4>
                <ul>
                    {color.map((item) =>(<li>{item.value}</li>))}
                </ul>
            </div>
            <div className='clarity'>
                <h4>Clarity</h4>
                <ul>
                    {clarity.map((item) =>(<li>{item.value}</li>))}
                </ul>
            </div>
            <div className='cut'>
                <h4>Cut</h4>
                <ul>
                    {cut.map((item) =>(<li>{item.value}</li>))}
                </ul>
            </div>
    </div>
    </div>
  )
}

export default FilterDiamond