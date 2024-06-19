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

    const handleFillterColor = (value) => {
        console.log(value);
        let isValid = false;
        colorFilter.map((item) => {
            if(item === value) {
                isValid = true;
            }
        })
      
        if(isValid){
          let colorActiveUpdate = colorFilter.filter((item) => 
            item !== value
          )
          setColorFilter(colorActiveUpdate);
        }else{
          let colorActiveUpdate = [...colorFilter,value];
          setColorFilter(colorActiveUpdate);
        }
    }  


    const handleFillterClarity = (value) => {
        console.log(value);
        let isValid = false;
        clarityFilter.map((item) => {
            if(item === value) {
                isValid = true;
            }
        })
      
        if(isValid){
          let clarityActiveUpdate = clarityFilter.filter((item) => 
            item !== value
          )
          setClarityFilter(clarityActiveUpdate);
        }else{
          let clarityActiveUpdate = [...clarityFilter,value];
          setClarityFilter(clarityActiveUpdate);
        }
    }  

    const handleFillterCut = (value) => {
        console.log(value);
        let isValid = false;
        cutFilter.map((item) => {
            if(item === value) {
                isValid = true;
            }
        })
      
        if(isValid){
          let cutActiveUpdate = cutFilter.filter((item) => 
            item !== value
          )
          setCutFilter(cutActiveUpdate);
        }else{
          let cutActiveUpdate = [...cutFilter,value];
          setCutFilter(cutActiveUpdate);
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
                    {color.map((item) =>(<li
                         onClick={() => handleFillterColor(item.value)}
                         className={colorFilter.includes(item.value) ? "isActive" : null}
                    
                    >{item.value}</li>))}
                </ul>
            </div>
            <div className='clarity'>
                <h4>Clarity</h4>
                <ul>
                    {clarity.map((item) =>(<li
                        onClick={() => handleFillterClarity(item.value)}
                        className={clarityFilter.includes(item.value) ? "isActive" : null}
                    
                    >{item.value}</li>))}
                </ul>
            </div>
            <div className='cut'>
                <h4>Cut</h4>
                <ul>
                    {cut.map((item) =>(<li
                         onClick={() => handleFillterCut(item.value)}
                         className={cutFilter.includes(item.value) ? "isActive" : null}
                    >{item.value}</li>))}
                </ul>
            </div>
    </div>
    </div>
  )
}

export default FilterDiamond