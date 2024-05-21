import React from 'react';
import ReactDOM from 'react-dom/client';

import './theme/index.css';
import Authentication from './Authentication';
import reportWebVitals from './reportWebVitals';
import Overview from './pages/Overview';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Product from './components/Product';
import Order from './components/Order';
import Account from './components/Account';
import Warranty from './components/Warranty';
import Promotions from './components/Promotions';
import GoldPrice from './components/GoldPrice';
import DiamondPrice from './components/DiamondPrice';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

      <BrowserRouter>
        <Routes>
            <Route path="/" element={<Authentication />} />
            <Route path="/admin" element={<Overview />}> 
                <Route index element={<Dashboard />} />
                <Route path="product" element={<Product />}/>
                <Route path="order" element={<Order />}/>
                <Route path="account" element={<Account />}/>
                <Route path="warranty" element={<Warranty />}/>
                <Route path="promotions" element={<Promotions />}/>
                <Route path="gold-price" element={<GoldPrice />}/>
                <Route path="diamond-price" element={<DiamondPrice />}/>
            </Route>
        </Routes>
    </BrowserRouter>

  </React.StrictMode>
);


reportWebVitals();
