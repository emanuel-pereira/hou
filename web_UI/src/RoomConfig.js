import React, {Component} from 'react';
import RoomList from './components/RoomComponents/RoomList';


class RoomConfig extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isHidden: true
        }
    }
    render() {
        return (
            <div className="list">
                <h1>Room Configuration</h1>
                <RoomList/>
            </div>
        );
    }
}

export default RoomConfig;