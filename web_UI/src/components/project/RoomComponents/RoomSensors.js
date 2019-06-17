import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";

class RoomSensors extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        const { data } = this.props.sensors;
        const rows = data.map((row, index) => {
            return (
                <table>
                    <tbody>
                    <tr key={index}>
                        <tr><td>Id: {row.id}</td></tr>
                        <tr><td>Name: {row.designation}</td></tr>
                        <tr><td>Start Date: {row.startDate}</td></tr>
                        <tr><td>Sensor type: {row.sensorType.sensorType}</td></tr>
                    </tr>
                    </tbody>
                </table>
            )
        })
        if (data.length > 0) {
            return (
                <div className="content">
                    <Row>
                        <Col md="12">
                            <Card>
                                <CardBody>
                                    <Table>
                                        <thead className="text-primary">
                                            <tr>
                                                <th>Sensors </th>
                                            </tr>
                                        </thead>
                                        <tbody>{rows}</tbody>
                                    </Table>
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
                </div>
            );
        } else {
            return null
        }
    }
}

const mapStateToProps = (state) => {
    return {
        sensors: {
            data: state.rooms.sensors.data,
        }
    }
}



export default connect(
    mapStateToProps,
    null
)(RoomSensors);
