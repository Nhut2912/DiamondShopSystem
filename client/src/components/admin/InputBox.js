import React from 'react'

import '../../theme/admin/InputBox.css';


function InputBox({title, 
  setParams,
  getParams
}) {

  const handleChange = (event) => {
    
    const value = event.target.value;
    if(value !== ""){
      console.log(value);
      setParams(value);
    }
    
  }
  return (
    <div className='input-admin-box-container'>
        <label>{title}</label>
        <input 
          placeholder={getParams !== null ? getParams : null}
          onBlur={(event) => handleChange(event)}
          type='text' />
    </div>
  )
}

export default InputBox