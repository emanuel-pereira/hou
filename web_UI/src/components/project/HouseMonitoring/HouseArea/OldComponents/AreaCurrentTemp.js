import React from 'react'
import {fetchCurrentTemp} from "../../../../../actions/actionsArea";
import {connect} from "react-redux";

class AreaCurrentTemp extends React.Component {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        this.props.onGetCurrentTemp();
    }

    render() {
        const {loading, error, data} = this.props.currentTemp;
        let moment = require('moment/moment');
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
                        </ul>
                    </div>);
            } else {
                if (data.readingValue && data.readingDateAndTime) {
                    return (
                        <div>
                        <br/>
                            <p>The Current Temperature is:</p>

                            <p>{data.readingValue}ºC</p>

                        </div>
                    )
                } else {
                    return <h1>no reading value</h1>
                }
            }
        }


    }
}

const mapStateToProps = (state) => {
    return {
        currentTemp: {
            loading: state.currentTemp.currentTemp.loading,
            data: state.currentTemp.currentTemp.data,
            error: state.currentTemp.currentTemp.error,
        }
    }
}
const mapDispatchToProps = dispatch => {
    return {
        onGetCurrentTemp: () => {
            dispatch(fetchCurrentTemp());
        },
    }
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(AreaCurrentTemp);