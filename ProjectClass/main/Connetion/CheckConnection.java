package main.Connetion;

import main.Cleaning.StopWordsRemoval;
import main.DAO.posthistory.PostHistory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class CheckConnection {

	public static Mongo conn;
	public static DB db;
	public static DBCollection postHistoryCollection;
	public BasicDBObject dbObject = null;
	
	
	public CheckConnection() {
	
		conn = MongoDBConnection.getInstance();
		
		if(conn.equals(null)) {
			System.out.println("Not able to connect to the DB !!!");
		}
		else {
			System.out.println("Connection is there. Wait whil we load the DB for you ...");
			
			db = conn.getDB("posthistory");
			
			if(db.equals(null)) {
				System.out.println("DB does not exists !!!");
			}
			else {
				System.out.println("whats up ?");
				postHistoryCollection = db.getCollection("postHistory");
				
				System.out.println(postHistoryCollection.getName());
				
				if(postHistoryCollection.equals(null)) {
					postHistoryCollection = db.createCollection("postHistory",dbObject);
					System.out.println("creating DB object");
				}
			}
		}
	}
	
	public void insertInDB(PostHistory postHistoryObj) {
		dbObject = new BasicDBObject();
		
		if(postHistoryObj.getID() == null) {
				postHistoryObj.setID("None");
		}
		dbObject.put("post_history_id", postHistoryObj.getID());
		
		if(postHistoryObj.getPOST_HISTORY_TYPE_ID() == null) {
			postHistoryObj.setPOST_HISTORY_TYPE_ID("none");
		}
		dbObject.put("post_history_id_type", postHistoryObj.getPOST_HISTORY_TYPE_ID());
		
		if(postHistoryObj.getPOST_ID() == null) {
			postHistoryObj.setPOST_ID("none");
		}
		dbObject.put("post_id", postHistoryObj.getPOST_ID());
		
		if(postHistoryObj.getCREATION_DATE() == null) {
			postHistoryObj.setCREATION_DATE("none");
		}
		dbObject.put("creation_date", postHistoryObj.getCREATION_DATE());
		
		if(postHistoryObj.getREVISION_GUID() == null) {
			postHistoryObj.setREVISION_GUID("none");
		}
		dbObject.put("revision_guid", postHistoryObj.getREVISION_GUID());
		
		if(!(postHistoryObj.getTEXT() == null)) {
			postHistoryObj.setTEXT(StopWordsRemoval.removeWords(postHistoryObj.getTEXT()));
		}
		else {
			postHistoryObj.setTEXT("None");
		}
		dbObject.put("text", postHistoryObj.getTEXT());
		
		if(postHistoryObj.getUSER_ID() == null) {
			postHistoryObj.setUSER_ID("none");
		}
		dbObject.put("user_id", postHistoryObj.getUSER_ID());
		
		if(postHistoryObj.getUSER_DISPLAY_NAME() == null) {
			postHistoryObj.setUSER_DISPLAY_NAME("none");
		}
		dbObject.put("user_display_name", postHistoryObj.getUSER_DISPLAY_NAME());
		
		if(postHistoryObj.getCOMMENT() == null) {
			postHistoryObj.setCOMMENT("none");
		}
		dbObject.put("comment", postHistoryObj.getCOMMENT());
		
		if(postHistoryObj.getCLOSE_REASON_ID() == null) {
			postHistoryObj.setCLOSE_REASON_ID("none");
		}
		dbObject.put("close_reason_id", postHistoryObj.getCLOSE_REASON_ID());
		
		
		postHistoryCollection.insert(dbObject);
	}

}
