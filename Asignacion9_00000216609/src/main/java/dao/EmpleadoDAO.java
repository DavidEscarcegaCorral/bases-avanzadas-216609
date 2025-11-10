package dao;

import dominio.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class EmpleadoDAO {

    public void insertar(Empleado e) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(e);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizar(Empleado e) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(e);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Empleado e = em.find(Empleado.class, id);
            if (e != null) {
                em.remove(e);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Empleado buscar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public List<Empleado> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT e FROM Empleado e ORDER BY e.nombre";
            TypedQuery<Empleado> query = em.createQuery(jpql, Empleado.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean aumentarSalario(Long id, Double porcentajeAumento) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if (porcentajeAumento <= 0) {
            System.err.println("El porcentaje debe ser mayor a 0.");
            return false;
        }

        try {
            tx.begin();
            Empleado empleado = em.find(Empleado.class, id);

            if (empleado == null) {
                System.out.println("No se encontró empleado con ID: " + id);
                tx.rollback();
                return false;
            }

            double salarioActual = empleado.getSalario();
            double aumento = salarioActual * (porcentajeAumento / 100.0);
            double nuevoSalario = salarioActual + aumento;

            empleado.setSalario(nuevoSalario);
            tx.commit();

            return true;

        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al aumentar salario, revertiendo transacción.");
            ex.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}