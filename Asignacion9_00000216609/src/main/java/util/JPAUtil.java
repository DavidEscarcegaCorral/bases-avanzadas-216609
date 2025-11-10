package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "EmpresaPU";
    private static EntityManagerFactory factory;

    private static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }
}
