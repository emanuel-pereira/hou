import React, { Component } from 'react';
import { connect } from 'react-redux';
import CreateNewIntSensor from "./CreateNewIntSensor";
import {
  Card,
  CardBody,
  Table,
  Row,
  Col
} from "reactstrap";

class RoomSensors extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isInEditMode: false,
    }
  }
  changeEditMode = () => {
    this.setState({
      isInEditMode: !this.state.isInEditMode
    })
  }

  renderEditView = () => {
    return <CreateNewIntSensor roomId={this.props.sensors.roomId} onClose={this.changeEditMode} />
  }

  renderDefaultView() {
    const { data, error } = this.props.sensors
    const rows = data.map((row, index) => {
      var startDate = (row.sensorBehavior.startDate).slice(0,10);
      return (
        <tr key={index}>
          <td>{row.id}</td>
          <td>{row.sensorBehavior.name}</td>
          <td>{row.sensorBehavior.sensorType.type}</td>
          <td>{startDate}</td>
        </tr>
      )
    })
    if (error === null) {
      return (
        <div className="content">
          <Row>
            <Col md="12">
              <Card>
                <CardBody>
                  <Table>
                    <thead className="text-primary">
                      <tr><th>Sensor on room {this.props.sensors.roomId} :</th></tr>
                      <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Sensor Type</th>
                        <th>Start date</th>
                      </tr>
                    </thead>
                    <tbody>{rows}</tbody>
                    <td ><button onClick={this.changeEditMode}>Create New Sensor</button></td>
                  </Table>
                </CardBody>
              </Card>
            </Col>
          </Row>
        </div>
      );
    }
    else return <p>No sensors available
       <td ><button onClick={this.changeEditMode}>Create New Sensor</button></td>
    </p>
  }

  render() {
    return this.state.isInEditMode ?
      this.renderEditView() :
      this.renderDefaultView()
  }
}

const mapStateToProps = (state) => {
  return {
    sensors: {
      data: state.rooms.sensors.data,
      error: state.rooms.sensors.error,
      roomId: state.rooms.sensors.roomId
    },
  }
}



export default connect(
  mapStateToProps,
  null
)(RoomSensors);
