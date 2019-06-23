import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";

class GridRooms extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isHidden: true,
            isInEditMode: false
        }
    }

    toggleHiddenRooms() {
        this.setState({
            isHidden: !this.state.isHidden
        })
    }

render(){
    const data  = this.props.houseGridRooms.data
    const rows = [data.rooms].map((row, index) => {
        return (
            <table>
                <tbody>
                <tr key={index}>
                    <tr><td> {row}</td></tr>
                </tr>
                </tbody>
            </table>
        )
    })
    if (data) {
        return (
            <div className="content">
                <Row>
                    <Col md="12">
                        <Card>
                            <CardBody>
                                <Table>
                                    <thead className="text-primary">
                                        <tr>
                                            <th>Rooms </th>
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
    } else {
        return null
    }
}
}
const mapStateToProps = (state) => {
    return {
        houseGridRooms: {
            data: state.grids.houseGridRooms.data
        }
    }
}


export default connect(
    mapStateToProps,
    null
)(GridRooms);