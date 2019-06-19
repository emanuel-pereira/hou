import axios from 'axios';
import {applyMiddleware as dispatch} from "redux";

export const USER_LOGIN_STARTED = 'USER_LOGIN_STARTED';
export const USER_LOGIN_SUCCESS = 'USER_LOGIN_SUCCESS';
export const USER_LOGIN_FAILURE = 'USER_LOGIN_FAILURE';
export const USER_LOGOUT = 'USER_LOGOUT';

export const sendUsernamePassword = ({username, password}) => {
        return axios
            .post(`https://localhost:8443/login`, JSON.stringify({
                username,
                password
            }), {headers: {'Content-Type': 'application/json'}})
            .then(response => {
                return response.headers['authorization'];
            })
            .then(token => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('token', JSON.stringify(token));
                window.location.reload();
            })
    }
;

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



