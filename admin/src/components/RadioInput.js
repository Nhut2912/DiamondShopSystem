import React, { useCallback, useState } from 'react'

import '../theme/InputRadio.css';

function RadioInput({title, _width, items, setParams}) {
  const [active,setActive] = useState(items[0].value)


  const handleClick = (item) => {
      setParams(item);
      setActive(item);
  }

  return (
    <div className='radio-input-container' >
        <label>
            {title}
        </label>
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