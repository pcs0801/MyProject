package model;

public class ProductVO {
	private int pNo; 
	private String pName; 
	private String pPrice;
	private String pQty; 
	private String pExpdate;
	private String pCategory; 
	private String pSubcategory;
	
	public ProductVO() {
		super();
	}

	public ProductVO(int pNo, String pName, String pPrice, String pQty, String pExpdate, String pCategory,
			String pSubcategory) {
		this.pNo = pNo;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pQty = pQty;
		this.pExpdate = pExpdate;
		this.pCategory = pCategory;
		this.pSubcategory = pSubcategory;
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

	public String getpPrice() {
		return pPrice;
	}

	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}

	public String getpQty() {
		return pQty;
	}

	public void setpQty(String pQty) {
		this.pQty = pQty;
	}

	public String getpExpdate() {
		return pExpdate;
	}

	public void setpExpdate(String pExpdate) {
		this.pExpdate = pExpdate;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getpSubcategory() {
		return pSubcategory;
	}

	public void setpSubcategory(String pSubcategory) {
		this.pSubcategory = pSubcategory;
	}

	@Override
	public String toString() {
		return "BooksVO [" + pNo + " | " + pName + " | " + pPrice + " | " + pQty + " | "
				+ pExpdate + " | " + pCategory + " | " + pSubcategory + "]";
	} 


}
