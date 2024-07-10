import React, { useState } from 'react'

import '../../theme/admin/CardDashboard.css'

import {ICONS} from '../../constants/admin'


const formattedNumber = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  minimumFractionDigits: 2,
})


function CardDashboard( {name,data,setDayActive,img}) {

  const [isTrendingDown,setIsTrendingDown] = useState(false);
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
      setDayActive(value)
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
            <img src={img} />
        </div>
        <h4>{name}</h4>
        <h2>{
        name === "Total Revenue" ? formattedNumber.format(data) : data
        }</h2>
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