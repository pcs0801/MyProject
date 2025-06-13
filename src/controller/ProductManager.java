package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.CategoryVO;
import model.ProductVO;
import model.SubCategoryVO;

public class ProductManager {
	public static Scanner scan = new Scanner(System.in);

	public static void productList() {
		ProductDAO productDAO = new ProductDAO();
		ArrayList<ProductVO> _productList = productDAO.selectAll();
		if (_productList.size() <= 0 || _productList == null) {
			System.out.println("값이 없거나 오류가 발생하였습니다.");
			return;
		}
		for (ProductVO data : _productList) {
			System.out.println(data.toString());
		}
	}

	public static void productInsert() {
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = null;
		int pNo = 0;

		System.out.print("제품명 입력 :");
		String pName = scan.nextLine();
		System.out.print("가격 입력 :");
		int pPrice = Integer.parseInt(scan.nextLine());
		System.out.print("수량 입력 :");
		int pQty = Integer.parseInt(scan.nextLine());
		System.out.print("유통기한 입력(없을 시 공란) :");
		String pExpdate = scan.nextLine();

		ArrayList<CategoryVO> cList = CategoryManager.categoryList();
		System.out.print("카테고리 번호 (대분류) :");
		String pCategory = "";
		int pCategoryInt = Integer.parseInt(scan.nextLine());
		for (CategoryVO c : cList) {
			if (c.getcNo() == pCategoryInt) {
				pCategory = c.getcName();
			}
		}

		ArrayList<SubCategoryVO> sList = SubCategoryManager.subCategoryList(pCategoryInt);
		System.out.print("카테고리 번호 (중분류) :");
		String pSubcategory = "";
		int pSubcategoryInt = Integer.parseInt(scan.nextLine());
		for (SubCategoryVO s : sList) {
			if (s.getsNo() == pSubcategoryInt) {
				pSubcategory = s.getsName();
			}
		}
		productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pSubcategoryInt);

		int count = productDAO.insert(productVO);
		if (count == 0) {
			System.out.println("입력 문제 발생");
		} else {
			System.out.println("입력 성공");
		}
	}

	public static void productUpdate() {
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = null;
		// 전체내용리스트
		productList();
		// 선택책정보
		System.out.print("수정할 제품번호 입력 :");
		int pNo = Integer.parseInt(scan.nextLine());
		productVO = new ProductVO();
		productVO.setpNo(pNo);
		productDAO.selectByName(productVO);

		// 사용자가 입력
		System.out.print("제품명 입력 :");
		String pName = scan.nextLine();
		System.out.print("가격 입력 :");
		int pPrice = Integer.parseInt(scan.nextLine());
		System.out.print("수량 입력 :");
		int pQty = Integer.parseInt(scan.nextLine());
		System.out.print("유통기한 입력(없을 시 공란) :");
		String pExpdate = scan.nextLine();
		System.out.print("카테고리(대분류) 입력 :");
		String pCategory = scan.nextLine();
		System.out.print("카테고리(중분류) 입력 :");
		String pSubcategory = scan.nextLine();

		productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pCategory, pSubcategory);
		int count = productDAO.update(productVO);
		if (count == 0) {
			System.out.println("수정 문제 발생");
		} else {
			System.out.println("수정 성공");
		}
	}

	public static void productDelete() {
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = null;
		// 전체내용리스트
		productList();
		// 선택책정보
		System.out.print("삭제할 제품번호 입력 :");
		int pNo = Integer.parseInt(scan.nextLine());
		productVO = new ProductVO();
		productVO.setpNo(pNo);
		int count = productDAO.delete(productVO);

		if (count == 0) {
			System.out.println("삭제 문제 발생");
		} else {
			System.out.println("삭제 성공");
		}
	}

	public static void productSearch() {
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = null;

		System.out.print("검색할 제품명 입력 : ");
		String pName = scan.nextLine();
		productVO = new ProductVO();
		productVO.setpName(pName);

		ArrayList<ProductVO> productList = productDAO.selectByName(productVO);
		if (productList.size() <= 0 || productList == null) {
			System.out.println("검색 오류가 발생했습니다.");
			return;
		}
		System.out.println(" 번호  |  제품명  |  가격  |  수량  |  유통기한  ");
		for (ProductVO data : productList) {
			System.out.println("  " + data.getpNo() + "  |  " + data.getpName() + "  |  " + data.getpPrice() + "  |  "
					+ data.getpQty() + "  |  " + data.getpExpdate());
		}
	}

	public static void orderByCategoryAsc() {
		ProductDAO productDAO = new ProductDAO();
		ArrayList<ProductVO> _productList = productDAO.orderByCategoryAsc();
		if (_productList.size() <= 0 || _productList == null) {
			System.out.println("값이 없거나 오류가 발생하였습니다.");
			return;
		}
		System.out.println(" 분류번호 |  번호  |  제품명  |  가격  |  수량  |  유통기한  ");
		for (ProductVO data : _productList) {
			System.out.println("  " + data.getpSubcategoryInt() + "   |  " + data.getpNo() + "  |  " + data.getpName()
					+ "  |  " + data.getpPrice() + "  |  " + data.getpQty() + "  |  " + data.getpExpdate());
		}

	}

	public static void orderByCategoryDesc() {
		ProductDAO productDAO = new ProductDAO();
		ArrayList<ProductVO> _productList = productDAO.orderByCategoryDesc();
		if (_productList.size() <= 0 || _productList == null) {
			System.out.println("값이 없거나 오류가 발생하였습니다.");
			return;
		}
		System.out.println(" 분류번호 |  번호  |  제품명  |  가격  |  수량  |  유통기한  ");
		for (ProductVO data : _productList) {
			System.out.println("  " + data.getpSubcategoryInt() + "   |  " + data.getpNo() + "  |  " + data.getpName()
					+ "  |  " + data.getpPrice() + "  |  " + data.getpQty() + "  |  " + data.getpExpdate());
		}

	}

}
