import React from 'react';
import { connect } from 'react-redux';
import { createHouseGrid } from '../../actions/actionsHouseGrid';

class CreateHouseGrid extends React.Component {
    state = {
        id: '',
        designation: ''
    };

    handleInputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        if (this.state.designation.trim()) {
            this.props.onAddHouseGrid(this.state);
            this.handleReset();
        }
    };

    handleReset = () => {
        this.setState({
            designation: ''
        });
    };

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        Designation
                        <input
                            type="text"
                            className="form-control"
                            name="designation"
                            onChange={this.handleInputChange}
                            value={this.state.designation}
                        />
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
        onAddHouseGrid: houseGrid => {
            dispatch(createHouseGrid(houseGrid));
        }
    };
};

export default connect(
    null,
    mapDispatchToProps
)(CreateHouseGrid);