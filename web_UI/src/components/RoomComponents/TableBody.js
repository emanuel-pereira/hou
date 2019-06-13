import React, { Component } from 'react';

class TableBody extends Component {
    constructor(props) {
        super(props);
    }
    handleClickDetails = (id) =>{
        this.props.getDetails(id)
        this.props.getSensors(id)
    }

    render() {
        const {data, roomId} = this.props;
        const rows = data.map((row, index) => {
          if(roomId !== row.id) {
            return (
              <tr key={index}>
                <td>{row.id} </td>
                <td>{row.description}</td>
                <button onClick={() => this.handleClickDetails(row.id) }>
                    DETAILS
                </button>
              </tr>
            )
          }
            return (
              <tr key={index}>
                <td>{row.id}</td>
                <td>{row.description}</td>
              </tr>
            )
            })
        return (
            <tbody>{rows}</tbody>
        );
    }
}
export default TableBody;