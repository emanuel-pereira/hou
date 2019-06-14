import React from "react";
import RoomTable from "components/project/RoomComponents/RoomTable.jsx";
import CreateRoom from "components/project/RoomComponents/CreateRoom";
import RoomDetailList from "components/project/RoomComponents/RoomDetailList";

class RoomConfig extends React.Component {
  render() {
    return (
      <>
        <div className="content">
          <h6>New Room  <i className="nc-icon nc-simple-add" onClick /></h6>
          <tr><RoomTable />
          <td><RoomDetailList/></td> </tr>
          <CreateRoom />
        </div>
      </>
    );
  }
}

export default RoomConfig;
