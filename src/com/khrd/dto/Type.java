package com.khrd.dto;

public class Type {
	private int tNo;
	private String tName;
	
	public Type() {}

	public Type(int tNo, String tName) {
		super();
		this.tNo = tNo;
		this.tName = tName;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	@Override
	public String toString() {
		return "Type [tNo=" + tNo + ", tName=" + tName + "]";
	}
}
