import React from 'react'
import {
    fetchCurrentTemp,
    fetchFirstHighTempDay,
    fetchHighAmpliTempDay,
    fetchLastLowTempDay, fetchTotalRainDay
} from "../../../../actions/actionsArea";
import {connect} from "react-redux";
import ErrorMessage from "./ErrorMessage";

class AreaTempGeneral extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            option: this.props.option,
            startDate: this.props.startDate,
            endDate: this.props.endDate,
            day: this.props.day
        }
    }

    componentDidMount() {
        let moment = require('moment/moment')
        moment().format();
        const startDate = moment(this.state.startDate).format('YYYYMMDD');
        const endDate = moment(this.state.endDate).format('YYYYMMDD');
        const day = moment(this.state.day).format('YYYYMMDD');
        if(this.state.option === 1){
            this.props.onGetCurrent()}
        if(this.state.option === 2){
            this.props.onGetLastLow(startDate, endDate)}
        if(this.state.option === 3){
            this.props.onGetFirstHigh(startDate, endDate)}
        if(this.state.option === 4){
            this.props.onGetHighAmpli(startDate, endDate)}
        if(this.state.option === 5){
            this.props.onGetTotalRain(day)}
    }

    getProps(){
        if(this.state.option === 1){
            return this.props.currentTemp
        }

        if(this.state.option === 2){
            return this.props.lastLowTemp
        }

        if(this.state.option === 3){
            return this.props.firstHighTemp
        }

        if(this.state.option === 4){
            return this.props.highAmpliTemp
        }
        if(this.state.option === 5){
            return this.props.totalRain
        }
    }

    getString(){
        if(this.state.option === 2){
            return ('last day with the lowest maximum temperature')
        }

        if(this.state.option === 3){
            return ('first day with the highest maximum temperature')
        }

        if(this.state.option === 4){
            return ('day with the highest temperature amplitude')
        }
        if(this.state.option === 5){
            return ('the total for the selected day was')
        }
    }


    render() {
        const {loading, data, error} = this.getProps()
        let moment = require('moment/moment')
        moment().format();

        if(this.state.option === 1){
            if (loading === true) {
                return (<p>Loading ....</p>);
            } else {
                if (error !== null) {
                    return (
                        <ErrorMessage/>
                    )
                } else {
                    if (data) {
                        return (
                            <div>
                                <br/>
                                <p>The Current Temperature is:</p>

                                <p>{data.readingValue}ºC</p>

                            </div>
                        )
                    }
                }
            }
        }
        else{
            if (loading === true) {
            return (<p>Loading ....</p>);
        } else {
            if (error !== null) {
                return (
                    <ErrorMessage/>
                )
            } else {
                if (data) {
                    return (
                        <div>
                            <tr>{<td> the {this.getString()}, between the selected dates,
                                <tr>was in: {moment(data.readingDateAndTime).format('DD-MM-YYYY')}</tr>
                                <tr>with a value of: {data.readingValue} ºC</tr>
                            </td>}
                            </tr>

                        </div>
                    )
                }
            }
        }}

    }
}


const
    mapStateToProps = (state) => {
        return {
            currentTemp: {
                loading: state.currentTemp.currentTemp.loading,
                data: state.currentTemp.currentTemp.data,
                error: state.currentTemp.currentTemp.error,
            },
            lastLowTemp: {
                loading: state.currentTemp.lastLowTemp.loading,
                data: state.currentTemp.lastLowTemp.data,
                error: state.currentTemp.lastLowTemp.error,
            },
            firstHighTemp: {
                loading: state.currentTemp.firstHighTemp.loading,
                data: state.currentTemp.firstHighTemp.data,
                error: state.currentTemp.firstHighTemp.error,
            },
            highAmpliTemp: {
                loading: state.currentTemp.highAmpliTemp.loading,
                data: state.currentTemp.highAmpliTemp.data,
                error: state.currentTemp.highAmpliTemp.error,
            },
            totalRain:{
                loading: state.currentTemp.totalRain.loading,
                data: state.currentTemp.totalRain.data,
                error: state.currentTemp.totalRain.error,
            }
        }
    }


const
    mapDispatchToProps = dispatch => {
        return {
            onGetCurrent: () => {
                dispatch(fetchCurrentTemp());
            },
            onGetLastLow: (startDate, endDate) => {
                dispatch(fetchLastLowTempDay(startDate, endDate));
            },
            onGetFirstHigh:(startDate, endDate) => {
                dispatch(fetchFirstHighTempDay(startDate,endDate));
            },
            onGetHighAmpli:(startDate, endDate) => {
                dispatch(fetchHighAmpliTempDay(startDate,endDate));
            },
            onGetTotalRain:(day) => {
                dispatch(fetchTotalRainDay(day));
            },

        }
    };

export default connect(
    mapStateToProps,
    mapDispatchToProps
)

(AreaTempGeneral)
;

/*

npm install
npm install redux
npm install react-redux
npm install react-thunk
npm install axios
npm install react-router-dom
npm install redux-thunk
npm install @material-ui/core
npm install @material-ui/icons
npm install typeface-roboto --save
npm install moment
npm install react-calendar
npm start
*/