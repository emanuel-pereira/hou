import React from 'react';
import {connect} from 'react-redux';
import {createRoom} from '../../actions/actionsRoom';

class CreateRoom extends React.Component {
    state = {
        id: '',
        description: '',
        floor: 0,
        length: 0,
        width: 0,
        height: 0
    };

    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        if (this.state.id.trim() && this.state.description.trim()) {
            this.props.onAddRoom(this.state);
            this.handleReset();
        }
    };

    handleReset = () => {
        this.setState({
            id: '',
            description: '',
            floor: 0,
            length: 0,
            width: 0,
            height: 0
        });
    };

    render() {
        return (
            <div className="roomCreation">

                <h2>New Room</h2>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        ID
                        <input
                            type="text"
                            className="form-control"
                            name="id"
                            onChange={this.handleInputChange}
                            value={this.state.id}
                        />
                    </div>
                    <div className="form-group">
                        Description
                        <input
                            type="text"
                            className="form-control"
                            name="description"
                            onChange={this.handleInputChange}
                            value={this.state.description}>
                        </input>
                    </div>
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
                        <button type="button" className="btn btn-warning" onClick={this.handleReset}>
                            RESET
                        </button>
                    </div>


                </form>
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