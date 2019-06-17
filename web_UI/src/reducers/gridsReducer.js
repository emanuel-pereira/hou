import {
    FETCH_HOUSEGRID_STARTED,
    FETCH_HOUSEGRID_SUCCESS,
    FETCH_HOUSEGRID_FAILURE,
    ADD_HOUSEGRID,

} from '../actions/actionsGrid'

const initialstate = {
    houseGrids: {
        loading: false,
        error: null,
        data: [],
    }
}

function gridsReducer(state = initialstate, action) {
    switch (action.type) {
        case FETCH_HOUSEGRID_STARTED:
            return {
                ...state,
                houseGrids: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_HOUSEGRID_SUCCESS:
            return {
                ...state,
                houseGrids: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_HOUSEGRID_FAILURE:
            return {
                ...state,
                houseGrids: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }
        case ADD_HOUSEGRID:
            return {
                ...state,
                data: { ...action.payload.data },
            }
        default:
            return state
    }
}
export default gridsReducer;
