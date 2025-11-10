package config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public enum MongoClientProvider {
    INSTANCE;

    private MongoClient mongoClient;
    private String dbName = "UsuariosDB";
    private String uri = "mongodb://localhost:27017";

    public synchronized void init() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(MongoConfig.biuldSettings(uri));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    mongoClient.close();
                } catch (Exception e) {
                }
            }));
        }

    }

    public MongoClient getMongoClient() {
        if (mongoClient == null)
            throw new IllegalStateException("Conexion no inicializada");
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        if (mongoClient == null)
            throw new IllegalStateException("Conexion no inicializada");
        return mongoClient.getDatabase(dbName);
    }

    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        if (mongoClient == null)
            throw new IllegalStateException("Conexion no inicializada");

        MongoDatabase database = mongoClient.getDatabase(dbName);
        return database.getCollection(collectionName, clazz);
    }
}
