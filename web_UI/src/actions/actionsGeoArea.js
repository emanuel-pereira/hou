import axios from 'axios';

export const FETCH_GEOAREAS_STARTED = 'FETCH_GEOAREAS_STARTED'
export const FETCH_GEOAREAS_SUCCESS = 'FETCH_GEOAREAS_SUCCESS'
export const FETCH_GEOAREAS_FAILURE = 'FETCH_GEOAREAS_FAILURE'
export const FETCH_GEOAREASTYPES_SUCCESS = 'FETCH_GEOAREASTYPES_SUCCESS'
export const ADD_EXT_SENSOR = 'ADD_EXT_SENSOR'
export const DELETE_EXT_SENSOR = 'DELETE_EXT_SENSOR'

axios.defaults.headers.common['Authorization'] = JSON.parse(localStorage.getItem('token')); // for all requests

export function fetchGeoAreas() {
  return dispatch => {
    dispatch(fetchGeoAreasStarted());
    axios
      .get(`https://localhost:8443/geoareas`)
      .then(res => {
        dispatch(fetchGeoAreasSuccess(res.data._embedded.geographicalAreaDToes));
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
    payload: {
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

export const FETCH_GEOAREA_DETAILS_SUCCESS = 'FETCH_ROOM_DETAILS_SUCCESS'
export const FETCH_GEOAREA_DETAILS_FAILURE = 'FETCH_ROOM_DETAILS_FAILURE'

export const fetchGADetails = (id) => {
  return dispatch => {
    axios
      .get(`https://localhost:8443/geoareas/${id}`)
      .then(res => {
        dispatch(fetchGADetailsSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchGADetailsFailure(err.message));
      });
  };
};


export function fetchGADetailsSuccess(details) {
  return {
    type: FETCH_GEOAREA_DETAILS_SUCCESS,
    payload: {
      data: details
    }

  }
}
export function fetchGADetailsFailure(message) {
  return {
    type: FETCH_GEOAREA_DETAILS_FAILURE,
    payload: {
      error: message
    }
  }
}

export const ADD_GEOAREA = 'ADD_GEOAREA'

export const createGeoArea = ({ id, designation, gaType, latitude, longitude, altitude, length, width }) => {
  return (dispatch) => {
    return axios.post(`https://localhost:8443/geoareas`, {
      identification: id,
      designation: designation,
      type: {
        type: gaType
      },
      location: {
        latitude: latitude,
        longitude: longitude,
        altitude: altitude
      },
      occupation: {
        length: length,
        width: width
      }
    })
      .then(response => {
        dispatch(createGeoAreaSuccess(response.data))
        dispatch(fetchGeoAreas(response.data))
      })
      .catch(error => {
        throw (error);
      });
  };
};

export const createGeoAreaSuccess = (data) => {
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

export const FETCH_GEOAREA_SENSORS_SUCCESS = 'FETCH_GEOAREA_SENSORS_SUCCESS'
export const FETCH_GEOAREA_SENSORS_FAILURE = 'FETCH_GEOAREA_SENSORS_FAILURE'

export const fetchGASensors = (id) => {
  return dispatch => {
    axios
      .get(`https://localhost:8443/externalSensors/${id}/geoArea`)
      .then(res => {
        dispatch(fetchGASensorsSuccess(res.data._embedded.externalSensorDToes, id));
      })
      .catch(err => {
        dispatch(fetchGASensorsFailure(err.message, id));
      });
  };
};

export function fetchGASensorsSuccess(sensors, id) {
  return {
    type: FETCH_GEOAREA_SENSORS_SUCCESS,
    payload: {
      data: sensors,
      gaId: id,
    }
  };
}

export function fetchGASensorsFailure(message, id) {
  return {
    type: FETCH_GEOAREA_SENSORS_FAILURE,
    payload: {
      error: message,
      gaId: id
    }
  }
}
export const createSensor = ({ id, gaId, name, latitude, longitude, altitude, sensorTypeName, startDate, unit, active }) => {
  return (dispatch) => {
    return axios.post(`https://localhost:8443/externalSensors/`,
      {
        id: id,
        locationDTO: {
          latitude: latitude,
          longitude: longitude,
          altitude: altitude
        },
        sensorBehaviorDTO: {
          name: name,
          sensorType: {
            type: sensorTypeName
          },
          startDate: startDate,
          unit: unit,
          active: true
        },
        idGA: gaId
      }
    )
      .then(response => {
        dispatch(createExtSensorSuccess(response.data))
        dispatch(fetchGASensors(gaId))
      })
      .catch(error => {
        throw (error);
      });
  };
};

export const createExtSensorSuccess = (data) => {
  return {
    type: ADD_EXT_SENSOR,
    payload: {
      id: data.id,
      roomId: data.roomID,
      name: data.name,
      sensorTypeName: data.sensorType,
      startDate: data.startDate,
      unit: 'C',
      active: true,
    }
  }
};

export const deleteSensor = (id,idGA) => {
  return dispatch => {
    axios
      .delete(`https://localhost:8443/externalSensors/${id}`)
      .then(res => {
        dispatch(deleteSensorSuccess(res.data));
        dispatch(fetchGASensors(idGA))
      })
      .catch(error => {
        throw (error);
      });
  };
};

export function deleteSensorSuccess() {
  return {
    type: DELETE_EXT_SENSOR,
  }
}

