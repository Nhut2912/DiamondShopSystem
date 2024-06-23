import React, { useEffect, useState } from 'react'

import '../../theme/admin/ChartAnalytics.css'


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

 const [maxValue,setMaxValue] = useState();
 const [keysArray,setKeysArray] = useState();
 const [valueArray,setValueArray] = useState();
 const [width,setWidth] = useState();
 const [height,setHeight] = useState();
 const [positionOptions,setPositionOptions] = useState();


 const lastDayAnalytics = [
    "Day","Week","Month","Year"
 ]

 const  data = {
    "1" : 1000,
    "2" : 0,
    "3" : 2000,
    "4" : 0,
    "5" : 1000,
    "6" : 3000,
    "7" : 1000
 }


 useEffect(() => {
        setMaxValue(findMaxValueAndRounded(data));
        setKeysArray(Object.keys(data));
        setValueArray(Object.values(data));
        const chart = document.getElementById("chart");
        const heightChart = chart.clientHeight;
        const widthChart = chart.clientWidth;
        setWidth(widthChart)
        setHeight(heightChart)
 },[])

 useEffect(() => {
    const options = document.getElementById("options");
    const optionElements = options.children; // Get all child option elements
    let positionLeft = []
    let positionRoot = 0;
    for (let i = 0; i < optionElements.length; i++) {
        const optionElement = optionElements[i];
        const position = optionElement.getBoundingClientRect(); // Get position info
        console.log(position.width)

        if(i === 0){
            positionRoot = position.left;
        }
        positionLeft.push(position.left - positionRoot);
     
    }
    setPositionOptions(positionLeft);
 },[valueArray])



 useEffect(() => {
    if(valueArray !== undefined && valueArray !== null && positionOptions !== undefined && positionOptions !== null){
        const chart = document.getElementById("chart");
       
        const heightChart = chart.clientHeight;
        const rangeMaxValue = maxValue + maxValue*25/100;


        const canvasContext = chart.getContext("2d");
        
        
        const gradient = canvasContext.createLinearGradient(width/2,0, width/2,height);
        gradient.addColorStop(0,"#d9b384");
        gradient.addColorStop(1,"#ffffff");


        canvasContext.beginPath();
        for(let i = 1; i < valueArray.length ; i++){
            const x_previous = heightChart - (valueArray[i-1] / rangeMaxValue)*heightChart;
            const y_previous = positionOptions[i-1];
            const x = heightChart - (valueArray[i] / rangeMaxValue)*heightChart;
            const y = positionOptions[i];

            canvasContext.lineWidth = 1.5;
            canvasContext.moveTo(y_previous,x_previous);
            canvasContext.lineTo(y,x);
            canvasContext.strokeStyle ="#d9b384"
        }
        canvasContext.stroke();
        
        for(let i = 1; i < valueArray.length ; i++){
            const x_previous = heightChart - (valueArray[i-1] / rangeMaxValue)*heightChart;
            const y_previous = positionOptions[i-1];
            const x = heightChart - (valueArray[i] / rangeMaxValue)*heightChart;
            const y = positionOptions[i];



            const points = [
                {x: heightChart, y: y_previous},
                {x: x_previous, y: y_previous}, 
                 {x: x, y: y},
                {x: heightChart, y: y},
            ]   
    
            canvasContext.beginPath();
            canvasContext.moveTo(points[0].y,points[0].x);
            for(let i = 1; i < points.length; i++){
                canvasContext.lineTo(points[i].y,points[i].x);
            }
            canvasContext.closePath();
            canvasContext.fillStyle = gradient;
            canvasContext.fill();
    
       
        }



    
       

        for(let i = 0; i < valueArray.length ; i++){
            canvasContext.beginPath();
            const x = heightChart - (valueArray[i] / rangeMaxValue)*heightChart;
            const y = positionOptions[i];
         
       
                canvasContext.arc(y,x,2,0,2*Math.PI)
                canvasContext.stroke();
                canvasContext.fillStyle = "#d9b384"
                canvasContext.fill();
            
             
                
        }
            
    }
       
    
 },[valueArray,positionOptions])


 const handleMouseMove = (event) => {
    const mouseX = event.nativeEvent.offsetX; 
    const mouseY = event.nativeEvent.offsetY;
    console.log(mouseX,mouseY)
 }

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
                <canvas 
                    onMouseMove={handleMouseMove}
                    width={width}
                    height={height}
                id="chart" />
            </div>
           
            <ul     id="options"
                style={{"column-gap": `calc(100%/${valueArray!== undefined && 
                    valueArray !== null ?
                    valueArray.length : 0})`}}
            className='options'>
                {
                    keysArray !== undefined && keysArray !== null&&
                    keysArray.map((item) => (
                        <li><span>{item}</span></li>
                    ))
                }
                
            </ul>
        </div>
    </div>
  )
}

export default ChartAnalytics