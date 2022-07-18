package com.spring_boot.projectEx.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberVO {
	private String memId;
	private String memPwd;
	private String memName;
	private String memEmail;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memJoinDate;
	private String memHP;
	private String memZipCode;
	private String memAddress1;
	private String memAddress2;
	public MemberVO() {}
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public Date getMemJoinDate() {
		return memJoinDate;
	}

	public void setMemJoinDate(Date memJoinDate) {
		this.memJoinDate = memJoinDate;
	}

	public String getMemHP() {
		return memHP;
	}

	public void setMemHP(String memHP) {
		this.memHP = memHP;
	}

	public String getMemZipCode() {
		return memZipCode;
	}

	public void setMemZipCode(String memZipCode) {
		this.memZipCode = memZipCode;
	}

	public String getMemAddress1() {
		return memAddress1;
	}

	public void setMemAddress1(String memAddress1) {
		this.memAddress1 = memAddress1;
	}

	public String getMemAddress2() {
		return memAddress2;
	}

	public void setMemAddress2(String memAddress2) {
		this.memAddress2 = memAddress2;
	}

	
	
	
	
}
