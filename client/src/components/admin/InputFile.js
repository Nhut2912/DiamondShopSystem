import React from 'react'

import '../../theme/admin/InputFile.css';

function InputFile({title , _width, imageData, index,setImageData, length ,setFileImage,fileImage}) {
 const loadFileHandle = (event) => {
    const file = event;
    if(file){
      
      const reader = new FileReader();
      reader.onload = event => {
         const dataURL = event.target.result;
         if(index < length){
            const updateImageData = [...imageData];
            updateImageData[index] = dataURL;
            const updateFileImage = [...fileImage];
            updateFileImage[index] = file;
            setFileImage(updateFileImage);
            setImageData(updateImageData);
         }else{
            setImageData([...imageData,dataURL]);
            setFileImage([...fileImage,file]);
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