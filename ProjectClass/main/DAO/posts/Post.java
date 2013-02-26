package main.DAO.posts;

import java.util.List;

public class Post {

	private String POST_ID;
	private String POST_TYPE_ID;
	private String ACCEPTED_ANSWER_ID;
	private String CREATION_DATE;
	private String SCORE;
	private String VIEW_COUNT;
	private String POST_TEXT;
	private String OWNER_USER_ID;
	private String LAST_EDITOR_ID;
	private String LAST_EDITOR_NAME;
	private String LAST_EDIT_DATE;
	private String LAST_ACTIVITY_DATE;
	private String TITLE;
	private List<String> TAGS;
	
	/*
	 * Getter Methods
	 */
	
	public String getPOST_ID() {
		return POST_ID;
	}
	public String getPOST_TYPE_ID() {
		return POST_TYPE_ID;
	}
	public String getACCEPTED_ANSWER_ID() {
		return ACCEPTED_ANSWER_ID;
	}
	public String getCREATION_DATE() {
		return CREATION_DATE;
	}
	public String getSCORE() {
		return SCORE;
	}
	public String getVIEW_COUNT() {
		return VIEW_COUNT;
	}
	public String getPOST_TEXT() {
		return POST_TEXT;
	}
	public String getOWNER_USER_ID() {
		return OWNER_USER_ID;
	}
	public String getLAST_EDITOR_ID() {
		return LAST_EDITOR_ID;
	}
	public String getLAST_EDITOR_NAME() {
		return LAST_EDITOR_NAME;
	}
	public String getLAST_EDIT_DATE() {
		return LAST_EDIT_DATE;
	}
	public String getLAST_ACTIVITY_DATE() {
		return LAST_ACTIVITY_DATE;
	}
	public String getTITLE() {
		return TITLE;
	}
	public List<String> getTAGS() {
		return TAGS;
	}
	
	/*
	 * Setter Methods
	 */
	
	public void setPOST_ID(String pOST_ID) {
		this.POST_ID = pOST_ID;
	}
	public void setPOST_TYPE_ID(String pOST_TYPE_ID) {
		this.POST_TYPE_ID = pOST_TYPE_ID;
	}
	public void setACCEPTED_ANSWER_ID(String aCCEPTED_ANSWER_ID) {
		this.ACCEPTED_ANSWER_ID = aCCEPTED_ANSWER_ID;
	}
	public void setCREATION_DATE(String cREATION_DATE) {
		this.CREATION_DATE = cREATION_DATE;
	}
	public void setSCORE(String sCORE) {
		this.SCORE = sCORE;
	}
	public void setVIEW_COUNT(String vIEW_COUNT) {
		this.VIEW_COUNT = vIEW_COUNT;
	}
	public void setPOST_TEXT(String pOST_TEXT) {
		this.POST_TEXT = pOST_TEXT;
	}
	public void setOWNER_USER_ID(String oWNER_USER_ID) {
		this.OWNER_USER_ID = oWNER_USER_ID;
	}
	public void setLAST_EDITOR_ID(String lAST_EDITOR_ID) {
		this.LAST_EDITOR_ID = lAST_EDITOR_ID;
	}
	public void setLAST_EDITOR_NAME(String lAST_EDITOR_NAME) {
		this.LAST_EDITOR_NAME = lAST_EDITOR_NAME;
	}
	public void setLAST_EDIT_DATE(String lAST_EDIT_DATE) {
		this.LAST_EDIT_DATE = lAST_EDIT_DATE;
	}
	public void setLAST_ACTIVITY_DATE(String lAST_ACTIVITY_DATE) {
		this.LAST_ACTIVITY_DATE = lAST_ACTIVITY_DATE;
	}
	public void setTITLE(String tITLE) {
		this.TITLE = tITLE;
	}
	public void setTAGS(List<String> tAGS) {
		this.TAGS = tAGS;
	}
	
	
	
}