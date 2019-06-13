import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchRooms, fetchRoomDetails,fetchRoomSensors } from '../../actions/actionsRoom';
import TableHeader from './TableHeader';
import TableBody from './TableBody';

class RoomTable extends Component {
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        this.props.onfetchRooms();
    }
    fetchRoomDetails = (id) => {
        this.props.onfetchRoomDetails(id);
    }
    fetchRoomSensors = (id) => {
        this.props.onfetchRoomSensors(id);
    }
    render() {

        const headers = {
            id: "ID",
            name: "Description",
        };
        const { loading, error, data } = this.props.rooms;
        const roomId = this.props.roomId;
        if (loading === true) {
            return (<h1>Loading ....</h1>);
        }
        else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {
                if (data.length > 0) {
                    return (
                        <table >
                            <TableHeader headers={headers} />
                            <TableBody data={data} getDetails={this.fetchRoomDetails} getSensors={this.fetchRoomSensors} roomId={roomId}/>
                        </table>
                    );
                } else {
                    return (null);
                }
            }
        }
    }
}


const mapStateToProps = (state) => {
    return {
        rooms: {
            loading: state.rooms.loading,
            data: state.rooms.data,
            error: state.rooms.error,
        },
        roomId: state.details.roomId,
        sensors: state.sensors,
    }
}
const mapDispatchToProps = (dispatch) => {
    return {
        onfetchRooms: () => {
            dispatch(fetchRooms())
        },
        onfetchRoomDetails: (id) => {
            dispatch(fetchRoomDetails(id))
        },
        onfetchRoomSensors: (id) => {
            dispatch(fetchRoomSensors(id))
        }
        
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(RoomTable);

