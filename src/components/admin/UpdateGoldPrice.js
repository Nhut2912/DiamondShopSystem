import React, { useState } from 'react';

function UpdateGoldPrice(){
    const [goldPriceData, setGoldPriceData] = useState({
        goldType: '',
        buyPrice: '',
        sellPrice: '',
      });

      const handleChange = (e) => {
        const { name, value } = e.target;
        setGoldPriceData({
          ...goldPriceData,
          [name]: value
        });
      };

      const handleSubmit = () => {
        // Logic to handle submit data
        console.log('Account Data Submitted: ', goldPriceData);
      };

      return (
        <div className='update-gold-price-container'>
          <table className='gold-price-table'>
            <thead>
              <tr>
                <th>Gold Type</th>
                <th>Buy Price</th>
                <th>Sell Price</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input
                    type='text'
                    name='goldType'
                    value={goldPriceData.goldType}
                    onChange={handleChange}
                    placeholder='M002'
                  />
                </td>
                <td>
                  <input
                    type='text'
                    name='buyPrice'
                    value={goldPriceData.buyPrice}
                    onChange={handleChange}
                    placeholder='300'
                  />
                </td>
                <td>
                  <input
                    type='text'
                    name='sellPrice'
                    value={goldPriceData.sellPrice}
                    onChange={handleChange}
                    placeholder='500'
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <button onClick={handleSubmit}>Add Account</button>
        </div>
      );
}
export default UpdateGoldPrice
