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
import Order from './pages/customers/Order';
import Login from './pages/customers/Login';
import Verify from './pages/customers/Verify';
import Account from './pages/customers/Account';
import AccountProfile from './components/customer/AccountProfile';
import AccountPurchase from './components/customer/AccountPurchase';

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
import OrderComplete from './pages/customers/OrderComplete';
import OrderCarddetailAdmin from './pages/admin/OrderCarddetail';
import CheckPaymentMomo from './components/customer/CheckPaymentMomo';




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
              <Route path="/checkout-cart">
                <Route index element={<CheckOutCart />} />
                
                  <Route path="order" element={<Order />} />
                  <Route path="complete" element={<OrderComplete />} />
              </Route>
              <Route path="/blog" element={<Blog />} />
              <Route path="/contact" element={<Contact />} />
            </Route>
            <Route path="/checkout-cart/check-payment" element={<CheckPaymentMomo />} />
            <Route path='/login'  >
              <Route index element={<Login />} />
              <Route path='verify' element={<Verify />} />
            </Route>

            <Route path='/account' element={<Account />} >
                <Route index element={<AccountProfile />}/>
                <Route path="purchase" element={<AccountPurchase />} />
            </Route>


            <Route  path='/admin' element={<Authentication />}/>
            <Route path='/admin/overview' element={<Overview />}> 
                    <Route index element={<Dashboard />} />
                    <Route path="products" element={<ProductAdmin />} />
                    <Route path="products/:id" element={<DetailProductAdmin />} />
                    <Route path="order" element={<OrderAdmin />}/>
                    <Route path="order/:id" element={<OrderCarddetailAdmin />}/>
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
