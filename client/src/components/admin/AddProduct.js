import React, { useState } from 'react'

import '../../theme/admin/AddProduct.css';
import InputBox from './InputBox';
import InputSelectBox from './InputSelectBox';
import InputFile from './InputFile';
import InputDoubleBox from './InputDoubleBox';
import AddDiamond from './AddDiamond';
import { ICONS } from '../../constants/admin/index';
import AddMaterial from './AddMaterial';

import { imageStorage } from '../../config/FirebaseConfig';
import { ref, uploadBytes } from 'firebase/storage';


function AddProduct() {

  const [imageDataProduct,setImageDataProduct] = useState([null,null,null]);
  
  const [fileImageProduct,setFileImageProduct] = useState([null,null,null]);

  const [certificateDiamond,setCertificateDiamond] = useState([]);

  const [fileCertificateDiamond,setFileCertificateDiamond] = useState([]);

  const [name,setName] = useState(null);
  const [code,setCode] = useState(null);
  const [categoryProduct,setCategoryProduct] = useState(null);
  const [productionCost,setProductionCost] = useState(null);
  const [secondaryDiamondCost,setSecondaryDiamondCost] = useState(null);
  const [SecondaryMaterialCost,setSecondaryMaterialCost] = useState(null);
  const [productSize,setProductSize] = useState(null);
  const [priceRate,setPriceRate] = useState(null);
  const [sizeUnitPrice,setSizeUnitPrice] = useState(null);
  /** */

  

 
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

  const size =[{name : '6'},{name : '7'},{name : '8'},
  {name : '9'},{name : '10'},{name : '11'},
  {name : '12'},{name : '13'},{name : '14'},
  {name : '15'},{name : '16'},{name : '17'},
  {name : '18'},{name : '19'},{name : '20'},
  {name : '21'},{name : '22'},{name : '23'},
  ];

  const category = [
    {name : 'Ring'},{name : 'Earring'},{name : 'Shake'},{name : 'Pendant'},{name : 'Jewelry Set'}
  ]
  const origin = [ {value : "NATURAL" },   {value : "LAB GROWN" }  ]

  const color = [{value : "K"},{value : "J"},{value : "I"},{value : "H"},{value : "G"}, {value : "F"},{value : "E"}, {value : "D"}
  ]

  const clarity = [ {value : "SI2"}, {value : "SI1"}, {value : "VS2"}, {value : "VS1"}, {value : "VVS2"}, {value : "VVS1"}, {value : "IF"}, {value : "FL"}
  ]

  const cut = [ {value : "FAIR"},  {value : "GOOD"}, {value : "V.GOOD"}, {value : "EX."},
  ]

  const material = [{name : "14K White Gold"}, 
   {name : "18K White Gold"},
   {name : "18K Yellow Gold"}, {name : "18K Rose Gold"},
   {name : "24K Gold"}]






 const [data,setData] = useState("");

  const handleAddProduct = () =>{
    let imageCount = 1;
    fileImageProduct.forEach(file => {
        if(file){
          const url = `uploads/${code+"_image_" + imageCount }`;
          const imageRef = ref(imageStorage,url);
          uploadBytes(imageRef,file);
          fileImageProduct[imageCount-1] = url;
          imageCount++;
        }
    });

    let certificateCount = 1; 
    fileCertificateDiamond.forEach(file => {
      if(file){
          const url = `uploads/${diamondProducts[certificateCount-1].DiamondCode+"_ceritificate_" + certificateCount }`;
          const imageRef = ref(imageStorage,url);
          uploadBytes(imageRef,file);
          
          const diamondInfor = {
            DiamondCode :diamondProducts[certificateCount-1].DiamondCode ,
            Origin : diamondProducts[certificateCount-1].Origin  ,
            Color : diamondProducts[certificateCount-1].Color ,
            Clarity : diamondProducts[certificateCount-1].Clarity ,
            Cut : diamondProducts[certificateCount-1].Cut ,
            Carat : diamondProducts[certificateCount-1].Carat ,
            Image : url
          }

          diamondProducts[certificateCount-1] = diamondInfor;
          certificateCount++;
      }
    })

    let diamonsProduct = [];
    diamondProducts.map((item) => {
      diamonsProduct = [...diamonsProduct,
        {
            "code": item.DiamondCode,
            "certificate": item.Image,
            "carat": item.Carat,
            "cut": {
                "cut": item.Cut
            },
            "origin": {
                "origin": item.Origin
            },
            "color": {
                "color": item.Color
            },
            "clarity": {
                "clarity": item.Clarity
            }
        }
      ]
    })

    console.log(diamonsProduct);

    let imagesProduct = [];
    fileImageProduct.map((item) => {
        imagesProduct = [...imagesProduct,{
          "url" : item
        }]
    })

    let materialsProduct = [];
    materialProducts.map((item) => {
      materialsProduct = [...materialsProduct, {
        "material" : {"name" :item.Type},
        "weight" :item.Weight
      }]
    })

      const raw = JSON.stringify(
        {
          "name": name,
          "code": code,
          "secondaryDiamondCost": secondaryDiamondCost,
          "secondaryMaterialCost": SecondaryMaterialCost,
          "productionCost": productionCost,
          "priceRate": priceRate,
          "category": {
            "name": categoryProduct
          },
          "size": {
            "size": productSize
          },
          "images":imagesProduct,
          "sizeUnitPrice": sizeUnitPrice,
          "productMaterials" :materialsProduct,
          "diamonds" :diamonsProduct
        }
      );

      console.log(raw);

      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
      };
      fetch("http://localhost:8080/api/product/save", requestOptions)
      .then((response) => response.text())
      .then(data =>  {
        if(data !== null){
          window.location.href = window.location.href;
        }
      })
      .catch((error) => console.error(error));

      console.log(data);

  }






  return (
    <div className='add-product-container'>
     
        <div className='main-information-product'>
            <div className='code-name'>
                <InputBox title={"Name"}  setParams={setName}  />
                <InputBox title={"Code"} setParams={setCode}  />
            </div>
            <div className='category'>
                <InputSelectBox 
                    title={"Category"} 
                    options={category}
                    setParams={setCategoryProduct}
                  
                />
            </div>
            <div className='production-diamond-material-cost'>
                <InputDoubleBox title={"Production Cost"} _width="150px" 
                  setParams={setProductionCost}
                  
                />
                <InputDoubleBox title={"Secondary Diamond Cost"}  _width="200px"
                setParams={setSecondaryDiamondCost}
               
                />
                <InputDoubleBox title={" Secondary Material Cost"}  _width="200px" 
                  setParams={setSecondaryMaterialCost}
                 
                />
                <InputDoubleBox title={"Price Rate (%)"}  _width="170px" 
                  setParams={setPriceRate}
                 
                />
                <InputDoubleBox title={"Size Unit Price"}  _width="150px" 
                  setParams={setSizeUnitPrice}
                 
                />
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
                           key={index}
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
                <InputSelectBox title={"Product Size"} 
                options={size}
                setParams={setProductSize}/>
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
                      setFileImage={setFileImageProduct}
                      fileImage={fileImageProduct}
                      
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
                      setFileImage={setFileImageProduct}
                      fileImage={fileImageProduct}
                   
                      />
                </div>
                <div>
                    <div className='image-card'>
                      {imageDataProduct[2] && <img src={imageDataProduct[2]} alt='' />}
                    </div>
                    <InputFile title={"images-3"} 
                      _width="150px" 
                      imageData={imageDataProduct}
                      setImageData={setImageDataProduct}
                      index={2}
                      length={imageDataProduct.length}
                      setFileImage={setFileImageProduct}
                      fileImage={fileImageProduct}
                      
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
                     key={index}
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
                        setFileImage={setFileCertificateDiamond}
                        fileImage={fileCertificateDiamond}
                      
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
        onClick={handleAddProduct}
      >
        <span>
          Add Product
        </span>
      </div>
    </div>
  )
}

export default AddProduct;