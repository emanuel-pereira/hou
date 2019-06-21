import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import roomReducer from '../reducers/roomReducer';
import gridsReducer from '../reducers/gridsReducer';
import areaMonitoringReducer from '../reducers/areaMonitoringReducer';
import { combineReducers } from 'redux';
import loginReducer from "../reducers/loginReducer";
import geoAreaReducer from "../reducers/geoAreaReducer";

const rootReducer = combineReducers({
    rooms: roomReducer,
    grids: gridsReducer,
    geoareas: geoAreaReducer,
    currentTemp: areaMonitoringReducer,
    login: loginReducer
})

export default function configureStore() {
    console.log("Store: configureStore");
    return createStore(rootReducer, applyMiddleware(thunk));
}