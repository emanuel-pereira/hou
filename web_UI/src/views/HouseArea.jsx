import React from "react";
import CreateGeoArea from "components/project/GeoAreaComponents/CreateGeoArea";
import GeoAreaTable from "components/project/GeoAreaComponents/GeoAreaTable";
import GADetailList from "components/project/GeoAreaComponents/GADetailList";
import GASensors from "components/project/GeoAreaComponents/GASensors.jsx";


import {
    Card,
    Row,
    Col,
    Button,
} from "reactstrap";

class HouseArea extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isHiddenCreate: true,
            showDetails: false,
            showSensors: false,
        }
    }

    toggleHiddenRooms() {
        this.setState({
            isHiddenCreate: !this.state.isHiddenCreate
        })
    }

    showDetails() {
        this.setState({
            showDetails: true,
            showSensors: false,
        })
    }
    showSensors() {
        this.setState({
            showSensors: true,
            showDetails: false
        })
    }

    render() {
        return (
            <>
                <div className="content">
                    <Button onClick={this.toggleHiddenRooms.bind(this)}><i className="nc-icon nc-simple-add"></i></Button>
                    <Row>
                        <Col md="12">
                            <Card><tr>
                                <td>{this.state.isHiddenCreate && <GeoAreaTable onShowDetails={this.showDetails.bind(this)} onShowSensors={this.showSensors.bind(this)} />}</td>
                                <td>{this.state.isHiddenCreate && this.state.showDetails && <GADetailList />}</td>
                                <td>{this.state.isHiddenCreate && this.state.showSensors && <GASensors />}</td>

                            </tr>
                                {!this.state.isHiddenCreate && <CreateGeoArea onClose={this.toggleHiddenRooms.bind(this)} />}
                            </Card>
                        </Col>
                    </Row>
                </div>
            </>
        );
    }
}

export default HouseArea;
