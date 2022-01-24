package domain.saveLoad;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import domain.game.NeedForSpearGame;

public class DatabaseSaveLoadAdapter implements ISaveLoadAdapter {

	static Logger mongoLogger;
	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;
	private static DatabaseSaveLoadAdapter instance;
	NeedForSpearGame game = NeedForSpearGame.getInstance();
	private DatabaseSaveLoadAdapter() {
	}

	public static DatabaseSaveLoadAdapter getInstance() {
		if (instance == null) {
			instance = new DatabaseSaveLoadAdapter();
		}
		return instance;
	}

	public static void dbConn() {
		mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
		mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302pass@cluster0.jml5k.mongodb.net/"
				+ "COMP302?retryWrites=true&w=majority");
		database = mongoClient.getDatabase("COMP302");
		collection = database.getCollection("NeedForSpear");
	}
	

	public static MongoCollection<Document> getCollection() {
		return collection;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void save(ArrayList<String> saveList, String username) throws IOException {
		Document doc = collection.find(eq("user_name", username)).first();
		ArrayList<String> saved_game = (ArrayList<String>) doc.get("saved_games");
		if (saved_game == null) {
			collection.findOneAndUpdate(Filters.eq("user_name", username), Updates.pushEach("saved_games", saveList));
		} else {
			int docSize= doc.size();
			int gameNum=docSize-5;
			collection.findOneAndUpdate(Filters.eq("user_name", username), Updates.pushEach("saved_games_"+gameNum, saveList));
		}

		System.out.println("Game saved to the database for " + username);
		NeedForSpearGame.getInstance().resetSaveLoadLists();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> load(String username) throws IOException {
		ArrayList<String> loadList;
		Document my_doc = collection.find(eq("user_name", username)).first();
		String selectedGame=game.getSelectedSavedGame();
		System.out.println("selected: " + selectedGame);
		loadList =(ArrayList<String>) my_doc.get(selectedGame);
		System.out.println("Game successfully loaded from the database for " + username);
		NeedForSpearGame.getInstance().resetSaveLoadLists();
		return loadList;
	}

	public boolean logIn(String username, String password) {
		Document doc = collection.find(eq("user_name", username)).first();
		String db_username = doc.getString("user_name");
		String db_pass = doc.getString("password");
		if (username.equals(db_username) && password.equals(db_pass)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean accountExists(String username) {
		Document doc = collection.find(eq("user_name", username)).first();
		if (doc != null) {
			return true;
		} else {
			return false;
		}
	}

	public void createAccount(String name, String surname, String username, String password) {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("surname", surname);
		doc.append("user_name", username);
		doc.append("password", password);

		// Inserting the document into the collection
		database.getCollection("NeedForSpear").insertOne(doc);
	}

}