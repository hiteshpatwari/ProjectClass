package main.DAO.posthistory;

public class PostHistory {

	private String ID;
	private String POST_HISTORY_TYPE_ID;
	private String POST_ID;
	private String REVISION_GUID;
	private String CREATION_DATE;
	private String USER_ID;
	private String USER_DISPLAY_NAME;
	private String COMMENT;
	private String TEXT;
	private String CLOSE_REASON_ID;
	
	public String getID() {
		return ID;
	}
	public String getPOST_HISTORY_TYPE_ID() {
		return POST_HISTORY_TYPE_ID;
	}
	public String getPOST_ID() {
		return POST_ID;
	}
	public String getREVISION_GUID() {
		return REVISION_GUID;
	}
	public String getCREATION_DATE() {
		return CREATION_DATE;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public String getUSER_DISPLAY_NAME() {
		return USER_DISPLAY_NAME;
	}
	public String getCOMMENT() {
		return COMMENT;
	}
	public String getTEXT() {
		return TEXT;
	}
	public String getCLOSE_REASON_ID() {
		return CLOSE_REASON_ID;
	}

	/*
	 * Setter methods
	 */
	public void setID(String iD) {
		this.ID = iD;
	}
	public void setPOST_HISTORY_TYPE_ID(String pOST_HISTORY_TYPE_ID) {
		this.POST_HISTORY_TYPE_ID = pOST_HISTORY_TYPE_ID;
	}
	public void setPOST_ID(String pOST_ID) {
		this.POST_ID = pOST_ID;
	}
	public void setREVISION_GUID(String rEVISION_GUID) {
		this.REVISION_GUID = rEVISION_GUID;
	}
	public void setCREATION_DATE(String cREATION_DATE) {
		this.CREATION_DATE = cREATION_DATE;
	}
	public void setUSER_ID(String uSER_ID) {
		this.USER_ID = uSER_ID;
	}
	public void setUSER_DISPLAY_NAME(String uSER_DISPLAY_NAME) {
		this.USER_DISPLAY_NAME = uSER_DISPLAY_NAME;
	}
	public void setCOMMENT(String cOMMENT) {
		this.COMMENT = cOMMENT;
	}
	public void setTEXT(String tEXT) {
		this.TEXT = tEXT;
	}
	public void setCLOSE_REASON_ID(String cLOSE_REASON_ID) {
		this.CLOSE_REASON_ID = cLOSE_REASON_ID;
	}
}