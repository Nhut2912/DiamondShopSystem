import React from 'react'

import '../theme/Footer.css';
import  ICONS  from '../constants/icons';

function Footer() {
  return (
    <div className='footer-container'>
        <div>
            <div className='img-footer'>
                <img src={ICONS.icon_logo} alt=''/>
            </div> 
            <p>Lot E2a-7, Street D1, D. D1, Long Thanh My, Thu Duc City, Ho Chi Minh City 700000</p>
        </div>
        <div>
            <h2>Quick Link</h2>
            <ul>
                <li>Home</li>
                <li>About</li>
                <li>Contact</li>
                <li>Blog</li>
            </ul>
        </div>
        <div>
            <h2>Products</h2>
            <ul>
                <li>Ring Diamond</li>
                <li>Earring Diamond</li>
                <li>Pendant Diamond</li>
                <li>Shake Diamond</li>
            </ul>

        </div>
        <div>
            <h2>Help & Support</h2>
            <ul>
                <li>Terms & Policy</li>
                <li>FAQs</li>
            </ul>
        </div>
        <div>
                <img src={ICONS.icon_facebook} alt='' />
                <img src={ICONS.icon_instagram} alt='' />
                <img src={ICONS.icon_twitter} alt='' />
        </div>
        <div>
            <span>CopyRight : Jewelry</span>
        </div>
    </div>
  )
}

export default Footer