import React, {  useEffect, useMemo, useState } from 'react'
import InputBox from './InputBox'
import RadioInput from './RadioInput'
import InputFile from './InputFile'
import InputDoubleBox from './InputDoubleBox'
import '../../theme/admin/AddDiamond.css';





function UpdateDiamond({origin, color, clarity,cut ,imageData,index,setImageData,length,diamondProducts,setDiamondProducts,fileImage,setFileImage}) { 
  
        
  const [code,setCode] = useState(diamondProducts[index].DiamondCode !== null ? 
    diamondProducts[index].DiamondCode  : null );
  const [originDiamond,setOriginDiamond] = useState(diamondProducts[index].Origin !== null ? 
    diamondProducts[index].Origin  : null);
  const [colorDiamond,setColorDiamond] = useState(diamondProducts[index].Color !== null ? 
    diamondProducts[index].Color  : null);
  const [clarityDiamond,setClarityDiamond] =useState(diamondProducts[index].Clarity !== null ? 
    diamondProducts[index].Clarity  : null);
  const [cutDiamond,setCutDiamond] = useState(diamondProducts[index].Cut !== null ? 
    diamondProducts[index].Cut  : null);
  const [caratDiamond,setCaratDiamond] =useState(diamondProducts[index].Carat !== null ? 
    diamondProducts[index].Carat  : null);

  const diamond = useMemo( () => ({
   "DiamondCode" :code ,
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
 },[diamond,diamondProducts]);

 useEffect(() => {
  setDiamondProducts(updatedDiamondProucts);
}, [code,originDiamond,colorDiamond,clarityDiamond,cutDiamond,caratDiamond]);


 

  return (
    <div className='add-diamond-container' style={{display: 'grid', rowGap: '20px'}}>
                <div className='diamond-code'>
                    <InputBox title={"Diamond Code"} setParams={setCode} getParams={diamondProducts[index].DiamondCode} />
                </div>
                <div className='Origin'>
                        <RadioInput title={'Origin'} items={origin} _width={"150px"} 
                            getParams={diamondProducts[index].Origin}
                        setParams={setOriginDiamond} />
                </div>
                <div className='Color'>
                        <RadioInput title={"Color"}  items={color} _width={"100px"} 
                             getParams={diamondProducts[index].Color}
                        setParams={setColorDiamond} />
                </div>
                <div className='Clarity'>
                        <RadioInput title={"Clarity"} items={clarity} _width={"100px"} 
                            getParams={diamondProducts[index].Clarity}
                        setParams={setClarityDiamond}/>
                </div>
                <div className='Cut'>
                        <RadioInput title={"Cut"} items={cut}  _width={"100px"}
                             getParams={diamondProducts[index].Cut}
                        setParams={setCutDiamond} />
                </div>
                <div className='Carat'>
                    <InputDoubleBox title={"Carat (g)"}  _width="100px" 
                        getParams={diamondProducts[index].Carat}
                    setParams={setCaratDiamond} />
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
                        fileImage={fileImage}
                        setFileImage={setFileImage}
                        />
                </div>
    </div>
  )
}

export default React.memo(UpdateDiamond);