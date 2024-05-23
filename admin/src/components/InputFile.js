import React from 'react'

import '../theme/InputFile.css';

function InputFile({title , _width, imageData, index,setImageData, length }) {
 const loadFileHandle = (event) => {
    const file = event;
    if(file){
      const reader = new FileReader();
      reader.onload = event => {
         const dataURL = event.target.result;
         if(index < length){
            const updateImageData = [...imageData];
            updateImageData[index] = dataURL;
            setImageData(updateImageData);
         }else{
            setImageData([...imageData,dataURL]);
         }
      };
      reader.readAsDataURL(file);
    }
 }
  



  return (
    <div className='input-file-container'>
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