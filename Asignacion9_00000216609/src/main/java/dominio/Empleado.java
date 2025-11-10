package dominio;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private Double salario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstatusEmpleado estatus;

    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;

    public Empleado() {
    }

    public Empleado(String nombre, String email, Double salario, EstatusEmpleado estatus, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.estatus = estatus;
        this.fechaContratacion = fechaContratacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public EstatusEmpleado getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusEmpleado estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", salario=" + salario +
                ", estatus=" + estatus +
                ", fechaContratacion=" + fechaContratacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id) &&
                Objects.equals(email, empleado.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}