import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import controller.CartManager;
import controller.DBUtil;
import controller.ProductManager;
import view.AdminMenuChoice;
import view.Menu;
import view.UserMenuChoice;

public class MyProjectMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		Connection con = DBUtil.getConnection();
		int choice = 0;
		boolean exitFlag = false;

		if (con == null) {
			System.out.println("DB 연결 실패");
			return;
		}
		while (!exitFlag) {
			Menu.mainMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1:
				adminMenu();
				break;
			case 2:
				userMenu();
				break;
			case 3:
				exitFlag = true;
				break;
			}
		}
		System.out.println("프로그램 종료");
	}

	public static void adminMenu() {
		int choice = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			Menu.adminMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case AdminMenuChoice.P_IN:
				ProductManager.productInsert();
				break;
			case AdminMenuChoice.P_DELETE:
				ProductManager.productDelete();
				break;
			case AdminMenuChoice.P_LIST:
				ProductManager.productList();
				break;
			case AdminMenuChoice.P_SEARCH:
				ProductManager.productSearch();
				break;
			case AdminMenuChoice.P_SORT:
				boolean sortFlag = false;
				while (!sortFlag) {
					Menu.sortMenu();
					int sortChoice = Integer.parseInt(scan.nextLine());
					switch (sortChoice) {
					case 1:
						ProductManager.orderByCategoryAsc();
						break;
					case 2:
						ProductManager.orderByCategoryDesc();
						break;
					case 3:
						sortFlag = true;
						break;
					}
				}
				break;
			case AdminMenuChoice.TO_MENU:
				exitFlag = true;
				break;
			}
		}
	}

	public static void userMenu() throws SQLException {
		int choice = 0;
		CartManager cartManager = new CartManager();
		boolean exitFlag = false;
		while (!exitFlag) {
			Menu.userMenu();
			choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case UserMenuChoice.ADD_CART:
				cartManager.cartInsert();
				break;
			case UserMenuChoice.DEL_CART:
				cartManager.cartDelete();
				break;
			case UserMenuChoice.CHK_AND_PAY:
				cartManager.cartPurchase();
				break;
			case UserMenuChoice.RECEIPT:
				cartManager.showReceipt();
				break;
			case UserMenuChoice.TO_MENU:
				exitFlag = true;
				break;
			}
		}
	}
}
