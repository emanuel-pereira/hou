import axios from 'axios';

export const FETCH_ROOMS_STARTED = 'FETCH_ROOMS_STARTED'
export const FETCH_ROOMS_SUCCESS = 'FETCH_ROOMS_SUCCESS'
export const FETCH_ROOMS_FAILURE = 'FETCH_ROOMS_FAILURE'
export const FETCH_ROOM_DETAILS_STARTED = 'FETCH_ROOM_DETAILS_STARTED'
export const FETCH_ROOM_DETAILS_SUCCESS = 'FETCH_ROOM_DETAILS_SUCCESS'
export const FETCH_ROOM_DETAILS_FAILURE = 'FETCH_ROOM_DETAILS_FAILURE'
export const ADD_ROOM = 'ADD_ROOM'
export const UPDATE_ROOM = 'UPDATE_ROOM'
export const FETCH_ROOM_SENSORS_SUCCESS = 'FETCH_ROOM_SENSORS_SUCCESS'

axios.defaults.headers.common['Authorization'] = JSON.parse(localStorage.getItem('token')); // for all requests

export function fetchRooms (){
  return dispatch => {
    dispatch(fetchRoomsStarted());
    axios
      .get(`https://localhost:8443/rooms`)
      .then(res => {
        dispatch(fetchRoomsSuccess(res.data['_embedded']['roomDToes']));
      })
      .catch(err => {
        dispatch(fetchRoomsFailure(err.message));
      });
  };
};

export function fetchRoomsStarted () {
  return {
    type: FETCH_ROOMS_STARTED,
  }
}

export function fetchRoomsSuccess(rooms) {
  return {
    type: FETCH_ROOMS_SUCCESS,
    payload:{
      data:
        [...rooms]
    }

  }
}
export function fetchRoomsFailure(message) {
  return {
    type: FETCH_ROOMS_FAILURE,
    payload: {
      error: message
    }
  }
}

export const fetchRoomDetails = (id) => {
  return dispatch => {
    dispatch(fetchRoomDetailsStarted(id));
    axios
      .get(`https://localhost:8443/rooms/${id}`)
      .then(res => {
        dispatch(fetchRoomDetailsSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchRoomDetailsFailure(err.message));
      });
  };
};


export function fetchRoomDetailsStarted(id) {
  return {
    type: FETCH_ROOM_DETAILS_STARTED,
    payload:{
      roomId: id
    }
  }
}

export function fetchRoomDetailsSuccess(details) {
  return {
    type: FETCH_ROOM_DETAILS_SUCCESS,
    payload:{
      data: details
    }

  }
}
export function fetchRoomDetailsFailure(message) {
  return {
    type: FETCH_ROOM_DETAILS_FAILURE,
    payload: {
      error: message
    }
  }
}


export const createRoom = ({ id, description, floor, length, width, height }) => {
  return (dispatch) => {
    return axios.post(`https://localhost:8443/rooms/`, {id, description, floor, length, width, height})
      .then(response => {
        dispatch(createRoomSuccess(response.data))
        dispatch(fetchRooms(response.data))
      })
      .catch(error => {
        throw(error);
      });
  };
};

export const createRoomSuccess =  (data) => {
  return {
    type: ADD_ROOM,
    payload: {
      id: data.id,
      description: data.description,
      floor: data.floor,
      length: data.length,
      width: data.width,
      height: data.height
    }
  }
};

export const updateRoom = ({ id, description, floor, length, width, height }) => {
  return (dispatch) => {
    return axios.put(`https://localhost:8443/rooms/${id}`, {id, description, floor, length, width, height})
      .then(response => {
        dispatch(updateRoomSuccess(response.data))
        dispatch(fetchRoomDetailsSuccess(response.data))
        dispatch(fetchRooms(response.data))
      })
      .catch(error => {
        throw(error);
      });
  };
};

export const updateRoomSuccess =  (data) => {
  return {
    type: UPDATE_ROOM,
    payload: {
      id: data.id,
      description: data.description,
      floor: data.floor,
      length: data.length,
      width: data.width,
      height: data.height
    }
  }
};

export const fetchRoomSensors = (id) => {
  return dispatch => {
    axios
      .get(`https://localhost:8443/rooms/${id}/sensors`)
      .then(res => {
        dispatch(fetchRoomSensorsSuccess(res.data));
      })
      .catch(error => {
        throw(error);
      });
  };
};

export function fetchRoomSensorsSuccess(sensors) {
  return {
    type: FETCH_ROOM_SENSORS_SUCCESS,
    payload:{
      data: [...sensors]
    }
  };
}
