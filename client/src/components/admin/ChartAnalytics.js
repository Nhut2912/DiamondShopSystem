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


const formatDate = (dateString) => {
    const dateObject = new Date(Date.parse(dateString));

    // Định dạng đối tượng Date theo định dạng "DD-MM" với dấu phân cách "-"
    const formattedDate = dateObject.toLocaleDateString("vi-VN", {
    day: "numeric",
    month: "numeric"
    });
    return formattedDate;
}

const sortDate = (data) => {
    const dateObjects = Object.entries(data).map(([dateString, value]) => ({
        date: new Date(dateString),
        value
      }));
    
      dateObjects.sort((a, b) => a.date - b.date);
      const sortedData = {};
      for (const obj of dateObjects) {
        sortedData[obj.date.toISOString().slice(0, 10)] = obj.value;
      }
      return sortedData;
}


function ChartAnalytics() {
 
 const [activeDays,setActiveDays] = useState("Week");

 const [maxValue,setMaxValue] = useState();
 const [keysArray,setKeysArray] = useState();
 const [valueArray,setValueArray] = useState();
 const [width,setWidth] = useState();
 const [height,setHeight] = useState();
 const [positionOptions,setPositionOptions] = useState();
 const [data,setData] = useState();

 const lastDayAnalytics = [
    "Week","Month"
 ]

 


 useEffect(() => {
    let url = "";
    if(activeDays === "Week"){
        url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/statisticToTalPriceByWeek`
    }else if(activeDays === "Month"){
        url= `${process.env.REACT_APP_API_ENDPOINT}/api/order/statisticToTalPriceByMonth`
    }
    fetch(url)
    .then((response) => response.json())
    .then((result) =>{
        setData(sortDate(result))
    } )
    .catch((error) => console.error(error));
    
 },[activeDays])


 useEffect(() => {
        if(data !== undefined && data !== null ){
            setMaxValue(findMaxValueAndRounded(data));
            setKeysArray(Object.keys(data));
            setValueArray(Object.values(data));
            const chart = document.getElementById("chart");
            const heightChart = chart.clientHeight;
            const widthChart = chart.clientWidth;
            setWidth(widthChart)
            setHeight(heightChart)
        }
 },[data])

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

 const handleChangeDays = (item) => {
    const chart = document.getElementById("chart");
    const canvasContext = chart.getContext("2d");
    canvasContext.clearRect(0, 0, chart.width, chart.height);
    setActiveDays(item);
 }

 const handleMouseMove = (event) => {
    const mouseX = event.nativeEvent.offsetX; 
    const mouseY = event.nativeEvent.offsetY;
  
 }

  return (
    <div className='chart-analytics-container'>
        <div className='header'>
            <h1>Revenue
                {
                    keysArray !== undefined && keysArray !== null &&
                    <span>{`${formatDate(keysArray[0])} - ${formatDate(keysArray[keysArray.length-1])} `}</span>
                }
                
            </h1>
            <div>
                {
                    lastDayAnalytics.map((item) => (
                        <div
                            onClick={() => handleChangeDays(item)}
                            className={activeDays === item ? "isFocus" : null}
                        >{item}</div>
                    ))
                }
            </div>
        </div>
        <div className='content-chart'>
            <ul className='numbers'>
                <li>{maxValue === 0 ? 10000 : maxValue}</li>
                <li>{maxValue === 0 ? 10000*75/100 :maxValue*75/100}</li>
                <li>{maxValue === 0 ? 10000*50/100  : maxValue*50/100}</li>
                <li>{maxValue === 0 ? 10000*25/100  : maxValue*25/100}</li>
                <li>{maxValue === 0 ? 0 : maxValue*0/100}</li>
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
                        <li><span>{formatDate(item)}</span></li>
                    ))
                }
                
            </ul>
        </div>
    </div>
  )
}

export default ChartAnalytics