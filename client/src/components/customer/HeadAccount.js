import React from 'react'

import '../../theme/customer/HeadAccount.css';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

function HeadAccount() {

  const navigate = useNavigate();

  const handleBackShopping = () => {
    navigate("/products");
  }

  return (
    <div className='head-account-container'>
        <div onClick={ handleBackShopping}>
            <span>
                BACK TO SHOPPING
            </span>
        </div>
        <div>
            <img src={ICONS.icon_notification} alt='' />
        </div>
    </div>
  )
}

export default HeadAccount