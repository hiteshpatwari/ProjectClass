package main.Connetion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import main.Cleaning.StopWordsRemoval;
import main.DAO.Votes.Vote;
import main.DAO.posthistory.PostHistory;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.Mongo;

public class CheckConnection {

	public static Mongo conn;
	public static DB db;
	public static DBCollection postHistoryCollection;
	public static DBCollection votesCollection;
	public BasicDBObject dbObject = null;
	public static int iTotalVotes = 0; 
	
	public static DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");
	public static DateTime parsed;
	public static Date date;
	
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
		System.out.println(postHistoryObj.getCREATION_DATE().substring(11, 19));
		
		parsed = formatter.parseDateTime(postHistoryObj.getCREATION_DATE().substring(0, 10) + " " + postHistoryObj.getCREATION_DATE().substring(11, 19));
		date = parsed.toDate();
		
		System.out.println(date.toString());
		
		dbObject.put("creation_date", date);
		
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

	public void insertInDB(Vote voteObj) throws ParseException {
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
		System.out.println(voteObj.getCREATION_DATE());
		
		parsed = formatter.parseDateTime(voteObj.getCREATION_DATE());
		date = parsed.toDate();
		
		System.out.println(date.toString());
		
		dbObject.put("creation_date", date);
		
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
	
	public void updateDateInVotesDocument(Vote voteObj) {
		
		parsed = formatter.parseDateTime(voteObj.getCREATION_DATE());
		date = parsed.toDate();
		
		System.out.println(date.toString());
		
		dbObject.put("creation_date", date);
		
		BasicDBObject find = new BasicDBObject();
		find.put("vote_type_id", 1);
		
		votesCollection.update(find, new BasicDBObject("$set", new BasicDBObject("creation_date", date)));
		
	}
	
	public void GetNoOfVotesForAType(String keyword) {
		
		System.out.println("inside GetNoOfVotesForAType");
		
		/*String map = "function() { "+
				             "var category; " + 
				             "if ( this.vote_type_id = "+keyword+" ) "+ 
				             "category = 'in'; " +
				             "emit(category, {vote_post_id: this.vote_post_id});}";

		String reduce = "function(key, values) { " +
				                            "var sum = 0; " +
				                            "values.forEach(function(doc) { " +
				                            "sum += 1; "+
				                            "}); " +
				                            "return {votes: sum};} ";

		MapReduceCommand cmd = new MapReduceCommand(votesCollection, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);

		MapReduceOutput out = votesCollection.mapReduce(cmd);
		
		for(DBObject o : out.results()) {
			System.out.println(o.toString());
		}
		
		System.out.println(i);
		*/
		
		/*// create our pipeline operations, first with the $match
		DBObject match = new BasicDBObject("$match", new BasicDBObject("$vote_type_id", "1") );

		// build the $projection operation
		DBObject fields = new BasicDBObject("vote_post_id", 1);
		DBObject project = new BasicDBObject("$project", fields );

		// Now the $group operation
		DBObject groupFields = new BasicDBObject( "_id", "$vote_post_id");
		groupFields.put("count", new BasicDBObject( "$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);

		System.out.println(group.toString());
		// run aggregation
		AggregationOutput output = votesCollection.aggregate( match, project, group );
		
		System.out.println(output.getCommandResult());
		*/
		
		
		// total records inserted for votes is 27371750
		
		BasicDBObject query = new BasicDBObject();
		query.put("vote_type_id", keyword);
		
		DBCursor cursor = votesCollection.find(query);
		
		iTotalVotes = iTotalVotes + cursor.count();
		
		System.out.println("No. of votes with " + keyword + " as vote_type_id : " + cursor.count());
	}

	public int getNoOfVotesForDates(Date startDate, Date endDate) {
		
		BasicDBObject query = new BasicDBObject();
		query.put("creation_date", BasicDBObjectBuilder.start("$gte", startDate).add("$lte", endDate).get());
		
		return votesCollection.find(query).count();
	}

	public void getVotes() {
		BasicDBObject query = new BasicDBObject();
		query.put("vote_user_id", "none");
		
		System.out.println("votes" + votesCollection.find(query).count());
	}
}
