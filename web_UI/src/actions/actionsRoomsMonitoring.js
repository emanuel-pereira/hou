import axios from 'axios';

export const FETCH_CURR_TEMP_ROOM_SUCCESS = 'FETCH_CURR_TEMP_ROOM_SUCCESS'
export const FETCH_CURR_TEMP_ROOM_FAILURE = 'FETCH_CURR_TEMP_ROOM_FAILURE'
export const FETCH_CURR_TEMP_ROOM_STARTED = 'FETCH_CURR_TEMP_ROOM_STARTED'


export const fetchCurrTempRooms = (id) => {
    return dispatch => {
        dispatch(fetchCurrTempRoomsStarted(id));
        axios
            .get(`https://localhost:8443/rooms/${id}/currentTemperature`)
            .then(res => {
                dispatch(fetchCurrTempRoomsSuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchCurrTempRoomsFailure(err.message));
            });
    };
};

export function fetchCurrTempRoomsStarted(id) {
    return {
        type: FETCH_CURR_TEMP_ROOM_STARTED,
        payload: {
            roomId: id,
        }

    }
}

export function fetchCurrTempRoomsSuccess(temperature) {

    return {

        type: FETCH_CURR_TEMP_ROOM_SUCCESS,
        payload: {
            data: {...temperature}

        }
    }
}

export function fetchCurrTempRoomsFailure(message) {
    return {
        type: FETCH_CURR_TEMP_ROOM_FAILURE,
        payload: {
            error: message
        }
    }
}


export const FETCH_ROOMS_MAX_TEMP_STARTED = 'FETCH_ROOMS_MAX_TEMP_STARTED'
export const FETCH_ROOMS_MAX_TEMP_SUCCESS = 'FETCH_ROOMS_MAX_TEMP_SUCCESS'
export const FETCH_ROOMS_MAX_TEMP_FAILURE = 'FETCH_ROOMS_MAX_TEMP_FAILURE'

export const fetchRoomMaxTemp = (id, day) => {
    return dispatch => {
        dispatch(fetchRoomMaxTempStarted(id, day));
        axios
            .get(`https://localhost:8443/rooms/${id}/maxTemperature?day=${day}`)
            .then(res => {
                dispatch(fetchRoomMaxTempSuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchRoomMaxTempFailure(err.message));
            });
    };
};


export function fetchRoomMaxTempStarted(id, day) {
    return {
        type: FETCH_ROOMS_MAX_TEMP_STARTED,

        payload:
            {
                roomId: id,
                day: day,

            }

    }
}

export function fetchRoomMaxTempSuccess(maxTemperature) {

    return {

        type: FETCH_ROOMS_MAX_TEMP_SUCCESS,
        payload: {
            data: {...maxTemperature},

        }
    }
}

export function fetchRoomMaxTempFailure(message) {
    return {
        type: FETCH_ROOMS_MAX_TEMP_FAILURE,
        payload: {
            error: message
        }
    }
}

