import React from 'react';
import AreaMonitoringMenu from "./HouseArea/AreaMonitoringMenu";
import RoomMonitoring from "../RoomMonitoringComponents/RoomMonitoring";

class HouseMonitoring extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            showAreaMonitoring: false,
            showRoomMonitoring: false
        }
    }

    clickToShowArea() {
        this.setState({
            showAreaMonitoring: true,
            showRoomMonitoring: false
        })
    }

    clickToShowRooms() {
        this.setState({
            showAreaMonitoring: false,
            showRoomMonitoring: true
        })
    }

    render() {
        return (
            <div>
                {/*<h3>Choose an option</h3>*/}
                <table>
                    <td>
                        <button onClick={this.clickToShowArea.bind(this)}>House's Area Monitoring</button>
                    </td>
                    <td>
                        <button onClick={this.clickToShowRooms.bind(this)}>House's Room Monitoring</button>

                    </td>
                </table>
                <table>
                    <tr>
                        {this.state.showAreaMonitoring && <AreaMonitoringMenu/>}
                    </tr>
                    <tr>
                        {this.state.showRoomMonitoring && <RoomMonitoring/>}
                    </tr>
                </table>
            </div>
        )
    }
}

export default HouseMonitoring