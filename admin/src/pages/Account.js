import React from 'react'

function Account() {
  return (
    <div style={{color: 'white'}} className="Account" >
      <meta charSet='utf-8'></meta>
      <h1>Account</h1>
      <a href="">Create</a><br/>
      <a href="">Add</a><br/>
      <table border="1">
      <thead>
        <tr>
          <td>ID</td>
          <td>Email</td>
          <td>Name</td>
          <td>Phone</td>
          <td>Address</td>
          <td>Role</td>
          <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <tr>
        <td>0001</td>
          <td>abc@gmail.com</td>
          <td>Nguyễn Văn A</td>
          <td>0123456789</td>
          <td>123bc</td>
          <td>Staff</td>
          <td>Active</td>
          <td><a href="">View</a></td>
          <td><a href="">Delete</a></td>
          <td><a href="">Edit</a></td>
        </tr>
        </tbody>
      </table>
    </div>
  )
}

export default Account