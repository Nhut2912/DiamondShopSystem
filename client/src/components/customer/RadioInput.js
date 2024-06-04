import React, { useState } from 'react'

import '../../theme/customer/RadioInput.css';

function RadioInput({ _width, items, setParams, getParams}) {
  const [active,setActive] = useState(getParams !== undefined && getParams !== null ? getParams : items[0].value) 

  const handleClick = (item) => {
    setParams(item)
      setActive(item);
  }

  return (
    <div className='radio-input-container' >
        <div className='container-option-radio'>
            {items && items.map((item) => (
              <div
                style={{"width" : _width }}
                key={item.value} 
                className={active === item.value ? 'isActive' : ''}
                onClick={() => handleClick(item.value)}
              
              > 
                <span>{item.value}</span>
              </div>
            ))}
        </div>
    </div>
  )
}

export default RadioInput