import React from 'react'
import Datetime from 'react-datetime';
import {
    Card,
    CardBody,
    CardHeader,
    CardTitle,
    FormGroup,
    Table,
    Row,
    Col,
    Button,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem
} from "reactstrap";
import AreaTempGeneral from "./AreaTempGeneral";

class GeneralCalendar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            option: this.props.option,
            startDate: undefined,
            endDate: undefined,
            day: undefined,
        }

        this.changeDate = this.changeDate.bind(this)
        this.getResult = this.getResult.bind(this)
    }

    getResult() {
        this.setState({
            showResult: !this.state.showResult
        })
    }

    changeDate = e => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }


    render() {
        if (this.state.option === 1) {
            return <AreaTempGeneral option={this.state.option}/>
        }

        if (this.state.option === 5) {
            return (<div>
                <Row>
                    <Col xs={12} md={4}>
                        <br/>
                        <table>
                            <td>
                                <div className="form-group">
                                    Pick a Day:
                                    <input
                                        type="date"
                                        className="form-control"
                                        name="day"
                                        onChange={this.changeDate}
                                        value={this.state.day}>
                                    </input>
                                </div>
                            </td>
                        </table>
                    </Col>
                </Row>
                <Button onClick={this.getResult} color="primary">Get Value</Button>
                <tr>
                    {this.state.showResult &&
                    <AreaTempGeneral
                        day={this.state.day}
                        option={this.state.option}
                    />}
                </tr>
            </div>)
        }
        else {
            return (
                <div>
                    <Row>
                        <Col xs={12} md={4}>
                            <br/>
                            <table>
                                <td>
                                    <div className="form-group">
                                        Pick a Start Date:
                                        <input
                                            type="date"
                                            className="form-control"
                                            name="startDate"
                                            onChange={this.changeDate}
                                            value={this.state.startDate}>
                                        </input>
                                    </div>
                                </td>
                                <td>
                                    <div className="form-group">
                                        Pick an End Date:
                                        <input
                                            type="date"
                                            className="form-control"
                                            name="endDate"
                                            onChange={this.changeDate}
                                            value={this.state.endDate}>
                                        </input>
                                    </div>
                                </td>
                            </table>
                        </Col>
                    </Row>
                    <Button onClick={this.getResult} color="primary">Get Value</Button>
                    <tr>
                        {this.state.showResult &&
                        <AreaTempGeneral
                            startDate={this.state.startDate}
                            endDate={this.state.endDate}
                            option={this.state.option}
                        />}
                    </tr>
                </div>
            )
        }
    }
}

export default GeneralCalendar