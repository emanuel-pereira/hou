import Notifications from "views/template/Notifications.jsx";
import Icons from "views/template/Icons.jsx";
import Typography from "views/template/Typography.jsx";
import TableList from "views/template/Tables.jsx";
import Room from "views/Room";
import Home from "views/Home";
import GeoArea from "views/GeoArea";
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
    path: "/geoaea",
    name: "Geographical Area Managment",
    icon: "nc-icon nc-map-big",
    component: GeoArea,
    layout: "/admin"
  },
  {
    path: "/rooms",
    name: "House Rooms Managment",
    icon: "nc-icon nc-app",
    component: Room,
    layout: "/admin"
  },
  {
    path: "/housegrids",
    name: "House Grids Managment",
    icon: "nc-icon nc-layout-11",
    component: HouseGrid,
    layout: "/admin"
  },
  {
    path: "/sensors",
    name: "House Sensors Managment",
    icon: "nc-icon nc-map-big",
    component: Sensor,
    layout: "/admin"
  },
  {
    path: "/monitoring",
    name: "House Monitoring",
    icon: "nc-icon nc-tv-2",
    component: Monitoring,
    layout: "/admin"
  },
  {
    path: "/icons",
    name: "Icons",
    icon: "nc-icon nc-diamond",
    component: Icons,
    layout: "/admin"
  },
  {
    path: "/notifications",
    name: "Notifications",
    icon: "nc-icon nc-bell-55",
    component: Notifications,
    layout: "/admin"
  },
  {
    path: "/tables",
    name: "Table List",
    icon: "nc-icon nc-tile-56",
    component: TableList,
    layout: "/admin"
  },
  {
    path: "/typography",
    name: "Typography",
    icon: "nc-icon nc-caps-small",
    component: Typography,
    layout: "/admin"
  },
    
];
export default routes;
