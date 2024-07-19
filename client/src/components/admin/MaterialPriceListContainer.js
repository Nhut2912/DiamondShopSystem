import React, { useEffect, useState } from 'react'
import { ICONS } from '../../constants/admin'

import "../../theme/admin/MaterialPriceListContainer.css"


function MaterialPriceListContainer() {

  const [data,setData] = useState();
  const [materialPriceList,setMaterialPriceList] = useState();
  const [isEdit,setIsEdit] = useState({
    index : '', status: false
  })

  const [sellPrice,setSellPrice] = useState();
  const [dateEffected,setDateEffected] = useState();

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/materialpricelist/getMaterialPriceLists`)
    .then((response) => response.json())
    .then((result) => setData(result))
    .catch((error) => console.error(error));
  },[])

  useEffect(() => {
    if(data !== undefined && data !== null){
        setMaterialPriceList(data);
    }
  },[data])

  const handleEdit = (index,value) => {
    setIsEdit({
      index : index, status: value
    })
    setSellPrice(data[index].sellPrice)
    setDateEffected(data[index].effDate)
  }
  
  const handleUpdate = (index,value) => {
    const Object = {
      "id": data[index].id,
      "effDate": dateEffected,
      "sellPrice": sellPrice,
      "material": data[index].material
    }

    if(!(dateEffected === data[index].effDate && sellPrice === data[index].sellPrice)){

      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
      
      
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(Object),
        redirect: "follow"
      };
      console.log(Object);
      fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/materialpricelist/updateMaterialPriceList`, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        window.location.href = window.location.href;
      })
      .catch((error) => console.error(error));
    }

    setIsEdit({
      index : index, status: value
    })
  }

  
  return (
    <div className='material-price-list-container'>
        <div className='seach-and-show'>
          <span> Showing 50 results</span>
          <div>
              <img src={ICONS.icon_search}  />
              <input placeholder='Search' type='text' />
          </div>
      </div>
        <ul className='head-price-list'>
            <li>Code</li>
            <li>Material</li>
            <li>Sell Price {"($)"}</li>
            <li>Effected Date</li>
            <li>Update</li>
        </ul>
        <div className='container-price-scroll' >
          { materialPriceList !== undefined && materialPriceList !== null &&
            materialPriceList.map((item,index) => (
              <ul className={isEdit.index === index && isEdit.status ? 'isActive' : null }>
                  <li>{item.id}</li>
                  <li>{item.material}</li>
                  <li>
                    {
                      !(isEdit.index === index && isEdit.status) ?
                       `${item.sellPrice}` : 
                      <input type='text' 
                      onChange={(event) => {
                        setSellPrice(event.target.value)
                      }}
                      value={sellPrice} />
                    }
                  
                  </li>
                  <li>
                  {
                      !(isEdit.index === index && isEdit.status) ?
                      item.effDate : 
                      <input type='text' 
                        onChange={(event) => {
                          setDateEffected(event.target.value)
                        }}
                      value={dateEffected} />
                    }
                  </li>
                  <li>
                    {
                      !(isEdit.index === index && isEdit.status) ? <span 
                      onClick={() => handleEdit(index,true)}
                      className='edit'>Edit</span> :
                      <span 
                      onClick={() => handleUpdate(index,false)}
                      className='update'>Update</span>
                    }
                    
                   
                  </li>
              </ul>
            ))
          }
           
        </div>
    </div>
  )
}

export default MaterialPriceListContainer