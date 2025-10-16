package dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "logro")
public class Logro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_logro;
    private int puntos;
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_videojuego")
    private Videojuego id_videojuego;

    public Logro() {
    }

    public Logro(Videojuego id_videojuego, String nombre, int puntos, Long id_logro) {
        this.id_videojuego = id_videojuego;
        this.nombre = nombre;
        this.puntos = puntos;
        this.id_logro = id_logro;
    }

    public Long getId_logro() {
        return id_logro;
    }

    public void setId_logro(Long id_logro) {
        this.id_logro = id_logro;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Videojuego getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(Videojuego id_videojuego) {
        this.id_videojuego = id_videojuego;
    }
}
