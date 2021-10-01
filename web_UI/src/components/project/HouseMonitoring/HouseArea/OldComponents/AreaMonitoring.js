import React from 'react';
import AreaCurrentTemp from "./AreaCurrentTemp";
import AreaLastLowCal from "./AreaLastLowCal";
import AreaFirstHighCal from "./AreaFirstHighCal";
import AreaHighAmpliCal from "./AreaHighAmpliCal";


class AreaMonitoring extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: false,
            showCalendar: false,
            startDate: undefined,
            endDate: undefined,
        }

        this.changeDate = this.changeDate.bind(this)
        /*this.getCalendar = this.getCalendar.bind(this)*/
        this.clickToShowLastLow = this.clickToShowLastLow.bind(this)
    }

    clickToShowCurrent() {
        this.setState({
            showCurrent: !this.state.showCurrent,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: false,
            showCalendar: false,
        })
    }

    clickToShowLastLow() {
        this.setState({
            showCurrent: false,
            showLastLow: !this.state.showLastLow,
            showFirstHigh: false,
            showHighAmpli: false,
            showCalendar: !this.state.showCalendar,
        })
    }

    clickToShowFirstHigh() {
        this.setState({
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: !this.state.showFirstHigh,
            showHighAmpli: false,
            showCalendar: !this.state.showCalendar,
        })
    }

    clickToShowHighAmpli() {
        this.setState({
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: !this.state.showHighAmpli,
            showCalendar: !this.state.showCalendar,
        })
    }

    changeDate(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    render() {
        let moment = require('moment/moment')
        moment().format();


        return (
            <div>
                <h4>House Area Monitoring</h4>
                {/*dropdown menu WIP
                <div className="dropdown">
                    <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Temperature Reports
                    </button>
                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a className="dropdown-item" href="#">Current Temperature</a>
                        <a className="dropdown-item" href="#">Last Lowest Maximum Temperature</a>
                        <a className="dropdown-item" href="#">First Highest Maximum Temperature</a>
                        <a className="dropdown-item" href="#">Highest Temperature Amplitude</a>
                    </div>
                </div>*/}
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
                        {this.state.showLastLow &&
                    <AreaLastLowCal/>}
                    </tr>
                    <tr>
                        {this.state.showFirstHigh &&
                        <AreaFirstHighCal/>}
                    </tr>
                    <tr>
                        {this.state.showHighAmpli &&
                        <AreaHighAmpliCal/>}
                    </tr>
                </table>
            </div>
        )
    }

}

export default AreaMonitoring

