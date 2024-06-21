import React, { useState } from 'react';

import '../../theme/admin/CardAccount.css';

function CardAccount(){
    
    const [isEditing, setIsEditing] = useState(false);
    
    const [formData, setFormData] = useState({
        id: '#12345',
        email: 'john.doe@example.com',
        name: 'John Doe',
        phone: '+1-234-567-8901',
        role: 'Admin',
        status: 'Active'
    });

    const handleEditClick = () => {
        setIsEditing(true);
    };

   

    const handleUpdateClick = () => {
        setIsEditing(false);
    };

    return (
        <div className='content-table-account-container'>
            <ul className={isEditing ? 'table-account-content isUpdate' : 'table-account-content'} >
                <li>#1234</li>
                <li>
                    nhuttmse172865@fpt.edu.vn
                </li>
                <li>
                    Tran Minh Nhut
                </li>
                <li>
                    0384463039
                </li>
                <li>
                    CUSTOMER
                </li>
                <li>
                   <span>Active</span>
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