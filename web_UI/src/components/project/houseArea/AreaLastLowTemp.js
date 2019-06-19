import React from 'react'
import Calendar from 'react-calendar';
import {fetchLastLowTempDay} from "../../../actions/actionsArea";
import {connect} from "react-redux";

class AreaLastLowTemp extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            firstDate: undefined,
            secondDate: undefined,
            showResult: false,
        }

        this.changeFirstDate = this.changeFirstDate.bind(this)
        this.changeSecondDate = this.changeSecondDate.bind(this)
        this.getResult = this.getResult.bind(this)
    }

    getResult() {
        this.setState({
            showResult: !this.state.showResult
        })
        let moment = require('moment/moment')
        moment().format();
        const startDate = moment(this.state.firstDate).format('YYYYMMDD');
        const endDate = moment(this.state.secondDate).format('YYYYMMDD');
        this.props.onGetTemperature(startDate, endDate);
    }


    changeFirstDate = chosenDate => {
        this.setState({
            firstDate: chosenDate
            /*[e.target.name]: e.target.value*/
        })

    };

    changeSecondDate = chosenDate => {
        this.setState({
            secondDate: chosenDate
            /*[e.target.name]: e.target.value*/
        })

    };

    render() {
        const {loading, error, data, startDate, endDate} = this.props.lastLowTemp;
        let moment = require('moment/moment')
        moment().format();

        /* if (loading === true) {
             return (<h1>Loading ....</h1>);
         } else {
             if (error !== null) {
                 return (<h1>Error ....</h1>);
             } else {*/
        /*if (data.readingValue && data.dateAndTime) {*/
        return (
            <div>
                <p>Last Lowest Temperature</p>
                <table>

                    <td>
                        <p>Pick a start date:</p>
                        <Calendar
                            onChange={this.changeFirstDate}
                            value={this.state.firstDate}
                        />

                    </td>
                    <td>
                        <p>Pick an end date:</p>
                        <Calendar
                            onChange={this.changeSecondDate}
                            value={this.state.secondDate}
                        />

                    </td>
                </table>
                <button onClick={this.getResult.bind(this)}>Go!</button>
                <tr>{this.state.showResult &&
                <td> the day with the last lowest maximum temperature, between the selected dates,
                    <tr>happened in:  {moment(data.readingDateAndTime).format('DD-MM-YYYY')}</tr>
                    <tr>with a value of:  {data.readingValue} ºC</tr>
                </td>}
                </tr>

            </div>
        )

    }
}


const mapStateToProps = (state) => {
    return {
        lastLowTemp: {
            loading: state.currentTemp.lastLowTemp.loading,
            data: state.currentTemp.lastLowTemp.data,
            error: state.currentTemp.lastLowTemp.error,
            startDate: state.currentTemp.lastLowTemp.startDate,
            endDate: state.currentTemp.lastLowTemp.endDate,
        }
    }
}
const mapDispatchToProps = dispatch => {
    return {
        onGetTemperature: (startDate, endDate) => {
            dispatch(fetchLastLowTempDay(startDate, endDate));
        },
    }
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(AreaLastLowTemp);

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