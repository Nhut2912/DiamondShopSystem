import React, { useState } from 'react'

import '../../theme/admin/Warranty.css'
import WarrantyView from '../../components/admin/WarrantyView';
import WarrantyPolicy from '../../components/admin/WarrantyPolicy';


function Warranty() {
  
  const navigate = [
    {name : "View Warranties"},
    {name : "Warranty Policy"}
  ]

  const [navigateActive,setNavigateActive] = useState("View Warranties");


  const handleClickNavigate = (value) => {
    setNavigateActive(value);
  }

  return (
    <div className='warranty-container'>
        <h1>Warranty</h1>
        <p>
          Admin / <span>Warranty</span>
        </p>
        <div className='warranty-navigation'>
            <ul className='navigator'>
                {
                  navigate.map((item) => (
                    <li
                      className={navigateActive === item.name ? "isActive" : null}
                      onClick={() => handleClickNavigate(item.name)}
                    >{item.name}</li>
                  ))
                }
            </ul>
            

            <div className='warranty-content-container'>
              {
                navigateActive !== undefined && navigateActive !== null &&
                navigateActive === "View Warranties"
                ? <WarrantyView />  : <WarrantyPolicy />
              }
                
            </div>

        </div>
    </div>
  )
}

export default Warranty