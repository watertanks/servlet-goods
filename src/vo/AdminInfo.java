package vo;

public class AdminInfo {
	//세션에 담을 관리자 정보
	private int ai_idx;
	private String ai_id, ai_pw, ai_name;
	
	public int getAi_idx() {
		return ai_idx;
	}
	public void setAi_idx(int ai_idx) {
		this.ai_idx = ai_idx;
	}
	public String getAi_id() {
		return ai_id;
	}
	public void setAi_id(String ai_id) {
		this.ai_id = ai_id;
	}
	public String getAi_pw() {
		return ai_pw;
	}
	public void setAi_pw(String ai_pw) {
		this.ai_pw = ai_pw;
	}
	public String getAi_name() {
		return ai_name;
	}
	public void setAi_name(String ai_name) {
		this.ai_name = ai_name;
	}
	
	
}
