import React, { Component } from 'react';
import { connect } from 'react-redux';
import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";

class GADetailList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isHidden: true,
            isInEditMode: false
        }
    }

    changeEditMode = () => {
        this.setState({
            isInEditMode: !this.state.isInEditMode
        })
    }

    toggleHiddenRooms() {
        this.setState({
            isHidden: !this.state.isHidden
        })
    }

    renderDefaultView = () => {
        const { loading, error, data } = this.props.details;
        if (loading === true) {
            return (<h1>Loading ....</h1>);
        }
        else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {
                if (data.identification) {
                    return (
                        <div className="content">
                            <Row>
                                <Col md="12">
                                    <Card>
                                        <CardBody>
                                            <Table>
                                                <thead className="text-primary">
                                                    <tr>
                                                        <th>Geographical Area of {data.identification}</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr><td>Type:</td> <td>{data.type.type}</td></tr>
                                                    <tr><td>Location (GPS coordinates):</td><td>({data.location.latitude},{data.location.longitude},{data.location.altitude})</td></tr>
                                                    <tr><td>Occupation:</td> <td>{data.occupation.occupation} m ({data.occupation.length} x {data.occupation.width})</td></tr>
                                                </tbody>
                                            </Table>
                                        </CardBody>
                                    </Card>
                                </Col>
                            </Row>
                        </div>
                    );
                } else {
                    return null;
                }
            }
        }
    }

    render() { 
        return this.state.isInEditMode ?
            this.renderEditView() :
            this.renderDefaultView()
    }
}
const mapStateToProps = (state) => {
    return {
        details: {
            loading: state.geoareas.details.loading,
            data: state.geoareas.details.data,
            error: state.geoareas.details.error,
            id: state.geoareas.details.id,
        }
    }
}


export default connect(
    mapStateToProps,
    null
)(GADetailList);
