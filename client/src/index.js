import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import reportWebVitals from './reportWebVitals';


import './theme/customer/index.css';
import App from './pages/customers/App'
import Home from './pages/customers/Home'
import Product from './pages/customers/Product'
import ProductDetail from './pages/customers/ProductDetail'
import Blog from './pages/customers/Blog'
import Contact from './pages/customers/Contact'
import CheckOutCart from './pages/customers/CheckOutCart'

import Authentication from './pages/admin/Authentication';
import Overview from './pages/admin/Overview';
import Dashboard from './pages/admin/Dashboard';
import ProductAdmin from './pages/admin/Product';
import OrderAdmin from './pages/admin/Order';
import AccountAdmin from './pages/admin/Account';
import WarrantyAdmin from './pages/admin/Warranty';
import PromotionsAdmin from './pages/admin/Promotions';
import GoldPriceAdmin from './pages/admin/GoldPrice';
import DiamondPriceAdmin from './pages/admin/DiamondPrice';
import NotificationsAdmin from './pages/admin/Notifications';
import DetailProductAdmin from './components/admin/DetailProduct';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
        <Routes> 
            <Route path='/' element={<App />}>
              <Route index element={<Home />} />
              <Route path='/home' element={<Home />} />
              <Route path="/products" element={<Product />}/>
              <Route path="/products/:id" element={<ProductDetail />}  />
              <Route path="/checkout-cart" element={<CheckOutCart />}/>
              <Route path="/blog" element={<Blog />} />
              <Route path="/contact" element={<Contact />} />
            </Route>
            <Route  path='/admin' element={<Authentication />}/>
            <Route path='/admin/overview' element={<Overview />}> 
                    <Route path='dashboard' element={<Dashboard />} />
                    <Route path="products" element={<ProductAdmin />} />
                    <Route path="products/:id" element={<DetailProductAdmin />} />
                    <Route path="order" element={<OrderAdmin />}/>
                    <Route path="account" element={<AccountAdmin />}/>
                    <Route path="warranty" element={<WarrantyAdmin />}/>
                    <Route path="promotions" element={<PromotionsAdmin />}/>
                    <Route path="gold-price" element={<GoldPriceAdmin />}/>
                    <Route path="diamond-price" element={<DiamondPriceAdmin />}/>
                    <Route path="notifications" element={<NotificationsAdmin />}/>
                 
            </Route>
        </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
