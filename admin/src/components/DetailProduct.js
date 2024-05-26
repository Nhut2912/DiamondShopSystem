import React, { useEffect, useState } from 'react'

import '../theme/DetailProduct.css'
import InputBox from './InputBox';
import InputSelectBox from './InputSelectBox';
import InputDoubleBox from './InputDoubleBox';
import InputFile from './InputFile';
import { ICONS } from '../constants';
import UpdateMaterial from './UpdateMateria';
import UpdateDiamond from './UpdateDiamond';
import { useNavigate } from 'react-router-dom';

import { getDownloadURL, listAll, ref } from 'firebase/storage';
import { imageStorage } from '../FirebaseConfig';

function DetailProduct() {

 const navigate = useNavigate();
 const [isEdit, setIsEdit] = useState(false);
 const [imageDataProduct,setImageDataProduct] = useState();
 const [fileImageProduct,setFileImageProduct] = useState();
 const [certificateDiamond,setCertificateDiamond] = useState();
 const [fileCertificateDiamond,setFileCertificateDiamond] = useState();
 const [name,setName] = useState();
 const [code,setCode] = useState();
 const [categoryProduct,setCategoryProduct] = useState();
 const [productionCost,setProductionCost] = useState();
 const [secondaryDiamondCost,setSecondaryDiamondCost] = useState();
 const [SecondaryMaterialCost,setSecondaryMaterialCost] = useState();
 const [productSize,setProductSize] = useState();
 const [diamondProducts,setDiamondProducts] = useState();
 const [diamondCount, setDiamondCount] = useState();
 const [materialProducts,setMaterialProducts] = useState();
 const [materiaCount, setMaterialCount] = useState();




    useEffect(() => {
        const product = {
            "Name": "Nhẫn Vàng trắng 10K đính đá ECZ PNJ XMXMW000128",
            "Code": "XMXMW000128",
            "Category": "Shake",
            "ProductionCost": "12.4",
            "SecondaryDiamondCost": "12.5",
            "SecondaryMaterialCost": "124.4",
            "Material": [
                {
                    "Type": "14K White Gold",
                    "Weight": "12.4"
                },
                {
                    "Type": "18K White Gold",
                    "Weight": "123.4"
                }
            ],
            "ProductSize": "15",
            "ImagesProduct": [
                "uploads/Nhẫn cưới Kim cương Vàng 18K Disney|PNJ Sleeping Beauty DDDDC001273_image_1",
                "uploads/Nhẫn cưới Kim cương Vàng 18K Disney|PNJ Sleeping Beauty DDDDC001273_image_2",
                "uploads/Nhẫn cưới Kim cương Vàng 18K Disney|PNJ Sleeping Beauty DDDDC001273_image_3"
            ],
            "DiamondsProduct": [
                {   
                    "DiamondCode" :"DDDDW000924",
                    "Origin": "LAB GROWN",
                    "Color": "J",
                    "Clarity": "FL",
                    "Cut": "GOOD",
                    "Carat": "12.3",
                    "Image": "uploads/undefined_ceritificate_1"
                },
                {   
                    "DiamondCode" :"DDDDW000923",
                    "Origin": "LAB GROWN",
                    "Color": "E",
                    "Clarity": "IF",
                    "Cut": "GOOD",
                    "Carat": "112.3",
                    "Image": "uploads/undefined_ceritificate_2"
                }
            ]
        }
   
        //     getDownloadURL(
        //         ref(imageStorage,"uploads/Nhẫn cưới Kim cương Vàng 18K Disney|PNJ Sleeping Beauty DDDDC001273_image_1")
        // ).then(url => {
        //    setImageDataProduct(url);
        // });
      
        
        let imagesProduct = [];
        
        
        product.ImagesProduct.map((item) => {
            const imageRef = ref(imageStorage,item);
            getDownloadURL(imageRef).then((url) => {
                imagesProduct = [...imagesProduct,url];
                setImageDataProduct(imagesProduct);
            })
        })

        setCertificateDiamond(product.DiamondsProduct.map((item) => {return item.Image}));




        setFileImageProduct(product.ImagesProduct);
        setFileCertificateDiamond(product.DiamondsProduct.map((item) => {return item.Image}));
        setName(product.Name);
        setCode(product.Code);
        setCategoryProduct(product.Category);
        setProductionCost(product.ProductionCost);
        setSecondaryDiamondCost(product.SecondaryDiamondCost);
        setSecondaryMaterialCost(product.SecondaryMaterialCost);
        setProductSize(product.ProductSize);
        setDiamondProducts(product.DiamondsProduct);
        setMaterialProducts(product.Material);
        setDiamondCount(product.DiamondsProduct.length);
        setMaterialCount(product.Material.length);

    },[])

if(!imageDataProduct || 
    !fileImageProduct ||
    !certificateDiamond ||
    !fileCertificateDiamond ||
    !name || !code || !categoryProduct || !productionCost ||
    !secondaryDiamondCost || !SecondaryMaterialCost || !productSize
    || !diamondProducts || !diamondCount || !materialProducts || !materiaCount
){
    return <div> Loadinggg...................</div>
}
 

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

const material = [{name : "14K White Gold"},  {name : "18K White Gold"}, {name : "24K Gold"}]







  const handleAddMaterial = () => {
    setMaterialProducts([...materialProducts, {
        "Type" :    null,
        "Weight" :  null
     }])
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
console.log(materialProducts);
const handleAddDiamond = () => {
    setDiamondProducts([...diamondProducts,{
        
            "DiamondCode" :null ,
            "Origin" :null ,
            "Color" : null,
            "Clarity" : null,
            "Cut" : null,
            "Carat" : null  ,
            "Image" : ""
    }])
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

console.log(
    {
        "Name" : name,
        "Code" : code,
        "Category" : categoryProduct,
        "ProductionCost" : productionCost,
        "SecondaryDiamondCost" : secondaryDiamondCost,
        "SecondaryMaterialCost" : SecondaryMaterialCost,
        "Material" : materialProducts,
        "ProductSize" : productSize,
        "ImagesProduct" : imageDataProduct,
        "DiamondsProduct" : diamondProducts
    }
)   

    const handleEdit = () => {
        setIsEdit(true);
    }

    const handleDelete = () => {
        setIsEdit(true);
    }
    const handleBreadCump = () => {
        navigate("/admin/products");
    }

  return (
    <div className='detail-product-container'>
      
        <h1>
            Product
        </h1>
        <p>
          Admin / <span onClick={handleBreadCump} >Products</span> / <span> {code}</span>
        </p>

        <div className='button-back' onClick={handleBreadCump}>
            <img src={ICONS.icon_drop_down} />
           <span>
                Previous
           </span>
        </div>
        <div className='detail-product-content'>

            <div className='detail-prodcuct-edit-delete'>
                    <div className='detail-prodcuct-edit' 
                        onClick={handleEdit}
                    >
                        <span>Edit</span>
                    </div>
                    <div className='detail-prodcuct-delete'
                        onClick={handleDelete}
                    >
                        <span>Delete</span>
                    </div>
            </div>
            <h2>Information Product</h2>
            <div className='detail-product-card'>

                
                <div className='main-information-product'
                
                    style={isEdit ? {"display" : 'flex',
                    "flexDirection" : 'column',
                    "rowGap": "20px" 
                } : {}}
                >
                    <div className='code-name'>

                        {
                            isEdit ? 
                            <InputBox title={"Name"}  
                            getParams={name}
                            setParams={setName} 
                            
                            /> : <div> 
                                <label>Name</label>
                                <h3>{name}</h3>
                            </div>
                        }

                        

                        
                        {
                            isEdit ? 
                            <InputBox title={"Code"}
                            getParams={code}
                            setParams={setCode}
                            /> : <div> 
                                <label>Code</label>
                                <h3>{code}</h3>
                            </div>
                        }

                    
                    </div>
                    <div className='category'>
                        {
                            isEdit ? 
                                <InputSelectBox 
                                    title={"Category"} 
                                    options={category}
                                    setParams={setCategoryProduct}
                                    getParams={categoryProduct}
                                />
                            : <div>
                                    <label>Category</label>
                                    <h3>{categoryProduct}</h3>
                                </div>
                            
                        }
                        
                    </div>
                    <div className='production-diamond-material-cost'>

                    {
                            isEdit ? 
                                <InputDoubleBox title={"Production Cost"} _width="150px" 
                                setParams={setProductionCost}
                                getParams={productionCost}
                                />
                            : <div>
                                    <label>Production Cost</label>
                                    <h3>{productionCost}</h3>
                                </div>
                            
                        }
                        
                        {
                            isEdit ? 
                                <InputDoubleBox title={"Secondary Diamond Cost"}  _width="150px"
                                setParams={setSecondaryDiamondCost}
                                getParams={secondaryDiamondCost}
                                />
                            : <div>
                                    <label>Secondary Diamond Cost</label>
                                    <h3>{secondaryDiamondCost}</h3>
                                </div>
                            
                        }

    {
                            isEdit ? 
                                <InputDoubleBox title={"Secondary Material Cost"}  _width="150px" 
                                setParams={setSecondaryMaterialCost}
                                getParams={SecondaryMaterialCost}
                                />
                            : <div>
                                    <label>Secondary Material Cost</label>
                                    <h3>{SecondaryMaterialCost}</h3>
                                </div>
                            
                        }

                    
                    
                    
                    </div>
                    <div className='product-material'>
                        <label>Material</label>
                        <div className='content-material'>
                                <ul>
                                    <li>
                                        No. 
                                    </li>
                                    <li
                                      
                                    >
                                        Type
                                    </li>
                                    <li>
                                        Weight {"(g)"}
                                    </li>
                                    <li>
                                    Delete
                                    </li>
                                </ul>

                                {   isEdit ? 
                                        (
                                            Array(materiaCount).fill(0).map((_,index) => (

                                                <UpdateMaterial index={index} 
                                                key={index}
                                                handleDeleteMaterial={handleDeleteMaterial} 
                                                materialProducts={materialProducts}
                                                setMaterialProducts={setMaterialProducts}
                                                material={material}
                                                />
                    
                                                ))
                                        )
                                    :
                                        (

                                            Array(materiaCount).fill(0).map((_,index) => (

                                                <ul>
                                                    <li>
                                                        {index+1}
                                                    </li>
                                                    <li  >
                                                        {materialProducts[index].Type}
                                                    
                                                    </li>
                                                    <li>
                                                        {materialProducts[index].Weight}
                                                    </li>
                                                    <li>
                                                        {""}
                                                    </li>
                                                </ul>
                    
                                                ))
                                        )
                                    
                                }
                            
                                {
                                    isEdit ?  <div onClick={handleAddMaterial} className='button-add-material-product'>
                                    <img src={ICONS.icon_add} alt=''/>
                                </div>: ""
                                }
                            
                        </div>
                        
                    </div>
                    <div className='product-size'>


                        {isEdit ? 
                            <InputSelectBox title={"Product Size"} 
                            options={size}
                            getParams={productSize}
                            setParams={setProductSize}/>
                            : <div>
                                    <label>Product Size</label>
                                    <h3>{productSize}</h3>
                            </div>
                        }



                    </div>
                    <div className='image-product'>
                        <label>
                            Images Product
                        </label>
                        <div>
                            {
                                isEdit ? 
                                (
                                    <>
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
                                        fileImage={fileImageProduct}  />
                                    </>
                                ) : <>
                                    
                                        <div>
                                            {imageDataProduct[0] && <img src={imageDataProduct[0]} alt='' />}
                                        </div>
                                        <label>images-1</label>
                                  
                                </>
                            }

                        </div>



                        <div>

                            {
                                isEdit ? 
                                (
                                    <>
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
                                    </>
                                ) : <>
                                    
                                        <div>
                                        {imageDataProduct[1] && <img src={imageDataProduct[1]} alt='' />}
                                        </div>
                                        <label>images-2</label>
                                   
                                </>
                            }

                        </div>
                        <div>

                        {
                                isEdit ? 
                                (
                                    <>
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
                                    </>
                                ) : <>
                                    
                                        <div>
                                        {imageDataProduct[2] && <img src={imageDataProduct[2]} alt='' />}
                                        </div>
                                        <label>images-3</label>
                                 
                                </>
                            }

                            
                        </div>
                    </div>
                </div>
                
                <div className='diamond-information-product'>
                    <h4>Diamonds product</h4>
                    {
                        isEdit ? 
                        <div className='container-input-diamond'>
                                {Array(diamondCount).fill(0).map((_,index) => (
                                <div className='diamond-information-add'>
                                    <span>No.{index+1}</span>
                                    <UpdateDiamond 
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


                            </div> :
                       <div className='container-diamond-no-edit' >
                            {
                                Array(diamondCount).fill(0).map((_,index) => (
                                    <div>
                                        <span>
                                            No. {index}
                                        </span>
                                        <div>
                                            <div>
                                                <label>Diamond Code</label>
                                                <h3>{diamondProducts[index].DiamondCode}</h3>
                                            </div>
                                            <div>
                                                <div>
                                                    <label>Origin</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[index].Origin}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label>Color</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[index].Color}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label>Clarity</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[index].Clarity}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label>Cut</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[index].Cut}
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div>
                                                <label>Carat (g)</label>
                                                <h3>{diamondProducts[index].Carat}</h3>
                                            </div>
                                            <div>
                                                <label>Certificate</label>
                                                <div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                ))
                            }
                                
                        </div>

                    }


                   

                </div>
                
                {
                    isEdit ? 
                    <div className='button-update-product'>
                    <span>Update</span>
                </div> : ""
                }

            </div>
        </div>
    </div>
  )
}

export default DetailProduct