import java.util.Scanner;

import controller.ProductManager;
import view.AdminMenuChoice;
import view.Menu;

public class MyProjectMain {
	public static Scanner scan = new Scanner(System.in); 
	public static void main(String[] args) {
		int choice = 0;
		boolean exitFlag = false; 
		while(!exitFlag) {
			Menu.mainMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch(choice) {
			case 1 : 
				adminMenu();
				break; 
			case 2 :
				break; 
			case 3 : 
				exitFlag = true; 
				break; 
			}
		}//end of while
		System.out.println("프로그램 종료");
	}
	
	//도서관리 메뉴
	public static void adminMenu() {
		int choice = 0;
		boolean exitFlag = false; 
		while(!exitFlag) {
			Menu.adminMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch(choice) {
			case AdminMenuChoice.P_IN : 
				ProductManager.productInsert();
				break; 
			case AdminMenuChoice.P_DELETE :
				ProductManager.productDelete();
				break; 
			case AdminMenuChoice.P_LIST : 
				ProductManager.productList();
				break; 
			case AdminMenuChoice.P_SEARCH :
				ProductManager.productSearch();
				break; 
			case AdminMenuChoice.P_SORT : 
				boolean sortFlag = false;
				while(!sortFlag) {
					Menu.sortMenu();
					int sortChoice = Integer.parseInt(scan.nextLine());
					switch(sortChoice) {
					case 1:
						ProductManager.orderByCategoryAsc();
						break;
					case 2:
						ProductManager.orderByCategoryDesc();
						break;
					case 3:
						sortFlag = true; 
						break; 
					}				}
				break; 
			case AdminMenuChoice.P_CUMSALES : 
				break; 
			case AdminMenuChoice.TO_MENU : 
				exitFlag = true; 
				break; 
			}
		}//end of while
	}
}
