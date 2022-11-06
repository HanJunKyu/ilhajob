package com.ilhajob.app.message.vo;

public class MessageVO {
	private int message_num;
	private String message_send_id;
	private String message_rcv_id;
	private String message_title;
	private String message_content;
	private String message_date;
	
	public MessageVO() {}

	public int getMessage_num() {
		return message_num;
	}

	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}

	public String getMessage_send_id() {
		return message_send_id;
	}

	public void setMessage_send_id(String message_send_id) {
		this.message_send_id = message_send_id;
	}

	public String getMessage_rcv_id() {
		return message_rcv_id;
	}

	public void setMessage_rcv_id(String message_rcv_id) {
		this.message_rcv_id = message_rcv_id;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_date() {
		return message_date;
	}

	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}
	
}
