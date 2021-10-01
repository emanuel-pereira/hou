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
    Table,

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

    toggleHidden() {
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
            <div className="content">
                <Button onClick={this.toggleHidden.bind(this)}>
                    <i className="nc-icon nc-simple-add"></i>
                </Button>
                <div>
                    {!this.state.isHiddenCreate && <CreateGeoArea onClose={this.toggleHidden.bind(this)}/>}
                </div>
                <div>
                    <Table>
                        <Row>
                            <Col md="5">{this.state.isHiddenCreate &&
                            <GeoAreaTable onShowDetails={this.showDetails.bind(this)}
                                          onShowSensors={this.showSensors.bind(this)}/>}</Col>
                            {this.state.isHiddenCreate && this.state.showDetails && <GADetailList/>}
                            {this.state.isHiddenCreate && this.state.showSensors && <GASensors/>}
                        </Row>
                    </Table>
                </div>
            </div>

        );
    }
}

export default HouseArea;
