import React from "react";
import RoomMonitoringTable from "./RoomMonitoringTable.js";
import RoomCurrentTemperature from "./RoomCurrentTemperature.js";
import RoomMaxTemperature from "./RoomMaxTemperature";
import {
    Card,
    CardHeader,
    CardBody,
    CardTitle,
    Table,
    Row,
    Col
} from "reactstrap";

class RoomMonitoring extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isHiddenCreate: true,
            showCurrTempRooms: false,
            showFetchRoomMaxTemp: false,
        }
    }

    toggleHiddenRooms() {
        this.setState({
            isHiddenCreate: !this.state.isHiddenCreate
        })
    }

    toggleHiddens() {
        this.setState({
            isHidden: !this.state.isHidden
        })
    }

    showCurrTempRooms() {
        this.setState({
            showCurrTempRooms: true,
            showFetchRoomMaxTemp: false,
        })
    }

    showFetchRoomMaxTemp() {
        this.setState({
            showFetchRoomMaxTemp: true,
            showCurrTempRooms: false,
        })
    }


    render() {
        return (
            <>
                <div className="content">
                    <h4>Room Monitoring</h4>
                    <tr>
                        <td>{this.state.isHiddenCreate &&
                        <RoomMonitoringTable onShowCurrTempRooms={this.showCurrTempRooms.bind(this)}
                                             onShowfetchRoomMaxTemp={this.showFetchRoomMaxTemp.bind(this)}/>}</td>
                        <td>{this.state.isHiddenCreate && this.state.showCurrTempRooms && <RoomCurrentTemperature/>}</td>
                        <td>{this.state.isHiddenCreate && this.state.showFetchRoomMaxTemp && <RoomMaxTemperature/>}</td>
                    </tr>
                </div>
            </>
        );
    }
}

export default RoomMonitoring;


