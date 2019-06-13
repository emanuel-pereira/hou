package smarthome;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;
import smarthome.io.ui.SmartHomeUI;
import smarthome.model.*;
import smarthome.repository.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class Application  {

    private static final String DEFAULT = "Default";
    //Location loc = new Location(1, 1, 1);
    Location loc = null;
    Address a1 = new Address(DEFAULT, DEFAULT, "0000-000", DEFAULT, DEFAULT, loc);
    OccupationArea oc = new OccupationArea(1, 1);
    //GeographicalArea g1 = new GeographicalArea(DEFAULT, DEFAULT, DEFAULT, oc, loc);
    GeographicalArea g1 = null;
            House house = House.getHouseInstance(a1, g1);
    /*House house = House.getHouseInstance();*/
    TypeGAList typeGAList = TypeGAList.getTypeGAListInstance();

    static final Logger log = Logger.getLogger(Application.class);

    /**
     * Main class that executes Spring application and invokes the init method to initialize the lists used
     * in the application and invokes the menuOptions() method that lists all UI options that users can select.
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException, SAXException, ParserConfigurationException, ParseException, NoSuchFieldException {
        SpringApplication.run(Application.class);
        SmartHomeUI.init();
        SmartHomeUI.menuOptions();
    }






    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, RoomRepository rRep, SensorTypeRepository unitRep, TypeGARepository typeRep,
                                  ExternalSensorRepository extSensorRep, InternalSensorRepository intSensorRep, HouseGridRepository gridsRep) {

        Repositories.setTypeGARepository(typeRep);
        Repositories.setGeoRepository(geoRep);
        Repositories.setRoomRepository(rRep);
        Repositories.setSensorTypeRepository(unitRep);
        Repositories.setExternalSensorRepository(extSensorRep);
        Repositories.setInternalSensorRepository(intSensorRep);
        Repositories.setGridsRepository(gridsRep);

        return args -> log.info("Application Start-Up");
    }

    //Code by NPS, added by AA.

    @Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    /*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
    redirected to HTTPS on 8443.
     */
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

}
