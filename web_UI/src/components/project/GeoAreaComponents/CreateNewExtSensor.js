import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchSensorTypes} from 'actions/actionsRoom';
import {createSensor} from 'actions/actionsGeoArea';
import {
    Button,
    Card,
    CardTitle,
    CardBody,
} from "reactstrap";


class CreateNewIntSensor extends React.Component {
    constructor(props) {
        super(props);
    }

    state = {
        id: '',
        gaId: this.props.gaId,
        name: '',
        sensorTypeName: '',
        startDate: '',
        unit: '',
        active: true,
        latitude: null,
        longitude: null,
        altitude: null,
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
            <div className="side-card">
                <Card>
                    <CardBody>
                        <CardTitle>
                            <h6>Add new sensor to {this.state.gaId}</h6>
                        </CardTitle>
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>ID</label>
                                    <input
                                        placeholder="ex. RS4"
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
                                        placeholder="ex. RainSensor4"
                                        type="text"
                                        className="form-control"
                                        name="name"
                                        onChange={this.handleInputChange}
                                        value={this.state.name}
                                    />
                                </div>
                            </div>
                            <div className="form-row">
                            <div className="form-group">
                                <label>Type</label>
                                <div>
                                <select type="select" name="sensorTypeName" value={this.state.sensorTypeName}
                                        onChange={this.handleInputChange}>
                                    <option value="" selected disabled hidden>Select Type</option>
                                    {sTypes.data.map(type =>
                                        <option name="sensorTypeName" value={type.type.name}>{type.type.name}</option>)};
                                </select>
                                </div>
                            </div>
                            </div>
                            <div className="form-row">
                            <div className="form-group">
                                <label>Start Date</label>
                                <input
                                    type="date"
                                    className="form-control"
                                    name="startDate"
                                    onChange={this.handleInputChange}
                                    value={this.state.startDate}>
                                </input>
                            </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Unit</label>
                                    <input
                                        placeholder="mm/h"
                                        type="text"
                                        className="form-control"
                                        name="unit"
                                        onChange={this.handleInputChange}
                                        value={this.state.unit}
                                    />
                                </div>
                            </div>

                            <div>
                                <Button color="primary" onClick={this.handleSubmit}>
                                    SAVE
                                </Button>
                                <Button color="danger" onClick={this.handleReset}>
                                    CANCEL
                                </Button>
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
        gaId: state.geoareas.sensors.gaId,
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