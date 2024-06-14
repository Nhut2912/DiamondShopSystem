import React, { useState } from 'react'

import '../../theme/customer/ProductDescription.css';
import DescriptionCharacteristics from './DescriptionCharacteristics';
import DiamondCertificate from './DiamondCertificate';

function ProductDescription({data}) {
  
  const [activeItem,setActiveItem] = useState("Description and characteristics");
  
  const navigateItems = [
    {name : "Description and characteristics"},
    {name : "Diamond certificate"}
  ]

  const handleNavigate = (item) => {
    setActiveItem(item);
  }

  return (
    <div className='product-description-container'>
        <div className='product-description-content'>
            <div className='navigate'>
                <ul>
                    {
                        navigateItems.map((item) => (
                            <li 
                            onClick={() => handleNavigate(item.name)}
                            className={activeItem === item.name ? 'isActive' : null}>
                                {item.name}
                            </li>
                        ))

                    }
 
                </ul>
            </div>
            <div className='content'>
                <div className='product-description'>
                  {
                    activeItem === "Description and characteristics" ? 
                    <DescriptionCharacteristics 
                    data={data}
                    /> :  <DiamondCertificate />
                  }
                      
                     
                </div>
            </div>
        </div>
    </div>
  )
}

export default ProductDescription