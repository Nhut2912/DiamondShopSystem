import React, { useState } from 'react'

import '../theme/AddProduct.css';
import InputBox from './InputBox';
import InputSelectBox from './InputSelectBox';
import InputFile from './InputFile';
import InputDoubleBox from './InputDoubleBox';
import AddDiamond from './AddDiamond';
import { ICONS } from '../constants';
import AddMaterial from './AddMaterial';


function AddProduct() {

  const [imageDataProduct,setImageDataProduct] = useState([null,null,null]);
  const [certificateDiamond,setCertificateDiamond] = useState([]);
 
  /**
   * 
   * 
   */
  const [name,setName] = useState(null);
  const [code,setCode] = useState(null);
  const [categoryProduct,setCategoryProduct] = useState(null);
  const [productionCost,setProductionCost] = useState(null);
  const [secondaryDiamondCost,setSecondaryDiamondCost] = useState(null);
  const [SecondaryMaterialCost,setSecondaryMaterialCost] = useState(null);
  const [productSize,setProductSize] = useState(null);
  /** */

  const product = {
      "Name" : "",
      "Code" : "",
      "Category" : "",
      "ProductionCost" : "",
      "SecondaryDiamondCost" : "",
      "SecondaryMaterialCost" : "",
      "Material" : [],
      "ProductSize" : "",
      "ImagesProduct" : [null],
      "DiamondsProduct" : [null]
  };

 
  /** Thong tin diamond trong product */
 const [diamondProducts,setDiamondProducts] = useState([]);

 const [diamondCount, setDiamondCount] = useState(1);
 const handleAddDiamond = () => {
    setDiamondCount((prevCount) => prevCount + 1);
  };

  const handleDeleteDiamond = (indexToDelete) => {
    setDiamondCount((prevCount) => {
      if (prevCount > 1) {
        const updatedCertificateDiamond = certificateDiamond.filter((_, i) => i !== indexToDelete);
        const updateDiamondProducts = diamondProducts.filter((_,i) =>  i !== indexToDelete );
        setDiamondProducts(updateDiamondProducts);
        setCertificateDiamond(updatedCertificateDiamond);
        return prevCount - 1;
      }
      return prevCount; 
    });
  };


 /** Thong tin material */
 const [materialProducts,setMaterialProducts] = useState([]);
 
 const [materiaCount, setMaterialCount] = useState(1);
 const handleAddMaterial = () => {
    setMaterialCount((prevCount) => prevCount +1);
 }
 const handleDeleteMaterial = (indexToDelete) => {
  setMaterialCount((prevCount) => {
    if (prevCount > 1) {
      const updateMaterialProducts = materialProducts.filter((_,i) =>  i !== indexToDelete );
      setMaterialProducts(updateMaterialProducts);
      return prevCount - 1;
    }
    return prevCount; 
  });
};


  const category = [
    {name : 'Rings'},{name : 'Earrings'},{name : 'Shake'},{name : 'Pendant'},{name : 'Jewelry Sets'}
  ]
  const origin = [ {value : "NATURAL" },   {value : "LAB GROWN" }  ]

  const color = [{value : "K"},{value : "J"},{value : "I"},{value : "H"},{value : "G"}, {value : "F"},{value : "E"}, {value : "D"}
  ]

  const clarity = [ {value : "SI2"}, {value : "SI1"}, {value : "VS2"}, {value : "VS1"}, {value : "VVS2"}, {value : "VVS1"}, {value : "IF"}, {value : "FL"}
  ]

  const cut = [ {value : "FAIR"},  {value : "GOOD"}, {value : "V.GOOD"}, {value : "EX."},
  ]

  const material = [{name : "14K White Gold"},  {name : "18K White Gold"}, {name : "24K Gold"}]


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
                <InputDoubleBox title={"Production Cost"} _width="150px" />
                <InputDoubleBox title={"Secondary Diamond Cost"}  _width="150px" />
                <InputDoubleBox title={"Material Cost"}  _width="150px" />
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
                            <li>
                              Delete
                            </li>
                        </ul>
                        {Array(materiaCount).fill(0).map((_,index) => (
                           <AddMaterial index={index} 
                           handleDeleteMaterial={handleDeleteMaterial} 
                           materialProducts={materialProducts}
                           setMaterialProducts={setMaterialProducts}
                           material={material}
                           />

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
                        {imageDataProduct[0] && <img src={imageDataProduct[0]} alt='' />}
                    </div>
                    <InputFile title={"images-1"} 
                      _width="150px" 
                      imageData={imageDataProduct}
                      setImageData={setImageDataProduct}
                      index={0}
                      length={imageDataProduct.length}
                      />
                </div>
                <div>
                    <div className='image-card'>
                      {imageDataProduct[1] && <img src={imageDataProduct[1]} alt='' />}
                    </div>
                    <InputFile title={"images-2"} 
                      _width="150px" 
                      imageData={imageDataProduct}
                      setImageData={setImageDataProduct}
                      index={1}
                      length={imageDataProduct.length}
                      />
                </div>
                <div>
                    <div className='image-card'>
                      {imageDataProduct[1] && <img src={imageDataProduct[2]} alt='' />}
                    </div>
                    <InputFile title={"images-3"} 
                      _width="150px" 
                      imageData={imageDataProduct}
                      setImageData={setImageDataProduct}
                      index={2}
                      length={imageDataProduct.length}
                      />
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
                        imageData ={certificateDiamond}
                        index={index}
                        setImageData={setCertificateDiamond}
                        length={certificateDiamond.length} 
                        setDiamondProducts={setDiamondProducts}
                        diamondProducts={diamondProducts}
                     />
                     {
                      index >=  1 ? 
                      <div className='delete-diamond-in-product' onClick={() => handleDeleteDiamond(index)} >
                          <img src={ICONS.icon_delete} alt=''/>
                     </div> : ''
                     }
                     
                 </div>
                ))}
             
                <div onClick={handleAddDiamond} className='add-diamond-in-product'>
                    <img src={ICONS.icon_add} alt=''/>
                </div>
            </div>
        </div>




      <div className='submit-button-add-product' 
      
      >
        <span>
          Add Product
        </span>
      </div>
    </div>
  )
}

export default AddProduct;