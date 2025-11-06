package main;

import conexion.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import persistencia.dao.ProductoDAO;

public class TiendaJPA {
    public static void main(String[] args) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);

    }
}
