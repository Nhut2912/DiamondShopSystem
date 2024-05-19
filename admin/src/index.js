import React from 'react';
import ReactDOM from 'react-dom/client';

import './theme/index.css';
import Authentication from './Authentication';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <Authentication />
  </React.StrictMode>
);


reportWebVitals();
