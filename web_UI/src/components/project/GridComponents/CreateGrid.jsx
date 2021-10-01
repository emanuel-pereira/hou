import React from 'react';
import { connect } from 'react-redux';
import { createHouseGrid } from 'actions/actionsGrid';
import {
    Button,
    Card

} from "reactstrap";
import CardBody from "reactstrap/es/CardBody";

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

        })
        this.props.onClose();
    };

    render() {
        return (
            <div className="content">
                <Card>
                    <CardBody>
                <h2>New House Grid</h2>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group col-md-6">
                        <label>ID</label>
                        <input
                            placeholder="ex. PG1"
                            type="text"
                            className="form-control"
                            name="id"
                            onChange={this.handleInputChange}
                            value={this.state.id}
                        />
                    </div>
                    <div className="form-group col-md-6">
                        <label>Description</label>
                        <input
                            placeholder="ex. PowerGrid1"
                            type="text"
                            className="form-control"
                            name="designation"
                            onChange={this.handleInputChange}
                            value={this.state.designation}>
                        </input>
                    </div>
                    <div className="buttons">
                        <Button  color="success"
                            type="submit"
                        >
                            SAVE
                        </Button>
                        <button type="button" className="btn btn-danger" onClick={this.handleReset}>
                            CANCEL
                        </button>
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
        onAddGrid: grid => {
            dispatch(createHouseGrid(grid));
        }
    };
};

export default connect(
    null,
    mapDispatchToProps
)(CreateGrid);