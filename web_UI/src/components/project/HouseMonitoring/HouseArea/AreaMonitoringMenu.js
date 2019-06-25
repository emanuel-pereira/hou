import React from 'react';
import AreaCurrentTemp from "./AreaCurrentTemp";
import AreaLastLowTemp from "./AreaLastLowTemp";
import AreaFirstHighTemp from "./AreaFirstHighTemp";
import AreaHighAmpliTemp from "./AreaHighAmpliTemp";

class AreaMonitoring extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: false,
        }
    }

    clickToShowCurrent() {
        this.setState({
            showCurrent: !this.state.showCurrent,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: false,
        })
    }

    clickToShowLastLow() {
        this.setState({
            showCurrent: false,
            showLastLow: !this.state.showLastLow,
            showFirstHigh: false,
            showHighAmpli: false,
        })
    }

    clickToShowFirstHigh() {
        this.setState({
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: !this.state.showFirstHigh,
            showHighAmpli: false,
        })
    }

    clickToShowHighAmpli() {
        this.setState({
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: !this.state.showHighAmpli,
        })
    }

    render() {

        return (
            <div>
                <h4>House Area Monitoring</h4>
                <table>
                    {/*buttons to access components*/}
                    <td>
                        <button onClick={this.clickToShowCurrent.bind(this)}>Current</button>
                    </td>
                    <td>
                        <button onClick={this.clickToShowLastLow.bind(this)}>Last Lowest Maximum</button>
                    </td>
                    <td>
                        <button onClick={this.clickToShowFirstHigh.bind(this)}>First Highest Maximum</button>
                    </td>
                    <td>
                        <button onClick={this.clickToShowHighAmpli.bind(this)}>Highest Amplitude</button>
                    </td>

                </table>

                {/*components calls*/}
                <table>
                    <tr>
                        {this.state.showCurrent && <AreaCurrentTemp/>}
                    </tr>
                    <tr>
                        {this.state.showLastLow && <AreaLastLowTemp/>}
                    </tr>
                    <tr>
                        {this.state.showFirstHigh && <AreaFirstHighTemp/>}
                    </tr>
                    <tr>
                        {this.state.showHighAmpli && <AreaHighAmpliTemp/>}
                    </tr>
                </table>
            </div>
        )


    }
}


export default AreaMonitoring
