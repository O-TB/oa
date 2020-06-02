package com.entity;

import java.util.*;

public class Report {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Date getCurtime() {
		return curtime;
	}

	public void setCurtime(Date curtime) {
		this.curtime = curtime;
	}

	private int id;
	private String empno;
	private String name;
	private String title;
	private String doc;
	private Date curtime;
}
