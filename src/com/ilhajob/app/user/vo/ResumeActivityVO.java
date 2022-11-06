package com.ilhajob.app.user.vo;

public class ResumeActivityVO {
	
	private int num;
	private String id;
	private String activity_place;
	private String activity_type;
	private String activity_start_date;
	private String activity_end_date;
	private String activity_detail;
	
	public ResumeActivityVO() {;}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActivity_place() {
		return activity_place;
	}
	public void setActivity_place(String activity_place) {
		this.activity_place = activity_place;
	}
	public String getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}
	
	public String getActivity_start_date() {
		return activity_start_date;
	}

	public void setActivity_start_date(String activity_start_date) {
		this.activity_start_date = activity_start_date;
	}

	public String getActivity_end_date() {
		return activity_end_date;
	}

	public void setActivity_end_date(String activity_end_date) {
		this.activity_end_date = activity_end_date;
	}

	public String getActivity_detail() {
		return activity_detail;
	}
	public void setActivity_detail(String activity_detail) {
		this.activity_detail = activity_detail;
	}
	
	

}
