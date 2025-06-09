package model;

public class ProductVO {
	private int pNo; 
	private String pName; 
	private int pPrice;
	private int pQty; 
	private String pExpdate;
	private String pCategory; 
	private String pSubcategory;
	private int pCategoryInt; 
	private int pSubcategoryInt;
	
	
	// Constructor
	public ProductVO() {
		super();
	}

	public ProductVO(int pNo, String pName, int pPrice, int pQty, String pExpdate, String pCategory,
			String pSubcategory) {
		this.pNo = pNo;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pQty = pQty;
		this.pExpdate = pExpdate;
		this.pCategory = pCategory;
		this.pSubcategory = pSubcategory;
	}
	
	public ProductVO(int pNo, String pName, int pPrice, int pQty, String pExpdate, int pSubcategoryInt) {
		this.pNo = pNo;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pQty = pQty;
		this.pExpdate = pExpdate;
		this.pSubcategoryInt = pSubcategoryInt;
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

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getpQty() {
		return pQty;
	}

	public void setpQty(int pQty) {
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
	
	public int getpSubcategoryInt() {
		return pSubcategoryInt;
	}

	@Override
	public String toString() {
		return "[" + pNo + " | " + pName + " | " + pPrice + " | " + pQty + " | "
				+ pExpdate + " | " + pCategory + " | " + pSubcategory + "]";
	} 


}
