import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import App from './App';
import 'typeface-roboto';
import Menu from './components/Menu.js';
import './style/index.css';
import configureStore from './store/configureStore';



const store = configureStore()
ReactDOM.render(
    <Provider store={store}>
        <App />
        <Menu />
    </Provider>,
    document.getElementById('root'));


