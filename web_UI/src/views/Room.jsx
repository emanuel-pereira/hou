import React from "react";
import RoomTable from "components/project/RoomComponents/RoomTable.jsx";
import CreateRoom from "components/project/RoomComponents/CreateRoom";
import RoomDetailList from "components/project/RoomComponents/RoomDetailList.jsx";
import RoomSensors from "components/project/RoomComponents/RoomSensors.jsx";

import {
  Button,
  CardHeader,
  CardTitle,

} from "reactstrap";

class Room extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isHiddenCreate: true,
      showDetails: false,
      showSensors: false,
    }
  }

  toggleHiddenRooms() {
    this.setState({
      isHiddenCreate: !this.state.isHiddenCreate
    })
  }

  showDetails() {
    this.setState({
      showDetails: true,
      showSensors: false,
    })
  }
  showSensors() {
    this.setState({
      showSensors: true,
      showDetails: false
    })
  }

  render() {
    return (
      <>
        <div className="content">
          <Button onClick={this.toggleHiddenRooms.bind(this)}><i className="nc-icon nc-simple-add"></i></Button>
          <tr>
            <td>{this.state.isHiddenCreate && <RoomTable onShowDetails={this.showDetails.bind(this)} onShowSensors={this.showSensors.bind(this)} />}</td>
            <td>{this.state.isHiddenCreate && this.state.showDetails && <RoomDetailList />}</td>
            <td>{this.state.isHiddenCreate && this.state.showSensors && <RoomSensors />}</td>
          </tr>
          {!this.state.isHiddenCreate && <CreateRoom onClose={this.toggleHiddenRooms.bind(this)} />}
        </div>
      </>
    );
  }
}

export default Room;
