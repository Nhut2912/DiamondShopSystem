import React, { useEffect, useState } from 'react'

import '../../theme/admin/DetailProduct.css'
import InputBox from './InputBox';
import InputSelectBox from './InputSelectBox';
import InputDoubleBox from './InputDoubleBox';
import InputFile from './InputFile';
import { ICONS } from '../../constants/admin/index';
import UpdateMaterial from './UpdateMateria';
import UpdateDiamond from './UpdateDiamond';
import { useLocation, useNavigate } from 'react-router-dom';

import { getDownloadURL, listAll, ref, uploadBytes } from 'firebase/storage';
import { imageStorage } from '../../config/FirebaseConfig';



function DetailProduct() {
 const location = useLocation(); 
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
 const [priceRate,setPriceRate] = useState();
 const [sizeUnitPrice,setSizeUnitPrice] = useState();
 const [productID,setProductID] = useState();


    useEffect(() => {
        const product = location.state;
        console.log(product)
        let imagesProduct = [];   
        product.images.map((item) => {
            const imageRef = ref(imageStorage,item);
            getDownloadURL(imageRef).then((url) => {
                imagesProduct = [...imagesProduct,url]     
                setImageDataProduct(imagesProduct); 
            })
        })
       

        let imageCertificate = [];
        product.diamonds.map((item) => {
            const imageRef = ref(imageStorage,item.image);
            getDownloadURL(imageRef).then((url) => {
                imageCertificate = [...imageCertificate,url]
                setCertificateDiamond(imageCertificate);      
            })
        })
        setProductID(product.id)

        setFileImageProduct(product.images);
        setFileCertificateDiamond(product.diamonds.map((item) => {return item.image}));

        setName(product.name);
        setCode(product.code);
        setCategoryProduct(product.category);
        setProductionCost(product.productionCost);
        setSecondaryDiamondCost(product.secondaryDiamondCost);
        setSecondaryMaterialCost(product.secondaryMaterialCost);
        setProductSize(product.size);


        setPriceRate(product.priceRate)
        setSizeUnitPrice(product.sizeUnitPrice)
     
        setDiamondProducts(product.diamonds);
        setMaterialProducts(product.materials);
        setDiamondCount(product.diamonds.length);
        setMaterialCount(product.materials.length);
     
    },[])



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
        "name" :    null,
        "weight" :  null
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

const handleAddDiamond = () => {
    setDiamondProducts([...diamondProducts,{
        
            "code" :null ,
            "origin" :null ,
            "color" : null,
            "clarity" : null,
            "cut" : null,
            "carat" : null  ,
            "image" : ""
    }])
    setDiamondCount((prevCount) => prevCount + 1);
  };

  const handleDeleteDiamond = (indexToDelete) => {
    setDiamondCount((prevCount) => {
      if (prevCount > 1) {
        const updatedCertificateDiamond = certificateDiamond.filter((_, i) => i !== indexToDelete);
        const updatedImageCertificateDiamond = fileCertificateDiamond.filter((_, i) => i !== indexToDelete);
        const updateDiamondProducts = diamondProducts.filter((_,i) =>  i !== indexToDelete );
        setDiamondProducts(updateDiamondProducts);
        setCertificateDiamond(updatedCertificateDiamond);
        setFileCertificateDiamond(updatedImageCertificateDiamond);
        return prevCount - 1;
      }
      return prevCount; 
    });
  };



    const handleEdit = () => {
        setIsEdit(true);
    }

    const handleDelete = () => {
        setIsEdit(true);
    }
    const handleBreadCump = () => {
        navigate("/admin/overview/products");
    }


    const handleUpdate = () => {


        let imageCount = 1;
        fileImageProduct.forEach(file => {
            if(file && !(typeof  file === "string")){
              const url = `uploads/${code+"_image_" + new Date().getTime() + imageCount}`;
              const imageRef = ref(imageStorage,url);
              uploadBytes(imageRef,file);
              fileImageProduct[imageCount-1] = url;
              imageCount++;
            }
        });
    
        let certificateCount = 1; 

        console.log(fileCertificateDiamond)
        console.log(diamondProducts)

        fileCertificateDiamond.forEach(file => {
          if(file && !(typeof  file === "string")){
              const url = `uploads/${diamondProducts[certificateCount-1].code+"_ceritificate_" +  new Date().getTime() }`;
              const imageRef = ref(imageStorage,url);
              uploadBytes(imageRef,file);
              const diamondInfor = {
                "code" :diamondProducts[certificateCount-1].code ,
                "origin" : diamondProducts[certificateCount-1].origin  ,
                "color" : diamondProducts[certificateCount-1].color ,
                "clarity" : diamondProducts[certificateCount-1].clarity ,
                "cut" : diamondProducts[certificateCount-1].cut ,
                "carat" : diamondProducts[certificateCount-1].carat ,
                "image" : url
              }
    
              diamondProducts[certificateCount-1] = diamondInfor;
              certificateCount++;
          }else{
            const diamondInfor = {
                "code" :diamondProducts[certificateCount-1].code ,
                "origin" : diamondProducts[certificateCount-1].origin  ,
                "color" : diamondProducts[certificateCount-1].color ,
                "clarity" : diamondProducts[certificateCount-1].clarity ,
                "cut" : diamondProducts[certificateCount-1].cut ,
                "carat" : diamondProducts[certificateCount-1].carat ,
                "image" : file
            }
            diamondProducts[certificateCount-1] = diamondInfor;
            certificateCount++;
          }
        })

        let diamonsProduct = [];
        console.log(diamondProducts)
        diamondProducts.map((item) => {
        diamonsProduct = [...diamonsProduct,
            {
                "code": item.code,
                "certificate": item.image,
                "carat": item.carat,
                "cut": {
                    "cut": item.cut
                },
                "origin": {
                    "origin": item.origin
                },
                "color": {
                    "color": item.color
                },
                "clarity": {
                    "clarity": item.clarity
                }
            }
        ]
        })

        let imagesProduct = [];
        fileImageProduct.map((item) => {
            imagesProduct = [...imagesProduct,{
              "url" : item
            }]
        })
        
        let materialsProduct = [];
        materialProducts.map((item) => {
        materialsProduct = [...materialsProduct, {
            "material" : {"name" :item.name},
            "weight" :item.weight
        }]
        })
        const product =
        {
            "id": productID,
            "code":code ,
            "name": name,
            "active": true,
            "priceRate": priceRate,
            "productionCost": productionCost === "" ? 0 :productionCost ,
            "secondaryDiamondCost":secondaryDiamondCost === "" ? 0 : secondaryDiamondCost,
            "secondaryMaterialCost": SecondaryMaterialCost === "" ? 0 : SecondaryMaterialCost,
            "sizeUnitPrice": sizeUnitPrice === "" ? 0 : sizeUnitPrice,
            "size": {
              "size": productSize
            },
            "diamonds": diamonsProduct,
            "images": imagesProduct,
            "category": {
              "name": categoryProduct,
            },
            "productMaterials":materialsProduct,
          }

          const myHeaders = new Headers();
          myHeaders.append("Content-Type", "application/json");
          const requestOptions = {
            method: "POST",
            headers: myHeaders,
            body: JSON.stringify(product),
            redirect: "follow"
          };

          console.log(product);

          fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/product/updateProduct`, requestOptions)
          .then((response) => response.text())
          .then(data =>  {
            if(data !== null){
              window.location.pathname = "/admin/overview/products";
            }
          })
          .catch((error) => console.error(error));


    }

  return (
    <div className='detail-product-container'>
      
        <h1>
            Product
        </h1>
        <p>
          Admin / <span onClick={handleBreadCump} >Products</span> / <span> {code !== undefined
          && code !== null ?
          code : null}</span>
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
                    <div className='production-diamond-material-cost'
                    style={{flexWrap: "wrap",
                        rowGap: "20px"
                    }}
                    
                    >

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
                        {
                            isEdit ? 
                                <InputDoubleBox title={"Secondary Material Cost"}  _width="150px" 
                                setParams={setPriceRate}
                                getParams={priceRate}
                                />
                            : <div>
                                    <label>Price Rate</label>
                                    <h3>{priceRate}</h3>
                                </div>
                            
                        }
                        {
                            isEdit ? 
                                <InputDoubleBox title={"Secondary Material Cost"}  _width="150px" 
                                setParams={setSizeUnitPrice}
                                getParams={sizeUnitPrice}
                                />
                            : <div>
                                    <label>Size Unit Price</label>
                                    <h3>{sizeUnitPrice}</h3>
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
                                            materiaCount !== undefined && materiaCount !== null
                                            && materialProducts !== undefined && materialProducts !== null &&
                                            Array(materiaCount).fill(0).map((_,index) => (

                                                <ul>
                                                    <li>
                                                        {index+1}
                                                    </li>
                                                    <li  >
                                                        {materialProducts[index].name}
                                                    
                                                    </li>
                                                    <li>
                                                        {materialProducts[index].weight}
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
                                            {imageDataProduct !== undefined 
                                            && imageDataProduct !== null && <img src={imageDataProduct[0]} alt='' />}
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
                                        {
                                        imageDataProduct !== undefined 
                                        && imageDataProduct !== null && imageDataProduct[1] && <img src={imageDataProduct[1]} alt='' />}
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
                                            {
                                            imageDataProduct !== undefined 
                                            && imageDataProduct !== null &&
                                            imageDataProduct[2] && <img src={imageDataProduct[2]} alt='' />}
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
                                        {
                                        imageDataProduct !== undefined 
                                        && imageDataProduct !== null  && <img src={imageDataProduct[2]} alt='' />}
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
                                {Array(diamondCount).fill(0).map((item,index) => (
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
                                diamondCount !== undefined && diamondCount !== null && 
                                diamondProducts !== undefined && diamondProducts !== null && 
                                Array(diamondCount).fill(0).map((item,_index) => (
                                    <div>
                                        <span>
                                            No. {_index}
                                        </span>
                                        <div>
                                            <div>
                                                <label>Diamond Code</label>
                                                <h3>{diamondProducts[_index].code}</h3>
                                            </div>
                                            <div>
                                                <div>
                                                    <label>Origin</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[_index].origin}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label>Color</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[_index].color}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label>Clarity</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[_index].clarity}
                                                        </span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <label>Cut</label>
                                                    <div>
                                                        <span>
                                                            {diamondProducts[_index].cut}
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div>
                                                <label>Carat (g)</label>
                                                <h3>{diamondProducts[_index].carat}</h3>
                                            </div>
                                            <div>
                                                <label>Certificate</label>
                                                <div>
                                                    {console.log(certificateDiamond)}
                                                    {   
                                                        certificateDiamond !== undefined && certificateDiamond !== null &&
                                                        certificateDiamond[_index] && <img 
                                                        style={{
                                                            "max-width" : '100%',
                                                            "max-height" : '100%'
                                                        }}
                                                        src={certificateDiamond[_index]} alt='' />
                                                    }
                                                    
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
                    <div    
                        onClick={() => handleUpdate()}
                    className='button-update-product'>
                    <span>Update</span>
                </div> : ""
                }

            </div>
        </div>
    </div>
  )
}

export default DetailProduct