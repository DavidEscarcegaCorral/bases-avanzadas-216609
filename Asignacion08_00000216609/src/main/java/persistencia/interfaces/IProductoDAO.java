package persistencia.interfaces;

import dominio.Producto;
import java.util.List;

public interface IProductoDAO {
    void iniciarTransaccion();
    void confirmarTransaccion();
    void revertirTransaccion();
    void insertar(Producto p);
    void actualizar(Producto p);
    void eliminar(Long id);
    Producto buscar(Long id);
    List<Producto> listar();


}
