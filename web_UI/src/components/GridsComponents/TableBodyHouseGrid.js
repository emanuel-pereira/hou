import React, { Component } from 'react';

class TableBodyHouseGrid extends Component {
    constructor(props) {
        super(props);
    }
    handleClick = (id) =>{
        this.props.getDetailsHouseGrid(id)
    }
    render() {
        const {data, id} = this.props;
        const rows = data.map((row, index) => {
          if(id !== row.id) {
            return (
              <tr key={index}>
                <td onClick={() => this.handleClick(row.id)}>{row.id} </td>
                <td>{row.designation}</td>

              </tr>
                /* <button onClick={() => this.handleClick(row.id)}>
                      config
                  </button>*/
            )
          }
            return (
              <tr key={index}>
                <td>{row.id}</td>
                <td>{row.designation}</td>
              </tr>
            )
            })
        return (
            <tbody>{rows}</tbody>
        );
    }
}
export default TableBodyHouseGrid;

