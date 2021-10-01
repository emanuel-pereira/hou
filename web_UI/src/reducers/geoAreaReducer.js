
import {
    FETCH_GEOAREAS_STARTED,
    FETCH_GEOAREAS_SUCCESS,
    FETCH_GEOAREAS_FAILURE,
    ADD_GEOAREA,
    FETCH_GEOAREASTYPES_SUCCESS,
    FETCH_GEOAREA_DETAILS_SUCCESS,
    FETCH_GEOAREA_DETAILS_FAILURE,
    FETCH_GEOAREA_SENSORS_SUCCESS,
    FETCH_GEOAREA_SENSORS_FAILURE,
    ADD_EXT_SENSOR,
    FETCH_SENSORTYPES_SUCCESS,
    DELETE_EXT_SENSOR,

} from '../actions/actionsGeoArea'

const initialstate = {
    geoareas: {
        loading: false,
        error: null,
        data: [],
    },
    details: {
        gaId: '',
        loading: false,
        error: null,
        data: {},
    },
    sensors: {
        data: [],
        error: null,
        gaId: null
    },
    gaTypes: {
        data: []
    }
};


function geoAreaReducer(state = initialstate, action) {
    switch (action.type) {
        case FETCH_GEOAREAS_STARTED:
            return {
                ...state,
                geoareas: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_GEOAREAS_SUCCESS:
            return {
                ...state,
                geoareas: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_GEOAREAS_FAILURE:
            return {
                ...state,
                geoareas: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }
        case FETCH_GEOAREA_DETAILS_SUCCESS:
            return {
                ...state,
                details: {
                    loading: false,
                    error: null,
                    data: { ...action.payload.data },
                    id: state.details.id,
                }
            }
        case FETCH_GEOAREA_DETAILS_FAILURE:
            return {
                ...state,
                details: {
                    loading: false,
                    error: action.payload.error,
                    data: {},
                    id: 0,
                }
            }
        case ADD_GEOAREA:
            return {
                ...state,
                data: { ...action.payload.data },
            }

        case FETCH_GEOAREASTYPES_SUCCESS:
            return {
                ...state,
                gaTypes: {
                    data: [...action.payload.data],
                }
            }

        case FETCH_GEOAREA_SENSORS_SUCCESS:
            return {
                ...state,
                sensors: {
                    data: [...action.payload.data],
                    error: null,
                    gaId: action.payload.gaId
                }
            }
        case FETCH_GEOAREA_SENSORS_FAILURE:
            return {
                ...state,
                sensors: {
                    data: [],
                    error: action.payload.error,
                    gaId: action.payload.gaId
                }
            }
        case DELETE_EXT_SENSOR:
            return {
                ...state
            }

        default:
            return state
    }
}
export default geoAreaReducer;
