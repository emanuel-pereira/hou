import axios from 'axios';

export const FETCH_CURRENTTEMP_STARTED = 'FETCH_CURRENTTEMP_STARTED'
export const FETCH_CURRENTTEMP_SUCCESS = 'FETCH_CURRENTTEMP_SUCCESS'
export const FETCH_CURRENTTEMP_FAILURE = 'FETCH_CURRENTTEMP_FAILURE'


export const fetchCurrentTemp = () => {
    return dispatch => {
        dispatch(fetchCurrentTempStarted());
        axios
            .get(`https://localhost:8443/house/currentTemperature`)
            .then(res => {
                dispatch(fetchCurrentTempSuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchCurrentTempFailure(err.message));
            });
    };
};

export function fetchCurrentTempStarted () {
    return {
        type: FETCH_CURRENTTEMP_STARTED,
    }
}

export function fetchCurrentTempSuccess(currentTemp) {
    return {
        type: FETCH_CURRENTTEMP_SUCCESS,
        payload:{
            data: currentTemp
        }

    }
}
export function fetchCurrentTempFailure(message) {
    return {
        type: FETCH_CURRENTTEMP_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_LASTLOWTEMPDAY_STARTED = 'FETCH_LASTLOWTEMPDAY_STARTED'
export const FETCH_LASTLOWTEMPDAY_SUCCESS = 'FETCH_LASTLOWTEMPDAY_SUCCESS'
export const FETCH_LASTLOWTEMPDAY_FAILURE = 'FETCH_LASTLOWTEMPDAY_FAILURE'


export const fetchLastLowTempDay = (startDate,endDate) => {
    return dispatch => {
        dispatch(fetchLastLowTempDayStarted());
        axios
            .get(`https://localhost:8443/house/dailyMinimum?startDate=${startDate}&endDate=${endDate}`)
            .then(res => {
                dispatch(fetchLastLowTempDaySuccess(res.data));
            })
            .catch(err => {
                /*/!*if(error.response){*!/dispatch(fetchLastLowTempDayFailure(err.response.status));/!*}*!/*/
                dispatch(fetchLastLowTempDayFailure(err.status));
            });
    };
};

export function fetchLastLowTempDayStarted () {
    return {
        type: FETCH_LASTLOWTEMPDAY_STARTED,
        payload:{
        }
    }
}

export function fetchLastLowTempDaySuccess(lowTemp) {
    return {
        type: FETCH_LASTLOWTEMPDAY_SUCCESS,
        payload:{
            data:
                {...lowTemp}
        }

    }
}
export function fetchLastLowTempDayFailure(message) {
    return {
        type: FETCH_LASTLOWTEMPDAY_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_FIRSTHIGHTEMPDAY_STARTED = 'FETCH_FIRSTHIGHTEMPDAY_STARTED'
export const FETCH_FIRSTHIGHTEMPDAY_SUCCESS = 'FETCH_FIRSTHIGHTEMPDAY_SUCCESS'
export const FETCH_FIRSTHIGHTEMPDAY_FAILURE = 'FETCH_FIRSTHIGHTEMPDAY_FAILURE'


export const fetchFirstHighTempDay = (startDate,endDate) => {
    return dispatch => {
        dispatch(fetchFirstHighTempDayStarted());
        axios
            .get(`https://localhost:8443/house/dailyMaximum?startDate=${startDate}&endDate=${endDate}`)
            .then(res => {
                dispatch(fetchFirstHighTempDaySuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchFirstHighTempDayFailure(err.message));
            });
    };
};

export function fetchFirstHighTempDayStarted () {
    return {
        type: FETCH_FIRSTHIGHTEMPDAY_STARTED,
        payload:{

        }
    }
}

export function fetchFirstHighTempDaySuccess(highTemp) {
    return {
        type: FETCH_FIRSTHIGHTEMPDAY_SUCCESS,
        payload:{
            data:
                {...highTemp}
        }

    }
}
export function fetchFirstHighTempDayFailure(message) {
    return {
        type: FETCH_FIRSTHIGHTEMPDAY_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_HIGHAMPLITEMPDAY_STARTED = 'FETCH_HIGHAMPLITEMPDAY_STARTED'
export const FETCH_HIGHAMPLITEMPDAY_SUCCESS = 'FETCH_HIGHAMPLITEMPDAY_SUCCESS'
export const FETCH_HIGHAMPLITEMPDAY_FAILURE = 'FETCH_HIGHAMPLITEMPDAY_FAILURE'


export const fetchHighAmpliTempDay = (startDate,endDate) => {
    return dispatch => {
        dispatch(fetchHighAmpliTempDayStarted());
        axios
            .get(`https://localhost:8443/house/dailyMaxAmplitude?startDate=${startDate}&endDate=${endDate}`)
            .then(res => {
                dispatch(fetchHighAmpliTempDaySuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchHighAmpliTempDayFailure(err.message));
            });
    };
};

export function fetchHighAmpliTempDayStarted () {
    return {
        type: FETCH_HIGHAMPLITEMPDAY_STARTED,
        payload:{

        }
    }
}

export function fetchHighAmpliTempDaySuccess(highAmpli) {
    return {
        type: FETCH_HIGHAMPLITEMPDAY_SUCCESS,
        payload:{
            data:
                {...highAmpli}
        }

    }
}
export function fetchHighAmpliTempDayFailure(message) {
    return {
        type: FETCH_HIGHAMPLITEMPDAY_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_TOTALRAINDAY_STARTED = 'FETCH_TOTALRAINDAY_STARTED'
export const FETCH_TOTALRAINDAY_SUCCESS = 'FETCH_TOTALRAINDAY_SUCCESS'
export const FETCH_TOTALRAINDAY_FAILURE = 'FETCH_TOTALRAINDAY_FAILURE'


export const fetchTotalRainDay = (day) => {
    return dispatch => {
        dispatch(fetchTotalRainDayStarted());
        axios
            .get(`https://localhost:8443/house/totalRainfall?day=${day}`)
            .then(res => {
                dispatch(fetchTotalRainDaySuccess(res.data));
            })
            .catch(err => {
                dispatch(fetchTotalRainDayFailure(err.message));
            });
    };
};

export function fetchTotalRainDayStarted () {
    return {
        type: FETCH_HIGHAMPLITEMPDAY_STARTED,
        payload:{
        }
    }
}

export function fetchTotalRainDaySuccess(totalRain) {
    return {
        type: FETCH_HIGHAMPLITEMPDAY_SUCCESS,
        payload:{
            data:
                {...totalRain}
        }

    }
}
export function fetchTotalRainDayFailure(message) {
    return {
        type: FETCH_HIGHAMPLITEMPDAY_FAILURE,
        payload: {
            error: message
        }
    }
}
