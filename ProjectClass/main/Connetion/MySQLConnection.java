package main.Connetion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.DAO.Votes.Vote;

import com.mongodb.BasicDBObject;

public class MySQLConnection {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String insertQuery = "";
	
	public void readDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/StackMiner","root","root");
	      
	      if(connect == null) {
	    	  System.out.println("Error while connecting to DB");
	      }
	    }
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	public void insertInDB(Vote voteObj) throws SQLException {
		Statement st = connect.createStatement();
		
		if(voteObj.getVOTE_ID() == null) {
			voteObj.setVOTE_ID("none");
		}
//		dbObject.put("vote_id", voteObj.getVOTE_ID());
		
		if(voteObj.getVOTE_POST_ID() == null) {
			voteObj.setVOTE_POST_ID("none");
		}
//		dbObject.put("vote_post_id", voteObj.getVOTE_POST_ID());
		
		if(voteObj.getVOTE_TYPE_ID() == null) {
			voteObj.setVOTE_TYPE_ID("none");
		}
//		dbObject.put("vote_type_id", voteObj.getVOTE_TYPE_ID());
		
		if(voteObj.getCREATION_DATE() == null) {
			voteObj.setCREATION_DATE("none");
		}
//		dbObject.put("creation_date", voteObj.getCREATION_DATE());
		
		if(voteObj.getBOUNTY_AMOUNT() == null) {
			voteObj.setBOUNTY_AMOUNT("none");
		}
//		dbObject.put("bounty_amount", voteObj.getBOUNTY_AMOUNT());
		
		if(voteObj.getVOTE_USER_ID() == null) {
			voteObj.setVOTE_USER_ID("none");
		}
		
		insertQuery = "INSERT INTO votes VALUES ( '" + voteObj.getVOTE_ID() + "','" + voteObj.getVOTE_POST_ID() + "','" + voteObj.getVOTE_TYPE_ID()
							+ "','" + voteObj.getCREATION_DATE() + "','" + voteObj.getBOUNTY_AMOUNT() + "','" + voteObj.getVOTE_USER_ID() + "')";
		
		int i = st.executeUpdate(insertQuery);
		
		System.out.println(i + " rows inserted !!!");
		
		st.close();
		
//		dbObject.put("vote_user_id", voteObj.getVOTE_USER_ID());
//		votesCollection.insert(dbObject);
	}
}
