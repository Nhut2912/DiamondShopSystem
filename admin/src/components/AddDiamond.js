import React, { useEffect } from 'react'
import InputBox from './InputBox'
import RadioInput from './RadioInput'
import InputFile from './InputFile'
import InputDoubleBox from './InputDoubleBox'


function AddDiamond({origin, color, clarity,cut }) {

        // useEffect(() => {
        //         if (selectedFile) {
        //           const reader = new FileReader();
        //           reader.onload = (event) => {
        //                 setImageData(event.target.result);
        //           };
        //           reader.readAsDataURL(selectedFile);
        //         }
        //       }, [selectedFile]);
  
  return (
    <div  style={{display: 'grid', rowGap: '20px'}}>
        <div className='diamond-code'>
                    <InputBox title={"Diamond Code"} />
                </div>
                <div className='Origin'>
                        <RadioInput title={'Origin'} items={origin} _width={"150px"} />
                </div>
                <div className='Color'>
                        <RadioInput title={"Color"}  items={color} _width={"100px"} />
                </div>
                <div className='Clarity'>
                        <RadioInput title={"Clarity"} items={clarity} _width={"100px"} />
                </div>
                <div className='Cut'>
                        <RadioInput title={"Cut"} items={cut}  _width={"100px"} />
                </div>
                <div className='Carat'>
                    <InputDoubleBox title={"Carat (g)"} />
                </div>
                <div className='Certificate'>
                   <div className='image-certificate'>
                        {/* {imageData && <img src={imageData} alt='' />} */}
                   </div>
                    <InputFile 
                        title={"Certificate"} 
                        _width={"200px"} 
                       
                        />
                </div>
    </div>
  )
}

export default AddDiamond