import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import roomReducer from '../reducers/roomReducer';
import gridsReducer from '../reducers/gridsReducer';
import roomMonitoringReducer from '../reducers/roomMonitoringReducer';
import areaMonitoringReducer from '../reducers/areaMonitoringReducer';
import { combineReducers } from 'redux';
import loginReducer from "../reducers/loginReducer";

const rootReducer = combineReducers({
    rooms: roomReducer,
    grids: gridsReducer,
    roomCurrentTemp: roomMonitoringReducer,
    currentTemp: areaMonitoringReducer,
    login: loginReducer
})

export default function configureStore() {
    console.log("Store: configureStore");
    return createStore(rootReducer, applyMiddleware(thunk));
}