import React from "react";
import {connect} from 'react-redux';
import {fetchRooms} from 'actions/actionsRoom';
import {fetchCurrTempRooms, fetchRoomMaxTemp} from "../../../actions/actionsRoomsMonitoring";

import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";

class RoomMonitoringTable extends React.Component {

    componentDidMount() {
        this.props.onfetchRooms();
    }

    fetchCurrTempRooms = (id) => {
        this.props.onfetchCurrTempRooms(id);
        this.props.onShowCurrTempRooms();
    }
    fetchRoomMaxTemp = ({id, day}) => {
        this.props.onfetchRoomMaxTemp = (id, day);
        this.props.onShowfetchRoomMaxTemp();
    }


    render() {
        const data = this.props.rooms.data;
        const rows = data.map((row, index) => {
            return (
                <tr key={index}>
                    <td>{row.id} </td>
                    <td>{row.description}</td>
                    <td onClick={() => this.fetchCurrTempRooms(row.id)}><i
                        className="nc-icon nc-zoom-split text-primary"/> <b className="text-primary">Get Temperature</b>
                    </td>
                    <td onClick={() => this.fetchRoomMaxTemp(row.id)}><i className="nc-icon nc-zoom-split text-danger"/>
                        <b className="text-danger">Max Temperature</b></td>

                </tr>)
        })
        if (data.length === 0) {
            return (
                <p>No data temperature</p>
            )
        } else {
            return (
                <div className="content">
                    <Row>
                        <Col md="12">
                            <Card>
                                <CardBody>
                                    <Table>
                                        <thead className="text-primary">
                                        <tr>
                                            <th>ID</th>
                                            <th>Designation</th>
                                        </tr>
                                        </thead>
                                        <tbody>{rows}</tbody>
                                    </Table>
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
                </div>
            );
        }
    }
}

const mapStateToProps = (state) => {
    return {
        rooms: {
            data: state.rooms.rooms.data,
        },
        roomCurrentTemp: {

            data: state.data,
        },

        roomMaxTemp: {

            data: state.data,
        }

    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onfetchRooms: () => {
            dispatch(fetchRooms())
        },
        onfetchCurrTempRooms: (id) => {
            dispatch(fetchCurrTempRooms(id))
        },
        onfetchRoomMaxTemp: (id, day) => {
            dispatch(fetchRoomMaxTemp(id, day))
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(RoomMonitoringTable);

