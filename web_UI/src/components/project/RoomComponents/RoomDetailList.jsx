import React, { Component } from 'react';
import { connect } from 'react-redux';
import UpdateRoom from "./UpdateRoom";
import {
    Card,
    CardHeader,
    CardBody,
    CardTitle,
    Table,
    Row,
    Col
} from "reactstrap";

class RoomDetailList extends Component {
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

    renderEditView = () => {
        return <UpdateRoom onClose={this.changeEditMode}/>
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
                if (data.id) {
                    return (
                        <div className="content">
                            <Row>
                                <Col md="12">
                                    <Card>
                                        <CardBody>
                                            <Table>
                                                <thead className="text-primary">
                                                    <tr>
                                                        <th>Room - {data.id}</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr><td>Description:</td> <td>{data.description}</td></tr>
                                                    <tr><td>Floor:</td> <td>{data.floor}</td></tr>
                                                    <tr><td>Length:</td> <td>{data.length} m</td></tr>
                                                    <tr><td>Width:</td> <td>{data.width} m </td></tr>
                                                    <tr><td>Height:</td> <td>{data.height}m </td></tr>
                                                    <td ><button onClick={this.changeEditMode}>
                                                        EDIT</button></td>
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
            loading: state.rooms.details.loading,
            data: state.rooms.details.data,
            error: state.rooms.details.error,
            id: state.rooms.details.id,
        }
    }
}


export default connect(
    mapStateToProps,
    null
)(RoomDetailList);
