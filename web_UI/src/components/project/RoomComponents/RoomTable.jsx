import React from "react";
import { connect } from 'react-redux';
import { fetchRooms, fetchRoomDetails, fetchRoomSensors } from 'actions/actionsRoom';


// reactstrap components
import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Table,
  Row,
  Col
} from "reactstrap";

class RoomTable extends React.Component {
  constructor(props) {
    super(props);
  }
  componentDidMount() {
    this.props.onfetchRooms();
  }

  fetchRoomDetails = (id) => {
    this.props.onfetchRoomDetails(id);
    
}
  
  render() {
    const { data } = this.props.rooms;
    const rows = data.map((row, index) => {
      return (
        <tr key={index}>
          <td>{row.id} </td>
          <td>{row.description}</td>
          <td><i className="nc-icon nc-zoom-split text-primary" onClick={() => this.fetchRoomDetails(row.id)} /> <b className="text-primary">Details</b></td>
          <td><i className="nc-icon nc-touch-id" onClick={() => this.handleClickDetails(row.id)} />Sensors </td>
        </tr>)
    })
    if(data.length===0) {
      return (
        <p>No Rooms Available</p>
          )
    }
    else{
    return (
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">Room Configuration</CardTitle>
              </CardHeader>
              <CardBody>
                <Table responsive>
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
      data: state.rooms.data,
    },
    roomId: state.details.roomId,
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
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RoomTable);