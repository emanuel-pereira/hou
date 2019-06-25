import React from "react";

class Home extends React.Component {
  render() {
    return (
      <>
        <div className="content">
        <h1>Welcome to Your Smart Home</h1>
                <p>
                    The objective of our Smart Home application is to develop a system that allows you to manage and monitoring personal comfort requirements and electric energy consumption. The system will gather information about temperature, rainfall and energy consumption.
                </p>
                <p>
                    All the devices of your house can be connected. Not just computers and smartphones, but everything: clocks, speakers, lights, doorbells, cameras, windows, window blinds, hot water heaters, appliances, cooking utensils... All of those devices can communicate, send you information, and take your commands. It's not science fiction; it's the Internet of Things (IoT), and it's a key component of home automation and smart homes.
                </p>
                <div className="image">
                  <img
                    alt="..."
                    src={require("assets/img/banner.jpg")}
                  />
                </div>
        </div>
      </>
    );
  }
}

export default Home;
