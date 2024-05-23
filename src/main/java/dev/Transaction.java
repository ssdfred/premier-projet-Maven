package dev;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.conecxion.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.TransactionBody;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.ClientSession;

public class Transaction {

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        MongoDatabase database = connection.getDatabase();
        
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            ClientSession clientSession = mongoClient.startSession();
            
            TransactionBody<String> txnBody = new TransactionBody<String>() {
                public String execute() {
                    MongoCollection<Document> bankingCollection = database.getCollection("accounts");

                    Bson fromAccount = Filters.eq("account_id", "mb45456");
                    Bson withdrawal = Updates.inc("balance", -100);

                    Bson toAccount = Filters.eq("account_id", "mb45457");
                    Bson deposit = Updates.inc("balance", 100);

                    System.out.println("Transaction en cours... de " + fromAccount.toBsonDocument().toJson() + " retrait " + withdrawal.toBsonDocument().toJson());
                    System.out.println("Transaction en cours... vers " + toAccount.toBsonDocument().toJson() + " dépôt " + deposit.toBsonDocument().toJson());

                    bankingCollection.updateOne(fromAccount, withdrawal);
                    bankingCollection.updateOne(toAccount, deposit);

                    return "Transaction effectuée avec succès";
                }
            };

            try {
                clientSession.withTransaction(txnBody);
                System.out.println(txnBody.execute());
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            } finally {
                clientSession.close();
            }
        }
    }
}
