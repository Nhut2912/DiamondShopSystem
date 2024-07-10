import React, { useEffect, useState } from 'react'

import '../../theme/admin/Dashboard.css'
import CardDashboard from '../../components/admin/CardDashboard'
import ChartAnalytics from '../../components/admin/ChartAnalytics'
import { ICONS } from '../../constants/admin';

function Dashboard() {

  const [newCustomer, setNewCustomer] = useState();
  const [revenue,setRevenue] = useState();
  const [order,setOrder] = useState();

  const [dayCustomerActive,setDayCustomerActive] = useState("Last one days");
  const [dayOrderActive,setDayOrderActive] = useState("Last one days");
  const [dayRevenueActive,setDayRevenueActive] = useState("Last one days");


  useEffect(() => {
      let url = '';
      if(dayCustomerActive === "Last one days"){
        url= `${process.env.REACT_APP_API_ENDPOINT}/api/account/getSumNewAccountStatisticByDay`
      }else if(dayCustomerActive === "Last 7 days"){
        url= `${process.env.REACT_APP_API_ENDPOINT}/api/account/getSumNewAccountStatisticByWeek`
      }else if(dayCustomerActive === "Last 30 days"){
        url= `${process.env.REACT_APP_API_ENDPOINT}/api/account/getSumNewAccountStatisticByMonth`
      }
      fetch(url)
      .then((response) => response.json())
      .then((result) => setNewCustomer(result))
      .catch((error) => console.error(error));

  },[dayCustomerActive])


  useEffect(() => {
    let url = '';
    if(dayOrderActive === "Last one days"){
      url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/getTheSumOfOrderStatisticByDay`
    }else if(dayCustomerActive === "Last 7 days"){
      url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/getTheSumOfOrderStatisticByWeek`
    }else if(dayCustomerActive === "Last 30 days"){
      url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/getTheSumOfOrderStatisticByMonth`
    }
    fetch(url)
    .then((response) => response.json())
    .then((result) => setOrder(result))
    .catch((error) => console.error(error));

},[dayOrderActive])


useEffect(() => {
  let url = '';
  if(dayRevenueActive === "Last one days"){
    url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/getSumTotalPriceStatisticByDay`
  }else if(dayCustomerActive === "Last 7 days"){
    url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/getSumTotalPriceStatisticByWeek`
  }else if(dayCustomerActive === "Last 30 days"){
    url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/getSumTotalPriceStatisticByMonth`
  }
  fetch(url)
  .then((response) => response.json())
  .then((result) => setRevenue(result))
  .catch((error) => console.error(error));

},[dayRevenueActive])




  return (
    <div className="dashboard-container">
        <h1>Dashboard</h1>
        <p>
          Admin / <span>DashBoard</span>
        </p>
        <div className='analytics-container'>
              <CardDashboard 
                setDayActive={setDayOrderActive}
                name={"Total Orders"}
                data={order}
                img={ICONS.icon_order_dashboard}
              />
              <CardDashboard 
                name={"New Customers"}
                setDayActive={setDayCustomerActive}
                data={newCustomer}
                img={ICONS.icon_user}
              />
              <CardDashboard
                setDayActive={setDayCustomerActive}
                name={""}
              />
              <CardDashboard 
                setDayActive={setDayRevenueActive}
                 name={"Total Revenue"}
                 data={revenue}
                 img={ICONS.icon_revenue_dashboard}
              />
        </div>
        <div className='container-chart-analytics'>
          <ChartAnalytics />
        </div>
          
       
       
        
    </div>
  )
}

export default Dashboard