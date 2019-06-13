import React, { Component } from 'react';
import RoomTable from './RoomTable';
import RoomDetailList from './RoomDetailList';
import CreateRoom from './CreateRoom';
import AddButton from './../AddButton';
import RoomSensors from './RoomSensors';


class RoomList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isHidden: true
        }
    }

    toggleHiddenRooms() {
        this.setState({
            isHidden: !this.state.isHidden
        })
    }

    render() {
        return (
            <div>
                <table>
                    <a onClick={this.toggleHiddenRooms.bind(this)}><AddButton/></a>
                    <a>New Room</a>
                    <tr></tr>
                    <td><RoomTable /></td>
                    <td>{!this.state.isHidden && <CreateRoom/>}
                        {this.state.isHidden && <RoomDetailList/> }
                        {this.state.isHidden && <RoomSensors/> }
                        </td>

                </table>
            </div>
            );
    }
}
export default RoomList;