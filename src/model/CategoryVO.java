package model;

public class CategoryVO {
	private int cNo; 
	private String cName;
	
	public CategoryVO() {
		super();
	}

	public CategoryVO(int cNo, String cName) {
		this.cNo = cNo;
		this.cName = cName;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String toString() {
		return "[   " + cNo + "\t| " + cName + "\t]";
	} 
	
	
	

}
