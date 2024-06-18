import React, { useEffect, useRef, useState } from 'react'

import '../../theme/customer/Product.css'
import ProductCard from '../../components/customer/ProductCard'
import InputSelectBox from '../../components/customer/InputSelectBox'
import { ICONS, IMAGES } from '../../constants/customer'

function Product() {


  const [data,setData] = useState();
  const [categoryActive,setCategoryActive] = useState([]);
  const [index,setIndex] = useState(0);
  const [numberProduct,setNumberProduct] = useState();
  const [materialActive,setMaterialActive] = useState([]);


  const [productPresent,setProductPresent] = useState();

  const [left,setLeft] = useState(0);
  const [right,setRight] = useState(0);
  const [minPrice,setMinPrice] =  useState(0);
  const [maxPrice,setMaxPrice] = useState(100000);

  const [minChangePrice,setMinChangePrice] =  useState(0);
  const [maxChangePrice,setMaxChangePrice] = useState(100000);


  const [originFilter,setOriginFilter] = useState([]);
  const [colorFilter,setColorFilter] = useState([]);
  const [clarityFilter,setClarityFilter] = useState([]);
  const [cutFilter,setCutFilter] = useState([]);

 


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

  const allProductRef = useRef(null);
  
  const handleScroller = () => {
    if (allProductRef.current) {
      allProductRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  }



  useEffect(() => {
        fetch('http://localhost:8080/api/product/getProducts')
          .then(response => response.json())
          .then(data => setData(data))
          .catch((error) => console.error(error));
  },[])

  useEffect(() => {
    if(data !== undefined && data !== null){
      setNumberProduct(
        data.filter((item) =>{  
        if(categoryActive.length === 0){
            return item;
        }else{
          let isActive = false;
          categoryActive.map((category) => {
              if(category === item.category){
                isActive = true;
              }
          })
          if(isActive) return item;
        }
      }).length
    )
    }
 
  },[categoryActive,data])


  useEffect(() => {
    if(data !== undefined && data !== null){
      const products =   data.filter((item) =>{  
        if(categoryActive.length === 0){
            return item;
        }else{
          let isActive = false;
          categoryActive.map((category) => {
              if(category === item.category){
                isActive = true;
              }
          })
          if(isActive) return item;
        }
      }).filter((item) => {
          if(item.price >= minChangePrice && item.price <= maxChangePrice ){
            return item;
          }

      }).filter((item) => {
      
          if(materialActive.length === 0){
            return item;
          }else{
            let isActive = false;
            materialActive.map((material) => {
                item.materials.map((materialProduct) => {
                  if(material === materialProduct.name){
                    isActive = true;
                  }
                })
            })
            if(isActive) return item;
          }
      }).filter((item) => {

        if(originFilter.length === 0){
          return item;
        }else{
          let isActive = false;
          originFilter.map((origin) => {
              item.diamonds.map((originProduct) => {
                if(origin === originProduct.origin){
                  isActive = true;
                }
              })
          })
          if(isActive) return item;

        }
      }).filter((item) => {
        if(colorFilter.length === 0){
          return item;
        }else{
          let isActive = false;
          colorFilter.map((color) => {
              item.diamonds.map((colorProduct) => {
                if(color === colorProduct.color){
                  isActive = true;
                }
              })
          })
          if(isActive) return item;

        }
      }).filter((item) => {
        if(clarityFilter.length === 0){
          return item;
        }else{
          let isActive = false;
          clarityFilter.map((clarity) => {
              item.diamonds.map((clarityProduct) => {
                if(clarity === clarityProduct.clarity){
                  isActive = true;
                }
              })
          })
          if(isActive) return item;

        }
      }).filter((item) => {
        if(cutFilter.length === 0){
          return item;
        }else{
          let isActive = false;
          cutFilter.map((cut) => {
              item.diamonds.map((cutProduct) => {
                if(cut === cutProduct.cut){
                  isActive = true;
                }
              })
          })
          if(isActive) return item;

        }
      })
      
      
      
      setNumberProduct(products.length);
      setProductPresent(products);
    }
  },[categoryActive,minChangePrice,maxChangePrice,data,
    materialActive,originFilter,colorFilter,clarityFilter,cutFilter])



  if(data === undefined || data === null) return <div>Loading</div>

  const itemsSorted = [
    {name : "Default Sorted"},
    {name : "Ascending Price"},
    {name : "Descending Price"},
    {name : "Newest products"}
  ]

  const CATEGORY = ["Ring","Earring","Pendant","Shake","Jewelry Set"];
  





  const handleFillterCategory = (value) => {
    let isValid = false;
    categoryActive.map((item) => {
        if(item === value) {
            isValid = true;
        }
    })
    if(isValid){
      let categoryActiveUpdate = categoryActive.filter((item) => 
        item !== value
      )
      setCategoryActive(categoryActiveUpdate);
    }else{
      let categoryActiveUpdate = [...categoryActive,value];
    setCategoryActive(categoryActiveUpdate);
    }
  }

  const handleFillterMaterial = (value) => {
    console.log(value);
    let isValid = false;
    materialActive.map((item) => {
        if(item === value) {
            isValid = true;
        }
    })
  
    if(isValid){
      let materialActiveUpdate = materialActive.filter((item) => 
        item !== value
      )
      setMaterialActive(materialActiveUpdate);
    }else{
      let materialActiveUpdate = [...materialActive,value];
      setMaterialActive(materialActiveUpdate);
    }
  }


  
  const handleFillterOrigin = (value) => {
    let isValid = false;
    originFilter.map((item) => {
        if(item === value) {
            isValid = true;
        }
    })
    if(isValid){
      let originActiveUpdate = originFilter.filter((item) => 
        item !== value
      )
      setOriginFilter(originActiveUpdate);
    }else{
      let originActiveUpdate = [...originFilter,value];
      setOriginFilter(originActiveUpdate);
    }
  }

  
  const handleFillterColor = (value) => {
    let isValid = false;
    colorFilter.map((item) => {
        if(item === value) {
            isValid = true;
        }
    })
    if(isValid){
      let colorActiveUpdate = colorFilter.filter((item) => 
        item !== value
      )
      setColorFilter(colorActiveUpdate);
    }else{
      let colorActiveUpdate = [...colorFilter,value];
      setColorFilter(colorActiveUpdate);
    }
  }


    
  const handleFillterClarity = (value) => {
    let isValid = false;
    clarityFilter.map((item) => {
        if(item === value) {
            isValid = true;
        }
    })
    if(isValid){
      let clarityActiveUpdate = clarityFilter.filter((item) => 
        item !== value
      )
      setClarityFilter(clarityActiveUpdate);
    }else{
      let clarityActiveUpdate = [...clarityFilter,value];
      setClarityFilter(clarityActiveUpdate);
    }
  }

  const handleFillterCut = (value) => {
    let isValid = false;
    cutFilter.map((item) => {
        if(item === value) {
            isValid = true;
        }
    })
    if(isValid){
      let cutActiveUpdate = cutFilter.filter((item) => 
        item !== value
      )
      setCutFilter(cutActiveUpdate);
    }else{
      let cutActiveUpdate = [...cutFilter,value];
      setCutFilter(cutActiveUpdate);
    }
  }




  const handleMinValuePrice = (event) => {
    const value = event.target.value;

  

    if(value <= maxPrice-1000 && value >= 0){
      setLeft((value/100000)*100);
      setMinPrice(value);
    }
  }
  const handleMaxValuePrive =(event) => {
    const value = event.target.value;


    if(value >= minPrice+1000 && value <= 100000){
      setRight(100-(value/100000)*100)
      setMaxPrice(value);
    }
  }


  const handleInputMinValuePrice = (event) => {
   const value = event.target.value;
 
   console.log(value);
   if(value <= maxPrice-1000 && value >= 0){
    setLeft((value/100000)*100);
    setMinPrice(value);
  }
  }


  const handleInputMaxValuePrice = (event) => {
    const value = event.target.value;
    if(value >= minPrice+1000 && value <= 100000 ){
      setRight(100-(value/100000)*100)
      setMaxPrice(value);
    }
   }




   const handleFilterPrice = () => {
      setMinChangePrice(minPrice);
      setMaxChangePrice(maxPrice);
   }
   
  



  return (
    <div className='product-page'>
            <div className='product-page-container'>
            <p>
              Home / <span>Product</span>
            </p>
            <div className='on-sale'>
                <h3>
                  On sale
                </h3>
                <div className='slider'>
                   <div className='slider-container' id="slider-container">
                    {
                            data.map((product) => (
                              <ProductCard 
                              name={product.name}
                              images={product.images}
                              id={product.id}
                              price={product.price}
                              promotions={product.promotions}
                              />
                            ))
                          }
                   </div>
                
                </div>
                
            </div>
            <div className='all-product' ref={allProductRef}>
                  <div className='search-product'>
                      <input type='text' placeholder='Search' />
                  </div>
                  <div className='sorted-products'>
                      <p>Showing 12 of 90 result</p>
                      <div className='input-select'>
                          <InputSelectBox options={itemsSorted} />
                      </div>
                  </div>
                  <div className='category'>
                      <h3>Categories</h3>
                      <ul>
                        {
                          CATEGORY.map((item) => (
                            <li 
                              onClick={() => handleFillterCategory(item)}
                            className={categoryActive.includes(item) ? "isActive" : ""}>
                              <div>

                              </div>
                              <span>
                                  {item}
                              </span>
                            </li>

                          ))
                        }
                        
                   

                      </ul>

                      <h3 className='filter-buy-price'>Filter By Price</h3>
                      <div className='input-filter-buy-price'>
                          <div className='slider'>
                              <div
                                style={{
                                  left: left+"%",
                                  right : right +"%"
                                }}  
                              
                              className='progress'></div>
                              <input type='range' 
                                onChange={(event) => handleMinValuePrice(event)}
                              className='range-min' min="0" max="100000" 
                                value={minPrice}
                              step="200"/>
                              <input type='range' 
                                onChange={(event) => handleMaxValuePrive(event)}
                              className='range-max' min="0" max="100000" 
                              value={maxPrice} step="200"/>
                          </div>
                          <div className='price-filter'>
                              <div className='price'>
                                  <span>Price</span>
                                  <div>
                                    <div>
                                        $ <input
                                          value={minPrice}
                                          onChange={handleInputMinValuePrice}
                                        type='number' />
                                    </div>
                                    <span>-</span>
                                    <div>
                                        $ <input 
                                        value={maxPrice}
                                        onChange={handleInputMaxValuePrice}
                                        type='number'/>
                                    </div>
                                  </div>
                              </div>
                              <div 
                                onClick={handleFilterPrice}
                                
                              >
                                <img src={ICONS.icon_back_arrow} />
                                <span>Filter</span>
                              </div>
                          </div>
                      </div>


                      <h3 className='materials'>Materials</h3>
                      <ul className='materials-list'>
                       
                        {
                          
                          material.map((item) => (
                          
                            <li 
                            className={materialActive.includes(item.name) ? "isActive-Material" : ""}
                            onClick={() => handleFillterMaterial(item.name.trim())}

                            >
                              <div>
                                  <div>

                                  </div>
                                  <span>
                                      {item.name}
                                  </span>
                              </div>
                              <span>{"(01)"}</span>
                            </li>

                          ))
                        }
                        
                      </ul>


                      <h3 className='diamonds'>Diamonds</h3>
                      <div className='diamonds-list'>
                          <h4>Origin</h4>
                          <ul className='origin'>
                              {origin.map((item) => (
                                <li
                                  className={originFilter.includes(item.value) ? 'isActive-diamonds' : null}
                                  onClick={() => handleFillterOrigin(item.value)}
                                >{item.value}</li>
                              ))}
                          </ul>
                          <h4>Color</h4>
                          <ul className='color'>
                              {color.map((item) => (
                                <li
                                className={colorFilter.includes(item.value) ? 'isActive-diamonds' : null}
                                onClick={() => handleFillterColor(item.value)}
                                >{item.value}</li>
                              ))}
                          </ul>
                          <h4>Clarity</h4>
                          <ul className='clarity'>
                              {clarity.map((item) => (
                                <li
                                className={clarityFilter.includes(item.value) ? 'isActive-diamonds' : null}
                                onClick={() => handleFillterClarity(item.value)}
                                >{item.value}</li>
                              ))}
                          </ul>
                          <h4>Cut</h4>
                          <ul className='cut'>
                              {cut.map((item) => (
                                <li
                                className={cutFilter.includes(item.value) ? 'isActive-diamonds' : null}
                                onClick={() => handleFillterCut(item.value)}
                                >{item.value}</li>
                              ))}
                          </ul>
                          <div
                            onClick={handleScroller}
                          >
                              <img src={ICONS.icon_back_arrow} />
                              <span>View</span>
                          </div>
                      </div>

                  </div>
                  
              
                  



                  <div className='container-all-product'>
                      <div className='products'>
                          { 
                          productPresent !== undefined && productPresent !== null
                            &&
                            productPresent.slice(index*12, index*12+12).map((product) => (

                              <ProductCard 
                              name={product.name}
                              images={product.images}
                              id={product.id}
                              price={product.price}
                              promotions={product.promotions}
                              />
                            ))
                          }


                           


                      </div>
                      <div> 
                          { numberProduct > 12 && 
                            Array.from({length : Math.ceil(numberProduct / 12)}, (_, i) => (
                              <div className={i == index ? "isActive" : 0}><span>
                                {i < 9 ? "0"+(i+1) : i+1 }
                                </span></div>
                            ))
                              
                            
                          }
                        
                      </div>
                  </div>
            </div>
      
        </div>
        
      <div className='banner-sale'>
            <img src={IMAGES.images_banner_in_product} alt=''/>
      </div>
    </div>
    
  )
}

export default Product