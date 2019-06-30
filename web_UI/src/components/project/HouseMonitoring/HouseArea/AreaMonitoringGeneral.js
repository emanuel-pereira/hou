import React from 'react';
import GeneralCalendar from "./GeneralCalendar";
import {Card, CardBody, Table, Row, Col, Button, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem} from "reactstrap";


class AreaMonitoringGeneral extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            option: 0,
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: false,
            showCard:false
        }

        this.clickToShowCurrent = this.clickToShowCurrent.bind(this)
        this.clickToShowLastLow = this.clickToShowLastLow.bind(this)
        this.clickToShowFirstHigh = this.clickToShowFirstHigh.bind(this)
        this.clickToShowHighAmpli = this.clickToShowHighAmpli.bind(this)
    }

    clickToShowCurrent() {
        this.setState({
            option: 1,
            showCurrent: true,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: false,
            showCard:true
        })
    }

    clickToShowLastLow() {
        this.setState({
            option: 2,
            showCurrent: false,
            showLastLow: true,
            showFirstHigh: false,
            showHighAmpli: false,
            showCard:true
        })
    }

    clickToShowFirstHigh() {
        this.setState({
            option: 3,
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: true,
            showHighAmpli: false,
            showCard:true
        })
    }

    clickToShowHighAmpli() {
        this.setState({
            option: 4,
            showCurrent: false,
            showLastLow: false,
            showFirstHigh: false,
            showHighAmpli: true,
            showCard:true
        })
    }


    render() {
        return (<div>
            <UncontrolledDropdown>
                <DropdownToggle caret>
                    Temperature
                </DropdownToggle>
                <DropdownMenu>
                    <DropdownItem onClick={this.clickToShowCurrent.bind(this)}>Current</DropdownItem>
                    <DropdownItem onClick={this.clickToShowLastLow}>Last Lowest Maximum</DropdownItem>
                    <DropdownItem onClick={this.clickToShowFirstHigh.bind(this)}>First Highest Maximum</DropdownItem>
                    <DropdownItem onClick={this.clickToShowHighAmpli.bind(this)}>Highest Amplitude</DropdownItem>
                </DropdownMenu>
            </UncontrolledDropdown>
                {this.state.showCard &&
                <Row>
                    <Col md="12">
                        <Card>
                            <CardBody>
                                <table>
                                    <tr>
                                        {this.state.showCurrent &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <tr>
                                        {this.state.showLastLow &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <tr>
                                        {this.state.showFirstHigh &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <tr>
                                        {this.state.showHighAmpli &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                </table>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>}
        </div>

        /*<div className="content">
                <Row>
                    <Col md="12">
                        <Card>
                            <CardBody>
                                <table>
                                    <thead className="text-primary">
                                    <tr>
                                        <th>ID</th>
                                        <th>Designation</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <td>
                                        <Button color="primary" disabled={this.state.showCurrent} onClick={this.clickToShowCurrent.bind(this)}>Current</Button>
                                    </td>
                                    <tr>
                                        {this.state.showCurrent &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <td>
                                        <Button color="primary" disabled={this.state.showLastLow} onClick={this.clickToShowLastLow}>Last Lowest Maximum</Button>
                                    </td>
                                    <tr>
                                        {this.state.showLastLow &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <td>
                                        <Button color="primary" disabled={this.state.showFirstHigh} onClick={this.clickToShowFirstHigh.bind(this)}>First Highest Maximum
                                        </Button>
                                    </td>
                                    <tr>
                                        {this.state.showFirstHigh &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <td>
                                        <Button color="primary" disabled={this.state.showHighAmpli} onClick={this.clickToShowHighAmpli.bind(this)}>Highest Amplitude
                                        </Button>
                                    </td>
                                    <tr>
                                        {this.state.showHighAmpli &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    </tbody>
                                </table>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </div>*/

        /*  < div >
            < h4 > House
        Area
        Monitoring < /h4>
        <table>
            {/!*buttons to access components*!/}
            <td>
                <button onClick={this.clickToShowCurrent.bind(this)}>Current</button>
            </td>
            <td>
                <button onClick={this.clickToShowLastLow}>Last Lowest Maximum</button>
            </td>
            <td>
                <button onClick={this.clickToShowFirstHigh.bind(this)}>First Highest Maximum</button>
            </td>
            <td>
                <button onClick={this.clickToShowHighAmpli.bind(this)}>Highest Amplitude</button>
            </td>

        </table>*/

        /*components calls*/

        /*<table>
            <tr>
                {this.state.showCurrent &&
                <GeneralCalendar option={this.state.option}/>}
            </tr>
            <tr>
                {this.state.showLastLow &&
                <GeneralCalendar option={this.state.option}/>}
            </tr>
            <tr>
                {this.state.showFirstHigh &&
                <GeneralCalendar option={this.state.option}/>}
            </tr>
            <tr>
                {this.state.showHighAmpli &&
                <GeneralCalendar option={this.state.option}/>}
            </tr>
        </table>
        < /div>*/
    )
    }

}

export default AreaMonitoringGeneral

