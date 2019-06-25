import {

    FETCH_CURR_TEMP_ROOM_SUCCESS,
    FETCH_CURR_TEMP_ROOM_FAILURE,
    FETCH_CURR_TEMP_ROOM_STARTED,

    FETCH_ROOMS_MAX_TEMP_STARTED,
    FETCH_ROOMS_MAX_TEMP_SUCCESS,
    FETCH_ROOMS_MAX_TEMP_FAILURE,

} from '../actions/actionsRoomsMonitoring'


const initialstate = {
    /*room: {
        loading: false,
        error: null,
        data: [],
    },*/

    roomCurrentTemp: {
        roomId: 0,
        loading: false,
        error: null,
        data: {},
    },

    roomMaxTemp: {
        id: 0,
        loading: false,
        error: null,
        data: {},
        day: null,
    },
};


function roomMonitoringReducer(state = initialstate, action) {
    switch (action.type) {
        case FETCH_CURR_TEMP_ROOM_STARTED:
            return {
                ...state,
                roomCurrentTemp: {
                    roomId: action.payload.id,
                    loading: true,
                    error: null,
                    data: {},

                }
            }
        case FETCH_CURR_TEMP_ROOM_SUCCESS:
            return {
                ...state,
                roomCurrentTemp: {
                    roomId: state.roomCurrentTemp.roomId,
                    data: {...action.payload.data}

                }
            }
        case FETCH_CURR_TEMP_ROOM_FAILURE:
            return {
                ...state,
                roomCurrentTemp: {
                    loading: false,
                    error: state.roomCurrentTemp.error,
                    value: {},
                    roomId: 0,
                }
            }

        case FETCH_ROOMS_MAX_TEMP_STARTED:
            return {
                ...state,
                roomMaxTemp: {
                    loading: true,
                    error: null,
                    data: {},
                    id: null,
                    day: null,
                }
            }
        case FETCH_ROOMS_MAX_TEMP_SUCCESS:

            return {
                ...state,
                roomMaxTemp: {
                    data: {...action.payload.data},
                    day: state.roomMaxTemp.day,
                    id: state.roomMaxTemp.id,
                }
            }
        case FETCH_ROOMS_MAX_TEMP_FAILURE:
            return {
                ...state,
                roomMaxTemp: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                    id: 0,
                    day: null,
                }
            }
        default:
            return state
    }
}

export default roomMonitoringReducer;

