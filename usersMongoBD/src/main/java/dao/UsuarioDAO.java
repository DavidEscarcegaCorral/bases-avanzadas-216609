package dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import config.MongoClientProvider;
import models.Usuario;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO{
    private final MongoCollection<Usuario> collection;

    public UsuarioDAO() {
        this.collection = MongoClientProvider.INSTANCE.getCollection("usuarios", Usuario.class);
    }

    @Override
    public ObjectId create(Usuario entity) throws MongoException {
        try{
            if(entity.get_id() == null) entity.set_id(new ObjectId());
            entity.setCreadoEn(Instant.now());
            collection.insertOne(entity);
            return entity.get_id();
        }catch(MongoException me){
            throw me;
        }
    }

    @Override
    public List<Usuario> fingAll() {
        return collection.find().limit(100).into(new ArrayList<>());
    }

    @Override
    public Usuario findById(ObjectId _id) {
        return collection.find(Filters.eq("_id", _id)).first();
    }

    @Override
    public boolean update(Usuario entity) {
        return false;
    }

    @Override
    public boolean deleteById(ObjectId _id) {
        return false;
    }

    @Override
    public long deteleAll() {
        return 0;
    }

    @Override
    public List<Usuario> findByName(String name) {
        return List.of();
    }
}
