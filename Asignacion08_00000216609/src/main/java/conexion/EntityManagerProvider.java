package conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {
    private static final String persistenceUnitEnUso = "TiendaPU";
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(persistenceUnitEnUso);
        } catch (Exception e) {
            System.err.println("Error al crear EntityManagerFactory: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
