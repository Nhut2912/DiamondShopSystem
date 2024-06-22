import React, { useEffect, useState } from 'react'

import '../../theme/admin/PriceListContainer.css';
import {ICONS} from '../../constants/admin'





function PriceListContainer({ originFilter,
  clarityFilter,
  colorFilter,
  cutFilter}) {

  const [data,setData] = useState();
 
  const [isEdit,setIsEdit] = useState({
    status: false, index : null
  });

  const [dateEffected,setDateEffected] = useState();
  const [price,setPrice] = useState();
  const [diamondPriceList,setDiamondPriceList] = useState();


  useEffect(() => {

    if(data === undefined || data === null){
      fetch("http://localhost:8080/api/diamondpricelist/getAll")
      .then((response) => response.json())
      .then((result) => setData(result))
      .catch((error) => console.error(error));
    }
  },[])




  useEffect(() => {
    if(data !== undefined && data !== null ){
    const diamondsPrice = data.filter((item) => {
          if(originFilter.length === 0){
            return item;
          }else{
            if(originFilter.some((origin) => origin === item.origin)) return item
          }
      }).filter((item) => {
        if(clarityFilter.length === 0){
          return item;
        }else{
          if(clarityFilter.some((clarity) => clarity === item.clarity)) return item
        }
    }).filter((item) => {
      if(colorFilter.length === 0){
        return item;
      }else{
        if(colorFilter.some((color) => color === item.color)) return item
      }
  }).filter((item) => {
      if(cutFilter.length === 0){
          return item;
      }else{
          if(cutFilter.some((cut) => cut === item.cut)) return item
      }
    })
      setDiamondPriceList(diamondsPrice);
    }

    

  },[data,originFilter,clarityFilter,colorFilter,cutFilter])


  const handleEdit = (index,date,price) => {
    setIsEdit({
      status: true, index : index
    });
    setPrice(price);
    setDateEffected(date);
  }


  const handleUpdate = (index) => {
    setIsEdit({
      status: false, index : null
    });
    
    console.log(price)
    if(!(diamondPriceList[index].price == Number(price).toFixed(2) && diamondPriceList[index].effDate === dateEffected)){
        let diamondPriceUpdate = diamondPriceList[index];
        diamondPriceUpdate.price = price;
        diamondPriceUpdate.effDate = dateEffected;

        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const requestOptions = {
          method: "POST",
          headers: myHeaders,
          body: JSON.stringify(diamondPriceUpdate),
          redirect: "follow"
        };

        fetch("http://localhost:8080/api/diamondpricelist/updateDiamondPriceList", requestOptions)
        .then((response) => response.text())
        .then((result) => {
          if(result === "true"){
            window.location.href = "/admin/overview/diamond-price" +`?origin=${diamondPriceUpdate.origin}&color=${diamondPriceUpdate.color}&clarity=${diamondPriceUpdate.clarity}&cut=${diamondPriceUpdate.cut}`;
          }
        })
        .catch((error) => console.error(error));
        
    }

  }

  const handleChangePrice = (event) => {
    const value = event.target.value;
    setPrice(value);
  }

  const handleChangeDateEffected = (event) => {
    const value = event.target.value;
    setDateEffected(value);
  }


  return (
    <div className='price-list-container'>
      <div className='seach-and-show'>
          <span> Showing {diamondPriceList!== undefined && diamondPriceList !== null ? diamondPriceList.length :0} results</span>
          <div>
              <img src={ICONS.icon_search}  />
              <input placeholder='Search' type='text' />
          </div>
      </div>
        <ul className='head-price-list'>
            <li>ID</li>
            <li>Origin</li>
            <li>Color</li>
            <li>Clarity</li>
            <li>Cut</li>
            <li>Carat</li>
            <li>Effected Date</li>
            <li>Price {"($)"}</li>
            <li>Update</li>
        </ul>
        <div className='container-price-scroll'>
          {
            diamondPriceList !== undefined && diamondPriceList !== null && 
            diamondPriceList.
            map((item,index) => (
                <>
                  <ul
                    className={isEdit.index === index && isEdit.status === true ? "isEdited" : null}
                  >
                    <li>{item.id}</li>
                    <li>{item.origin}</li>
                    <li>{item.color}</li>
                    <li>{item.clarity}</li>
                    <li>{item.cut}</li>
                    <li>
                        {`(${item.caratFrom} - ${item.caratTo})`}
                    </li>
                    <li>
                      {isEdit.index === index && isEdit.status === true ? 
                        <input 
                          onChange={(event) => handleChangeDateEffected(event)}
                        value={dateEffected} /> :  item.effDate
                      }
            
                      </li>
                    <li>
                      {isEdit.index === index && isEdit.status === true ? 
                        <input
                        onChange={(event) => handleChangePrice(event)}
                        value={price} /> :   `${item.price}`
                      }
                     </li>
                    
                    <li>{isEdit.index === index && isEdit.status === true ? <span
                      onClick={ () => handleUpdate(index)}
                      className='update'
                    >
                      Update
                    </span> : <span
                    className='edit'
                      onClick={() => handleEdit(index,item.effDate,item.price)}
                    >Edit</span>}</li>
                  </ul>
                  {
                    index <= data.length && <div className='line'></div>
                  }
                </>
            ))
          }
            
            
         
        </div>
    </div>
  )
}

export default PriceListContainer