import React from "react";
import RoomTable from "components/project/RoomComponents/RoomTable.jsx";
import CreateRoom from "components/project/RoomComponents/CreateRoom";

import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Table,
  Row,
  Col
} from "reactstrap";
import GridTable from "../components/project/GridComponents/GridTable";

class HouseGrid extends React.Component {
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
      <>
        <div className="content">
          <h6>New Grid  <i className="nc-icon nc-simple-add" onClick={this.toggleHiddenRooms.bind(this)} /></h6>
          <CardHeader>
            <CardTitle tag="h4">House Grid Configuration</CardTitle>
          </CardHeader>
          <tr>
            <td>{this.state.isHidden && <GridTable />}</td>
          </tr>
          {!this.state.isHidden && <CreateRoom toggleHiddenRooms={this.toggleHiddenRooms.bind(this)} />}
        </div>
      </>
    );
  }
}

export default HouseGrid;
