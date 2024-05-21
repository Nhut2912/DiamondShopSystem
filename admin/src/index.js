import React from 'react';
import ReactDOM from 'react-dom/client';

import './theme/index.css';
import Authentication from './Authentication';
import reportWebVitals from './reportWebVitals';
import Overview from './pages/Overview';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

      <BrowserRouter>
        <Routes>
            <Route path="/" element={<Authentication />} />
            <Route path="/admin" element={<Overview />} />
        </Routes>
    </BrowserRouter>

  </React.StrictMode>
);


reportWebVitals();
