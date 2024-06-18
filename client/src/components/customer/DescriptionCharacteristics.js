import React, { useEffect, useState } from 'react'

import '../../theme/customer/DescriptionCharacteristics.css';

function DescriptionCharacteristics({data}) {
 
  console.log(data.id);
 const [productWeight,setProductWeight] = useState();
 const [diamonds,setDiamonds] = useState();


 useEffect(() => {
    let product_weight = 0;
    if(data !== undefined && data !== null ){
        data.materials.map((item) => {
            product_weight += item.weight;
        })
    }
    setProductWeight(product_weight);
 },[data])

 useEffect(() => {

    if(data !== undefined && data !== null){
        fetch(`http://localhost:8080/api/diamond/product/${data.id}`)
        .then((response) => response.json())
        .then((result) => setDiamonds(result))
        .catch((error) => console.error(error));
    }


 },[data])

 console.log(diamonds);

  return (
    <div className='description-container'>

        <div>
           <h4>Weight of Product</h4> 
            <p>{productWeight !== undefined && productWeight !== null ? productWeight : ""} {"(g)"}</p>
       </div>

       <div>
           <h4>Number of materials</h4> 
           <p>{data !== undefined && data !== null ? data.materials.length : 0}</p>
       </div>

       <div>
           <h4>Materials</h4> 
           <div>
                {
                    data !== undefined && data !== null && data.materials.map((item) => (
                        <><p>{item.name}</p> <span> {item.weight} {"(g)"}</span></>
                    ))
                }
                
           </div>
       </div>

       <div>
            <h4>Number of diamonds</h4>
            <span>{diamonds !== undefined && diamonds !== null && diamonds.length }</span>
       </div>

       <div>
            <h4>Diamond characteristics</h4>
            <div>
                {
                    diamonds !== undefined && diamonds !== null && diamonds.map((item,index) => (

                        <ul>
                    <li>No. {index+1}</li>
                    <li>
                        <span>
                            Origin 
                        </span>
                        <span>
                            {item.origin}
                        </span>
                    </li>
                    <li>
                        <span>
                            Color 
                        </span>
                        <span>
                        {item.color}
                        </span>
                    </li>
                    <li>
                        <span>
                            Clarity 
                        </span>
                        <span>
                        {item.clarity}
                        </span>
                   </li>
                    <li>
                        <span>
                            Cut {"(Cut Score)"}
                        </span>
                        <span>
                        {item.cut}
                        </span>
                    </li>
                    <li>
                        <span>
                            Carat
                        </span>
                        <span>
                            {item.carat}
                        </span>
                    
                   </li>
                </ul>

                    ))
                }

                
                
            </div>
       </div>
       <p>
            <span>
            Money diamonds are jewelry that brings freedom and endless fashion inspiration.
           Owning your own diamond jewelry is what everyone desires. The ring is crafted from 
           {

            
                data !== undefined && data !== null && data.materials.map((item,index) => (
                  <><b> {item.name} </b>
                    <b>{index < data.materials.length-1 ? ", " : null}</b>
                  </>  
                ))
        

           } and accented with diamonds, creating jewelry full of luxury and class.
            </span>
            <br />
           <span>
           Diamonds are beautiful, diamond jewelry is even more 
           attractive. This new finish will definitely create a mark in modern fashion and help ladies stand out, be confident and attract everyone's admiration.
           </span>
       </p>
    </div>
  )
}

export default DescriptionCharacteristics