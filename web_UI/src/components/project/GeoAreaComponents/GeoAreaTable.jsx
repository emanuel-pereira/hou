import React from "react";
import { connect } from 'react-redux';
import { fetchGeoAreas, fetchGADetails,fetchGASensors } from 'actions/actionsGeoArea'

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

    fetchGADetails = (id) => {
        this.props.onfetchGADetails(id);
        this.props.onShowDetails();
    }
    fetchGASensors = (id) => {
        this.props.onfetchGASensors(id);
        this.props.onShowSensors();
    }

    render() {
        const data = this.props.geoareas.data;
        const rows = data.map((row, index) => {
            return (
                <tr key={index}>
                    <td>{row.identification} </td>
                    <td>{row.designation}</td>
                    <td onClick={() => this.fetchGADetails(row.identification)}><i className="nc-icon nc-zoom-split text-primary" /> <b className="text-primary">Details</b></td>
                    <td onClick={() => this.fetchGASensors(row.identification)}><i className="nc-icon nc-sound-wave text-primary" /> <b className="text-primary">Sensors</b></td>
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
                            <Card>
                                <CardBody>
                                    <Table>
                                        <thead className="text-primary">
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {rows}
                                        </tbody>
                                    </Table>
                                </CardBody>
                            </Card>
                </div>
            );
        }
    }
}

const mapStateToProps = (state) => {
    return {
        geoareas: {
            data: state.geoareas.geoareas.data
        },
        sensors: {
            data: state.rooms.sensors.data,
          },
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onfetchGeoAreas: () => {
            dispatch(fetchGeoAreas())
        },
        onfetchGADetails: (id) => {
            dispatch(fetchGADetails(id))
        },
        onfetchGASensors: (id) => {
            dispatch(fetchGASensors(id))
          }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(GeoAreaTable);