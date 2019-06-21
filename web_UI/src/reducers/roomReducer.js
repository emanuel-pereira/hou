
import {
  FETCH_ROOMS_STARTED,
  FETCH_ROOMS_SUCCESS,
  FETCH_ROOMS_FAILURE,

  FETCH_ROOM_DETAILS_STARTED,
  FETCH_ROOM_DETAILS_SUCCESS,
  FETCH_ROOM_DETAILS_FAILURE,

  ADD_ROOM,
  FETCH_ROOM_SENSORS_SUCCESS,
  FETCH_ROOM_SENSORS_FAILURE,
  ADD_SENSOR,
  FETCH_SENSORTYPES_SUCCESS,

} from '../actions/actionsRoom'

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
  sensors: {
    data: [],
    error: null,
    roomId: null
  },
  sTypes: {
    data: []
  }
};


function roomReducer(state = initialstate, action) {
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
          data: { ...action.payload.data },
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
        data: { ...action.payload.data },
      }

    case FETCH_ROOM_SENSORS_SUCCESS:
      return {
        ...state,
        sensors: {
          data: [...action.payload.data],
          error: null,
          roomId: action.payload.roomId
        }
      }
    case FETCH_ROOM_SENSORS_FAILURE:
      return {
        ...state,
        sensors: {
          data: [],
          error: action.payload.error,
          roomId: action.payload.roomId
        }
      }
    case ADD_SENSOR:
      return {
        ...state,
      
    }
    case FETCH_SENSORTYPES_SUCCESS:
      return {
        ...state,
        sTypes: {
          data: [...action.payload.data],
        }
      }
    default:
      return state
  }
}
export default roomReducer;
