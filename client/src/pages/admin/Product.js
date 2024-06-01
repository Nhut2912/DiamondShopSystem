import React, { useState } from 'react'

import '../../theme/admin/Product.css'
import { ICONS } from '../../constants/admin';
import HeadTableCardProduct from '../../components/admin/HeadTableCardProduct';
import CardProduct from '../../components/admin/CardProduct';
import AddProduct from '../../components/admin/AddProduct';

function Product() {
  
  const [activeNavigations,setActiveNavigations] = useState('View products');
  const [isAddProduct,setAddProduct] = useState(false);

  const navigationProducts = [
    {name: 'View products'},
    {name: 'Add product'}
  ]

  const handleClick = (item) => {
    if(item === navigationProducts[0].name){
        setAddProduct(false)
    }else{
        setAddProduct(true)
    }
    setActiveNavigations(item);
  }

  const options = [
    { value: 'default', label: 'Default Sorted' },
    { value: 'option2', label: 'Option 2' },
    { value: 'option3', label: 'Option 3' },
  ];

  return (
    <div className='product-container'>
        <h1>Products</h1>
        <p>
          Admin / <span>Products</span>
        </p>
        <div className='product-navigation'>
              <ul>
                  {
                    navigationProducts.map((item) => (
                        <li
                          key={item.name}
                          className={item.name === activeNavigations ? 'isActive' : ''}
                          onClick={() => handleClick(item.name)}
                        >
                            {item.name}
                        </li>
                    ))
                  }
              </ul>
              { !isAddProduct ? 
                <div className='search-sort'>
                  <div className='search'>
                      <input type='text' placeholder='Search ' />
                  </div>

                  <div className='sort'>
                  <img src={ICONS.icon_drop_down} alt='' className='img-drop-down'/>
                      <select className='select-list'>
                         
                          {
                            options.map((option) => (
                              <option key={option.value} value={option.value
                              }>
                                {option.label}
                              </option>
                            ))
                          }
                      </select>
                  </div>
                </div> : null
              }
              
        </div>
        <div className='product-content'>
              {!isAddProduct ? 
                  (
                    <div className='product-view'>
                      <h4>
                        Showing 50 results    
                      </h4>
                      <HeadTableCardProduct />
                      <div className='produc-items-container'>
                      
                          <CardProduct />
                          <CardProduct />
                          <CardProduct />
                          <CardProduct />
                          <CardProduct />
                          <CardProduct />
                      </div>
                    </div>
                  ) :<AddProduct />
              
              }




        </div>
    </div>
  )
}

export default Product