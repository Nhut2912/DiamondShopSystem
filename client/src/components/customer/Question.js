import React from 'react'

import '../../theme/customer/Questions.css';
import { ICONS } from '../../constants/customer';
function Question() {
  return (
    <div className='question-container'>
        <h4>FAQs</h4>
        <h1>Frequently asked questions</h1>
        <div className='questions-content'>
            
            <ul>
                    <li>
                        <span>
                            What is a diamond clarity ?
                        </span>
                        <div>
                            <img src={ICONS.icon_drop_down} alt='' />
                        </div>
                    </li>
                    <li>
                        <span>
                            What is a diamond clarity ?
                        </span>
                        <div>
                            <img src={ICONS.icon_drop_down} alt='' />
                        </div>
                    </li>
                    <li>
                        <span>
                            What is a diamond clarity ?
                        </span>
                        <div>
                            <img src={ICONS.icon_drop_down} alt='' />
                        </div>
                    </li>
                   
            </ul>
            <ul>
                <li>
                    <span>
                        What is a diamond clarity ?
                    </span>
                    <div>
                         <img src={ICONS.icon_drop_down} alt='' />
                    </div>
                    
                </li>
                <li >
                    <span>
                        What is a diamond clarity ?
                    </span>
                    <div>
                         <img src={ICONS.icon_drop_down} alt='' />
                    </div>
                </li>
                <li>
                        <span>
                            What is a diamond clarity ?
                        </span>
                        <div>
                            <img src={ICONS.icon_drop_down} alt='' />
                        </div>
                    </li>
                    <li>
                        <span>
                            What is a diamond clarity ?
                        </span>
                        <div>
                            <img src={ICONS.icon_drop_down} alt='' />
                        </div>
                    </li>
                
          
            </ul>
           
            
        </div>
    </div>
  )
}

export default Question