import React, { useEffect, useState } from 'react'

import '../theme/AddProduct.css';
import InputBox from './InputBox';
import InputSelectBox from './InputSelectBox';
import InputFile from './InputFile';
import InputDoubleBox from './InputDoubleBox';
import AddDiamond from './AddDiamond';
import { ICONS } from '../constants';


function AddProduct() {

  /*
    
  */ 
 

    const [imageProduct_1, setImageProduct_1] = useState(null);
    const [imageProduct_2, setImageProduct_2] = useState(null);
    const [imageProduct_3, setImageProduct_3] = useState(null);
    const [imageData_1,setImageData_1] = useState(null);
    const [imageData_2,setImageData_2] = useState(null);
    const [imageData_3,setImageData_3] = useState(null);

  /*
    
   */

 const [diamondCount, setDiamondCount] = useState(1);

 const handleAddDiamond = () => {
    setDiamondCount((prevCount) => prevCount + 1);
 
  };

 const [materiaCount, setMaterialCount] = useState(1);
 const handleAddMaterial = () => {
    setMaterialCount((prevCount) => prevCount +1);
 }

  const category = [
    {name : 'Rings'},
    {name : 'Earrings'},
    {name : 'Shake'},
    {name : 'Pendant'},
    {name : 'Jewelry Sets'}
  ]


  const origin = [
    {value : "NATURAL" },
    {value : "LAB GROWN" }
  ]

  const color = [
    {value : "K"},
    {value : "J"},
    {value : "I"},
    {value : "H"},
    {value : "G"},
    {value : "F"},
    {value : "E"},
    {value : "D"}
  ]

  const clarity = [
    {value : "SI2"},
    {value : "SI1"},
    {value : "VS2"},
    {value : "VS1"},
    {value : "VVS2"},
    {value : "VVS1"},
    {value : "IF"},
    {value : "FL"}
  ]

  const cut = [
    {value : "FAIR"},
    {value : "GOOD"},
    {value : "V.GOOD"},
    {value : "EX."},
  ]


  useEffect(() => {
    if (imageProduct_1) {
      const reader = new FileReader();
      reader.onload = (event) => {
        setImageData_1(event.target.result);
      };
      reader.readAsDataURL(imageProduct_1);
    }
  }, [imageProduct_1]);

  
  
  useEffect(() => {
    if (imageProduct_2) {
      const reader = new FileReader();
      reader.onload = (event) => {
        setImageData_2(event.target.result);
      };
      reader.readAsDataURL(imageProduct_2);
    }
  }, [imageProduct_2]);
  
  useEffect(() => {
    if (imageProduct_3) {
      const reader = new FileReader();
      reader.onload = (event) => {
        setImageData_3(event.target.result);
      };
      reader.readAsDataURL(imageProduct_3);
    }
  }, [imageProduct_3]);

  const imageData = {
    'image' : imageData_1
    
  }
 console.log(imageData);

  return (
    <div className='add-product-container'>
     
        <div className='main-information-product'>
            <div className='code-name'>
                <InputBox title={"Name"} />
                <InputBox title={"Code"} />
            </div>
            <div className='category'>
                <InputSelectBox 
                    title={"Category"} 
                    options={category}
                />
            </div>
            <div className='production-diamond-material-cost'>
                <InputDoubleBox title={"Production Cost"} />
                <InputDoubleBox title={"Secondary Diamond Cost"} />
                <InputDoubleBox title={"Material Cost"} />
            </div>
            <div className='product-material'>
                <label>Material</label>
                <div className='content-material'>
                        <ul>
                            <li>
                                No. 
                            </li>
                            <li>
                                Type
                            </li>
                            <li>
                                Weight {"(g)"}
                            </li>
                        </ul>
                        {Array(materiaCount).fill(0).map((_,index) => (
                            <ul>
                                <li>
                                    {index+1}
                                </li>
                                <li>
                                    <InputSelectBox />
                                </li>
                                <li>
                                    <InputDoubleBox />
                                </li>
                            </ul>

                        ))}
                        
                        <div onClick={handleAddMaterial} className='button-add-material-product'>
                              <img src={ICONS.icon_add} alt=''/>
                        </div>
                </div>
                
            </div>
            <div className='product-size'>
                <InputSelectBox title={"Product Size"} />
            </div>
            <div className='image-product'>
                <label>
                    Images Product
                </label>
                <div>
                    <div className='image-card'>
                        {imageData_1 && <img src={imageData_1} alt='' />}
                    </div>
                    <InputFile title={"images-1"} _width="150px" selectedFile={setImageProduct_1} />
                </div>
                <div>
                    <div className='image-card'>
                        {imageData_2 && <img src={imageData_2} alt='' />}
                    </div>
                    <InputFile title={"images-2"} _width="150px"  selectedFile={setImageProduct_2}  />
                </div>
                <div>
                    <div className='image-card'>
                        {imageData_3 && <img src={imageData_3} alt='' />}
                    </div>
                    <InputFile title={"images-3"} _width="150px"  selectedFile={setImageProduct_3}  />
                </div>
            </div>
        </div>
        <div className='diamond-information-product'>
            <h4>Diamonds product</h4>
            <div className='container-input-diamond'>
                {Array(diamondCount).fill(0).map((_,index) => (
                     <div className='diamond-information-add'>
                     <span>No.{index+1}</span>
                     <AddDiamond 
                        color={color}
                        clarity={clarity} 
                        cut={cut} 
                        origin={origin}
                       
                     />
                 </div>
                ))}
             
                <div onClick={handleAddDiamond} className='add-diamond-in-product'>
                    <img src={ICONS.icon_add} alt=''/>
                </div>
            </div>
        </div>
    </div>
  )
}

export default AddProduct