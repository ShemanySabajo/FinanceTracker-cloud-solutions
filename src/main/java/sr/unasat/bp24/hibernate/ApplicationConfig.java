package sr.unasat.bp24.hibernate;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import sr.unasat.bp24.hibernate.configuration.JPAConfiguration;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        JPAConfiguration.getEntityManager();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "sr.unasat.bp24.hibernate.controller");
        return properties;
    }
}
