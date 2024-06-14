import React, { useEffect, useState } from 'react'

import '../../theme/customer/DescriptionCharacteristics.css';

function DescriptionCharacteristics({data}) {
 
  console.log(data);
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

 },[data])

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
            <span>2</span>
       </div>

       <div>
            <h4>Diamond characteristics</h4>
            <div>
                <ul>
                    <li>No. 1</li>
                    <li>
                        <span>
                            Origin 
                        </span>
                        <span>
                            NATURAL
                        </span>
                    </li>
                    <li>
                        <span>
                            Color 
                        </span>
                        <span>
                            K
                        </span>
                    </li>
                    <li>
                        <span>
                            Clarity 
                        </span>
                        <span>
                            SI2
                        </span>
                   </li>
                    <li>
                        <span>
                            Cut {"(Cut Score)"}
                        </span>
                        <span>
                        EX.
                        </span>
                    </li>
                    <li>
                        <span>
                            Carat
                        </span>
                        <span>
                            0.2
                        </span>
                    
                   </li>
                </ul>
                <ul>
                <li>No. 2</li>
                <li>
                        <span>
                            Origin 
                        </span>
                        <span>
                            NATURAL
                        </span>
                    </li>
                    <li>
                        <span>
                            Color 
                        </span>
                        <span>
                            K
                        </span>
                    </li>
                    <li>
                        <span>
                            Clarity 
                        </span>
                        <span>
                            SI2
                        </span>
                   </li>
                    <li>
                        <span>
                            Cut {"(Cut Score)"}
                        </span>
                        <span>
                        EX.
                        </span>
                    </li>
                    <li>
                        <span>
                            Carat
                        </span>
                        <span>
                            0.2
                        </span>
                    
                   </li>
                </ul>
                <ul>
                <li>No. 3</li>
                <li>
                        <span>
                            Origin 
                        </span>
                        <span>
                            NATURAL
                        </span>
                    </li>
                    <li>
                        <span>
                            Color 
                        </span>
                        <span>
                            K
                        </span>
                    </li>
                    <li>
                        <span>
                            Clarity 
                        </span>
                        <span>
                            SI2
                        </span>
                   </li>
                    <li>
                        <span>
                            Cut {"(Cut Score)"}
                        </span>
                        <span>
                        EX.
                        </span>
                    </li>
                    <li>
                        <span>
                            Carat
                        </span>
                        <span>
                            0.2
                        </span>
                    
                   </li>
                </ul>
            </div>
       </div>
       <p>
            <span>
            Money diamonds are jewelry that brings freedom and endless fashion inspiration.
           Owning your own diamond jewelry is what everyone desires. The ring is crafted from 
           14K gold and accented with diamonds, creating jewelry full of luxury and class.
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