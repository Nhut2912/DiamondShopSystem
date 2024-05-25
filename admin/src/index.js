import React from 'react';
import ReactDOM from 'react-dom/client';

import './theme/index.css';
import Authentication from './Authentication';
import reportWebVitals from './reportWebVitals';
import Overview from './pages/Overview';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Product from './pages/Product';
import Order from './pages/Order';
import Account from './pages/Account';
import Warranty from './pages/Warranty';
import Promotions from './pages/Promotions';
import GoldPrice from './pages/GoldPrice';
import DiamondPrice from './pages/DiamondPrice';
import Notifications from './pages/Notifications';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

      <BrowserRouter>
        <Routes>
            <Route path="/" element={<Authentication />} />
            <Route path="/admin" element={<Overview />}> 
                <Route index element={<Dashboard />} />
                <Route path="product" element={<Product />} >
                    
                </Route>
                <Route path="order" element={<Order />}/>
                <Route path="account" element={<Account />}/>
                <Route path="warranty" element={<Warranty />}/>
                <Route path="promotions" element={<Promotions />}/>
                <Route path="gold-price" element={<GoldPrice />}/>
                <Route path="diamond-price" element={<DiamondPrice />}/>
                <Route path="notifications" element={<Notifications />}/>
            </Route>
        </Routes>
    </BrowserRouter>

  </React.StrictMode>
);


reportWebVitals();
