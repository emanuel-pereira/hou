import axios from 'axios';

export const FETCH_GEOAREAS_STARTED = 'FETCH_GEOAREAS_STARTED'
export const FETCH_GEOAREAS_SUCCESS = 'FETCH_GEOAREAS_SUCCESS'
export const FETCH_GEOAREAS_FAILURE = 'FETCH_GEOAREAS_FAILURE'
export const ADD_GEOAREA = 'ADD_GEOAREA'
export const FETCH_GEOAREASTYPES_SUCCESS = 'FETCH_GEOAREASTYPES_SUCCESS'

axios.defaults.headers.common['Authorization'] = JSON.parse(localStorage.getItem('token')); // for all requests

export function fetchGeoAreas (){
    return dispatch => {
        dispatch(fetchGeoAreasStarted());
        axios
            .get(`https://localhost:8443/geoareas`)
            .then(res => {
                dispatch(fetchGeoAreasSuccess(res.data['_embedded']['geographicalAreaDToes']));
            })
            .catch(err => {
                dispatch(fetchGeoAreasFailure(err.message));
            });
    };
};

export function fetchGeoAreasStarted() {
    return {
        type: FETCH_GEOAREAS_STARTED,
    }
}

export function fetchGeoAreasSuccess(geoareas) {
    return {
        type: FETCH_GEOAREAS_SUCCESS,
        payload:{
            data:
                [...geoareas]
        }

    }
}
export function fetchGeoAreasFailure(message) {
    return {
        type: FETCH_GEOAREAS_FAILURE,
        payload: {
            error: message
        }
    }
}


export const createGeoArea = ({ id, designation, type, latitude, longitude, altitude, length, width }) => {
    return (dispatch) => {
        return axios.post(`https://localhost:8443/geoareas`, {id, designation, type, latitude, longitude, altitude, length, width})
            .then(response => {
                dispatch(createGeoAreaSuccess(response.data))
                dispatch(fetchGeoAreas(response.data))
            })
            .catch(error => {
                throw(error);
            });
    };
};

export const createGeoAreaSuccess =  (data) => {
    return {
        type: ADD_GEOAREA,
        payload: {
            id: data.id,
            designation: data.designation,
            type: data.type,
            latitude: data.latitude,
            longitude: data.longitude,
            altitude: data.altitude,
            length: data.length,
            width: data.width

        }
    }
};

export function fetchGeoAreaTypes() {
    return dispatch => {
        axios
            .get(`https://localhost:8443/typeGAs`)
            .then(res => {
                dispatch(fetchGeoAreaTypesSuccess(res.data._embedded.typeGAs));
            })
            .catch(error => {
                throw (error);
            });
    };
};

export function fetchGeoAreaTypesSuccess(gaTypes) {
    return {
        type: FETCH_GEOAREASTYPES_SUCCESS,
        payload: {
            data: [...gaTypes]
        }
    }
}
