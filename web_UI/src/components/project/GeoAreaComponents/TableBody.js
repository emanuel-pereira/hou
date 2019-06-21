import React, {Component} from 'react';

class TableBody extends Component {
    constructor(props) {
        super(props);
    }

}

render()
{
    const rows = data.map((row, index) => {
        return (
            <tr key={index}>
                <td>{row.id}</td>
                <td>{row.designation}</td>
                <td>{row.latitude}</td>
            </tr>
        )
    })
    return (
        <tbody>{rows}</tbody>
    );
}
export default TableBody;
