
import {
    FETCH_GEOAREAS_STARTED,
    FETCH_GEOAREAS_SUCCESS,
    FETCH_GEOAREAS_FAILURE,
    ADD_GEOAREA,
    FETCH_GEOAREASTYPES_SUCCESS,
} from '../actions/actionsGeoArea'

const initialstate = {
    geoareas: {
        loading: false,
        error: null,
        data: [],
    },
    gaTypes:{
        data:[]
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

        default:
            return state
    }
}
export default geoAreaReducer;
