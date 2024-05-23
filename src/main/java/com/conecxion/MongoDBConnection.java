package com.conecxion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.mongodb.client.model.Filters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoDBConnection {

    public static MongoDatabase getDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("bank");
    }
    public static void main(String[] args) {


        try (MongoClient mongoClient = MongoClients.create()) {
            // Connexion à la base de données
            MongoDatabase database = mongoClient.getDatabase("bank");
            
            // Connexion à une collection spécifique
            MongoCollection<Document> collection = database.getCollection("accounts");

            // Affichage d'un message de succès
            System.out.println("Connexion réussie à la base de données " + database.getName());

            // // Exemple : Insertion d'un document dans la collection
            // Document document = new Document("account_id", "mb45456")
            // .append("id", "10021-2015-ENFO")
            // .append("certificate_number", 9278886)
            // .append("buiseness_name", "Morris Park Bake Shop")
            // .append("city", "Paris")
            // .append("date",
            // Date.from(LocalDate.of(2015, 2,
            // 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            //
            // collection.insertOne(document); // Insérer le document
            // System.out.println("Document inséré avec succès");

            // Exemple : Suppression d'un document dans la collectio
            DeleteResult delResult = collection.deleteMany(Filters.eq("account_id",
                    "mb45456"));
            System.out.println("Nombre de documents supprimés : " +
                    delResult.getDeletedCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
