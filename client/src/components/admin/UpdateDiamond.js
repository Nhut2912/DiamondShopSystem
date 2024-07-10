import React, {  useEffect, useMemo, useState } from 'react'
import InputBox from './InputBox'
import RadioInput from './RadioInput'
import InputFile from './InputFile'
import InputDoubleBox from './InputDoubleBox'
import '../../theme/admin/AddDiamond.css';





function UpdateDiamond({origin, color, clarity,cut ,imageData,index,setImageData,length,diamondProducts,setDiamondProducts,fileImage,setFileImage}) { 
  
  console.log(diamondProducts)

  const [code,setCode] = useState(diamondProducts[index].code !== null ? 
    diamondProducts[index].code  : null );
  const [originDiamond,setOriginDiamond] = useState(diamondProducts[index].orgin !== null ? 
    diamondProducts[index].orgin  : null);
  const [colorDiamond,setColorDiamond] = useState(diamondProducts[index].color !== null ? 
    diamondProducts[index].color  : null);
  const [clarityDiamond,setClarityDiamond] =useState(diamondProducts[index].clarity !== null ? 
    diamondProducts[index].clarity  : null);
  const [cutDiamond,setCutDiamond] = useState(diamondProducts[index].cut !== null ? 
    diamondProducts[index].cut  : null);
  const [caratDiamond,setCaratDiamond] =useState(diamondProducts[index].carat !== null ? 
    diamondProducts[index].carat  : null);

  const diamond = useMemo( () => ({
   "code" :code ,
   "origin" : originDiamond === null ? origin[0].value : originDiamond ,
   "color" : colorDiamond === null ? color[0].value : colorDiamond,
   "clarity" : clarityDiamond === null ? clarity[0].value : clarityDiamond,
   "cut" : cutDiamond === null ? cut[0].value : cutDiamond,
   "carat" : caratDiamond  ,
   "image" : ""}
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
                    <InputBox title={"Diamond Code"} setParams={setCode} getParams={diamondProducts[index].code} />
                </div>
                <div className='Origin'>
                        <RadioInput title={'Origin'} items={origin} _width={"150px"} 
                            getParams={diamondProducts[index].origin}
                        setParams={setOriginDiamond} />
                </div>
                <div className='Color'>
                        <RadioInput title={"Color"}  items={color} _width={"100px"} 
                             getParams={diamondProducts[index].color}
                        setParams={setColorDiamond} />
                </div>
                <div className='Clarity'>
                        <RadioInput title={"Clarity"} items={clarity} _width={"100px"} 
                            getParams={diamondProducts[index].clarity}
                        setParams={setClarityDiamond}/>
                </div>
                <div className='Cut'>
                        <RadioInput title={"Cut"} items={cut}  _width={"100px"}
                             getParams={diamondProducts[index].cut}
                        setParams={setCutDiamond} />
                </div>
                <div className='Carat'>
                  {console.log(diamondProducts[index].carat)}
                    <InputDoubleBox title={"Carat (g)"}  _width="100px" 
                        getParams={diamondProducts[index].carat}
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