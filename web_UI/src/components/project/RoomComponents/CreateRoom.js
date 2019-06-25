import React from 'react';
import {connect} from 'react-redux';
import {createRoom} from 'actions/actionsRoom';
import NotificationAlert from "react-notification-alert";
import {
    Button,
} from "reactstrap";
import Card from "reactstrap/es/Card";
import CardBody from "reactstrap/es/CardBody";

var options = {};
options = {
    place: 'tl',
    message: (
        <div>Room Created with success.</div>
    ),
    type: "success",
    icon: "now-ui-icons ui-1_bell-53",
    autoDismiss: 7
}

class CreateRoom extends React.Component {
    state = {
        id: '',
        description: '',
        floor: '',
        length: '',
        width: '',
        height: ''
    };

    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.onAddRoom(this.state)
        this.refs.notify.notificationAlert(options);
        this.handleReset();
        setTimeout(this.props.onClose(), 1000)
    };

    handleReset = () => {
        this.setState({
            id: '',
            description: '',
            floor: '',
            length: '',
            width: '',
            height: ''
        })
        this.props.onClose();
    };

    render() {
        return (
            <div>
                <NotificationAlert ref="notify"/>
                <Card>
                    <CardBody>
                    <h4>New Room</h4>
                    <form onSubmit={this.handleSubmit}>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label>ID</label>
                                <input
                                    placeholder="ex. LR1"
                                    type="text"
                                    className="form-control"
                                    name="id"
                                    onChange={this.handleInputChange}
                                    value={this.state.id}
                                />
                            </div>
                            <div className="form-group col-md-4">
                                <label>Description</label>
                                <input
                                    placeholder="ex. Living Room"
                                    type="text"
                                    className="form-control"
                                    name="description"
                                    onChange={this.handleInputChange}
                                    value={this.state.description}>
                                </input>
                            </div>
                            <div className="form-group col-md-2">
                                <label>Floor</label>
                                <input
                                    placeholder="0"
                                    type="number"
                                    className="form-control"
                                    name="floor"
                                    onChange={this.handleInputChange}
                                    value={this.state.floor}>
                                </input>
                            </div>
                        </div>

                        <div className="form-row">
                            <div className="form-group col-md-2">
                                <label>Length</label>
                                <input
                                    placeholder="0 m"
                                    type="number"
                                    className="form-control"
                                    name="length"
                                    onChange={this.handleInputChange}
                                    value={this.state.length}>
                                </input>
                            </div>
                            <div className="form-group col-md-2">
                                <label>Width</label>
                                <input
                                    placeholder="0 m"
                                    type="number"
                                    className="form-control"
                                    name="width"
                                    onChange={this.handleInputChange}
                                    value={this.state.width}>
                                </input>
                            </div>
                            <div className="form-group col-md-2">
                                <label>Height</label>
                                <input
                                    placeholder="0 m"
                                    type="number"
                                    className="form-control"
                                    name="height"
                                    onChange={this.handleInputChange}
                                    value={this.state.height}>
                                </input>
                            </div>
                        </div>

                        <div>
                            <Button className="btn btn-success" color="primary" type="submit">
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

const mapDispatchToProps = dispatch => {
    return {
        onAddRoom: room => {
            dispatch(createRoom(room));
        }
    };
};

export default connect(
    null,
    mapDispatchToProps
)(CreateRoom);