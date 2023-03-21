package com.sw.dto;

public class MemberDto {
	
	String id;
	String pw;
	String name;
	String address;
	String nickname;
	int pnum;
	
	public MemberDto() {}
	
	public MemberDto(String i, String p, String n, String a, String nn, int pn) {
		id = i;
		pw = p;
		name = n;
		address = a;
		nickname = nn;
		pnum = pn;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPhone() {
		return pnum;
	}
	public void setPhone(int pnum) {
		this.pnum = pnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

}
