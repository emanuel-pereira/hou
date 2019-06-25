import React, {Component} from 'react';
import {connect} from 'react-redux';
import CreateNewIntSensor from "./CreateNewIntSensor";
import {
    Card,
    CardBody,
    Table,
    Row,
    Col,
    Button,
} from "reactstrap";
import CardTitle from "reactstrap/es/CardTitle";

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
        return <CreateNewIntSensor roomId={this.props.sensors.roomId} onClose={this.changeEditMode}/>
    }

    renderDefaultView() {
        const {data, error} = this.props.sensors
        const rows = data.map((row, index) => {
            var startDate = (row.sensorBehavior.startDate).slice(0, 10);
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
                <div>
                    <Card>
                        <CardBody className="side-card">
                            <CardTitle>
                                <h6> Sensors on Room {this.props.sensors.roomId}</h6>
                            </CardTitle>
                            <Table>
                                <thead className="text-primary">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Sensor Type</th>
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
                <Button color="default" onClick={this.changeEditMode}>
                    <i className="nc-icon nc-simple-add"></i>
                </Button>
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
