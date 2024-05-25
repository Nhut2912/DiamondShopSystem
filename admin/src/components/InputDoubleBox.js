import React from 'react'

import '../theme/InputDoubleBox.css'

function InputDoubleBox({title, _width, setParams}) {

  const handleChange = (event) => {
      const value = event.target.value;
      setParams(value);
  }
  return (
    <div className='input-double-box-container'>
        <label>{title}</label>
        <input
          onBlur={(event) => handleChange(event)}
        style={{'width': _width}} type='number' />
    </div>
  )
}


export default InputDoubleBox;