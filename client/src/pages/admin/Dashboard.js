import React from 'react'

import '../../theme/admin/Dashboard.css'
import CardDashboard from '../../components/admin/CardDashboard'
import RecentOrders from '../../components/admin/RecentOrders'
import ChartAnalytics from '../../components/admin/ChartAnalytics'

function Dashboard() {
  return (
    <div className="dashboard-container">
        <h1>Dashboard</h1>
        <p>
          Admin / <span>DashBoard</span>
        </p>
        <div className='analytics-container'>
              <CardDashboard 
                name={"Total Orders"}
              />
              <CardDashboard 
                name={"New Customers"}
              />
              <CardDashboard
                name={""}
              />
              <CardDashboard 
                 name={"Total Revenue"}
              />
        </div>
        <div className='container-chart-analytics'>
          <ChartAnalytics />
        </div>
          
       
       
        
    </div>
  )
}

export default Dashboard