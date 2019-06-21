import React, {Component} from 'react';
import GeoAreaTable from './GeoAreaTable';
import CreateGeoArea from './CreateGeoArea';
import {
    Button
} from "reactstrap";


class GeoAreaList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isHidden: true
        }
    }

    toggleHiddenGeoAreas() {
        this.setState({
            isHidden: !this.state.isHidden
        })
    }

    render() {
        return (
            <div>
                <table>
                    <Button color="primary" onClick={this.toggleHiddenGeoAreas.bind(this)}>
                    New Area
                    </Button>
                    <tr></tr>
                    <td><GeoAreaTable/></td>
                    <td>{!this.state.isHidden && <CreateGeoArea/>}</td>
                </table>
            </div>
        );
    }
}

export default GeoAreaList;