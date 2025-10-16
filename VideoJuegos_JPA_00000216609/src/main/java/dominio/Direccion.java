package dominio;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_direccion;
    private String calle;
    private String colonia;
    private String numero;

    public Direccion() {
    }

    public Long getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(Long id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
