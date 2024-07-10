import React, { useState } from 'react'

import '../../theme/customer/AccountProfile.css';
import HeadAccount from './HeadAccount';



function AccountProfile() {


  return (
    <div className='account-profile-container'>
          <HeadAccount />
          <p>
              Home / <span> Profile </span>
          </p>
          
          <div className='account-information'>
            <h3>Account Information</h3>
                <ul>
                    <li>
                        <h3>Name</h3>
                        <div>
                            <input type='text' />
                        </div>
                    </li>
                    <li>
                        <h3>Date Of Birth</h3>
                        <div>
                            <input type='date' />
                        </div>
                    </li>
                    <li>
                        <h3>Gender</h3>
                        <div>
                            <div className='isActive'>
                                <div>

                                </div>
                                <span>
                                    Male
                                </span>
                            </div>
                            <div>
                                <div>

                                </div>
                                <span>
                                    Female
                                </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3>Number Phone</h3>
                        <div>
                            <div>

                            </div>
                            <input type='number' />
                        </div>
                    </li>
                    <li>
                        <h3>Email</h3>
                        <div>
                            <input type='text' />
                        </div>
                    </li>
                    <li>
                        <h3>Address</h3>
                        <div>
                            <input type='text' />
                        </div>
                    </li>
                </ul>
            <div className='change-profile'>
                <span>Change Profile Information</span>
            </div>
          </div>
    </div>
  )
}

export default AccountProfile