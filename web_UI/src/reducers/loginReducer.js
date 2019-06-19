import {
    USER_LOGIN_STARTED,
    USER_LOGIN_SUCCESS,
    USER_LOGIN_FAILURE
} from '../actions/actionsLogin'

export const initialState = {
    currentUser: {
        isLoggedIn: false,
        error: null
    }
};

export function loginReducer(state = initialState, action) {
    switch (action.type) {
        case USER_LOGIN_STARTED:
            return {
                ...state,
                currentUser: {
                    isLoggedIn: false,
                    error: null
                }
            };
        case USER_LOGIN_SUCCESS:
            return {
                ...state,
                currentUser: {
                    isLoggedIn: true,
                    error: null
                }
            };
        case USER_LOGIN_FAILURE:
            return {
                ...state,
                currentUser: {
                    isLoggedIn: false,
                    error: "Username or password are incorrect. Please try again."
                }
            };
        default:
            return state;
    }
}

export default loginReducer;
