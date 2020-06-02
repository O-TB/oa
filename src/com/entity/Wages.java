package com.entity;

import java.util.Date;

public class Wages {
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

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getPerf() {
		return perf;
	}

	public void setPerf(int perf) {
		this.perf = perf;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public int getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(int subsidy) {
		this.subsidy = subsidy;
	}

	public String getSubsidyDesc() {
		return subsidyDesc;
	}

	public void setSubsidyDesc(String subsidyDesc) {
		this.subsidyDesc = subsidyDesc;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	private int id;
	private String empno;
	private String name;
	private int base;
	private int perf;
	private int insurance;
	private int subsidy;
	private String subsidyDesc;
	private String publish;
	private Date curtime;

	public Date getCurtime() {
		return curtime;
	}

	public void setCurtime(Date curtime) {
		this.curtime = curtime;
	}

	@Override
	public String toString() {
		return "Wages [id=" + id + ", empno=" + empno + ", name=" + name + ", base=" + base + ", perf=" + perf
				+ ", insurance=" + insurance + ", subsidy=" + subsidy + ", subsidyDesc=" + subsidyDesc + ", publish="
				+ publish + ", curtime=" + curtime + "]";
	}

}
