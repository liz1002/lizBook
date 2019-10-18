package com.khrd.dto;

public class Cart {
	private int cNo;
	private int cCount;
	private Product product;
	private String uId;
	
	public Cart() {}
	public Cart(int cNo, int cCount, Product product, String uId) {
		super();
		this.cNo = cNo;
		this.cCount = cCount;
		this.product = product;
		this.uId = uId;
	}
	public int getcNo() {
		return cNo;
	}
	public void setcNo(int cNo) {
		this.cNo = cNo;
	}
	public int getcCount() {
		return cCount;
	}
	public void setcCount(int cCount) {
		this.cCount = cCount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	@Override
	public String toString() {
		return "Cart [cNo=" + cNo + ", cCount=" + cCount + ", product=" + product + ", uId=" + uId + "]";
	}
}
