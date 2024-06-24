import React, { useEffect, useState } from 'react'

import "../../theme/admin/WarrantyView.css"
import { ICONS } from '../../constants/admin';
import ConvertLocalDateToFormat from '../../function/ConvertLocalDateToFormat';

function WarrantyView() {

  const [isEdit, setIsEdit] = useState({
    index : '',
    status : false
  });
  const [isDropDown, setIsDropDown] = useState(false);

  const [status,setStatus] = useState();
  const [data, setData] = useState();
  const [dateStart,setDateStart] = useState();
  const [dateEnd,setDateEnd] = useState();


  useEffect(() => {
    fetch("${process.env.REACT_APP_API_ENDPOINT}/api/warranty/get")
      .then((response) => response.json())
      .then((result) => {
        setData(result)

      })
      .catch((error) => console.error(error));
  }, [])


  const handleUpdateWarranty = (index) => {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");


    const warranty ={
      "status" :status[index],
      "dateStart": dateStart[index],
      "dateEnd" : dateEnd[index],
      "id": data[index].id
    }
    if(!(data[index].status === status[index] &&
      data[index].dateStart === dateStart[index] &&
      data[index].dateEnd === dateEnd[index])
     ){
      
    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(warranty),
      redirect: "follow"
    };
    

    fetch("${process.env.REACT_APP_API_ENDPOINT}/api/warranty/update", requestOptions)
      .then((response) => response.text())
      .then((result) => 
        window.location.href = window.location.href
      )
      .catch((error) => console.error(error));
   
     }
     setIsEdit(false);
  } 


  useEffect(() => {
    if(data !== undefined && data !== null){
        let status = [];
        let dateStart = [];
        let dateEnd = [];
        data.map((item) => {
          dateStart.push(item.dateStart)
          dateEnd.push(item.dateEnd)
          status.push(item.status);
        })
        setDateStart(dateStart)
        setDateEnd(dateEnd)
        setStatus(status);
    }
  },[data])

  const handleUpdatStatus = (index,value) => {
    let listStatusUpdate = status;
    listStatusUpdate[index] = value;
    setStatus(listStatusUpdate);
  }

  const handleEditWarranty = (index,value) => {
      const isEdit = {
        index : index,
        status : value
      }
      setIsDropDown(false);
      setIsEdit(isEdit);
  } 

  const handleChangeDateStart = (index,event) =>{
      let dateStartUpdate = dateStart;
      const value = event.target.value;
      dateStartUpdate[index] = value;
      setDateStart(dateStartUpdate);
  }
  const handleChangeDateEnd = (index,event) => {
      let dateEndUpdate = dateEnd;
      const value = event.target.value;
      dateEndUpdate[index] = value;
      setDateEnd(dateEndUpdate);
  }
  return (
    <div className='warranty-view-container'>
      <ul>

        <li>ID</li>
        <li>Customer</li>
        <li>Phone</li>
        <li>Product Code</li>
        <li>Date Start</li>
        <li>Date End</li>
        <li>Status</li>
        <li>Update</li>
      </ul>
      {
        data !== undefined && data !== null && status !== undefined && status !== null &&
        data.map((item,index) => (
          <ul className={isEdit.index === index && isEdit.status ? 'isUpdate' : null}>
            <li>{item.id}</li>
            <li>{item.userName}</li>
            <li>{item.phoneNumber}</li>
            <li>{item.product.code}</li>
            <li>

              {
                !(isEdit.index === index && isEdit.status) ? ConvertLocalDateToFormat(dateStart[index]) :
                  <input
                    placeholder={dateStart[index]}
                    onChange={(event) =>  handleChangeDateStart(index,event)}
                  type='text' />
              }
            </li>
            <li>
              {
                !(isEdit.index === index && isEdit.status) ? ConvertLocalDateToFormat(dateEnd[index]) :
                  <input
                  placeholder={dateEnd[index]}
                  onChange={(event) =>  handleChangeDateEnd(index,event)}
                 type='text' />
              }
            </li>
            <li
              style={
                {
                  color: status[index] ? 'rgba(54, 227, 57, 1)' : 'rgba(217, 4, 61, 1)'
                }
              }
              onClick={() => setIsDropDown(!isDropDown)}
              className={isEdit.index === index && isEdit.status ? 'isUpdate' : null}>
         
              {status[index] ? "ACTIVE" :"INACTIVE" }

              {
                isEdit.index === index && isEdit.status ? <>
                  <ul className={isDropDown ? 'isActive' : null}>
                    <li
                      onClick={() => handleUpdatStatus(index,true)}
                      className='isActive' >ACTIVE</li>
                    <li
                      onClick={() => handleUpdatStatus(index,false)}
                      className='isInActive' >INACTIVE</li>
                  </ul>
                  <img
                    onClick={() => setIsDropDown(!isDropDown)}
                    src={ICONS.icon_drop_down} />
                </> : null
              }

            </li>
            <li className={isEdit.index === index && isEdit.status ? 'isUpdate' : null}>
              {
                !(isEdit.index === index && isEdit.status) ? <span
                  onClick={() => handleEditWarranty(index,true)}
                >Edit</span> : <span
                  onClick={() => handleUpdateWarranty(index)}
                >Update</span>
              }

            </li>
          </ul>
        ))

      }

    </div>
  )
}

export default WarrantyView