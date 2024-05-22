import React from 'react'

import '../theme/InputBox.css';


function InputBox({title, size}) {
  return (
    <div className='input-box-container'>
        <label>{title}</label>
        <input type='text' />
    </div>
  )
}

export default InputBox