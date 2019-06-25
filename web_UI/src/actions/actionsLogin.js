import axios from 'axios';
import {applyMiddleware as dispatch} from "redux";
import Home from "../views/Home";
import HouseArea from "../views/HouseArea";
import Room from "../views/Room";
import HouseGrid from "../views/HouseGrid";
import HouseMonitoring from "../components/project/HouseMonitoring/HouseMonitoring";
import RoomMonitoringMenu from "../views/RoomMonitoringMenu";

export const USER_LOGIN_STARTED = 'USER_LOGIN_STARTED';
export const USER_LOGIN_SUCCESS = 'USER_LOGIN_SUCCESS';
export const USER_LOGIN_FAILURE = 'USER_LOGIN_FAILURE';
export const USER_LOGOUT = 'USER_LOGOUT';

export var routes = [];

export const sendUsernamePassword = ({username, password}) => {
        return axios
            .post(`https://localhost:8443/login`, JSON.stringify({
                username,
                password
            }), {headers: {'Content-Type': 'application/json'}})
            .then(response => {
                setRoutes(username);
                console.log(routes);
                return response.headers['authorization'];
            })
            .then(token => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('token', JSON.stringify(token));
                window.location.reload();
            })
    }
;

function setRoutes(username) {
    if (username === "admin") {
        routes = [
            {
                path: "/Home",
                name: "Home",
                icon: "nc-icon nc-bank",
                component: Home,
                layout: "/admin"
            },
            {
                path: "/geoarea",
                name: "House Area",
                icon: "nc-icon nc-pin-3",
                component: HouseArea,
                layout: "/admin"
            },
            {
                path: "/rooms",
                name: "Rooms",
                icon: "nc-icon nc-app",
                component: Room,
                layout: "/admin"
            },
            {
                path: "/housegrids",
                name: "Power Grids",
                icon: "nc-icon nc-layout-11",
                component: HouseGrid,
                layout: "/admin"
            }];
    } else
        routes = [
            {
                path: "/monitoring",
                name: "House Area",
                icon: "nc-icon nc-chart-bar-32",
                component: HouseMonitoring,
                layout: "/admin"
            },
            {
                path: "/roomMonitoring",
                name: "Room Monitoring",
                icon: "nc-icon nc-chart-bar-32",
                component: RoomMonitoringMenu,
                layout: "/admin"
            }];
    localStorage.setItem('routes', JSON.stringify(routes));
}

export const logout = () => {
    // remove user from local storage to log user out
    localStorage.removeItem('token');
    return {
        type: USER_LOGOUT
    }
}

export function userLoginStarted() {
    return {
        type: USER_LOGIN_STARTED,
    }
}

export function userLoginSuccess(response) {
    return {
        type: USER_LOGIN_SUCCESS,
        payload: response
    }
}

export function userLoginFailure(message) {
    return {
        type: USER_LOGIN_FAILURE,
        payload: {
            error: message
        }
    }
}



