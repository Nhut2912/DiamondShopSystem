import React from 'react'

import '../../theme/customer/InputFile.css';

function InputFile({title , _width, setImageData,setFileImage}) {
 const loadFileHandle = (event) => {
    const file = event;
    if(file){
      const reader = new FileReader();
      reader.onload = event => {
         const dataURL = event.target.result;
            setFileImage(file);
            setImageData(dataURL);
      };
      reader.readAsDataURL(file);
    }
 }
  



  return (
    <div className='input-file-customer-container'>
        <label>{title}</label>
        <div className='input-file-card'>
            <input style={{"width" : _width}} 
              onChange={(event) => loadFileHandle(event.target.files[0])}
              type='file'accept='image/png, image/jpeg'

            />
        </div>
    </div>
  )
}

export default InputFile