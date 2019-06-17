import React, { Component } from 'react';
import { connect } from 'react-redux';
import { updateRoom } from 'actions/actionsRoom';

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
            <div className="roomCreation">
                <thead className="text-primary">
                    <tr>
                    <th>ROOM - {this.state.id}</th>
                    </tr>
                </thead>
                <tbody></tbody>
                <form onSubmit={this.handleSubmit}>
                    <tr><td>Description:</td> <td><div className="form-group">
                        <input
                            type="text"
                            className="form-control"
                            name="description"
                            onChange={this.handleInputChange}
                            value={this.state.description}>
                        </input>
                    </div></td></tr>
                    <div className="form-group">
                        Floor
                        <input
                            type="text"
                            className="form-control"
                            name="floor"
                            onChange={this.handleInputChange}
                            value={this.state.floor}>
                        </input>
                    </div>
                    <div className="form-group">
                        Length
                        <input
                            type="text"
                            className="form-control"
                            name="length"
                            onChange={this.handleInputChange}
                            value={this.state.length}>
                        </input>
                    </div>
                    <div className="form-group">
                        Width
                        <input
                            type="text"
                            className="form-control"
                            name="width"
                            onChange={this.handleInputChange}
                            value={this.state.width}>
                        </input>
                    </div>
                    <div className="form-group">
                        Height
                        <input
                            type="text"
                            className="form-control"
                            name="height"
                            onChange={this.handleInputChange}
                            value={this.state.height}>
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