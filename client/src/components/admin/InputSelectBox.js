import React from 'react'

import '../../theme/admin/InputSelectBox.css';
import { ICONS } from '../../constants/admin/index';


export default function InputSelectBox({ title,
     options,
     setParams,
     getParams
    }) {
 const handleChange = (event) => {
    const selectedValue = event.target.value;
    setParams(selectedValue);
};
  return (
   <div className='input-admin-select-box-container'>
        <label>{title}</label>
        <div className='content-select'>
            <img src={ICONS.icon_drop_down} className='img-select-box' alt=''/>
            <select onChange={(event) => handleChange(event)}
                value={getParams}
            className='content-option'>
                {
                    options && options.map((item) => (
                        <option  key={item.name} value={item.name} >
                            {item.name}
                        </option>
                    ))
                }
            </select>
        </div>
   </div>
  )
}
