package com.ilhajob.app.bbs.vo;

public class FilesVO {
	private String bbs_file_name;
	private int bbs_num;
	
	public FilesVO() {;}

	public String getFile_bbs_name() {
		return bbs_file_name;
	}

	public void setFile_bbs_name(String file_bbs_name) {
		this.bbs_file_name = file_bbs_name;
	}

	public int getBbs_num() {
		return bbs_num;
	}

	public void setBbs_num(int bbs_num) {
		this.bbs_num = bbs_num;
	}
}
