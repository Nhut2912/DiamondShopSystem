import React, { useEffect, useState } from 'react'

import '../../theme/customer/Header.css';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';

function Header() {
 const navigate = useNavigate();

 const [activeItem,setActiveItem] = useState(null);
 
 const navigation = [
    {name : 'HOME', path : "/home"},
    {name : 'PRODUCTS', path : "/products"},
    {name : 'BLOG', path : "/blog"},
    {name : 'CONTACT', path : "/contact"},
 ]

 useEffect(() => {
    const currentURL = window.location.href;
    navigation.map((item) => {
        if(currentURL.endsWith("/home") || currentURL.endsWith("/")){
            setActiveItem('HOME');
        }else if(currentURL.includes(item.path)){
            setActiveItem(item.name)
        }
    })
 },[])




 const handleClickNavigate = (item) => {
    navigation.map((element) => {
        if(element.name === item){
            navigate(element.path);
        }
    })
    setActiveItem(item)
 }

  return (
    <div class="container-header-page">
        <div className='information-header'>
                <div>
                    <div>
                        <h5>Location</h5>
                        <span>Thu Duc - HCM - VN</span>
                    </div>
                    <div>
                        <h5>Tel</h5>
                        <span>{"(+84)"} 3939393939</span>
                    </div>
                </div>
                <div>
                        <img src={ICONS.icon_logo} alt=''/>
                </div>
                <div>
                    <div >
                        <img src={ICONS.icon_user} />
                        <span>Login</span>
                    </div>
                    <div>
                        <div   
                            onClick={() => {
                                navigate("/checkout-cart");
                                setActiveItem(null);
                            }}
                        >
                            <img  src={ICONS.icon_cart} />
                        </div>
                        <span>0</span>
                    </div>        
                </div>
        </div>
        <div className='header-navigation'>
            <ul>
                {
                   navigation && navigation.map((item) => (
                        <li className={item.name === activeItem ? 'isActive' : ''}
                            onClick={() => handleClickNavigate(item.name)}
                        >
                            {item.name}
                        </li>
                   ))
                }
            </ul>
        </div>
    </div>
  )
}

export default Header