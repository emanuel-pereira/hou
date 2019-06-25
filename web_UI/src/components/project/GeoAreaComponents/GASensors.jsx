import React, { Component } from 'react';
import { connect } from 'react-redux';
import CreateNewExtSensor from "./CreateNewExtSensor";
import {deleteSensor} from 'actions/actionsGeoArea';
import {
  Card,
  CardBody,
  Table,
  Row,
  Col
} from "reactstrap";

class GASensors extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isInEditMode: false,
    }
  }

  deleteSensor = (id,idGA) => {
    this.props.onDeleteSensor(id,idGA);
  }
  
  changeEditMode = () => {
    this.setState({
      isInEditMode: !this.state.isInEditMode
    })
  }

  renderEditView = () => {
    return <CreateNewExtSensor gaId={this.props.sensors.gaId} onClose={this.changeEditMode} />
  }

  renderDefaultView() {
    const { data, error } = this.props.sensors
    const rows = data.map((row, index) => {
      var startDate = (row.sensorBehaviorDTO.startDate).slice(0,10);
      return (
        <tr key={index}>
          <td>{row.id}</td>
          <td>{row.sensorBehaviorDTO.name}</td>
          <td>{row.sensorBehaviorDTO.sensorType.type}</td>
          <td>{startDate}</td>
          <td ><button onClick={() => this.deleteSensor(row.id,row.idGA)}>Delete</button></td>
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
                      <tr><th>Sensors on {this.props.sensors.gaId} :</th></tr>
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
      data: state.geoareas.sensors.data,
      error: state.geoareas.sensors.error,
      gaId: state.geoareas.sensors.gaId
    },
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
      onDeleteSensor: (id,idGA) => {
          dispatch(deleteSensor(id,idGA))
      },
  }
}



export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GASensors);
