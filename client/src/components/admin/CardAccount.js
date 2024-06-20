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
        setIsEditing(!isEditing);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSaveClick = () => {
        setIsEditing(false);
        // Save data logic here
    };

    return (
        <div className='content-table-account-container'>
            <ul className='table-account-content'>
                <li>{formData.id}</li>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="email" 
                            value={formData.email} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.email
                    )}
                </li>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="name" 
                            value={formData.name} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.name
                    )}
                </li>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="phone" 
                            value={formData.phone} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.phone
                    )}
                </li>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="role" 
                            value={formData.role} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.role
                    )}
                </li>
                <li>
                    <div className='status-account'>
                        {isEditing ? (
                            <select 
                                name="status" 
                                value={formData.status} 
                                onChange={handleChange}
                            >
                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>
                            </select>
                        ) : (
                            <span>{formData.status}</span>
                        )}
                    </div>
                </li>
                <li className='edit-account'>
                    {isEditing ? (
                        <button onClick={handleSaveClick}>Save</button>
                    ) : (
                        <button onClick={handleEditClick}>Edit</button>
                    )}
                </li>
            </ul>
        </div>
    );
}


export default CardAccount