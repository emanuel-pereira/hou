import React from 'react'
import {fetchCurrentTemp} from "../../../../actions/actionsArea";
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
                return (<p>Error ....</p>);
            } else {
                if (data.readingValue && data.readingDateAndTime) {
                    return (
                        <div>
                            <p>The Current Temperature is:</p>

                            <p>{data.readingValue}ºC</p>
                            <p>{moment(data.readingDateAndTime).format('DD-MM-YYYY')}</p>

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