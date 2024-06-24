import React, {useEffect, useState} from 'react' 

import '../../theme/admin/Account.css'
import HeadTableCardAccount from '../../components/admin/HeadTableAccount';
import CardAccount from '../../components/admin/CardAccount';
import AddAccount from '../../components/admin/AddAccount';

function Account(){
  const [activeNavigations,setActiveNavigations] = useState('View Account');
  const [isAddAccount,setAddAccount] = useState(false);

  const [data,setData] = useState();


  useEffect(() => {
    fetch("${process.env.REACT_APP_API_ENDPOINT}/api/account")
    .then((response) => response.json())
    .then((result) => setData(result))
    .catch((error) => console.error(error));
  },[])



  const navigationAccount = [
    {name: 'View Account'},
    {name: 'Create Account'}
  ]

  const handleClick = (item) => {
    if(item === navigationAccount[0].name){
        setAddAccount(false)
    }else{
        setAddAccount(true)
    }
    setActiveNavigations(item);
  }

  return(
    <div className='account-container'>
        <h1>Account</h1>
        <p>
          Admin / <span>Account</span>
        </p>
        <div className='account-navigation'>
            <ul>
              {
                navigationAccount.map((item) => (
                  <li 
                    key={item.name}
                    className={item.name === activeNavigations ? 'isActive' : ''}
                    onClick={() => handleClick(item.name)}
                  >
                      {item.name}
                  </li>
                ))
              }
            </ul>
        </div>
        <div className='account-content'>
            {!isAddAccount ? 
            (
              <div className='account-view'>
                  <HeadTableCardAccount />
                  <div className='account-items-container'>
                  {
                    data !== undefined && data !== null && data.map((item) => (
                      <CardAccount data={item}/>
                    ))
                  }
           
                  </div>
              </div>
            ) :<AddAccount/>
            }
        </div>
    </div>
  )
}

export default Account