import React, { Component } from 'react';
import { connect } from 'react-redux';

class RoomDetailList extends Component {
    constructor(props) {
        super(props);
    this.state = {
        isHidden: true
    }
}

toggleHiddenRooms() {
    this.setState({
        isHidden: !this.state.isHidden
    })
}

    render() {
        const { loading, error, data } = this.props.details;
        if (loading === true) {
            return (<h1>Loading ....</h1>);
        }
        else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {
                if (data.id) {
                    return (
                        <div className="roomCreation"><tr>
                            <h2>Room {data.id}</h2>
                            <p>Description: {data.description} </p>
                            <p>Floor: {data.floor} </p>
                            <p>Length: {data.length}</p>
                            <p>Width: {data.width}</p>
                            <p>Height: {data.height}</p>
                            <td ><button onClick={this.toggleHiddenRooms.bind(this)}>
                 EDIT
                </button></td>
                        </tr>
                        </div>
                    );
                }else{
                    return null;
                }
            }
        }
    }
}

const mapStateToProps = (state) => {
    return {
        details: {
            loading: state.details.loading,
            data: state.details.data,
            error: state.details.error,
            id: state.details.id,
        }
    }
}


export default connect(
    mapStateToProps,
    null
)(RoomDetailList);
