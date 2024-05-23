package resources;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDBConnection {

    public static void main(String[] args) {
        // Remplacez par votre URI de connexion MongoDB
        String uri = "mongodb://localhost:27017";
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Connexion à la base de données
            MongoDatabase database = mongoClient.getDatabase("yourDatabaseName");

            // Connexion à une collection spécifique
            MongoCollection<Document> collection = database.getCollection("yourCollectionName");

            // Affichage d'un message de succès
            System.out.println("Connexion réussie à la base de données " + database.getName());

            // Exemple : Insertion d'un document dans la collection
            Document document = new Document("name", "Alice")
                                    .append("age", 25)
                                    .append("city", "Paris");
            collection.insertOne(document);

            System.out.println("Document inséré avec succès");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
