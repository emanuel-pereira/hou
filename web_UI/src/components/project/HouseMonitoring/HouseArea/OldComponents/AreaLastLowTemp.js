import React from 'react'
import {fetchLastLowTempDay} from "../../../../../actions/actionsArea";
import {connect} from "react-redux";

class AreaLastLowTemp extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            startDate: this.props.startDate,
            endDate: this.props.endDate,
            /*showResult: this.props,*/
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
        const {loading, data, error, startDate, endDate} = this.props.lastLowTemp;
        let moment = require('moment/moment')
        moment().format();

        if (loading === true) {
            return (<p>Loading ....</p>);
        } else {
            if (error !== null) {
                return (
                    <div><p>ERROR :It wasn't possible to successfully return a value. Possible causes for this: </p>
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
                            <tr>{<td> the last day with the lowest maximum temperature, between the selected dates,
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


const
    mapStateToProps = (state) => {
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
const
    mapDispatchToProps = dispatch => {
        return {
            onGetTemperature: (startDate, endDate) => {
                dispatch(fetchLastLowTempDay(startDate, endDate));
            },
        }
    };

export default connect(
    mapStateToProps,
    mapDispatchToProps
)

(AreaLastLowTemp)
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