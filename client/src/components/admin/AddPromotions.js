import React, { useState } from 'react'

import '../../theme/admin/AddPromotions.css';
import ICONS from '../../constants/admin/icons';
import ProductPromotion from './ProductPromotion';


function AddPromotions() {

 const [searchActive,setSearchActive] = useState(false);

 const [isAddProduct,setIsAddProduct] = useState(false);
 const [productsData,setProductsData] = useState();
 const [namePromotion,setNamePromotion] = useState();
 const [promotionRate,setPromotionRate] = useState();
 const [dateStart,setDateStart] = useState();
 const [dateEnd,setDateEnd] = useState();

 const [productsPromotion,setProductsPromotion] = useState([]);


 const handleProductsData = async() => {
    if(productsData === undefined || productsData === null){
        await fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/product/getProducts`)
        .then(response => response.json())
        .then(data => setProductsData(data))
        .catch((error) => console.error(error));
    }
 }

 const handleNamePromotion = (event) => {
    const value = event.target.value;
    setNamePromotion(value);
 }
 const handlePromotionRate = (event) => {
    const value = event.target.value;
    setPromotionRate(value);
 }

 const handleDateStart = (event) => {
    const value = event.target.value;
    setDateStart(value);
 }

 const handleDateEnd = (event) => {
    const value = event.target.value;
    setDateEnd(value);
 }

 const handleCloseAddProduct = (event) => {

    const addProductPromotion = document.getElementById("add-product-promotion");
    const productList = document.getElementById("product-list");
  
    if (addProductPromotion && addProductPromotion.contains(event.target)) {
      return; 
    }
  
    if (productList && productList.contains(event.target)) {
      return;
    }
    setIsAddProduct(false);
 } 

 const handleDrapProductsPromotion = (event) => {
    event.dataTransfer.setData("product",event.currentTarget.dataset.key);
    
 }

 const handleDropProductsPromotion = (event) => {
    event.preventDefault();

    let  product = event.dataTransfer.getData("product");
    
    if(product !== undefined && product !== null && product !== "" ){
        let productsPromotionUpdate = [...productsPromotion,product];
        let productsPromotionSet = [...new Set(productsPromotionUpdate)];
        setProductsPromotion(productsPromotionSet)
    }
 }

 const handleSavePromotion = () => {
    const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

        const raw = JSON.stringify({
        "promotionRate": promotionRate,
        "active": true,
        "namePromotion": namePromotion,
        "dateStart": dateStart,
        "dateEnd": dateEnd,
        "productIds": productsPromotion
        });

        const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
        };

        fetch(`${process.env.REACT_APP_API_ENDPOINT}/Promotion/create`, requestOptions)
        .then((response) => response.text())
        .then((result) => {
            if(result){
                window.location.href = window.location.href;
            }
        })
        .catch((error) => console.error(error));
 }

  return (
    <div className='add-promotions-container' 
    
    onDrop={(event) => handleDropProductsPromotion(event)}
    onMouseDown={(event) => handleCloseAddProduct(event)}>
         <div className='add-promotion-information'>
                <h2>Promotion Information</h2>


                <div
                    onClick={handleSavePromotion}
                className='save-promotion'> Save Promotion</div>


                <div className='primary-add-information'>
                        <div>
                            <div>
                                <label>Name</label>
                                <input 
                                    onChange={handleNamePromotion}
                                    value={namePromotion} type='text'  />
                            </div>
                            <div>
                                <label>Promotion Rate {"(%)"}</label>
                                <input  
                                    onChange={handlePromotionRate}
                                    value={promotionRate}
                                type='text' />
                            </div>
                        </div>
                        <div>
                            <div>
                                <label>Date Start</label>
                                <input 
                                    onChange={handleDateStart}
                                    value={dateStart}
                                type='text' />
                            </div>
                            <div>
                                <label>Date End</label>
                                <input 
                                    onChange={handleDateEnd}
                                    value={dateEnd}
                                type='text' />
                            </div>
                        </div>
                </div>

                <h2>Products on this promotions</h2>
                <div className='search-product-on-promotion'>
                    <span>50 products on this promotions</span>
                  
                    <div className='search-product-promotion'>
                        <input placeholder='Search' type='text' />
                        <img src={ICONS.icon_search} />
                    </div> 

                    {
                        true ?<div 
                        
                            onClick={() =>{
                                setIsAddProduct(true);
                                handleProductsData();
                    
                            } }
                        className='add-product-promotion'  id="add-product-promotion">
                            
                            
                            Add Product
                            
                            {
                                isAddProduct ? <div className='product-list' id="product-list"> 
                                
                                    <div className='header-product'>
                                        <span>Products</span>
                                        <div className='search-products'>
                                            <img 
                                               onClick={() => setSearchActive(true)}
                                            src={ICONS.icon_search} />
                                            <input 
                                            
                                            className={searchActive ? "searchActive" : null} type='text' />
                                        </div>
                                       
                                    </div> 
                                    <ul>
                                            <li>Code</li>
                                            <li>Name</li>
                                            <li>Category</li>
                                            <li>
                                                
                                            </li>
                                    </ul>
                                    <div className='container-products'>
                                   
                                    {
                                        productsData !== undefined && productsData !== null &&
                                        productsData.filter((item) => !productsPromotion.some(id => id === item.id)).map((item,index) => (
                                            <ul
                                                draggable="true"
                                                onDragStart={handleDrapProductsPromotion} 
                                                key={index} data-key={item.id}
                                            >
                                                <li>#{item.code}</li>
                                                <li>{item.name}</li>
                                                <li>{item.category}</li>
                                                <li>
                                                    <div>Add</div>
                                                </li>
                                        </ul>
                                        ))
                                    }
                                    


                                    </div>  
                                   
                                
                                
                                </div> : null
                            }
                            
                            
                            </div> : null
                    }
                    
                </div>
                
                <div className='container-products-promotion' 
                    onDragOver={(event) => event.preventDefault()}
                    onDrop={(event) => handleDropProductsPromotion(event)}
             >

                            <ProductPromotion 
                                productsPromotion={productsPromotion}
                                thisPromotion={namePromotion}
                                thisPromotionRate={promotionRate}
                            />
                </div>
                
            
         </div>
    </div>
  )
}

export default AddPromotions