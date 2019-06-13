
import {
  FETCH_ROOMS_STARTED,
  FETCH_ROOMS_SUCCESS,
  FETCH_ROOMS_FAILURE,
  
  FETCH_ROOM_DETAILS_STARTED,
  FETCH_ROOM_DETAILS_SUCCESS,
  FETCH_ROOM_DETAILS_FAILURE,
  
  ADD_ROOM,
  FETCH_ROOM_SENSORS_SUCCESS,

} from '../actions/actionsRoom'

import {
  FETCH_HOUSEGRID_STARTED,
  FETCH_HOUSEGRID_SUCCESS,
  FETCH_HOUSEGRID_FAILURE,
  
  FETCH_HOUSEGRID_DETAILS_STARTED,
  FETCH_HOUSEGRID_DETAILS_SUCCESS,
  FETCH_HOUSEGRID_DETAILS_FAILURE,
  
  ADD_HOUSEGRID,

} from '../actions/actionsHouseGrid'



const initialstate = {
  rooms: {
    loading: false,
    error: null,
    data: [],
  },
  details: {
    roomId: 0,
    loading: false,
    error: null,
    data: {},
  },
  sensors:{
    data: []},

houseGrids: {
    loading: false,
    error: null,
    data: [],
  },
};


function roomsReducer(state = initialstate, action) {
  switch (action.type) {
    case FETCH_ROOMS_STARTED:
      return {
        ...state,
        rooms: {
          loading: true,
          error: null,
          data: []
        }
      }
    case FETCH_ROOMS_SUCCESS:
      return {
        ...state,
        rooms: {
          loading: false,
          error: null,
          data: [...action.payload.data]
        }
      }
    case FETCH_ROOMS_FAILURE:
      return {
        ...state,
        rooms: {
          loading: false,
          error: action.payload.error,
          data: [],
        }
      }
    case FETCH_ROOM_DETAILS_STARTED:
      return {
        ...state,
        details: {
          loading: true,
          error: null,
          data: {},
          id: action.payload.id,
        }
      }
    case FETCH_ROOM_DETAILS_SUCCESS:
      return {
        ...state,
        details: {
          loading: false,
          error: null,
          data: {...action.payload.data},
          id: state.details.id,
        }
      }
    case FETCH_ROOM_DETAILS_FAILURE:
      return {
        ...state,
        details: {
          loading: false,
          error: action.payload.error,
          data: {},
          id: 0,
        }
      }
    case ADD_ROOM:
      return {
        ...state,
        data: {...action.payload.data},
      }

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
      case FETCH_HOUSEGRID_DETAILS_STARTED:
        return {
          ...state,
          details: {
            loading: true,
            error: null,
            data: {},
            id: action.payload.id,
          }
        }
      case FETCH_HOUSEGRID_DETAILS_SUCCESS:
        return {
          ...state,
          details: {
            loading: false,
            error: null,
            data: {...action.payload.data},
            id: state.details.id,
          }
        }
      case FETCH_HOUSEGRID_DETAILS_FAILURE:
        return {
          ...state,
          details: {
            loading: false,
            error: action.payload.error,
            data: {},
            id: 0,
          }
        }
      case ADD_HOUSEGRID:
        return {
          ...state,
          data: {...action.payload.data},
        }
        case FETCH_ROOM_SENSORS_SUCCESS:
          return {
            ...state,
            sensors: {
            data: [...action.payload.data]
          }
        }

    default:
      return state
  }
}


export default roomsReducer;
