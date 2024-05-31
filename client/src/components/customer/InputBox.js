import React from 'react'

import '../../theme/customer/InputBox.css';


function InputBox({title, 
  setParams,
  getParams
}) {


  return (
    <div className='input-box-container'>
        <label>{title}</label>
        <input 
        
          type='text' />
    </div>
  )
}

export default InputBox