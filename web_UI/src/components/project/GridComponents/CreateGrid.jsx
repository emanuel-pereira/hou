import React from 'react';
import { connect } from 'react-redux';
import { createHouseGrid } from 'actions/actionsGrid';
import {
    Button,

} from "reactstrap";

class CreateGrid extends React.Component {
    state = {
        id: undefined,
        designation: '',
    };

    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.onAddGrid(this.state)
        this.handleReset();
        this.props.onClose();
    };

    handleReset = () => {
        this.setState({
            id: '',
            description: '',

        });
    };

    render() {
        return (
            <div className="content">
                <h2>New House Grid</h2>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        ID
                        <input
                            type="number"
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
                            name="designation"
                            onChange={this.handleInputChange}
                            value={this.state.designation}>
                        </input>
                    </div>
                    <div className="buttons">
                        <Button
                            className="btn-round"
                            color="primary"
                            type="submit"
                        >
                            Create Grid
                        </Button>
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
        onAddGrid: grid => {
            dispatch(createHouseGrid(grid));
        }
    };
};

export default connect(
    null,
    mapDispatchToProps
)(CreateGrid);