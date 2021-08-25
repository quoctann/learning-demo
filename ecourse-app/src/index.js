import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { createStore } from 'redux';
import { reducer } from './UserReducer';
import { Provider } from 'react-redux';
// import DemoRedux from './DemoRedux';
// import Parent from './DemoContext1';
// import User from './User';

const store = createStore(reducer);

const render = () => ReactDOM.render(
  <Provider store={store}>
    <App/>
    {/* <DemoRedux 
      value={store.getState()}
      helloVi={() => store.dispatch({"type": "vi"})}
      helloEn={() => store.dispatch({"type": "en"})}
    /> */}
    {/* <Parent/> */}
    {/* <User firstName="Tan" lastName="Tran"/> */}
  </Provider>,
  document.getElementById('root')
);

render();
store.subscribe(render);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
