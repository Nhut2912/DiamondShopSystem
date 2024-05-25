import React from 'react'

import '../theme/InputBox.css';


function InputBox({title, setParams}) {
  const handleChange = (event) => {
    const value = event.target.value;
    setParams(value);
  }
  return (
    <div className='input-box-container'>
        <label>{title}</label>
        <input 
          onBlur={(event) => handleChange(event)}
          type='text' />
    </div>
  )
}

export default InputBox