import React, { useState } from 'react';

import '../../theme/admin/CardAccount.css';
import { ICONS } from '../../constants/admin';

function CardAccount({data}){
    
    const [isEditing, setIsEditing] = useState(false);
    const [isDropDown,setIsDropDown] = useState(false);
    const [role,setRole] = useState();

    const handleEditClick = () => {
        setIsEditing(true);
    };
    const handleUpdateClick = () => {
        setIsEditing(false);
    };


    return (
        <div className='content-table-account-container'>
            <ul className={isEditing ? 'table-account-content isUpdate' : 'table-account-content'} >
                <li>
                    #{data.id}
                </li>
                <li>
                    {data.email}
                </li>
                <li>
                    {
                        !isEditing ? data.name :
                        <input />
                    }
                </li>
                <li>
                    {
                        !isEditing ? data.numberPhone :
                        <input />
                    }
                   
                </li>
                <li>
                    {
                        !isEditing ?   data.role:
                        
                             <span
                                onClick={() => setIsDropDown(!isDropDown)}
                             className='isRole'>
                                    {data.role} 
                                    <img src={ICONS.icon_drop_down} />
                                    <ul className={isDropDown ? 'isActive' : ''}>
                                        <li 
                                        onClick={(event) => {
                                            setRole(event.target.innerText)
                                            setIsDropDown(false)
                                        }} >ADMIN</li>
                                        <li
                                            onClick={(event) => {
                                            setRole(event.target.innerText)
                                            setIsDropDown(false)
                                        }}
                                        >CUSTOMER</li>
                                        <li
                                            onClick={(event) => {
                                            setRole(event.target.innerText)
                                            setIsDropDown(false)
                                        }}
                                        >MANAGER</li>
                                        <li
                                            onClick={(event) => {
                                            setRole(event.target.innerText)
                                            setIsDropDown(false)
                                        }}
                                        >SALE STAFF</li>
                                        <li
                                            onClick={(event) => {
                                            setRole(event.target.innerText)
                                            setIsDropDown(false)
                                        }}
                                        >DELIVERY STAFF</li>
                                </ul>
                             </span>

                            
                        
                    }
              
                </li>
                <li className={data.active ? 'isActive' : 'isInActive'}>
                    {console.log(data.active)}
                   <span>{data.active ? 'ACTIVE' : 'INACTIVE'}</span>
                </li>
                <li className={isEditing ? 'edit-account isUpdate' : 'edit-account' }>
                    {
                        !isEditing ? <span
                            onClick={handleEditClick}
                        >Edit</span> : <span
                            onClick={handleUpdateClick}
                        >Update</span>
                    }
                    
                </li>
            </ul>
        </div>
    );
}


export default CardAccount