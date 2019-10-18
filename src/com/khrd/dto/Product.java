package com.khrd.dto;

import java.util.Date;

public class Product {
	private int pNo;
	private String pTitle;
	private String pName;
	private int pPrice;
	private String pDetail;
	private String pPublisher;
	private Date pRegdate;
	private String pFile;
	private Type type;
	
	public Product() {}

	public Product(int pNo, String pTitle, String pName, int pPrice, String pDetail, String pPublisher, Date pRegdate,
			String pFile, Type type) {
		super();
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pDetail = pDetail;
		this.pPublisher = pPublisher;
		this.pRegdate = pRegdate;
		this.pFile = pFile;
		this.type = type;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpDetail() {
		return pDetail;
	}

	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}

	public String getpPublisher() {
		return pPublisher;
	}

	public void setpPublisher(String pPublisher) {
		this.pPublisher = pPublisher;
	}

	public Date getpRegdate() {
		return pRegdate;
	}

	public void setpRegdate(Date pRegdate) {
		this.pRegdate = pRegdate;
	}

	public String getpFile() {
		return pFile;
	}

	public void setpFile(String pFile) {
		this.pFile = pFile;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", pTitle=" + pTitle + ", pName=" + pName + ", pPrice=" + pPrice + ", pDetail="
				+ pDetail + ", pPublisher=" + pPublisher + ", pRegdate=" + pRegdate + ", pFile=" + pFile + ", type="
				+ type + "]";
	}	
}
