package com.ilhajob.app.bbs.vo;

public class BbsReplyVO {
	private int bbs_reply_num;
	private int bbs_num;
	private String user_id;
	private String bbs_reply_content;
	
	public BbsReplyVO() {;}

	public int getBbs_reply_num() {
		return bbs_reply_num;
	}

	public void setBbs_reply_num(int bbs_reply_num) {
		this.bbs_reply_num = bbs_reply_num;
	}

	public int getBbs_num() {
		return bbs_num;
	}

	public void setBbs_num(int bbs_num) {
		this.bbs_num = bbs_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBbs_reply_content() {
		return bbs_reply_content;
	}

	public void setBbs_reply_content(String bbs_reply_content) {
		this.bbs_reply_content = bbs_reply_content;
	}
}
