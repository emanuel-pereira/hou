import React from 'react'
import {fetchFirstHighTempDay} from "../../../../../actions/actionsArea";
import {connect} from "react-redux";

class AreaFirstHighTemp extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            startDate: this.props.startDate,
            endDate: this.props.endDate,
        }

    }

    componentDidMount() {
        let moment = require('moment/moment')
        moment().format();
        const startDate = moment(this.state.startDate).format('YYYYMMDD');
        const endDate = moment(this.state.endDate).format('YYYYMMDD');
        this.props.onGetTemperature(startDate, endDate);
    }

    render() {
        const {loading, error, data, startDate, endDate} = this.props.firstHighTemp;
        let moment = require('moment/moment')
        moment().format();

        if (loading === true) {
            return (<p>Loading ....</p>);
        } else {
            if (error !== null) {
                return (
                    <div><p>ERROR:It wasn't possible to successfully return a value. Possible causes for this: </p>
                        <ul>
                            <li>
                                No Geographical Areas and/or External Sensors;
                            </li>
                            <li>
                                Undefined House Geographical Area and/or Location;
                            </li>
                            <li>
                                No Temperature Sensors in the House's Geographical Area;
                            </li>
                            <li>
                                No Available Readings between the selected dates.
                            </li>
                        </ul>
                    </div>);
            } else {
                if (data) {
                    return (
                        <div>
                            <tr>{
                                <td> the day with the first highest maximum temperature, between the selected dates,
                                    <tr>happened in: {moment(data.readingDateAndTime).format('DD-MM-YYYY')}</tr>
                                    <tr>with a value of: {data.readingValue} ºC</tr>
                                </td>}
                            </tr>

                        </div>
                    )

                }
            }
        }
    }
}


const mapStateToProps = (state) => {
    return {
        firstHighTemp: {
            loading: state.currentTemp.firstHighTemp.loading,
            data: state.currentTemp.firstHighTemp.data,
            error: state.currentTemp.firstHighTemp.error,
            startDate: state.currentTemp.firstHighTemp.startDate,
            endDate: state.currentTemp.firstHighTemp.endDate,
        }
    }
}
const mapDispatchToProps = dispatch => {
    return {
        onGetTemperature: (startDate, endDate) => {
            dispatch(fetchFirstHighTempDay(startDate, endDate));
        },
    }
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(AreaFirstHighTemp);

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