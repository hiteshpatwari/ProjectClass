package main.DAO.comments;

/*
 * This is a simple POJO file which stores helps us to store the value of a Single Comment in DB.
 */
public class Comment {

	private static String COMMENT_ID;
	private static String POST_ID;
	private static String COMMENT_TEXT;
	private static String CREATION_DATE;
	private static String COMMENT_USER_ID;

	// Initializing the Data members on Constructor call
	public Comment() {
		COMMENT_ID = "";
		POST_ID = "";
		COMMENT_TEXT = "";
		CREATION_DATE = "";
		COMMENT_USER_ID = "";	
	}
	
	public String getCOMMENT_ID() {
		return COMMENT_ID;
	}

	public String getPOST_ID() {
		return POST_ID;
	}

	public String getCOMMENT_TEXT() {
		return COMMENT_TEXT;
	}

	public String getCREATION_DATE() {
		return CREATION_DATE;
	}
	
	public String getCOMMENT_USER_ID() {
		return COMMENT_USER_ID;
	}

	public void setCOMMENT_ID(String COMMENT_ID) {
		this.COMMENT_ID = COMMENT_ID;
	}

	public void setPOST_ID(String POST_ID) {
		this.POST_ID = POST_ID;
	}

	public void setCOMMENT_TEXT(String COMMENT_TEXT) {
		this.COMMENT_TEXT = COMMENT_TEXT;
	}

	public void setCREATION_DATE(String CREATION_DATE) {
		this.CREATION_DATE = CREATION_DATE;
	}

	public void setCOMMENT_USER_ID(String COMMENT_USER_ID) {
		this.COMMENT_USER_ID = COMMENT_USER_ID;
	}
	
	
	
}