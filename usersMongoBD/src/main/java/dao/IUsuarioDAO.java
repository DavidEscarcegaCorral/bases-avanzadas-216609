package dao;

import com.mongodb.MongoException;
import models.Usuario;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUsuarioDAO {
    ObjectId create(Usuario entity) throws MongoException;
    List<Usuario> fingAll();
    Usuario findById(ObjectId _id);
    boolean update(Usuario entity);
    boolean deleteById(ObjectId _id);
    long deteleAll();
    List<Usuario> findByName(String name);
}
