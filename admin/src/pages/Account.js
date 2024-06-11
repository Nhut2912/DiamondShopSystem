import React, { useState } from 'react'

import '../../theme/admin/Account.css'

const Account = () => {
  const [users, setUsers] = useState([
    { id: 1, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 2, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 3, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 4, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 5, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 6, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 7, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 8, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 9, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 10, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 11, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
    { id: 12, email: '', name: '', phone:'', Address:'', Role:'', Status:'' },
  ]);

  const handleDelete = (id) => {
    setUsers(users.filter(user => user.id !== id));
  };

  const handleAddUser = () => {
    const newUser = { id: 'new id', email: '', name: '', phone:'', Address:'', Role:'', Status:''  };
    setUsers([...users, newUser]);
  };

  return (
    <div className="manage-account">
      <div className="main-content">
        <header>
          <h1>Manage Account</h1>
          <button onClick={handleAddUser} className="add-user-btn">+ Add User</button>
        </header>
        <table className="users-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Email</th>
              <th>Name</th>
              <th>Phone</th>
              <th>Address</th>
              <th>Role</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {users.map(user => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.email}</td>
                <td>{user.name}</td>
                <td>{user.phone}</td>
                <td>{user.address}</td>
                <td>{user.role}</td>
                <td>{user.status}</td>
                <td><button onClick={() => handleDelete(user.id)} className="delete-btn">Delete</button></td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="pagination">
          <button>Previous</button>
          <button>1</button>
          <button>2</button>
          <button>3</button>
          <button>Next</button>
        </div>
      </div>
    </div>
  );
};

export default Account