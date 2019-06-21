import React, { Component } from 'react';
class TableHeader extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        const {headers} = this.props;
        return (
            <thead>
            <tr>
                <th>{headers.id}</th>
                <th>{headers.designation}</th>
                <th>{headers.latitude}</th>
            </tr>
            </thead>
        );
    }
}
export default TableHeader;