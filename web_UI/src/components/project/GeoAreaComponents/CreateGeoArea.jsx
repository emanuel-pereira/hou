import React from 'react';
import {connect} from 'react-redux';
import {createGeoArea} from 'actions/actionsGeoArea';
import {
    Button,
    Row,
    Col,
    Input,
    CardBody,
} from "reactstrap";
import {fetchGeoAreaTypes} from "../../../actions/actionsGeoArea";
import Card from "reactstrap/es/Card";

class CreateGeoArea extends React.Component {
    state = {
        id: '',
        designation: '',
        gaType: '',
        latitude: undefined,
        longitude: undefined,
        altitude: undefined,
        length: undefined,
        width: undefined,
    };

    componentDidMount() {
        this.props.onfetchGeoAreaTypes();
    }

    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.onAddGeoArea(this.state)
        this.handleReset();
        this.props.onClose();
    };

    handleReset = () => {
        this.setState({
            id: '',
            designation: '',
            gaType: {
                id: undefined,
                type: ''
            },
            latitude: 0,
            longitude: 0,
            altitude: 0,
            length: 0,
            width: 0
        })
        this.props.onClose();
    };

    render() {
        const gaTypes = this.props.gaTypes
        return (
            <div>
                <Card>
                    <CardBody>
                        <h4>New Area</h4>
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-row">
                                <div className="form-group col-md-4">
                                    <label>ID</label>
                                    <input
                                        placeholder="ex. PT"
                                        type="text"
                                        className="form-control"
                                        name="id"
                                        onChange={this.handleInputChange}
                                        value={this.state.id}
                                    />
                                </div>
                                <div className="form-group col-md-4">
                                    <label>Name</label>
                                    <input
                                        placeholder="ex. Portugal"
                                        type="text"
                                        className="form-control"
                                        name="designation"
                                        onChange={this.handleInputChange}
                                        value={this.state.designation}
                                    />
                                </div>
                                <div className="form-group col-md-4">
                                    <label>Type</label>
                                    <div>
                                        <select type="select" name="gaType" value={this.state.gaType}
                                                onChange={this.handleInputChange}>
                                            <option value="" selected disabled hidden>Select Type</option>
                                            {gaTypes.data.map(type =>
                                                <option name="gaType" value={type.type}>{type.type}</option>)};
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-4">
                                    <label>Latitude</label>
                                    <input
                                        placeholder="0"
                                        type="number"
                                        className="form-control"
                                        name="latitude"
                                        onChange={this.handleInputChange}
                                        value={this.state.latitude}
                                    />
                                </div>
                                <div className="form-group col-md-4">
                                    <label>Longitude</label>
                                    <input
                                        placeholder="0"
                                        type="number"
                                        className="form-control"
                                        name="longitude"
                                        onChange={this.handleInputChange}
                                        value={this.state.longitude}
                                    />
                                </div>
                                <div className="form-group col-md-4">
                                    <label>Altitude</label>
                                    <input
                                        placeholder="0"
                                        type="number"
                                        className="form-control"
                                        name="altitude"
                                        onChange={this.handleInputChange}
                                        value={this.state.altitude}
                                    />
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-4">
                                    <label>Length</label>
                                    <input
                                        placeholder="0 m"
                                        type="number"
                                        className="form-control"
                                        name="length"
                                        onChange={this.handleInputChange}
                                        value={this.state.length}
                                    />
                                </div>
                                <div className="form-group col-md-4">
                                    <label>Width</label>
                                    <input
                                        placeholder="0 m"
                                        type="number"
                                        className="form-control"
                                        name="length"
                                        onChange={this.handleInputChange}
                                        value={this.state.width}
                                    />
                                </div>
                            </div>


                            <Button color="success" type="submit">
                                SAVE
                            </Button>
                            <Button color="danger" onClick={this.handleReset}>
                                CANCEL
                            </Button>
                        </form>
                    </CardBody>
                </Card>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        gaTypes: {
            data: state.geoareas.gaTypes.data,
        },
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onAddGeoArea: geoArea => {
            dispatch(createGeoArea(geoArea));
        },
        onfetchGeoAreaTypes: () => {
            dispatch(fetchGeoAreaTypes())
        },
    };
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(CreateGeoArea);