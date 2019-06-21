import Room from "views/Room";
import Home from "views/Home";
import HouseArea from "views/HouseArea";
import HouseGrid from "views/HouseGrid";
import Sensor from "views/Sensor";
import Monitoring from "views/Monitoring";

var routes = [
  {
    path: "/Home",
    name: "Home",
    icon: "nc-icon nc-bank",
    component: Home,
    layout: "/admin"
  },
  {
    path: "/geoarea",
    name: "House Area",
    icon: "nc-icon nc-pin-3",
    component: HouseArea,
    layout: "/admin"
  },
  {
    path: "/rooms",
    name: "Rooms",
    icon: "nc-icon nc-app",
    component: Room,
    layout: "/admin"
  },
  {
    path: "/housegrids",
    name: "Power Grids",
    icon: "nc-icon nc-layout-11",
    component: HouseGrid,
    layout: "/admin"
  },
  {
    path: "/sensors",
    name: "Sensors",
    icon: "nc-icon nc-map-big",
    component: Sensor,
    layout: "/admin"
  },
  {
    path: "/monitoring",
    name: "Reports",
    icon: "nc-icon nc-chart-bar-32",
    component: Monitoring,
    layout: "/admin"
  },
];
export default routes;
