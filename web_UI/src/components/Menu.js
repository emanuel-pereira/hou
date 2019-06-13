import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Link, Switch} from "react-router-dom";
import Home from '../Home';
import RoomConfig from '../RoomConfig';
import HouseGridConfig from "../HouseGridConfig";
import '../style/Menu.css'
import HomeButton from '././HomeButton';

class Menu extends Component {
    render() {
        return (
            <div className="AppMenu">
            <Router>
                <nav className="nav">
                    <ul className="nav__menu">
                        <li className="nav__menu-item">
                        <Link to="/"><HomeButton /></Link>
                        </li>
                        <li className="nav__menu-item">
                            <a>House Configuration</a>
                            <Submenu1/>
                        </li>
                        <li className="nav__menu-item">
                            <a>House Monitoring</a>
                            <Submenu2/>
                        </li>
                    </ul>
                </nav>

                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/home" component={Home}/>
                    <Route exact path="/houseConfig" component={RoomConfig}/>
                    <Route exact path="/houseGridConfig" component={HouseGridConfig}/>
                </Switch>

            </Router>
            </div>

        )
    }
}

class Submenu1 extends Component {
    render() {
        return (
            <ul className="nav__submenu">
                <li className="nav__submenu-item ">
                    <Link to="/houseConfig">Rooms</Link>
                </li>
                <li className="nav__submenu-item ">
                    <Link to="/houseGridConfig">House Grids</Link>
                </li>
            </ul>
        )
    }
}

class Submenu2 extends Component {
    render() {
        return (
            <ul className="nav__submenu">
                <li className="nav__submenu-item ">
                    <Link to="/topics">House Area</Link>
                </li>
                <li className="nav__submenu-item ">
                    <Link to="/topics">Room</Link>
                </li>
            </ul>
        )
    }
}


export default Menu;
