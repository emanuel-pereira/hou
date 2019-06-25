import React, {Component} from 'react';
import {connect} from 'react-redux';
import {createSensor, fetchSensorTypes} from 'actions/actionsRoom';
import {
    Button,
    Card,
} from "reactstrap";
import CardBody from "reactstrap/es/CardBody";
import CardTitle from "reactstrap/es/CardTitle";

class CreateNewIntSensor extends React.Component {
    constructor(props) {
        super(props);
    }

    state = {
        id: '',
        roomId: this.props.roomId,
        name: '',
        sensorTypeName: '',
        startDate: '',
        unit: '',
        active: true,
    }

    componentDidMount() {
        this.props.onfetchStypes();
    }

    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.onCreateSensor(this.state);
        this.handleReset();
        this.props.onClose();
    }

    handleReset = () => {
        this.setState({
            id: '',
            roomId: '',
            sensorBehavior: {
                name: '',
                sensorType: {
                    id: undefined,
                    type: ''
                },
                startDate: '',
                unit: '',
                active: true
            }
        })
        this.props.onClose();

    }

    render() {
        const sTypes = this.props.sTypes
        return (
            <div>
                <Card>
                    <CardBody>
                        <CardTitle>
                            <h6>New Sensor on {this.state.roomId}</h6>
                        </CardTitle>
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>ID</label>
                                    <input
                                        placeholder="ex. TS10"
                                        type="text"
                                        className="form-control"
                                        name="id"
                                        onChange={this.handleInputChange}
                                        value={this.state.id}
                                    />
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Designation</label>
                                    <input
                                        placeholder="ex. TemperatureSensor"
                                        type="text"
                                        className="form-control"
                                        name="name"
                                        onChange={this.handleInputChange}
                                        value={this.state.name}
                                    />
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Type</label>
                                    <div>
                                        <select type="select" name="sensorTypeName" value={this.state.sensorTypeName}
                                                onChange={this.handleInputChange}>
                                            <option value="" selected disabled hidden>Select Type</option>
                                            {sTypes.data.map(type =>
                                                <option name="sensorTypeName"
                                                        value={type.type.name}>{type.type.name}</option>)};
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Start Date</label>
                                    <input
                                        type="date"
                                        className="form-control"
                                        name="startDate"
                                        onChange={this.handleInputChange}
                                        value={this.state.startDate}
                                    />
                                </div>
                            </div>

                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Unit</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="unit"
                                        onChange={this.handleInputChange}
                                        value={this.state.unit}
                                    />
                                </div>
                            </div>
                            <div>
                                <Button color="success" onClick={this.handleSubmit}>SAVE</Button>
                                <Button color="danger" onClick={this.handleReset}>CANCEL</Button>
                            </div>
                        </form>
                    </CardBody>
                </Card>
            </div>

        );
    }
}

const mapStateToProps = (state) => {
    return {
        sTypes: {
            data: state.rooms.sTypes.data,
        },
        roomId: state.rooms.sensors.roomId,
    }
}
const mapDispatchToProps = dispatch => {
    return {
        onCreateSensor: sensor => {
            dispatch(createSensor(sensor));
        },
        onfetchStypes: () => {
            dispatch(fetchSensorTypes())
        },
    };
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(CreateNewIntSensor);