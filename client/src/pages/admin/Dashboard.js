import React from 'react'

import '../../theme/admin/Dashboard.css'
import CardDashboard from '../../components/admin/CardDashboard'
import RecentOrders from '../../components/admin/RecentOrders'

function Dashboard() {
  return (
    <div className="dashboard-container">
        <h1>Dashboard</h1>
        <div className='analytics-container'>
              <CardDashboard />
              <CardDashboard />
              <CardDashboard />
              <CardDashboard />
        </div>

        <RecentOrders />
        
    </div>
  )
}

export default Dashboard