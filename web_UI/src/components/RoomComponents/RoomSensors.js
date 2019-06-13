import React, {Component} from 'react';
import {connect} from 'react-redux';

class RoomSensors extends Component {
    constructor(props) {
        super(props);
    this.state = {
        isHidden: true
    }
}

    render() {
        const {data} = this.props.sensors;
        const rows = data.map((row, index) => {
            return (
                <table className="roomCreation">
                <tr><h2>Room Sensors</h2></tr>
                <tr key={index}>
                    <tr><td>Id: {row.id}</td></tr>
                    <tr> <td>Name: {row.designation}</td></tr>
                <tr>Start Date: {row.startDate}</tr>
              </tr>
              </table>
            )
            })
        return (
            <tbody>{rows}</tbody>
        );
    }
}
const mapStateToProps = (state) => {
    return {
        sensors: state.sensors
        }
    }



export default connect(
    mapStateToProps,
    null
)(RoomSensors);
