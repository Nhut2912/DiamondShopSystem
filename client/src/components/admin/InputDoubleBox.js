import React from 'react'

import '../../theme/admin/InputDoubleBox.css'

function InputDoubleBox({title, 
  _width, 
  setParams,
  getParams
}) {

  const handleChange = (event) => {
    const value = event.target.value;
    setParams(value);
      
  }
  return (
    <div className='input-double-box-container'>
        <label>{title}</label>
        <input
          onChange={(event) => handleChange(event)}
          value={getParams !== null ? getParams : null}
        style={{'width': _width}} type='number' />
    </div>
  )
}


export default InputDoubleBox;