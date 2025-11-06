package persistencia.dao;

import dominio.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.*;
import persistencia.interfaces.IProductoDAO;

import java.util.List;

public class ProductoDAO implements IProductoDAO {
    private EntityManager entityManager;

    public ProductoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void iniciarTransaccion() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void confirmarTransaccion() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void revertirTransaccion() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }

    @Override
    public void insertar(Producto p) {
        try {
            iniciarTransaccion();
            entityManager.persist(p);
            confirmarTransaccion();
            System.out.println("Producto insertado: " + p.getNombre());
        } catch (Exception e) {
            System.err.println("Error al insertar producto.");
            revertirTransaccion();
        }
    }

    @Override
    public void actualizar(Producto p) {
        try {
            iniciarTransaccion();
            entityManager.merge(p);
            confirmarTransaccion();
            System.out.println("Producto actualizado: " + p.getNombre());
        } catch (Exception e) {
            System.err.println("Error al actualizar producto.");
            revertirTransaccion();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            iniciarTransaccion();
            Producto p = entityManager.find(Producto.class, id);
            if (p != null) {
                entityManager.remove(p);
                System.out.println("Producto eliminado con ID: " + id);
            } else {
                System.out.println("No se encontró producto con ID: " + id);
            }
            confirmarTransaccion();
        } catch (Exception e) {
            System.err.println("Error al eliminar producto.");
            revertirTransaccion();
        }
    }

    @Override
    public Producto buscar(Long id) {
        Producto p = entityManager.find(Producto.class, id);
        entityManager.close();
        return p;
    }

    @Override
    public List<Producto> listar() {
        Query q = entityManager.createQuery("SELECT p FROM Producto p");
        List<Producto> productos = q.getResultList();
        entityManager.close();
        return productos;
    }
}
