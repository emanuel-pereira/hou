import React from "react";
import { connect } from 'react-redux';
import { fetchRooms, fetchRoomDetails, fetchRoomSensors } from 'actions/actionsRoom';

import {
  Card,
  CardBody,
  Table,
  Row,
  Col
} from "reactstrap";

class RoomTable extends React.Component {

  componentDidMount() {
    this.props.onfetchRooms();
  }

  fetchRoomDetails = (id) => {
    this.props.onfetchRoomDetails(id);
    this.props.onShowDetails();
  }
  fetchRoomSensors = (id) => {
    this.props.onfetchRoomSensors(id);
    this.props.onShowSensors();
  }


  render() {
    const data = this.props.rooms.data;
    const rows = data.map((row, index) => {
      return (
        <tr key={index}>
          <td>{row.id} </td>
          <td>{row.description}</td>
          <td onClick={() => this.fetchRoomDetails(row.id)}><i className="nc-icon nc-zoom-split text-primary" /> <b className="text-primary">Check Details</b></td>
          <td onClick={() => this.fetchRoomSensors(row.id)}><i className="nc-icon nc-touch-id text-danger" /> <b className="text-danger">Sensors</b></td>
        </tr>
        )
    })
    if (data.length === 0) {
      return (
        <p>No Rooms Available</p>
      )
    }
    else {
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
    sensors: {
      data: state.rooms.sensors.data,
    }
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