import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';


import './theme/index.css';
import Home from './pages/Home';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Product from './pages/Product';
import Blog from './pages/Blog';
import Contact from './pages/Contact';
import App from './pages/App';
import ProductDetail from './pages/ProductDetail';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
        <Routes> 
            <Route path='/' element={<App />}>
              <Route index element={<Home />} />
              <Route path='/home' element={<Home />} />
              <Route path="/products" element={<Product />}/>
              <Route path="/blog" element={<Blog />} />
              <Route path="/contact" element={<Contact />} />
            </Route>
            <Route path="/products/:id" element={<ProductDetail />}  />
        </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
