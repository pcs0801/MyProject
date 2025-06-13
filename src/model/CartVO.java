package model;


public class CartVO {
	private int cartItemNo; 
	private int pNo; 
	private int pQty; 
	private int pPrice;
	private int totalPrice;
	private String pName;
	private String pExpdate;
	private String pCategory;
	private String pSubcategory;
	
	public CartVO() {
		super();
	}

	public CartVO(int cartItemNo, int pNo, int pQty, int pPrice, int totalPrice) {
		this.cartItemNo = cartItemNo;
		this.pNo = pNo;
		this.pQty = pQty;
		this.pPrice = pPrice;
		this.totalPrice = totalPrice;
	}
	
	public CartVO(int cartItemNo, String pName, int pQty, int pPrice, int totalPrice) {
		this.cartItemNo = cartItemNo;
		this.pName = pName;
		this.pQty = pQty;
		this.pPrice = pPrice;
		this.totalPrice = totalPrice;
	}
	
	public CartVO(int pNo, String pName, int pPrice, int pQty, String pExpdate, String pCategory, String pSubcategory) {
	    this.pNo = pNo;
	    this.pName = pName;
	    this.pPrice = pPrice;
	    this.pQty = pQty;
	    this.pExpdate = pExpdate;
	    this.pCategory = pCategory;
	    this.pSubcategory = pSubcategory;
	}

	public int getCartItemNo() {
		return cartItemNo;
	}

	public void setCartItemNo(int cartItemNo) {
		this.cartItemNo = cartItemNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	
	public String getpName() {
		return pName;
	}
	
	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpQty() {
		return pQty;
	}

	public void setpQty(int pQty) {
		this.pQty = pQty;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartVO [cartItemNo=" + cartItemNo + ", pNo=" + pNo + ", pQty=" + pQty + ", pPrice=" + pPrice
				+ ", totalPrice=" + totalPrice + "]";
	}
	
}
