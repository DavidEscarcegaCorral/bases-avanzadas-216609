import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class GestionCafes {
    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("restaurants");
            MongoCollection<Document> collection = database.getCollection("cafes");
            collection.drop();
            System.out.println("Se ha reiniciado la coleccion.");

            // 1. Insertar un solo documento:
            System.out.println("\n1. Insertar un solo documento...");
            Document cafePlaza = new Document("name", "Café de la Plaza")
                    .append("stars", 4.3)
                    .append("categories", Arrays.asList("Café", "Postres", "Desayuno"));
            collection.insertOne(cafePlaza);
            System.out.println("Terminado");

            //2. Insertar varios documentos adicionales:
            System.out.println("\n2. Insertar varios documentos adicionales...");
            Document espresso = new Document("name", "Espresso Express")
                    .append("stars", 4.8)
                    .append("categories", Arrays.asList("Café", "Rápido", "Takeaway"));

            Document teaHouse = new Document("name", "The Tea House")
                    .append("stars", 3.9)
                    .append("categories", Arrays.asList("Té", "Infusiones", "Postres"));

            Document morningBrew = new Document("name", "Morning Brew")
                    .append("stars", 4.0)
                    .append("categories", Arrays.asList("Café", "Desayuno", "Bakery"));

            List<Document> cafesList = Arrays.asList(espresso, teaHouse, morningBrew);
            collection.insertMany(cafesList);
            System.out.println("Terminado");

            // Documentos añadidos
            System.out.println("\nDocumento agregado");

            Consumer<Document> printDocument = doc -> System.out.println(doc.toJson());
            collection.find().forEach(printDocument);

            //3. Filtros para mostrar:
            //Documentos con stars >= 4.5
            System.out.println("\nDocumentos con stars >= 4.5.");

            printDocument = doc -> System.out.println(doc.toJson());

            collection.find(Filters.gte("stars", 4.5)).forEach(printDocument);

            //Documentos cuyo nombre contiene "Café"
            System.out.println("\nDocumentos cuyo nombre contiene 'Café'");
            collection.find(Filters.regex("name", "Café")).forEach(printDocument);

            //Documentos con categories que incluyan "Postres"
            System.out.println("\nDocumentos con categories que incluyan 'Postres'");
            collection.find(Filters.eq("categories", "Postres")).forEach(printDocument);

            //Documentos con stars entre 3 y 4.3
            System.out.println("\nDocumentos con stars entre 3 y 4.3");
            collection.find(
                    Filters.and(
                            Filters.gte("stars", 3.0),
                            Filters.lte("stars", 4.3)
                    )
            ).forEach(printDocument);

            //Documentos cuyo nombre empieza con "T"
            System.out.println("\nDocumentos cuyo nombre empieza con T");
            collection.find(Filters.regex("name", "^T")).forEach(printDocument);

            // 4. Updates:
            System.out.println("\nActualizaciones...");

            //Cambiar stars a 4.5 para "Morning Brew"
            collection.updateOne(
                    Filters.eq("name", "Morning Brew"),
                    Updates.set("stars", 4.5)
            );

            //Incrementar stars +0.2 para "Bakery" o "Desayuno"
            collection.updateMany(
                    Filters.or(
                            Filters.eq("categories", "Bakery"),
                            Filters.eq("categories", "Desayuno")
                    ),
                    Updates.inc("stars", 0.2)
            );
            System.out.println("Incrementado +0.2 estrellas a 'Bakery' o 'Desayuno'.");

            //Agregar campos a "Café de la Plaza"
            collection.updateOne(
                    Filters.eq("name", "Café de la Plaza"),
                    Updates.combine(
                            Updates.set("phone", "555-111-2222"),
                            Updates.set("open", true)
                    )
            );
            System.out.println("Agregados campos 'phone' y 'open' a 'Café de la Plaza'.");

            // 5. Deletes:
            System.out.println("\nEliminaciones...");

            //Eliminar documento con nombre "Espresso Express"
            collection.deleteOne(Filters.eq("name", "Espresso Express"));
            System.out.println("Eliminado 'Espresso Express'.");

            //Eliminar todos los documentos con stars < 4
            collection.deleteMany(Filters.lt("stars", 4.0));
            System.out.println("Eliminados documentos con estrellas < 4.");

            //Eliminar documentos con "Takeaway" o "Infusiones"
            collection.deleteMany(
                    Filters.or(
                            Filters.eq("categories", "Takeaway"),
                            Filters.eq("categories", "Infusiones")
                    )
            );
            System.out.println("Eliminados documentos con 'Takeaway' o 'Infusiones'.");

            // Imprimir los documentos resultantes
            System.out.println("\n--- Documentos restantes en la colección 'cafes' ---");
            collection.find().forEach(printDocument);

        }catch (Exception e) {
            System.err.println("Ocurrió un error: " + e);
        }

    }
}
