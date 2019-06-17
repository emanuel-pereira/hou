import axios from 'axios';
export const FETCH_HOUSEGRID_STARTED = 'FETCH_HOUSEGRID_STARTED'
export const FETCH_HOUSEGRID_SUCCESS = 'FETCH_HOUSEGRIDS_SUCCESS'
export const FETCH_HOUSEGRID_FAILURE = 'FETCH_HOUSEGRID_FAILURE'
export const ADD_HOUSEGRID = 'ADD_HOUSEGRID';

export function fetchHouseGrids() {
    return dispatch => {
        dispatch(fetchHouseGridsStarted());
        axios
            .get(`https://localhost:8443/housegrids`)
            .then(res => {
                dispatch(fetchHouseGridsSuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchHouseGridsFailure(err.message));
            });
    };
};

export function fetchHouseGridsStarted() {
    return {
        type: FETCH_HOUSEGRID_STARTED,

    }
}

export function fetchHouseGridsSuccess(housegrids) {
    return {
        type: FETCH_HOUSEGRID_SUCCESS,
        payload: {
            data:
                [...housegrids]
        }

    }
}

export function fetchHouseGridsFailure(message) {
    return {
        type: FETCH_HOUSEGRID_FAILURE,
        payload: {
            error: message
        }
    }
}

export const createHouseGrid = ({id, designation}) => {
    return (dispatch) => {
        return axios.post(`https://localhost:8443/housegrids/`, {id, designation})
            .then(response => {
                dispatch(createHouseGridSuccess(response.data))
                dispatch(fetchHouseGrids(response.data))
            })
            .catch(error => {
                throw(error);
            });
    };
};

export const createHouseGridSuccess = (data) => {
    return {
        type: ADD_HOUSEGRID,
        payload: {
            designation: data.designation,
        }
    }
};