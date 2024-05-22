import React from 'react'

import '../theme/InputDoubleBox.css'

function InputDoubleBox({title}) {
  return (
    <div className='input-double-box-container'>
        <label>{title}</label>
        <input type='number' />
    </div>
  )
}

export default InputDoubleBox;