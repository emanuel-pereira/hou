import React, {Component} from 'react';
import {connect} from 'react-redux';
import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";
import CardTitle from "reactstrap/es/CardTitle";

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
        const {loading, error, data} = this.props.details;
        if (loading === true) {
            return (<h1>Loading ....</h1>);
        } else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {
                if (data.identification) {
                    return (
                        <div>

                            <Card>
                                <CardBody>
                                    <CardTitle>
                                        <h6>Geographical Area of {data.identification}</h6>
                                    </CardTitle>
                                    <Table>
                                        <tr>
                                            <td>Type:</td>
                                            <td>{data.type.type}</td>
                                        </tr>
                                        <tr>
                                            <td>Location:</td>
                                            <td>{data.location.latitude}, {data.location.longitude}, {data.location.altitude}</td>
                                        </tr>
                                        <tr>
                                            <td>Occupation Area:</td>
                                            <td>{data.occupation.occupation} m ({data.occupation.length} x {data.occupation.width} m)
                                            </td>
                                        </tr>
                                    </Table>
                                </CardBody>
                            </Card>
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
