import React, {Component} from 'react';
import {connect} from 'react-redux';
import {updateRoom} from 'actions/actionsRoom';
import Button from "reactstrap/es/Button";
import {Card, CardBody, Col, Row, Table} from "reactstrap";
import CardTitle from "reactstrap/es/CardTitle";

class UpdateRoom extends React.Component {
    constructor(props) {
        super(props);
    }

    state = {
        id: this.props.details.data.id,
        description: this.props.details.data.description,
        floor: this.props.details.data.floor,
        length: this.props.details.data.length,
        width: this.props.details.data.width,
        height: this.props.details.data.height
    };
    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.onEditRoom(this.state);
        this.handleReset();
        this.props.onClose();
    }


    handleReset = () => {
        this.setState({
            id: '',
            description: '',
            floor: 0,
            length: 0,
            width: 0,
            height: 0
        });
        this.props.onClose();
    };

    render() {
        return (
            <div>
                <Card>
                    <CardBody>
                        <CardTitle>
                            <h6>ROOM {this.state.id}</h6>
                        </CardTitle>
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Description</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="description"
                                        onChange={this.handleInputChange}
                                        value={this.state.description}>
                                    </input>
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Floor</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="floor"
                                        onChange={this.handleInputChange}
                                        value={this.state.floor}>
                                    </input>
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Length</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="length"
                                        onChange={this.handleInputChange}
                                        value={this.state.length}>
                                    </input>
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Width</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="width"
                                        onChange={this.handleInputChange}
                                        value={this.state.width}>
                                    </input>
                                </div>
                            </div>

                            <div className="form-row">
                                <div className="form-group col-md-12">
                                    <label>Height</label>
                                    <input
                                        type="number"
                                        className="form-control"
                                        name="width"
                                        onChange={this.handleInputChange}
                                        value={this.state.height}>
                                    </input>
                                </div>
                            </div>
                            <div>
                                <Button type="button" className="btn btn-info"
                                        onClick={this.handleSubmit}>UPDATE</Button>
                                <Button type="button" className="btn btn-danger"
                                        onClick={this.handleReset}>CANCEL</Button>

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
        details: {
            loading: state.rooms.details.loading,
            data: state.rooms.details.data,
            error: state.rooms.details.error,
            id: state.rooms.details.id,
        }
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onEditRoom: room => {
            dispatch(updateRoom(room));
        }
    };
};

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(UpdateRoom);