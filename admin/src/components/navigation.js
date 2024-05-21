import React, { useState } from "react";

import '../theme/Navigation.css';
import { ICONS } from "../constants";
import {  useNavigate } from "react-router-dom";

const Navigation = () => {

    const [activeItem, setActiveItem] = useState('Dashboard'); // Initial active item
    const navigate = useNavigate();

    const contentItems = [
        { name: 'Dashboard', path : "/admin"},
        { name: 'Product', path : "product" },
        { name: 'Order',  path : "order"},
        { name: 'Account',  path : "account"},
        { name: 'Warranty',  path : "warranty"},
        { name: 'Promotions',  path : "promotions"},
        { name: 'Gold Price',  path : "gold-price"},
        { name: 'Diamond Price', path : "diamond-price" },
    ]

    const handleClick = (item) => {
        contentItems.map((element) => {
            if(element.name === item){
                navigate(element.path);
            }
       })
       setActiveItem(item);
       
    };
   
    const navigationItems = [
        { name: 'Dashboard', icon: ICONS.icon_dashboard, icon_active : ICONS.icon_dashboard_active },
        { name: 'Product', icon: ICONS.icon_product , icon_active : ICONS.icon_product_active},
        { name: 'Order', icon: ICONS.icon_orders , icon_active : ICONS.icon_order_active},
        { name: 'Account', icon: ICONS.icon_user , icon_active : ICONS.icon_user_active},
        { name: 'Warranty', icon: ICONS.icon_warranty , icon_active : ICONS.icon_warranty_active},
        { name: 'Promotions', icon: ICONS.icon_promotions , icon_active : ICONS.icon_promotion_active},
        { name: 'Gold Price', icon: ICONS.icon_gold_price , icon_active : ICONS.icon_gold_price_active},
        { name: 'Diamond Price', icon: ICONS.icon_diamond , icon_active : ICONS.icon_diamond_active},
      ];


    return(
        <div className="navigation">
            <div className="head-navigation">
                <img src={ICONS.icon_logo_no_jewelry_text} width="40px" alt="" />
                <h2>JEWELRY</h2>
            </div>
            <div className="main-menu">
                <h5>
                    MAIN MENU
                </h5>
                <ul className="navigation-bar">
                    {navigationItems.map((item) => (
                        <li
                        key={item.name}
                        className={activeItem === item.name ? 'isActive' : ''}
                        onClick={() => handleClick(item.name)}
                        >
                        <div>
                            <img src={activeItem === item.name ? item.icon_active : item.icon} width="24px" height="24px" alt="" />
                        </div>
                        {item.name}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}
export default Navigation;