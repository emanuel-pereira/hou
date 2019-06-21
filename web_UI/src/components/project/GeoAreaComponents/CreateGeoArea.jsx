import React from 'react';
import { connect } from 'react-redux';
import { createGeoArea } from 'actions/actionsGeoArea';
import NotificationAlert from "react-notification-alert";
import {
    Button,
    Row,
    Col,
    Input
} from "reactstrap";
import {fetchGeoAreaTypes} from "../../../actions/actionsGeoArea";

var options = {};
options = {
    place: 'tl',
    message: (
        <div>
            <div>
                Area created with success.
            </div>
        </div>
    ),
    type: "success",
    icon: "now-ui-icons ui-1_bell-53",
    autoDismiss: 7
}

class CreateGeoArea extends React.Component {
    state = {
        id: '',
        designation: '',
        gaType: '',
        latitude: 0,
        longitude: 0,
        altitude: 0,
        length:0,
        width:0
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
                id:undefined,
                type:''
            },
            latitude: 0,
            longitude: 0,
            altitude: 0,
            length:0,
            width:0
        })
        this.props.onClose();
    };

    render() {
        const gaTypes = this.props.gaTypes
        return (
            <div>
                <thead className="text-primary">
                <th>Create a new area</th>
                </thead>
                <tbody></tbody>
                <form onSubmit={this.handleSubmit}>

                        <Col>
                    <div className="form-group">
                        ID
                        <Input
                            type="text"
                            className="form-control"
                            name="id"
                            onChange={this.handleInputChange}
                            value={this.state.id}
                        />
                    </div>
                        </Col>
                        <Col>
                    <div className="form-group">
                        Name
                        <Input
                            type="text"
                            className="form-control"
                            name="designation"
                            onChange={this.handleInputChange}
                            value={this.state.designation}>
                        </Input>
                    </div>
                        </Col>
                    <div className="form-group">
                        Type
                        <Input
                            type="text"
                            className="form-control"
                            name="type"
                            onChange={this.handleInputChange}
                            value={this.state.type}>
                        </Input>
                    </div>
                    <div className="form-group">
                        Latitude
                        <Input
                            type="text"
                            className="form-control"
                            name="latitude"
                            onChange={this.handleInputChange}
                            value={this.state.latitude}>
                        </Input>
                    </div>
                    <div className="form-group">
                        Longitude
                        <Input
                            type="text"
                            className="form-control"
                            name="longitude"
                            onChange={this.handleInputChange}
                            value={this.state.longitude}>
                        </Input>
                    </div>
                    <div className="form-group">
                        Height
                        <Input
                            type="text"
                            className="form-control"
                            name="altitude"
                            onChange={this.handleInputChange}
                            value={this.state.altitude}>
                        </Input>
                    </div>
                    <div className="form-group">
                        Length
                        <Input
                            type="text"
                            className="form-control"
                            name="length"
                            onChange={this.handleInputChange}
                            value={this.state.length}>
                        </Input>
                    </div>
                    <div className="form-group">
                        Width
                        <Input
                            type="text"
                            className="form-control"
                            name="width"
                            onChange={this.handleInputChange}
                            value={this.state.width}>
                        </Input>
                    </div>

                    <div className="buttons">
                        <Button color="success" type="submit">
                            SAVE
                        </Button>
                        <Button color="warning"  onClick={this.handleReset}>
                            RESET
                        </Button>
                    </div>

                </form>
            </div>
        );
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onAddGeoArea: geoArea => {
            dispatch(createGeoArea(geoArea));
        },
        onfetchGeoAreaTypes:() => {
            dispatch(fetchGeoAreaTypes())
        },
    };
};

export default connect(
    null,
    mapDispatchToProps
)(CreateGeoArea);