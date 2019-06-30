import React from 'react'

class ErrorMessage extends React.Component {
    render() {
        return(
            <div><p>ERROR :It wasn't possible to successfully return a value. Possible causes for this: </p>
            <ul>
                <li>
                    No Geographical Areas and/or External Sensors;
                </li>
                <li>
                    Undefined House Geographical Area and/or Location;
                </li>
                <li>
                    No Temperature Sensors in the House's Geographical Area;
                </li>
                <li>
                    No Available Readings between the selected dates.
                </li>
            </ul>
        </div>);
    }
}

export default ErrorMessage