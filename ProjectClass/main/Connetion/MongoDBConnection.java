package main.Connetion;

import java.net.UnknownHostException;

import com.mongodb.Mongo;


/*
 * This class is tha basic connection class for Mongo DB. The program will connect to Mongo DB from here 
 * 
 */
public class MongoDBConnection {

	private static MongoDBConnection mongoInstance = null;
	private static Mongo connection = null;
	private MongoDBConnection() {
		/*
		 * Creating the variable at class level
		 */
	}
	
	public static Mongo getInstance() {
		
		if(connection == null) {
			try {
				connection = new Mongo("localhost", 27017);
			} 
			catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
