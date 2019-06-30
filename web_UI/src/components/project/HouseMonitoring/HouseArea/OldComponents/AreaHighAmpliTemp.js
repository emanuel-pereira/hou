import React from 'react'
import {fetchHighAmpliTempDay} from "../../../../../actions/actionsArea";
import {connect} from "react-redux";

class AreaHighAmpliTemp extends React.Component {
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
        const {loading, error, data, startDate, endDate} = this.props.highAmpliTemp;
        let moment = require('moment/moment')
        moment().format();

        if (loading === true) {
            return (<p>Loading ....</p>);
        } else {
            if (error !== null) {
                return (<div><p>ERROR:It wasn't possible to successfully return a value. Possible causes for this: </p>
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
                            <td> the day with the highest temperature amplitude, between the selected dates,
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