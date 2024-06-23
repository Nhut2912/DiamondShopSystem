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
              <CardDashboard />
              <CardDashboard />
              <CardDashboard />
              <CardDashboard />
        </div>
     
          <ChartAnalytics />
       
       
        
    </div>
  )
}

export default Dashboard