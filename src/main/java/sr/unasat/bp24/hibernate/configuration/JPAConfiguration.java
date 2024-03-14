package sr.unasat.bp24.hibernate.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfiguration {

    private static final String PERSISTENCE_UNIT_NAME = "ORM_BP";
    private static EntityManagerFactory factory;

    // Lazily initialize the EntityManagerFactory
    private static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    // Get EntityManager instance
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    // Shutdown EntityManager and EntityManagerFactory
    public static void shutdown() {
        EntityManager entityManager = getEntityManager();
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
