import React, { useEffect, useState } from "react";

import '../../theme/admin/Navigation.css';
import { ICONS } from "../../constants/admin/index";
import {  useNavigate } from "react-router-dom";




const Navigation = () => {
    
    const [contentItems,setContentItems] = useState(null); 
    const [navigationItems,setNavigationItems] = useState(null);
    const [activeItem, setActiveItem] = useState('Dashboard'); // Initial active item
    const navigate = useNavigate();
    const [account,setAccount] = useState(null);
    const [notificationsActive, setNotificationsActive] = useState(false);
    const [isSetting,setIsSetting] = useState(false);


    useEffect(() => {
            const accountLogged = localStorage.getItem('account');
            if (accountLogged) {
                try{
                    const accountJson = JSON.parse(accountLogged);
                    setAccount(JSON.parse(accountLogged));

                    if(accountJson.role === "ADMIN" || accountJson.role === "MANAGER" ){
                        setContentItems( [
                            { name: 'Dashboard', path : "/admin/overview"},
                            { name: 'Product', path : "products" },
                            { name: 'Order',  path : "order"},
                            { name: 'Account',  path : "account"},
                            { name: 'Warranty',  path : "warranty"},
                            { name: 'Promotions',  path : "promotions"},
                            { name: 'Gold Price',  path : "gold-price"},
                            { name: 'Diamond Price', path : "diamond-price" },
                        ])
                        setNavigationItems ([
                            { name: 'Dashboard', icon: ICONS.icon_dashboard, icon_active : ICONS.icon_dashboard_active },
                            { name: 'Product', icon: ICONS.icon_product , icon_active : ICONS.icon_product_active},
                            { name: 'Order', icon: ICONS.icon_orders , icon_active : ICONS.icon_order_active},
                            { name: 'Account', icon: ICONS.icon_user , icon_active : ICONS.icon_user_active},
                            { name: 'Warranty', icon: ICONS.icon_warranty , icon_active : ICONS.icon_warranty_active},
                            { name: 'Promotions', icon: ICONS.icon_promotions , icon_active : ICONS.icon_promotion_active},
                            { name: 'Gold Price', icon: ICONS.icon_gold_price , icon_active : ICONS.icon_gold_price_active},
                            { name: 'Diamond Price', icon: ICONS.icon_diamond , icon_active : ICONS.icon_diamond_active},
                          ])
                    }else if(accountJson.role === "SALE STAFF" || accountJson.role === "DELIVERY STAFF"){
                        setContentItems( [
                            { name: 'Order',  path : "order"},
                        ])
                        setNavigationItems( [
                            { name: 'Order', icon: ICONS.icon_orders , icon_active : ICONS.icon_order_active},
                          ])
                    }
                

                }catch(error){
                    console.log(error);
                }
            }else navigate("/admin")
      }, []);
    
      useEffect(()=> {
        const uri = window.location.pathname;
        if(contentItems !== null){
                contentItems.map((element) => {
                    if(uri.endsWith("/admin/")){
                        setActiveItem('Dashboard')
                    }else if(uri.includes(element.path)){
                        setActiveItem(element.name)
                    }
                })
            }
     })


    if(account === null) return <div>Loadding.............</div>;
    console.log(account);

   
    

    const handleClick = (item) => {
       contentItems.forEach((element) => {
            if(element.name === item){
                navigate(element.path);
            }
       })

       setNotificationsActive(false);
       setActiveItem(item);
       
    };

    const handleClickNotifications = () => {
        navigate("notifications")
        setNotificationsActive(true);
    }



    const optionsAccount = () => {
        const optionsAccount = document.getElementById("logout-setting");
        if(isSetting){
            optionsAccount.classList.remove("isActive");
        }else{
            optionsAccount.classList.add("isActive");
        }
        setIsSetting(!isSetting);
    }
    const optionsAccountMouseOut = () => {
        const optionsAccount = document.getElementById("logout-setting");
        if(isSetting){
            optionsAccount.classList.remove("isActive");
            setIsSetting(false);
        }
    }
    
    const handleLogout = () => {
        localStorage.removeItem('account');
        navigate("/admin");
    }


    return(
        <div className="navigation">
            <div className="head-navigation">
                <img src={ICONS.icon_logo_no_jewelry_text} width="50px" alt="" />
                <h2>JEWELRY</h2>
            </div>
            <div className="main-menu">
                <h5>
                    MAIN MENU
                </h5>
                <ul className="navigation-bar">
                    { navigationItems && navigationItems.map((item) => (
                        <li
                        key={item.name}
                        className={activeItem === item.name && !notificationsActive ? 'isActive' : ''}
                        onClick={() => handleClick(item.name)}
                        >
                        <div>
                            <img src={activeItem === item.name && !notificationsActive ? item.icon_active : item.icon} width="25px" height="25px" alt="" />
                        </div>
                        {item.name}
                        </li>
                    ))}
                </ul>
            </div>

            <div className="main-menu">
                <h5>
                    NOTIFICATIONS
                </h5>
                <ul>
                    <li 
                        className={notificationsActive ? 'isActive' : ''}
                        onClick={handleClickNotifications}
                    >
                        <div>
                            <img src={notificationsActive ? ICONS.icon_notification_active : ICONS.icon_notification} width="25px" height="25px" alt="" />
                        </div>
                        Notifications
                    </li>
                </ul>
            </div>

            <div className="account" onMouseLeave={optionsAccountMouseOut}>
                    <div className="account-img">

                    </div>
                    <div className="account-info">
                        <span className="name-account">nhutminhtran</span>
                        <br />
                        <span className="role-account">Admin</span>
                    </div>
                    <div className="option-account" onClick={optionsAccount}>
                        <ul className="logout-setting" id="logout-setting">
                            <li onClick={ handleLogout}>Logout</li>
                            <li>Setting</li>
                        </ul>
                        <img style={{cursor: "pointer"}} src={ICONS.icon_optional} alt="" />
                    </div>
            </div>
        </div>
    );
}
export default Navigation;