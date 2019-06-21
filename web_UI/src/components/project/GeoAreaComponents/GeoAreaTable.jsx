import React from "react";
import {connect} from 'react-redux';
import {fetchGeoAreas} from 'actions/actionsGeoArea'

import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";

class GeoAreaTable extends React.Component {

    componentDidMount() {
        this.props.onfetchGeoAreas();
    }

    render() {
        const data = this.props.geoareas.data;
        const rows = data.map((row, index) => {
            return (
                <tr key={index}>
                    <td>{row.id} </td>
                    <td>{row.designation}</td>
                    <td>{row.latitude}</td>
                </tr>
            )
        })
        if (data.length === 0) {
            return (
                <p>No Areas Available</p>
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
                                            <th>Name</th>
                                            <th>Latitude</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {rows}
                                        </tbody>
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
        geoareas: {
            data: state.geoareas.geoareas.data
        }
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onfetchGeoAreas: () => {
            dispatch(fetchGeoAreas())
        },
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(GeoAreaTable);