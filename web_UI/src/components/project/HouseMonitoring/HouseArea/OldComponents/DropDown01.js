import React, {Component} from 'react';
import {Dropdown} from 'primereact/dropdown'


class DropDown01 extends Component {
    constructor(){
        super();
        this.state = {
            report:null
        }
        this.onOptionChange = this.onOptionChange.bind(this);
    }


    onOptionChange(choice){
        this.setState({report: choice.value})
    }

    render(){
        const reports = [
            {id:'600',name:'Current'},
            {id:'630',name:'Last Coldest Day'},
            {id:'631',name:'First Hottest Day'},
            {id:'633',name:'Highest Amplitude Day'}
        ]
        return (<div>
                <h1>Area Monitoring</h1>
                <p>Choose an option to obtain temperature sensor reports</p>
                <Dropdown value={this.state.option} options={reports} onChange={this.onOptionChange} placeholder="Select an Option" optionLabel="name"/>
                <div style={{marginTop: '.5em'}}>{this.state.report ? 'Selected Report: ' + this.state.report.name : 'No option selected'}</div>
            </div>
        )
    }
}

export default DropDown01