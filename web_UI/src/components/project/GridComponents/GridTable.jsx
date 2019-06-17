import React from "react";
import { connect } from 'react-redux';
import { fetchHouseGrids } from 'actions/actionsGrid';

import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Table,
  Row,
  Col
} from "reactstrap";

class GridTable extends React.Component {

  componentDidMount() {
    this.props.onfetchHouseGrids();
  }
  
  render() {
    const data = this.props.houseGrids.data;
    const rows = data.map((row, index) => {
      return (
        <tr key={index}>
          <td>{row.id} </td>
          <td>{row.designation}</td>
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
    houseGrids: {
      data: state.grids.houseGrids.data,
    },
  }
}
const mapDispatchToProps = (dispatch) => {
  return {
    onfetchHouseGrids: () => {
      dispatch(fetchHouseGrids())
    }
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GridTable);