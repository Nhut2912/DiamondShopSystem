import React, { useState } from 'react'

import '../../theme/customer/InputBox.css';


function InputBox({title, 
  setParams,
  getParams
}) {

  const handleChange = (event) => {
    const value = event.target.value;
    setParams(value);
  }

  return (
    <div className='input-box-container'>
        <label>{title}</label>
        <input 
          value={getParams}
          onChange={handleChange}
          type='text' />
    </div>
  )
}

export default InputBox