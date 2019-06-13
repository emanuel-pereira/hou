import axios from 'axios';


export const FETCH_HOUSEGRID_STARTED = 'FETCH_HOUSEGRID_STARTED'
export const FETCH_HOUSEGRID_SUCCESS = 'FETCH_HOUSEGRIDS_SUCCESS'
export const FETCH_HOUSEGRID_FAILURE = 'FETCH_HOUSEGRID_FAILURE'


export function fetchHouseGrids() {
    return dispatch => {
        dispatch(fetchHouseGridsStarted());
        axios
            .get(`http://localhost:8080/housegrids`)
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


export const FETCH_HOUSEGRID_DETAILS_STARTED = 'FETCH_HOUSEGRID_DETAILS_STARTED'
export const FETCH_HOUSEGRID_DETAILS_SUCCESS = 'FETCH_HOUSEGRID_DETAILS_SUCCESS'
export const FETCH_HOUSEGRID_DETAILS_FAILURE = 'FETCH_HOUSEGRID_DETAILS_FAILURE'
export const ADD_HOUSEGRID = 'ADD_HOUSEGRID';

export const fetchHouseGridDetails = (id) => {
    return dispatch => {
        dispatch(fetchHouseGridDetailsStarted(id));
        axios
            .get(`http://localhost:8080/housegrids/${id}/rooms`)
            .then(res => {
                dispatch(fetchHouseGridDetailsSuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchHouseGridDetailsFailure(err.message));
            });
    };
};


export function fetchHouseGridDetailsStarted(id) {
    return {
        type: FETCH_HOUSEGRID_DETAILS_STARTED,
        payload: {
            houseGridId: id
        }
    }
}

export function fetchHouseGridDetailsSuccess(details) {
    return {
        type: FETCH_HOUSEGRID_DETAILS_SUCCESS,
        payload: {
            hgRoom: details.name
        }

    }
}

export function fetchHouseGridDetailsFailure(message) {
    return {
        type: FETCH_HOUSEGRID_DETAILS_FAILURE,
        payload: {
            error: message
        }
    }
}


export const createHouseGrid = ({id, designation}) => {
    return (dispatch) => {
        return axios.post(`http://localhost:8080/housegrids/`, {id, designation})
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