import React, { useEffect, useMemo, useState } from 'react'

import '../../theme/customer/Header.css';
import { ICONS } from '../../constants/customer';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { CartContext } from '../../context/CartContext';

function Header() {
 const navigate = useNavigate();

 const [account,setAccount] = useState();

 const [login,setLogin] = useState(false);

 const [activeItem,setActiveItem] = useState(null);

 const cart = useContext(CartContext);
 
 const navigation = [
    {name : 'HOME', path : "/home"},
    {name : 'PRODUCTS', path : "/products"},
    {name : 'BLOG', path : "/blog"},
    {name : 'CONTACT', path : "/contact"},
 ]

 useEffect(() => {
    const accountLocal = JSON.parse(localStorage.getItem("account"));
    if(accountLocal !== undefined && accountLocal !== null ){
        fetch(`${process.env.REACT_APP_API_ENDPOINT}/api/account/AccountEmail?email=${accountLocal.email}`)
        .then((response) => response.json())
        .then((result) => {
            if(result !== undefined && result !== null && result !== false){
               setAccount(accountLocal);
            }else{
                localStorage.removeItem("account");
                window.location.href =  window.location.href;
            };
        })
        .catch((error) => console.error(error));
    }
    
 },[])


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

 useEffect(() => {
    if(account !== null && account !== undefined){
        setLogin(true);
    }
 },[account])

 const handleClickNavigate = (item) => {
    navigation.map((element) => {
        if(element.name === item){
            navigate(element.path);
        }
    })
    setActiveItem(item)
 }  




  return (
    <div class="container-header-page" id="header-customer">
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
                    <div onClick={
                        () => {
                            if(login){
                                navigate("/account")
                            }else{
                                navigate("/login")
                            }
                        }
                    }>
                        <img src={ICONS.icon_user} />
                        <span>
                            
                            {account !== undefined && account !== null & login ? account.email.split("@")[0] : "Login"}
                        </span>
                    </div>
                    <div 
                        onClick={() => {
                            if(login){
                                navigate("/checkout-cart");
                                setActiveItem(null);
                            }else{
                                navigate("/login")
                            }
                            
                        }}
                    >
                        <div   
                            
                        >
                            <img  src={ICONS.icon_cart} />
                        </div>
                        <span>
                            {
                               account !== undefined && account !== null && cart.cart !== undefined
                               ?
                               cart.cart : 0
                            }

                        </span>
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