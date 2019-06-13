import React, { Component } from 'react'
import RoomList from './RoomComponents/RoomList'
import HouseGridList from './GridsComponents/HouseGridList'

class App extends Component {
  render() {
    return (
      <div>
        <h1>Room Configuration</h1>
        <RoomList />

        <HouseGridList />
      </div>
    )
  }
}

export default App;
