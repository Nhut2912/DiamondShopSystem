import React, { useEffect, useState } from 'react'

import '../../theme/admin/ChartAnalytics.css'
import { calculateNewValue } from '@testing-library/user-event/dist/utils';

const findMaxValueAndRounded = (data) => {
    const highestValue = Math.max(...Object.values(data));
    const roundedValue = Math.round(highestValue / 10000) * 10000;
    if(roundedValue < highestValue){
        return roundedValue + 10000;
    }
    return roundedValue;
}


function ChartAnalytics() {
 
 const [activeDays,setActiveDays] = useState("Day");
 const [chartPaddingTop,setChartPaddingTop] = useState();
 const [maxValue,setMaxValue] = useState();
 const [keysArray,setKeysArray] = useState();
 const [valueArray,setValueArray] = useState();
 const lastDayAnalytics = [
    "Day","Week","Month","Year"
 ]

 const  data = {
    "1" : 123000,
    "2" : 0,
    "3" : 2000,
    "4" : 0,
    "5" : 1000,
    "6" : 3000,
    "7" : 80000,
    "8" : 3000,
    "9" : 80000,
    "10" : 1000,
    "11" : 3000,
    "12" : 80000,
    "13" : 3000,
    "14" : 80000,
    "15" : 123000,
    "16" : 0,
    "17" : 2000,
    "18" : 0,
    "19" : 1000,
    "20" : 3000,
    "21" : 80000,
    "22" : 3000,
    "23" : 80000,
    "24" : 1000,
    "25" : 3000,
    "26" : 80000,
    "27" : 3000,
    "29" : 80000,
    "30" : 80000
 }

 useEffect(() => {
    const chart = document.getElementById("chart");
    const containerChart = document.getElementById("container-chart");
    setChartPaddingTop(containerChart.clientHeight/5-10)
    setMaxValue(findMaxValueAndRounded(data));
    setKeysArray(Object.keys(data));
    setValueArray(Object.values(data));
    //COng65 them 25% cua max value     
    // heightChart 100% = maxValue + maxValu*25%;
    const heightChart = chart.clientHeight;
    const widthChart = chart.clientWidth;
    const stepOptions  = widthChart/7;
    const rangeMaxValue = maxValue + maxValue*25/100;
    
    const canvasContext = chart.getContext("2d");



 },[])

 

  return (
    <div className='chart-analytics-container'>
        <div className='header'>
            <h1>Revenue
                <span>{"0h - 24h 23/06/2024"}</span>
            </h1>
            <div>
                {
                    lastDayAnalytics.map((item) => (
                        <div
                            className={activeDays === item ? "isFocus" : null}
                        >{item}</div>
                    ))
                }
            </div>
        </div>
        <div className='content-chart'>
            <ul className='numbers'>
                <li>{maxValue}</li>
                <li>{maxValue*75/100}</li>
                <li>{maxValue*50/100}</li>
                <li>{maxValue*25/100}</li>
                <li>{maxValue*0/100}</li>
            </ul>
            <div id="container-chart"
                
            className='chart-canvas'>
                <canvas id="chart" />
            </div>
           
            <ul 
                style={{"column-gap": `calc(100%/${valueArray!== undefined && 
                    valueArray !== null ?
                    valueArray.length : 0})`}}
            className='options'>
                {
                    keysArray !== undefined && keysArray !== null&&
                    keysArray.map((item) => (
                        <li>{item}</li>
                    ))
                }
                
            </ul>
        </div>
    </div>
  )
}

export default ChartAnalytics