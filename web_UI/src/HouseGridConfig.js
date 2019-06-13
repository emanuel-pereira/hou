import React, {Component} from 'react';
import HouseGridList from './components/GridsComponents/HouseGridList'


class HouseGridConfig extends Component {
    constructor() {
        super()
        this.state = {
            isHidden: true
        }
    }
    render() {
        return (
            <div className="list">
                <h1>House Grids Configuration</h1>
                <HouseGridList/>
            </div>
        );
    }
}

export default HouseGridConfig;