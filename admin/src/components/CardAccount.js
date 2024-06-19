import React from 'react'

import '../../theme/admin/CardAccount.css';
import { useNavigate } from 'react-router-dom';

function CardAccount(){
    const navigate = useNavigate();
    const accountId = 123;
    const handClick = () => {

        console.log("...");
        navigate(""+accountId);
    }
    return (
        <div className='content-table-account-container' onClick={handClick} style={{'cursor':'pointer'}}>
            <ul className='table-account-content'>
               <li>12345</li>
               <li>john.doe@example.com</li>
               <li>John Doe</li>
               <li>+1-234-567-8901</li>
               <li>
                <div className='status-account'>
                    <span>
                        Active
                    </span>
                </div>
               </li>
               <li>Admin</li>
            </ul>
        </div>
    )
}

export default CardAccount
