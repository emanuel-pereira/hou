import React from 'react'
import Calendar from 'react-calendar';
import {fetchHighAmpliTempDay} from "../../../../actions/actionsArea";
import {connect} from "react-redux";

class AreaHighAmpliTemp extends React.Component {
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
        })

    };

    changeSecondDate = chosenDate => {
        this.setState({
            secondDate: chosenDate
        })

    };

    render() {
        const {loading, error, data, startDate, endDate} = this.props.highAmpliTemp;
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
                <p>Highest Temperature Amplitude</p>
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
                <td> the day with the highest temperature amplitude, between the selected dates,
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
        highAmpliTemp: {
            loading: state.currentTemp.highAmpliTemp.loading,
            data: state.currentTemp.highAmpliTemp.data,
            error: state.currentTemp.highAmpliTemp.error,
            startDate: state.currentTemp.highAmpliTemp.startDate,
            endDate: state.currentTemp.highAmpliTemp.endDate,
        }
    }
}
const mapDispatchToProps = dispatch => {
    return {
        onGetTemperature: (startDate, endDate) => {
            dispatch(fetchHighAmpliTempDay(startDate, endDate));
        },
    }
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(AreaHighAmpliTemp);