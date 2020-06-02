package com.entity;

import java.util.*;

public class Notice {
	private int id;
	private String title;
	private Date curtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCurtime() {
		return curtime;
	}

	public void setCurtime(Date curtime) {
		this.curtime = curtime;
	}
}
