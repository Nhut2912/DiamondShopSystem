import React from "react";

import Navigation from "../../components/admin/navigation";
import '../../theme/admin/Overview.css'
import { Outlet } from "react-router-dom";

const Overview = () => {
    return(
        <div className="container-admin-overview">
            <Navigation />
            <div className="overview-container">
                <div className="scroll-view">
                    <div className='view-container'>
                        <Outlet />
                    </div>
                </div>
            </div>
            
        </div>
    )
}
export default Overview;