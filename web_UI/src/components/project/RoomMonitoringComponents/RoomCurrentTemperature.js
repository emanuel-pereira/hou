import React, { Component } from 'react';


import { connect } from 'react-redux';
import {
    Card,
    CardBody,
    Table,
    Row,
    Col
} from "reactstrap";

class RoomCurrentTemperature extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        const {roomId, loading, error, data} = this.props.roomCurrentTemp;

        let moment =  require ('moment/moment');
        moment().format();

        /*if (loading === true) {
            return (<h1>Loading ....</h1>);
        } else {
            if (error !== null) {
                return (<h1>Error ....</h1>);
            } else {*/
                //if (data.roomId && data.roomCurrentTemp) {
                    return (
                        <div className="content">
                            <Row>
                                <Col md="12">
                                    <Card>
                                        <CardBody>
                                            <Table>
                                                <thead className="text-primary">
                                                <tr>
                                                    <th>Room{data.roomId}</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>

                                                    <td>Date: {moment(data.readingDateAndTime).format('DD-MM-YYYY')}</td>
                                                </tr>
                                                <tr>
                                                    <td>Temperature:</td>
                                                    <td>{data.readingValue}ºC</td>
                                                </tr>

                                                </tbody>
                                            </Table>
                                        </CardBody>
                                    </Card>
                                </Col>
                            </Row>
                        </div>
                    );
               /* } else {
                    return <p>Not working</p>;

            }*/
                }
/*
        }
    }
*/
}

const mapStateToProps = (state) => {
    return {

        roomCurrentTemp: {
            roomId: state.roomCurrentTemp.roomCurrentTemp.roomId,
            loading: state.roomCurrentTemp.roomCurrentTemp.loading,
            error: state.roomCurrentTemp.roomCurrentTemp.error,
            data: state.roomCurrentTemp.roomCurrentTemp.data,
        }

    }
}



export default connect(
    mapStateToProps,
    null
)(RoomCurrentTemperature)


