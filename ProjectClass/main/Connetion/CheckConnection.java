package main.Connetion;

import main.Cleaning.StopWordsRemoval;
import main.DAO.Votes.Vote;
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
	public static DBCollection votesCollection;
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
		}
	}
	
	public void createVotesCollection() {
		votesCollection = db.getCollection("votes");
		
		System.out.println(votesCollection.getName());
		
		if(votesCollection.equals(null)) {
			votesCollection = db.createCollection("votes", dbObject);
			System.out.println("creating DB Object");
		}
	}
	
	public void createPostHistoryCollection() {
		postHistoryCollection = db.getCollection("postHistory");
		
		System.out.println(postHistoryCollection.getName());
		
		if(postHistoryCollection.equals(null)) {
			postHistoryCollection = db.createCollection("postHistory",dbObject);
			System.out.println("creating DB object");
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
		System.out.println(postHistoryObj.getCREATION_DATE().substring(0, 10));
		dbObject.put("creation_date",  postHistoryObj.getCREATION_DATE().substring(0, 10));
		
		if(postHistoryObj.getREVISION_GUID() == null) {
			postHistoryObj.setREVISION_GUID("none");
		}
		dbObject.put("revision_guid", postHistoryObj.getREVISION_GUID());
		
		if(!(postHistoryObj.getTEXT() == null)) {
			//postHistoryObj.setTEXT(postHistoryObj.getTEXT());
			//postHistoryObj.setTEXT(StopWordsRemoval.removeWords(postHistoryObj.getTEXT()));
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

	
	public void insertInDB(Vote voteObj) {
		dbObject = new BasicDBObject();
		
		if(voteObj.getVOTE_ID() == null) {
			voteObj.setVOTE_ID("None");
		}
		dbObject.put("vote_id", voteObj.getVOTE_ID());
		
		if(voteObj.getVOTE_POST_ID() == null) {
			voteObj.setVOTE_POST_ID("none");
		}
		dbObject.put("vote_post_id", voteObj.getVOTE_POST_ID());
		
		if(voteObj.getVOTE_TYPE_ID() == null) {
			voteObj.setVOTE_TYPE_ID("none");
		}
		dbObject.put("vote_type_id", voteObj.getVOTE_TYPE_ID());
		
		if(voteObj.getCREATION_DATE() == null) {
			voteObj.setCREATION_DATE("none");
		}
		dbObject.put("creation_date", voteObj.getCREATION_DATE());
		
		if(voteObj.getBOUNTY_AMOUNT() == null) {
			voteObj.setBOUNTY_AMOUNT("none");
		}
		dbObject.put("bounty_amount", voteObj.getBOUNTY_AMOUNT());
		
		if(voteObj.getVOTE_USER_ID() == null) {
			voteObj.setVOTE_USER_ID("none");
		}
		dbObject.put("vote_user_id", voteObj.getVOTE_USER_ID());
		
		votesCollection.insert(dbObject);
	}
}
