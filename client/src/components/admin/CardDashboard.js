import React, { useState } from 'react'

import '../../theme/admin/CardDashboard.css'

import {ICONS} from '../../constants/admin'

function CardDashboard() {

  const [isTrendingDown,setIsTrendingDown] = useState(true);
  const [lastDaysActive,setLastDaysActive] = useState("Last one days");
  const [isDropdown,setDropDown] = useState(false);

  const listLastDays =[
    "Last 30 days","Last 7 days","Last one days"
  ]

  const handleDropdown = () => {
    setDropDown(!isDropdown);
  }
  
  const handleChooseLastdays = (value) => {
    if(isDropdown){
      setLastDaysActive(value)
      setDropDown(!isDropdown)
    }
    
    
  }

  return (
    <div className='card-dashboard-analy'>
        <div className='date-period' >
          {lastDaysActive !== undefined && lastDaysActive !== null ?
            <span
              onClick={handleDropdown}
            >{lastDaysActive}</span>: 0
          }
          
          <ul className={isDropdown ? 'isActive' : ''}>
              {
                listLastDays.map((item) =>(
                  <li 
                  onClick={() => handleChooseLastdays(item)}
                  className={lastDaysActive === item ? "isFocus": null}>{item}</li>
                ))
              }
            
          </ul>
        </div>
        <div className='icon'>
            <img src={ICONS.icon_user} />
        </div>
        <h4>New Customers</h4>
        <h2>1,726</h2>
        <div className={isTrendingDown ?'percent isTrendingDown' :  'percent isTrending'}>
          {
            !isTrendingDown
            ? <>
              <img src={ICONS.icon_trending_up} />
              12%
            </> : 
            <>
            <img src={ICONS.icon_trending_down} />
            12%
          </> 
          }
          
        </div>
    </div>
  )
}

export default CardDashboard