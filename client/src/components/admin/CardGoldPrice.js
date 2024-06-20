import React, { useState } from 'react';

import '../../theme/admin/CardGoldPrice.css';

function CardGoldPrice(){
     
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({
        goldType: 'M001',
        buyPrice: '100',
        sellPrice: '200'
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

    return(
        <div className='content-table-gold-price-container'>
           <ul className='table-gold-price-content'>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="goldType" 
                            value={formData.goldType} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.goldType
                    )}
                </li>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="buyPrice" 
                            value={formData.buyPrice} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.buyPrice
                    )}
                </li>
                <li>
                    {isEditing ? (
                        <input 
                            type="text" 
                            name="sellPrice" 
                            value={formData.sellPrice} 
                            onChange={handleChange} 
                        />
                    ) : (
                        formData.sellPrice
                    )}
                </li>
                <li className='edit-gold-price'>
                    {isEditing ? (
                        <button onClick={handleSaveClick}>Save</button>
                    ) : (
                        <button onClick={handleEditClick}>Edit</button>
                    )}
                </li>
           </ul>
        </div>
    )
}
export default CardGoldPrice