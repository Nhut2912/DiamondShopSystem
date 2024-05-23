import React, { useCallback, useEffect, useMemo, useState } from 'react'
import InputBox from './InputBox'
import RadioInput from './RadioInput'
import InputFile from './InputFile'
import InputDoubleBox from './InputDoubleBox'
import '../theme/AddDiamond.css';





function AddDiamond({origin, color, clarity,cut ,imageData,index,setImageData,length,diamondProducts,setDiamondProducts}) { 
  
        
  const [code,setCode] = useState(null);
  const [originDiamond,setOriginDiamond] = useState(null);
  const [colorDiamond,setColorDiamond] = useState(null);
  const [clarityDiamond,setClarityDiamond] =useState(null);
  const [cutDiamond,setCutDiamond] = useState(null);
  const [caratDiamond,setCaratDiamond] =useState(null);
  const diamond = useMemo( () => ({
   "Diamond Code" :code ,
   "Origin" : originDiamond === null ? origin[0].value : originDiamond ,
   "Color" : colorDiamond === null ? color[0].value : colorDiamond,
   "Clarity" : clarityDiamond === null ? clarity[0].value : clarityDiamond,
   "Cut" : cutDiamond === null ? cut[0].value : cutDiamond,
   "Carat" : caratDiamond  ,
   "Image" : ""}
  ), [code,originDiamond,colorDiamond,clarityDiamond,cutDiamond,caratDiamond]) 
  
 const updatedDiamondProucts = useMemo(() => {
     const _updateDiamondProducts = [...diamondProducts];
     _updateDiamondProducts[index] = diamond;
     return _updateDiamondProducts;
 },[diamond]);

 setDiamondProducts(updatedDiamondProucts);

  return (
    <div className='add-diamond-container' style={{display: 'grid', rowGap: '20px'}}>
                <div className='diamond-code'>
                    <InputBox title={"Diamond Code"} setParams={setCode} />
                </div>
                <div className='Origin'>
                        <RadioInput title={'Origin'} items={origin} _width={"150px"} setParams={setOriginDiamond} />
                </div>
                <div className='Color'>
                        <RadioInput title={"Color"}  items={color} _width={"100px"} setParams={setColorDiamond} />
                </div>
                <div className='Clarity'>
                        <RadioInput title={"Clarity"} items={clarity} _width={"100px"}  setParams={setClarityDiamond}/>
                </div>
                <div className='Cut'>
                        <RadioInput title={"Cut"} items={cut}  _width={"100px"} setParams={setCutDiamond} />
                </div>
                <div className='Carat'>
                    <InputDoubleBox title={"Carat (g)"}  _width="100px" setParams={setCaratDiamond} />
                </div>
                <div className='Certificate'>
                   <div className='image-certificate' 
        
                   >
                        {imageData[index] && <img src={imageData[index]} alt='' />}
                   </div>
                    <InputFile 
                        title={"Certificate"} 
                        _width={"200px"} 
                        imageData ={imageData}
                        index={index}
                        setImageData={setImageData}
                        length={length} 
                        />
                </div>
    </div>
  )
}

export default React.memo(AddDiamond);