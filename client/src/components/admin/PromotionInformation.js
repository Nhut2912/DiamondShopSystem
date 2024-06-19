import React, { useEffect, useState } from 'react'

import '../../theme/admin/PromotionInformation.css'
import ProductPromotion from './ProductPromotion'
import ICONS from '../../constants/admin/icons'

function PromotionInformation({data}) {


 const [id,setId] = useState();
 const [name,setName] = useState();
 const [rate,setRate] = useState();
 const [dateStart,setDateStart] = useState();
 const [dateEnd,setDateEnd] = useState();
 const [productsData,setProductsData] = useState();
 const [isAddProduct,setIsAddProduct] = useState(false);
 const [searchActive,setSearchActive] = useState(false);
 const [productsPromotion,setProductsPromotion] = useState();

 const [isEdit,setIsEdit] = useState(true)
 const [active,setActive] = useState();


 const status = [
    {name : "AVAILABLE"},
    {name : "UNAVAILABLE"}
 ]

 

 useEffect(() => {
    if(data !== undefined && data !== null){
        setName(data.namePromotion);
        setRate(data.promotionRate);
        setDateStart(data.dateStart);
        setDateEnd(data.dateEnd);
        setProductsPromotion(data.productIds);
        setId(data.idPromotion)
        setActive(data.active ? "AVAILABLE" : "UNAVAILABLE")
    }
 },[data])




 const handleProductsData = async() => {
    if(productsData === undefined || productsData === null){
        await fetch('http://localhost:8080/api/product/getProducts')
        .then(response => response.json())
        .then(data => setProductsData(data))
        .catch((error) => console.error(error));
    }
 }

 const handleCloseAddProduct = (event) => {

    const addProductPromotion = document.getElementById("add-product-promotion-information");
    const productList = document.getElementById("product-list-information");
  
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

 const handleDeleteProduct = (value) => {
   let productsPromotionUpdate = productsPromotion.filter((item) => item !== value);
   setProductsPromotion(productsPromotionUpdate)
 }

 const handleChangeName = (event) => {
    const value = event.target.value;
    setName(value);
 }
 const handleChangeRate = (event) => {
    const value = event.target.value;
    setRate(value);
 }
 const handleChangeDateStart = (event) => {
    const value = event.target.value;
    setDateStart(value);
 }
 const handleChangeDateEnd = (event) => {
    const value = event.target.value;
    setDateEnd(value);
 }


 const handleUpdatePromotion = () => {
    console.log("....")
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    
            const raw = JSON.stringify({
            "idPromotion":id ,
            "promotionRate": rate,
            "active": active === "AVAILABLE" ? true : false,
            "namePromotion": name,
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
            
            console.log(raw)

            fetch("http://localhost:8080/Promotion/update", requestOptions)
            .then((response) => response.text())
            .then((result) => {
                if(result){
                  
                    window.location.href = window.location.href;
                }
            })
            .catch((error) => console.error(error));
 }

 const handleStatusPromotions = (value) => {
    setActive(value);
 }

  return (
    <div className='promotion-information-container'
    onMouseDown={(event) => handleCloseAddProduct(event)}
    >
        <h1>PROMOTION DETAILS</h1>
        {   !isEdit ? 
             <div className='edit-promotion'
                onClick={() => setIsEdit(true)}
            >Edit</div> : 
             <div className='save-promotion'
            onClick={handleUpdatePromotion}
            >Update</div>
        }
       
         <div className='information-promotion'>
                
                <div className='head-information'>
                    <span>
                       Promotion ID : #{data !== undefined && data !== null ?
                       data.idPromotion : 0}
                    </span>
                    {
                        isEdit ? 
                        <ul className='edited-status'>
                            {
                                status.map((item) => (
                                    <li
                                    onClick={() => handleStatusPromotions(item.name)}
                                    className={active!== undefined && active !== null
                                        && active === item.name ?'isActiveStatus':null }>{item.name}</li>
                                ))
                            }
                        </ul>

                        : <div className='un-edit-status'
                        >
                            {data !== undefined && data !== null && data.active ? 
                            "AVAILABLE" : "UNAVAILABLE"
                            }
                            
                        </div>
                    }
                   
                </div>
                <div className='primary-information'>
                    <div>
                        <div>
                            <label>Name</label>
                            <input 
                                style={isEdit?{
                                    border: "1px solid rgba(0,0,0,0.2)"
                                }:{}}
                                onChange={(event) => handleChangeName(event)}
                            value={name} type='text'  />
                        </div>
                        <div>
                            <label>Promotion Rate {"(%)"}</label>
                            <input 
                                style={isEdit?{
                                    border: "1px solid rgba(0,0,0,0.2)"
                                }:{}}
                                onChange={(event) => handleChangeRate(event)}
                            value={rate} type='text' />
                        </div>
                    </div>
                    <div>
                        <div>
                            <label>Date Start</label>
                            <input 
                                style={isEdit?{
                                    border: "1px solid rgba(0,0,0,0.2)"
                                }:{}}
                                onChange={(event) => handleChangeDateStart(event)}
                            value={dateStart} type='text' />
                        </div>
                        <div>
                            <label>Date End</label>
                            <input
                                style={isEdit?{
                                    border: "1px solid rgba(0,0,0,0.2)"
                                }:{}}
                                onChange={(event) => handleChangeDateEnd(event)}
                            value={dateEnd} type='text' />
                        </div>
                    </div>
                </div>
                <h4>Products are on this promotion</h4>
                <div className='search-product-on-promotion'
                 
                 style={!isEdit? {
                    display:'flex',
                    justifyContent:'space-between'
                 }: {}}
         
                
                >
                    <span>50 products on this promotions</span>
                  
                    <div className='search-product-promotion'>
                        <input placeholder='Search' type='text' />
                        <img src={ICONS.icon_search} />
                    </div> 

                    {
                        isEdit ?<div 
                        
                            onClick={() =>{
                                
                                setIsAddProduct(true);
                                handleProductsData();
                    
                            } }
                        className='add-product-promotion'  id="add-product-promotion-information">
                            
                            
                            Add Product
                            
                            {
                                isAddProduct ? <div className='product-list' id="product-list-information"> 
                                
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
                                thisPromotion={name}
                                thisPromotionRate={rate}
                                thisPromotionID={ id
                                }
                                handleDeleteProduct={handleDeleteProduct}
                            />
                </div>
         </div>
    </div>
  )
}

export default PromotionInformation