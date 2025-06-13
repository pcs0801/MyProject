package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;

public class CartManager {
	public static Scanner scan = new Scanner(System.in); 
	
	public static void cartList() {
		CartDAO cartDAO = new CartDAO();
		ArrayList<CartVO> _cartList =  cartDAO.selectAll();
		if(_cartList.size() <= 0 || _cartList == null) {
			System.out.println("장바구니에 제품이 없습니다.");
			return; 
		}
		for(CartVO data  : _cartList) {
			System.out.println(data.toString());
		}
	}
	
	public static void cartInsert() throws SQLException{
		CartDAO cartDAO = new CartDAO();
		ProductDAO productDAO = new ProductDAO();
		int cartItemNo = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = DBUtil.getConnection();
		
		cartDAO.productList();
		System.out.print("상품번호 입력 :");
		int pNo = Integer.parseInt(scan.nextLine());
		pstmt = con.prepareStatement("SELECT P_PRICE FROM PRODUCT WHERE P_NO = ?");
		pstmt.setInt(1, pNo);
		rs = pstmt.executeQuery();
		System.out.print("수량 입력 :");
		int pQty = Integer.parseInt(scan.nextLine());
		int pPrice = rs.getInt("P_PRICE"); 
		int totalPrice = pPrice * pQty;
		
		CartVO cartVO = new CartVO(cartItemNo, pNo, pQty, pPrice, totalPrice);
		
		
	}
}
		
		/*cartVO = new CartVO(pName, pQty, 

		int count = cartDAO.insert(cartVO);
		if(count == 0) {
			System.out.println("입력 문제 발생");
		}else {
			System.out.println("입력 성공");
		}
	}
	*/
	
	/*
	public static void cartUpdate() {
		CartDAO cartDAO = new CartDAO();
		CartVO cartVO = null;
		cartList();
		System.out.print("수정할 제품번호 입력 :");
		int pNo = Integer.parseInt(scan.nextLine());
		cartVO = new CartVO(); 
		cartVO.setpNo(pNo);
		cartDAO.selectByName(cartVO);
		P_NO, P_QTY, P_PRICE, TOTAL_PRICE
		//사용자가 입력
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
		if(count == 0) {
			System.out.println("수정 문제 발생");
		}else {
			System.out.println("수정 성공");
		}
	}
	
	public static void cartDelete() {
		CartDAO cartDAO = new CartDAO();
		CartVO cartVO = null;
		//전체내용리스트
		cartList();
		//선택책정보
		System.out.print("삭제할 제품번호 입력 :");
		int pNo = Integer.parseInt(scan.nextLine());
		cartVO = new ProductVO(); 
		cartVO.setpNo(pNo);
		int count = cartDAO.delete(cartVO);

		if(count == 0) {
			System.out.println("삭제 문제 발생");
		}else {
			System.out.println("삭제 성공");
		}
	}
	*/
	
