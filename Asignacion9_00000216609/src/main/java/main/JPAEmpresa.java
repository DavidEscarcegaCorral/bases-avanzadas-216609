package main;

import dao.EmpleadoDAO;
import dominio.Empleado;
import dominio.EstatusEmpleado;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class JPAEmpresa {
    public static void main(String[] args) {

        EmpleadoDAO dao = new EmpleadoDAO();

        // 1. INSERTAR EMPLEADOS
        System.out.println("--- 1. Insertando Empleados ---");
        Empleado e1 = new Empleado("Ana Gómez", "ana.gomez@empresa.com", 60000.0, EstatusEmpleado.ACTIVO, LocalDate.of(2022, 5, 15));
        Empleado e2 = new Empleado("Luis Torres", "luis.torres@empresa.com", 75000.0, EstatusEmpleado.ACTIVO, LocalDate.of(2021, 1, 30));
        Empleado e3 = new Empleado("Carla Díaz", "carla.diaz@empresa.com", 55000.0, EstatusEmpleado.INACTIVO, LocalDate.of(2023, 11, 1));

        dao.insertar(e1);
        dao.insertar(e2);
        dao.insertar(e3);
        System.out.println("Empleados insertados.");

        // 2. LISTAR TODOS
        System.out.println("\n--- 2. Listando Empleados ---");
        List<Empleado> empleados = dao.listar();
        empleados.forEach(System.out::println);

        // Guardar el ID de Ana (e1)
        Long idAna = e1.getId();
        Long idLuis = e2.getId();

        // 3. MODIFICAR UN EMPLEADO
        System.out.println("\n--- 3. Modificando Empleado (ID: " + idAna + ") ---");
        Empleado anaModificar = dao.buscar(idAna);
        if (anaModificar != null) {
            System.out.println("Salario anterior de Ana: " + anaModificar.getSalario());
            anaModificar.setSalario(65000.0); // Aumento
            anaModificar.setEstatus(EstatusEmpleado.INACTIVO); // Cambio de estatus
            dao.actualizar(anaModificar);
            System.out.println("Empleado actualizado.");
        }
        Empleado anaActualizada = dao.buscar(idAna);
        System.out.println("Datos actualizados: " + anaActualizada);

        // 4. USAR FUNCIÓN CON TRANSACCIÓN (aumentarSalario)
        System.out.println("\n--- 4. Aumentando Salario de Luis (ID: " + idLuis + ") en 10% ---");
        Empleado luisAntes = dao.buscar(idLuis);
        System.out.println("Salario anterior de Luis: " + luisAntes.getSalario());

        dao.aumentarSalario(idLuis, 10.0); // Aumento del 10%

        Empleado luisDespues = dao.buscar(idLuis);
        System.out.println("Nuevo salario de Luis: " + luisDespues.getSalario());


        // 5. ELIMINAR UN EMPLEADO
        System.out.println("\n--- 5. Eliminando a Ana (ID: " + idAna + ") ---");
        dao.eliminar(idAna);
        System.out.println("Empleado eliminado.");

        // 6. LISTAR FINALMENTE
        System.out.println("\n--- 6. Lista Final de Empleados ---");
        dao.listar().forEach(System.out::println);

        JPAUtil.shutdown();
        System.out.println("\nAplicación finalizada. Recursos liberados.");
    }
}
