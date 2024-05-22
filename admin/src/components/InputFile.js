import React, { useEffect } from 'react'

import '../theme/InputFile.css';

function InputFile({title , _width, selectedFile}) {
  return (
    <div className='input-file-container'>
        <label>{title}</label>
        <div className='input-file-card'>
            <input style={{"width" : _width}} 
              onChange={(event) => selectedFile(event.target.files[0])}
              type='file'accept='image/png, image/jpeg'

            />
        </div>
    </div>
  )
}

export default InputFile