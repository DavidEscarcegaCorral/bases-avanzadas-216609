package models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

public class Usuario {
    private ObjectId _id;

    private String nombre;
    private Integer edad;
    private Direccion  direccion;

    private List<Telefono> telefonos;
    private List<String> tags;

    @BsonProperty("creado_en")
    private Instant creadoEn;

    public Usuario() {}

    public Usuario(ObjectId _id, String nombre, Integer edad, Direccion direccion, List<Telefono> telefonos, List<String> tags, Instant creadoEn) {
        this._id = _id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.tags = tags;
        this.creadoEn = creadoEn;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}
