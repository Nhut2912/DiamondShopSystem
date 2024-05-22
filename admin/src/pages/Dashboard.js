import React from 'react'

import '../theme/Dashboard.css'
import CardDashboard from '../components/CardDashboard'
import RecentOrders from '../components/RecentOrders'

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