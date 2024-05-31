import React from 'react'

import '../../theme/admin/InputDoubleBox.css'

function InputDoubleBox({title, 
  _width, 
  setParams,
  getParams
}) {

  const handleChange = (event) => {
    const value = event.target.value;
    if(value !== ""){
      setParams(value);
    }
      
  }
  return (
    <div className='input-double-box-container'>
        <label>{title}</label>
        <input
          onBlur={(event) => handleChange(event)}
          placeholder={getParams !== null ? getParams : null}
        style={{'width': _width}} type='number' />
    </div>
  )
}


export default InputDoubleBox;