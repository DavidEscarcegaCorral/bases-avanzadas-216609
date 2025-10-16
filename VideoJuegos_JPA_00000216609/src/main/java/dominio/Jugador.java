package dominio;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table (name = "Jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jugador;

    private LocalDate fecha_nacimiento;

    @OneToOne
    @JoinColumn(name = "id_direccion")
    private Direccion id_direccion;

    private String pseudonimo;
    private String sexo;

    @ManyToMany(mappedBy = "id_jugador")
    private Set<Videojuego> id_videojuego;

    public Jugador() {
    }

    public Jugador(Long id_jugador, LocalDate fecha_nacimiento, Direccion id_direccion, String pseudonimo, String sexo, Set<Videojuego> id_videojuego) {
        this.id_jugador = id_jugador;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_direccion = id_direccion;
        this.pseudonimo = pseudonimo;
        this.sexo = sexo;
        this.id_videojuego = id_videojuego;
    }

    public Long getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(Long id_jugador) {
        this.id_jugador = id_jugador;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Direccion getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(Direccion id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<Videojuego> getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(Set<Videojuego> id_videojuego) {
        this.id_videojuego = id_videojuego;
    }
}