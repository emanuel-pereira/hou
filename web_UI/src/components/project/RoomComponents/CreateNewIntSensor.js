import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createSensor, fetchSensorTypes } from 'actions/actionsRoom';

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
            <div className="roomCreation">
                <thead className="text-primary">
                    <th>Add new sensor to {this.state.roomId}</th>
                </thead>
                <tbody></tbody>
                <form onSubmit={this.handleSubmit}>
                    <tr><td><div className="form-group">
                        Id
                        <input
                            type="text"
                            className="form-control"
                            name="id"
                            onChange={this.handleInputChange}
                            value={this.state.id}>
                        </input>
                    </div></td></tr>
                    <div className="form-group">
                        Designation
                        <input
                            type="text"
                            className="form-control"
                            name="name"
                            onChange={this.handleInputChange}
                            value={this.state.name}>
                        </input>
                    </div>
                    <div className="form-group">
                        Sensor type
                            <select type="select" name="sensorTypeName" value={this.state.sensorTypeName} onChange={this.handleInputChange}>
                            <option value="" selected disabled hidden>Choose sensor type here</option>
                            {sTypes.data.map(type =>
                            <option name="sensorTypeName" value={type.type.name}>{type.type.name}</option>)};
                            </select>
                    </div>
                    <div className="form-group">
                        Start Date:
                        <input
                            type="date"
                            className="form-control"
                            name="startDate"
                            onChange={this.handleInputChange}
                            value={this.state.startDate}>
                        </input>
                    </div>
                    <div className="form-group">
                        Unit:
                        <input
                            type="text"
                            className="form-control"
                            name="unit"
                            onChange={this.handleInputChange}
                            value={this.state.unit}>
                        </input>
                    </div>
                    <div className="buttons">
                        <button type="submit" className="btn btn-primary" onClick={this.handleSubmit}>SAVE</button>
                        <button type="button" className="btn btn-warning" onClick={this.handleReset}>CANCEL</button>
                    </div>


                </form>

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