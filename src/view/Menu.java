package view;

import java.util.Scanner;

public class Menu{
	//메인메뉴
	public static void mainMenu() { 
		System.out.println("도서관 관리 프로그램"); 
		System.out.println("해당 번호를 입력하세요."); 
		System.out.println("1. 도서 정보 목록/입력/수정/삭제/검색"); 
		System.out.println("2. 회원 정보 목록/입력/수정/삭제"); 
		System.out.println("3. 대여 정보 목록/입력/수정/삭제"); 
		System.out.println("4. 프로그램 종료"); 
		System.out.print("번호 선택 : ");
	}
	
	//도서정보메뉴
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

}
