import React from 'react'

import '../../theme/customer/Contact.css'

function Contact() {
  return (
    <div className='contact-container'>
         <p>
              Home / <span>Blog</span>
          </p>
          <div className='contact-content'>
              <div className='contact-us'>
                  <span>Say hi to the team</span>
                  <h1>Contact Us</h1>
                  <div className='content-input-contact'>
                        <p>Free to contact us and we will get back to you as soon as we can</p>

                        <input placeholder='Name' type='text' />
                        <input placeholder='Email address' type='text' />
                        <input placeholder='Tell us at about it' type='text' />
                        <div>Send</div>
                  </div>
              </div>
              <div className='information-us'>
                  <div>
                  <div>
                      <h4>opening hours</h4>
                      <p>Monday-Friday : <span>9am-5pm</span></p>
                      
                      <p>Weekend : <span>Closed</span></p>
                    
                  </div>
                  <div>
                      <h4>address</h4>
                      <p>Lot E2a-7, Street D1, D. D1, Long Thanh My, Thu Duc City, Ho Chi Minh City 700000</p>
                  </div>
                  <div>
                      <h4>support</h4>
                      <p>jewelry@gmail.com</p>
                      <p>+08409090909</p>
                  </div>
                  </div>
              </div>
          </div>
    </div>
  )
}

export default Contact