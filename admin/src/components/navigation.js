import React from "react";

import '../theme/Navigation.css';
import { ICONS } from "../constants";

const Navigation = () => {
    return(
        <div className="navigation">
            <div>
                <img src={ICONS.icon_logo_no_jewelry_text} alt="" />
                <h2>JEWELRY</h2>
            </div>
            <div>
                <h3>Dashboard</h3>
                <ul>
                    <li>
                        <img  alt="" />
                        <span>Overview</span>
                    </li>
                </ul>
            </div>
            <div>
                <h3>
                    Manage
                </h3>
                <ul>
                    <li>
                        Product
                    </li>
                    <li>
                        Order
                    </li>
                    <li>
                        Account
                    </li>
                    <li>
                        Warranty
                    </li>
                    <li>
                        Promotions
                    </li>
                    <li>
                        Gold Price
                    </li>
                    <li>
                        Diamond Price
                    </li>

                </ul>
            </div>
        </div>
    );
}
export default Navigation;