import React, { useState } from 'react';


import '../../theme/admin/AddAccount.css';

function AddAccount() {
  const [accountData, setAccountData] = useState({
    id: '',
    email: '',
    name: '',
    phone: '',
    role: '',
    status: 'Active'
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAccountData({
      ...accountData,
      [name]: value
    });
  };

  const handleSubmit = () => {
    // Logic to handle submit data
    console.log('Account Data Submitted: ', accountData);
  };

  return (
    <div className='add-account-container'>
      <table className='account-table'>
        <thead>
          <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input
                type='text'
                name='id'
                value={accountData.id}
                onChange={handleChange}
                placeholder='#12345'
              />
            </td>
            <td>
              <input
                type='email'
                name='email'
                value={accountData.email}
                onChange={handleChange}
                placeholder='john.doe@example.com'
              />
            </td>
            <td>
              <input
                type='text'
                name='name'
                value={accountData.name}
                onChange={handleChange}
                placeholder='John Doe'
              />
            </td>
            <td>
              <input
                type='text'
                name='phone'
                value={accountData.phone}
                onChange={handleChange}
                placeholder='+1-234-567-8901'
              />
            </td>
            <td>
              <input
                type='text'
                name='role'
                value={accountData.role}
                onChange={handleChange}
                placeholder='Admin'
              />
            </td>
            <td>
              <select
                name='status'
                value={accountData.status}
                onChange={handleChange}
              >
                <option value='Active'>Active</option>
                <option value='Inactive'>Inactive</option>
              </select>
            </td>
          </tr>
        </tbody>
      </table>
      <button onClick={handleSubmit}>Add Account</button>
    </div>
  );
}

export default AddAccount;