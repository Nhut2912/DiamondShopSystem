import React, {useState} from 'react' 

import '../../theme/admin/GoldPrice.css'
import UpdateGoldPrice from '../../components/admin/UpdateGoldPrice';
import HeadTableGoldPrice from '../../components/admin/HeadTableGoldPrice';
import CardGoldPrice from '../../components/admin/CardGoldPrice';

function GoldPrice(){
    const [activeNavigations,setActiveNavigations] = useState('View Gold Price');
    const [isUpdateGoldPrice,setUpdateGoldPrice] = useState(false);

  const navigationGoldPrice = [
    {name: 'View Gold Price'},
    {name: 'Update Gold Price'}
  ]

  const handleClick = (item) => {
    if(item === navigationGoldPrice[0].name){
        setUpdateGoldPrice(false)
    }else{
        setUpdateGoldPrice(true)
    }
    setActiveNavigations(item);
  }

   return(
       <div className='gold-price-container'>
          <h1>Gold Price</h1>
          <div className='gold-price-navigation'>
          <ul>
              {
                navigationGoldPrice.map((item) => (
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
          </div>
          <div className='gold-pricce-content'>
             {!isUpdateGoldPrice ?
              (
                <div className='gold-price-view'>
                  <HeadTableGoldPrice/>
                  <div className='gold-price-items-container'>
                      <CardGoldPrice/>
                      <CardGoldPrice/>
                      <CardGoldPrice/>
                  </div>
                </div>
              ) :<UpdateGoldPrice/>
             }
          </div>
       </div>
   )
}
export default GoldPrice
