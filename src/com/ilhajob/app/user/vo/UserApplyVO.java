package com.ilhajob.app.user.vo;

public class UserApplyVO {
	
	private int num;
	private String user_id;
	private int jobs_num;
	private String apply_date;
	private String apply_status;
	private String apply_result;
	private String apply_area;
	private String read_date;
	
	public UserApplyVO() {;}
	
	

	public String getRead_date() {
		return read_date;
	}



	public void setRead_date(String read_date) {
		this.read_date = read_date;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getJobs_num() {
		return jobs_num;
	}

	public void setJobs_num(int jobs_num) {
		this.jobs_num = jobs_num;
	}

	public String getApply_date() {
		return apply_date;
	}

	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	public String getApply_status() {
		return apply_status;
	}

	public void setApply_status(String apply_status) {
		this.apply_status = apply_status;
	}

	public String getApply_result() {
		return apply_result;
	}

	public void setApply_result(String apply_result) {
		this.apply_result = apply_result;
	}

	public String getApply_area() {
		return apply_area;
	}

	public void setApply_area(String apply_area) {
		this.apply_area = apply_area;
	}
	
	

}
