import React from "react";

import Navigation from "../components/navigation";
import '../theme/Overview.css'
import { Outlet } from "react-router-dom";
import Header from "../components/Header";

const Overview = () => {
    return(
        <div className="container-admin">
            <Navigation />
            <div className="outlet">
                <Header />
                <Outlet />
            </div>
        </div>
    )
}
export default Overview;