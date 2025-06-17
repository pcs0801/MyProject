package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;

public class CartManager {
	int receiptCounter = 1;
	public static Scanner scan = new Scanner(System.in);

	public ArrayList<CartVO> cartList() {
		CartDAO cartDAO = new CartDAO();
		ArrayList<CartVO> _cartList = cartDAO.selectAll();

		if (_cartList == null || _cartList.isEmpty()) {
			System.out.println("장바구니에 제품이 없습니다.");
			return null;
		}

		for (CartVO data : _cartList) {
			System.out.println("[" + data.getCartItemNo() + "] " + data.getpName() + " | " + data.getpQty() + "개 | 단가: "
					+ data.getpPrice() + " | 총액: " + data.getTotalPrice() + "원");
		}
		return _cartList;
	}

	public void cartInsert() throws SQLException {
		CartDAO cartDAO = new CartDAO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			boolean stopFlag = false;

			while (!stopFlag) {

				CartDAO.productList();

				System.out.print("상품번호 입력 : ");
				int pNo = Integer.parseInt(scan.nextLine());

				pstmt = con.prepareStatement("SELECT P_PRICE FROM PRODUCT WHERE P_NO = ?");
				pstmt.setInt(1, pNo);
				rs = pstmt.executeQuery();

				int pPrice = 0;
				if (rs.next()) {
					pPrice = rs.getInt("P_PRICE");
				} else {
					return;
				}

				System.out.print("수량 입력 : ");
				int pQty = Integer.parseInt(scan.nextLine());

				int totalPrice = pPrice * pQty;
				CartVO cartVO = new CartVO(0, pNo, pQty, pPrice, totalPrice);
				int count = cartDAO.insert(cartVO);

				if (count > 0) {
					System.out.println("장바구니 담기 성공");
				} else {
					System.out.println("장바구니 담기 실패");
				}
				if (pNo == 0) {
					stopFlag = true;
				}
			}
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
	}

	/*
	 * public static void cartUpdate() { CartDAO cartDAO = new CartDAO(); CartVO
	 * cartVO = null; cartList(); System.out.print("수정할 제품번호 입력 :"); int pNo =
	 * Integer.parseInt(scan.nextLine()); cartVO = new CartVO(); cartVO.setpNo(pNo);
	 * cartDAO.selectByName(cartVO); P_NO, P_QTY, P_PRICE, TOTAL_PRICE //사용자가 입력
	 * System.out.print("제품명 입력 :"); String pName = scan.nextLine();
	 * System.out.print("가격 입력 :"); int pPrice = Integer.parseInt(scan.nextLine());
	 * System.out.print("수량 입력 :"); int pQty = Integer.parseInt(scan.nextLine());
	 * System.out.print("유통기한 입력(없을 시 공란) :"); String pExpdate = scan.nextLine();
	 * System.out.print("카테고리(대분류) 입력 :"); String pCategory = scan.nextLine();
	 * System.out.print("카테고리(중분류) 입력 :"); String pSubcategory = scan.nextLine();
	 * 
	 * productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pCategory,
	 * pSubcategory); int count = productDAO.update(productVO); if(count == 0) {
	 * System.out.println("수정 문제 발생"); }else { System.out.println("수정 성공"); } }
	 */

	public void cartDelete() {
		CartDAO cartDAO = new CartDAO();
		Scanner scan = new Scanner(System.in);
		boolean stopFlag = false;

		while (!stopFlag) {
			cartList();
			System.out.println("● 메뉴로 돌아가기(0 입력)");

			System.out.print("삭제할 제품번호 입력: ");
			int cartItemNo = Integer.parseInt(scan.nextLine());

			CartVO cartVO = new CartVO();
			cartVO.setCartItemNo(cartItemNo);
			int count = cartDAO.delete(cartVO);

			if (count > 0) {
				System.out.println("장바구니 삭제 성공");
			}
			if (cartItemNo == 0) {
				stopFlag = true;
			}
		}
	}

	public void cartPurchase() {
		ArrayList<CartVO> _cartList = cartList();
		if (_cartList == null)
			return;

		int total = 0;
		for (CartVO data : _cartList) {
			total += data.getTotalPrice();
		}
		System.out.println("총 결제 금액: " + total + "원");
		System.out.print("결제하시겠습니까? (1=예, 0=아니오): ");
		int choice = Integer.parseInt(scan.nextLine());
		if (choice != 1) {
			System.out.println("결제 취소됨");
			return;
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(
					"UPDATE CART SET DEL_YN = 1, RECEIPT_NO = ?, PAYMENT_DAY = SYSDATE WHERE DEL_YN = 0");
			pstmt.setInt(1, receiptCounter);
			int count = pstmt.executeUpdate();
			System.out.println("결제 완료, 영수증 번호: " + receiptCounter);

			for (CartVO data : _cartList) {
				pstmt = con.prepareStatement("UPDATE PRODUCT SET P_QTY = P_QTY - ? WHERE P_NO = ?");
				pstmt.setInt(1, data.getpQty());
				pstmt.setInt(2, data.getpNo());
				pstmt.executeUpdate();
			}

			receiptCounter++;

		} catch (SQLException e) {
			System.out.println("결제 오류");
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
	}
	
	public void showReceipt() {
		
		System.out.print("확인할 영수증 번호 입력: ");
		int no = Integer.parseInt(scan.nextLine());

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT P_NO, P_QTY, P_PRICE, TOTAL_PRICE, PAYMENT_DAY FROM CART WHERE RECEIPT_NO = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			boolean found = false;
			int sum = 0;
			System.out.println("── 영수증 번호 : " + no + " ──");
			while (rs.next()) {
				found = true;
				System.out.println("상품번호: " + rs.getInt("P_NO") + " | 수량: " + rs.getInt("P_QTY") +
					" | 단가: " + rs.getInt("P_PRICE") + " | 합계: " + rs.getInt("TOTAL_PRICE"));
				sum += rs.getInt("TOTAL_PRICE");
			}
			if (found) {
				System.out.println("총 결제금액: " + sum + "원");
			} else {
				System.out.println("해당 영수증이 없습니다.");
			}
		} catch (Exception e) {
			System.out.println("영수증 조회 오류");
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
	}
}