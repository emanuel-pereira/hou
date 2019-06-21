import React from "react";
import CreateGeoArea from "components/project/GeoAreaComponents/CreateGeoArea";
import GeoAreaTable from "components/project/GeoAreaComponents/GeoAreaTable"


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

    toggleHidden() {
        this.setState({
            isHiddenCreate: !this.state.isHiddenCreate
        })
    }



    render() {
        return (
            <>
                <div className="content">
                    <Button onClick={this.toggleHidden.bind(this)}>
                        <i className="nc-icon nc-simple-add"></i>
                    </Button>
                    <Row>
                        <Col md="12">
                            <Card>
                                <tr>
                                    <td>{this.state.isHiddenCreate && <GeoAreaTable />}</td>
                                </tr>
                                {!this.state.isHiddenCreate && <CreateGeoArea onClose={this.toggleHidden.bind(this)}/>}
                            </Card>
                        </Col>
                    </Row>
                </div>
            </>
        );
    }
}

export default HouseArea;
