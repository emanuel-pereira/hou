import React from "react";
import CreateGrid from "components/project/GridComponents/CreateGrid";

import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Table,
  Row,
  Col, Button
} from "reactstrap";
import GridTable from "../components/project/GridComponents/GridTable";
import GridRooms from "../components/project/GridComponents/GridRooms";

class HouseGrid extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isHidden: true,
      showRooms: false,
    }
  }
  showRooms() {
    this.setState({
      showRooms: true,
    })}

  toggleHidden() {
    this.setState({
      isHidden: !this.state.isHidden
    })
  }
  render() {
    return (
        <div className="content">
          <Button onClick={this.toggleHidden.bind(this)}>
            <i className="nc-icon nc-simple-add"></i>
          </Button>
          <div>
          {!this.state.isHidden && <CreateGrid onClose={this.toggleHidden.bind(this)} />}
          </div>
          <Table>
            <Row>
              <Col md="8">
          <tr>
            <td>{this.state.isHidden && <GridTable onShowRooms={this.showRooms.bind(this)} />}</td>
            <td>{this.state.isHidden && this.state.showRooms && <GridRooms />}</td>
          </tr>
              </Col>
            </Row>
          </Table>
        </div>
    );
  }
}

export default HouseGrid;
