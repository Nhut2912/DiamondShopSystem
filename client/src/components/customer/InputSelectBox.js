import React from 'react'

import '../../theme/customer/InputSelectBox.css';
import { ICONS } from '../../constants/customer';


export default function InputSelectBox({ title,
     options,setParams,
     getParams
    }) {
        const handleChange = (event) => {
            const selectedValue = event.target.value;
            setParams(selectedValue);
        };
  return (

   <div className='input-select-box-container'>
        <label>{title}</label>
        <div className='content-select'>
            <img src={ICONS.icon_drop_down} className='img-select-box' alt=''/>
            <select 
            value={getParams}
            className='content-option'  onChange={(event) => handleChange(event)} >
                {
                    options && options.map((item) => (
                        <option  key={item.name} >
                           <span> {item.name}</span>
                        </option>
                    ))
                }
            </select>
        </div>
   </div>
  )
}
