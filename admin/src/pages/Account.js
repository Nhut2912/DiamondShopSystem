import React, {useState} from 'react' 

import '../../theme/admin/Account.css'
import HeadTableCardAccount from '../../components/admin/HeadTableCardAccount';
import CardAccount from '../../components/admin/CardAccount';
import AddAccount from '../../components/admin/AddAccount';

function Account(){
  const [activeNavigations,setActiveNavigations] = useState('View Account');
  const [isAddAccount,setAddAccount] = useState(false);

  const navigationAccount = [
    {name: 'View Account'},
    {name: 'Add Account'}
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
          Admin/ <span>Account</span>
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
                       <CardAccount/>
                       <CardAccount/>
                       <CardAccount/>
                       <CardAccount/>
                       <CardAccount/>
                       <CardAccount/>
                       <CardAccount/>
                  </div>
              </div>
            ) :<AddAccount/>
            }
        </div>
    </div>
  )
}

export default Account
