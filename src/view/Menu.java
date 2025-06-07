package view;

public class Menu{
	public static void mainMenu() {
		System.out.println("");
		System.out.println("―――――― Ｍ Ｅ Ｎ Ｕ ――――――");
		System.out.println("   1.  관리자 모드");
		System.out.println("   2.  사용자 모드");
		System.out.println("   3.  종료");
		System.out.println("――――――――――――――――――――――――");
		System.out.print(" ▶ 번호입력 : ");
	}
	
	public static void adminMenu() {
		System.out.println("");
		System.out.println("―――――― Ｍ Ｅ Ｎ Ｕ ――――――");
		System.out.println("   1.  재고 입고"); 
		System.out.println("   2.  재고 삭제");
		System.out.println("   3.  재고 현황"); 
		System.out.println("   4.  재고 검색");
		System.out.println("   5.  재고 정렬"); 
		System.out.println("   6.  누적 매출"); 
		System.out.println("   7.  상위 메뉴로 돌아가기");
		System.out.println("――――――――――――――――――――――――");
		System.out.print(" ▶ 번호입력 : ");
	}
	
	public static void sortMenu() {
		System.out.println("");
		System.out.println("―――――― Ｍ Ｅ Ｎ Ｕ ――――――");
		System.out.println("   1.  오름차순(카테고리 기준)");
		System.out.println("   2.  내림차순(카테고리 기준)");
		System.out.println("   3.  종료");
		System.out.println("――――――――――――――――――――――――");
		System.out.print(" ▶ 번호입력 : ");
	}

}
