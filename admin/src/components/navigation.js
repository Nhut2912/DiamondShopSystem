import React from "react";

import '../theme/Navigation.css';
import { ICONS } from "../constants";

const Navigation = () => {
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
                <ul>
                    <li>
                        <div>

                        </div>
                        Dashboard
                    </li>
                    <li>    
                        <div>
                            
                        </div>
                        Product
                    </li>
                    <li>
                        <div></div>
                        Order
                    </li>
                    <li>
                        <div></div>
                        Account
                    </li>
                    <li>
                        <div></div>
                        Warranty
                    </li>
                    <li>
                        <div></div>
                        Promotions
                    </li>
                    <li>
                        <div></div>
                        Gold Price
                    </li>
                    <li>
                        <div></div>
                        Diamond Price
                    </li>

                </ul>
            </div>
        </div>
    );
}
export default Navigation;