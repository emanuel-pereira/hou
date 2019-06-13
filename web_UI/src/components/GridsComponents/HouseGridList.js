import React, { Component } from 'react';
import HouseGridTable from './HouseGridTable';
import HouseGridDetailList from './HouseGridDetailList';
import CreateHouseGrid from './CreateHouseGrid';
import AddButton from "../AddButton";


class HouseGridList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isHidden: true
        }
    }

    toggleHiddenHouseGrids() {
        this.setState({
            isHidden: !this.state.isHidden
        })
    }
    render() {
        return (
            <div>
                <table>
                    <td>
                        <a onClick={this.toggleHiddenHouseGrids.bind(this)}>
                            <AddButton/>
                            <a>New House Grid</a>
                        </a>
                        {!this.state.isHidden && <CreateHouseGrid/>}</td>
                    <tr>
                        <td>
                            <HouseGridTable  />
                        </td>


                    </tr>

                </table>
            </div>
        );
    }
}
export default HouseGridList;