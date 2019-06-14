import React from "react";

class Home extends React.Component {
  render() {
    return (
      <>
        <div className="content">
        <h1>Welcome to Smart Home</h1>
                <p>Configure your house to get the most of it!
                </p>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in ipsum id elit lobortis scelerisque.
                    Vestibulum feugiat lobortis velit id semper. Aliquam in augue pharetra, posuere dui quis, porttitor
                    arcu. Cras aliquet volutpat lacus, ac cursus elit volutpat eu. Sed id est at tellus sodales
                    ultricies eu at velit. Integer sapien ipsum, scelerisque sed eleifend et, ultricies vitae mi. Etiam
                    facilisis nibh ut leo consequat, sed volutpat arcu iaculis. In in tellus a mi ultrices suscipit. Sed
                    in tempor lectus, vehicula efficitur tortor.
                </p>
                <p>
                    Maecenas tempor eleifend sem, ut rutrum ligula gravida sed. Mauris vulputate non urna nec venenatis.
                    Praesent id ligula ornare augue condimentum tempor sed quis lectus. Duis a urna sed lorem fermentum
                    consequat et nec dui.
                </p>
                <div className="image">
                  <img
                    alt="..."
                    src={require("assets/img/image.jpg")}
                  />
                </div>
        </div>
      </>
    );
  }
}

export default Home;
