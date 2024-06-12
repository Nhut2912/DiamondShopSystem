import React, { useState } from 'react'

import '../../theme/customer/ProductDescription.css';

function ProductDescription() {
  
  const [activeItem,setActiveItem] = useState("Description and characteristics");
  
  const navigateItems = [
    {name : "Description and characteristics"},
    {name : "Diamond certificate"}
  ]


  return (
    <div className='product-description-container'>
        <div className='product-description-content'>
            <div className='navigate'>
                <ul>
                    {
                        navigateItems.map((item) => (
                            <li className={activeItem === item.name ? 'isActive' : null}>
                                {item.name}
                            </li>
                        ))

                    }
 
                </ul>
            </div>
            <div className='content'>

            </div>
        </div>
    </div>
  )
}

export default ProductDescription