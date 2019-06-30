import React, {Component} from 'react';
import {connect} from 'react-redux';
import CreateNewExtSensor from "./CreateNewExtSensor";
import {deleteSensor} from 'actions/actionsGeoArea';
import {confirmAlert} from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';


import {
    Card,
    CardBody,
    Table,
    Row,
    Col,
    Button,

} from "reactstrap";
import CardTitle from "reactstrap/es/CardTitle";

class GASensors extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isInEditMode: false,
    }
  }

  deleteSensor = (name,id,idGA) => {
      confirmAlert({
          title: 'Confirm to remove',
          message: 'Are you sure that you want to remove the sensor: '+id+' '+name+'?',
          buttons: [
              {
                  label: 'Yes',
                  onClick: () => this.props.onDeleteSensor(id,idGA)
              },
              {
                  label: 'No',
                  onClick: () => {
                  }
              }
          ]
      });
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
        const {data, error} = this.props.sensors
        const rows = data.map((row, index) => {
            var startDate = (row.sensorBehaviorDTO.startDate).slice(0, 10);
            return (
                <tr key={index}>
                    <td>{row.id}</td>
                    <td>{row.sensorBehaviorDTO.name}</td>
                    <td>{row.sensorBehaviorDTO.sensorType.type}</td>
                    <td>{startDate}</td>
                        <tr><Button color="danger" onClick={() => this.deleteSensor(row.sensorBehaviorDTO.name,row.id,row.idGA)}>
                            DELETE
                        </Button></tr>

                </tr>
            )
        })
        if (error === null) {
            return (
                <div>
                    <Card>
                        <CardBody>
                            <CardTitle>
                                <h6>Sensors on {this.props.sensors.gaId}</h6>
                            </CardTitle>
                            <Table>
                                <thead className="text-primary">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Type</th>
                                    <th>Start date</th>
                                </tr>
                                </thead>
                                <tbody>{rows}</tbody>
                            </Table>
                            <Button color="default" onClick={this.changeEditMode}>
                                <i className="nc-icon nc-simple-add"></i>
                            </Button>
                        </CardBody>
                    </Card>
                </div>
            );
        } else return <Card>
            <CardBody>
                <h5>No sensors available</h5>
                <td>
                    <Button color="default" onClick={this.changeEditMode}>
                        <i className="nc-icon nc-simple-add"></i>
                    </Button>
                </td>
            </CardBody>
        </Card>
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
