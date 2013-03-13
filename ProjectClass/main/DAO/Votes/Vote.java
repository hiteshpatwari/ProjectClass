package main.DAO.Votes;

public class Vote {
	private String VOTE_ID;
	private String VOTE_POST_ID;
	private String VOTE_TYPE_ID;
	private String CREATION_DATE;
	private String VOTE_USER_ID;
	private String BOUNTY_AMOUNT;
	
	public String getVOTE_ID() {
		return VOTE_ID;
	}
	public String getVOTE_POST_ID() {
		return VOTE_POST_ID;
	}
	public String getVOTE_TYPE_ID() {
		return VOTE_TYPE_ID;
	}
	public String getCREATION_DATE() {
		return CREATION_DATE;
	}
	public void setVOTE_ID(String vOTE_ID) {
		VOTE_ID = vOTE_ID;
	}
	public void setVOTE_POST_ID(String vOTE_POST_ID) {
		VOTE_POST_ID = vOTE_POST_ID;
	}
	public void setVOTE_TYPE_ID(String vOTE_TYPE_ID) {
		VOTE_TYPE_ID = vOTE_TYPE_ID;
	}
	public void setCREATION_DATE(String cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}
	public String getVOTE_USER_ID() {
		return VOTE_USER_ID;
	}
	public void setVOTE_USER_ID(String vOTE_USER_ID) {
		VOTE_USER_ID = vOTE_USER_ID;
	}
	public String getBOUNTY_AMOUNT() {
		return BOUNTY_AMOUNT;
	}
	public void setBOUNTY_AMOUNT(String bOUNTY_AMOUNT) {
		BOUNTY_AMOUNT = bOUNTY_AMOUNT;
	}
	
	
}
