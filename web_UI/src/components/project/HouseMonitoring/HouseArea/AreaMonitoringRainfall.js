import React from 'react';
import GeneralCalendar from "./GeneralCalendar";
import {Card, CardBody, Table, Row, Col, Button, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem} from "reactstrap";


class AreaMonitoringRainfall extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            option: 0,
            showTotal: false,
            showAverage: false,
            showCard:false
        }

        this.clickToShowTotal = this.clickToShowTotal.bind(this)
        this.clickToShowAverage = this.clickToShowAverage.bind(this)
    }

    clickToShowTotal() {
        this.setState({
            option: 5,
            showTotal: true,
            showAverage: false,
            showCard:true
        })
    }

    clickToShowAverage() {
        this.setState({
            option: 6,
            showTotal: false,
            showAverage: true,
            showCard:true
        })
    }


    render() {
        return (<div>
            <UncontrolledDropdown>
                <DropdownToggle caret>
                    Rainfall
                </DropdownToggle>
                <DropdownMenu>
                    <DropdownItem onClick={this.clickToShowTotal}>Total Rainfall in Day</DropdownItem>
                    <DropdownItem onClick={this.clickToShowAverage}>Average Rainfall In Period</DropdownItem>
                </DropdownMenu>
            </UncontrolledDropdown>
                {this.state.showCard &&
                <Row>
                    <Col md="12">
                        <Card>
                            <CardBody>
                                <table>
                                    <tr>
                                        {this.state.showTotal &&
                                        <GeneralCalendar option={this.state.option}/>}
                                    </tr>
                                    <tr>
                                        {this.state.showAverage &&
                                        <p>Under Maintenance</p>}
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

export default AreaMonitoringRainfall

