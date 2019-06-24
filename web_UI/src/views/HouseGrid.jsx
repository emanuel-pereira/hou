import React from "react";
import CreateGrid from "components/project/GridComponents/CreateGrid";

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
import GridRooms from "../components/project/GridComponents/GridRooms";

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
            <td>{this.state.isHidden  && <GridRooms />}</td>
          </tr>
          {!this.state.isHidden && <CreateGrid onClose={this.toggleHiddenRooms.bind(this)} />}
        </div>
      </>
    );
  }
}

export default HouseGrid;
