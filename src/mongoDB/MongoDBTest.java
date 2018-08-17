package mongoDB;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBTest {

	private static void insert(MongoCollection<Document> collection) {
		Document document = new Document("name", "dog");

		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		collection.insertMany(documents);
	}

	public static void main(String args[]) {
		MongoClient mongoClient = null;
		try {

			mongoClient = new MongoClient("localhost", 27017);

			MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
			System.out.println("Connect to database successfully");

			MongoCollection<Document> collection = mongoDatabase
					.getCollection("person");
			
			// insert(collection);

			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		finally{
			mongoClient.close();
		}
	}
}