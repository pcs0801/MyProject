import java.util.Scanner;

import controller.BooksManager;
import view.AdminMenuChoice;
import view.BooksChoice;
import view.Menu;
import view.MenuChoice;

public class MyProjectMain {
	public static Scanner scan = new Scanner(System.in); 
	public static void main(String[] args) {
		int choice = 0;
		boolean exitFlag = false; 
		while(!exitFlag) {
			Menu.adminMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch(choice) {
			case AdminMenuChoice.P_IN : 
				booksMenu();
				break; 
			case AdminMenuChoice.P_DELETE :
				//memberMenu();
				break; 
			case AdminMenuChoice.P_LIST : 
				break; 
			case AdminMenuChoice.P_SEARCH : 
				break; 
			case AdminMenuChoice.P_SORT : 
				break; 
			case AdminMenuChoice.P_CUMSALES : 
				break; 
			case AdminMenuChoice.TO_MENU : 
				exitFlag = true; 
				break; 
			}
			
		}//end of while
		System.out.println("프로그램 종료");
	}
	
	//도서관리 메뉴
	public static void booksMenu() {
		int choice = 0;
		boolean exitFlag = false; 
		while(!exitFlag) {
			Menu.booksMenu();;
			choice = Integer.parseInt(scan.nextLine());
			switch(choice) {
			case BooksChoice.도서목록 : 
				BooksManager.booksList();
				break; 
			case  BooksChoice.도서입력  :
				BooksManager.booksInsert();
				break; 
			case  BooksChoice.도서수정 : 
				BooksManager.booksUpdate();
				break; 
			case  BooksChoice.도서삭제: 
				BooksManager.booksDelete();
				break; 
			case  BooksChoice.도서검색: 
				BooksManager.booksSearch();;
				break; 
			case  BooksChoice.메인메뉴 :
				System.out.println("도서관리화면 종료");
				exitFlag = true; 
				break; 
			}
			
		}//end of while
	}
}
