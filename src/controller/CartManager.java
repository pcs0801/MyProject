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

	public static ArrayList<CartVO> cartList() {
		CartDAO cartDAO = new CartDAO();
		ArrayList<CartVO> _cartList = cartDAO.selectAll();
		if (_cartList.size() <= 0 || _cartList == null) {
			System.out.println("장바구니에 제품이 없습니다.");
			return null;
		}
		for (CartVO data : _cartList) {
			System.out.println("[" + data.getCartItemNo() + "] " + data.getpName() + " | " + data.getpQty() + "개 | 단가: "
					+ data.getpPrice() + " | 총액: " + data.getTotalPrice() + "원");
		}
		return _cartList;
	}

	public static void cartInsert() throws SQLException {
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

	public static void cartDelete() {
		CartDAO cartDAO = new CartDAO();
		Scanner scan = new Scanner(System.in);

		cartList();

		System.out.print("삭제할 제품번호 입력: ");
		int cartItemNo = Integer.parseInt(scan.nextLine());

		CartVO cartVO = new CartVO();
		cartVO.setCartItemNo(cartItemNo);
		int count = cartDAO.delete(cartVO);

		if (count > 0) {
			System.out.println("장바구니 삭제 성공");
		} else {
			System.out.println("장바구니 삭제 실패");
		}
	}

	public static void cartPurchase() {
		CartDAO cartDAO = new CartDAO();
		Scanner scan = new Scanner(System.in);
		boolean stopFlag = false;
		int total = 0;
		
		ArrayList<CartVO> _cartList = cartList();
		
		for (CartVO data : _cartList) {
			total += (data.getpQty() * data.getpPrice());
		}
		
		System.out.println("총계 : " + total + "원");

		while (!stopFlag) {
			System.out.println("결제 여부 : 결제(1) / 취소(0)");
			System.out.print(" ▶ 번호입력 : ");
			int purChoice = Integer.parseInt(scan.nextLine());

			if (purChoice == 1) {
				// DELETE FROM CART
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = DBUtil.getConnection();
					if (con == null) {
						System.out.println("DB 연결 문제 발생");
					}
					pstmt = con.prepareStatement("UPDATE CART SET DEL_YN = 1 WHERE DEL_YN = 0");
					int count = pstmt.executeUpdate();
					if (count >= 1) {
						int count2 = 0;
						for (CartVO data : _cartList) {
							pstmt = con.prepareStatement("UPDATE PRODUCT SET P_QTY = (P_QTY - ?) WHERE P_NO = ?");
							
							pstmt.setInt(1, data.getpQty());
							pstmt.setInt(2, data.getpNo());
							count2 += pstmt.executeUpdate();
						}

						System.out.println("결제 완료("+count2+"건)");
					} else {
						System.out.println("결제 실패");
					}
				} catch (SQLException e) {
					System.out.println("createStatement 오류 발생");
				} finally {
					DBUtil.dbClose(con, pstmt);
				}
				stopFlag = true;
			} else if (purChoice == 0) {
				System.out.println("결제취소");
				stopFlag = true;
			} else {
				System.out.println("제대로 입력해주세요. (0 또는 1)");
			}
		}
//		CartVO cartVO = new CartVO();
//		cartVO.setCartItemNo(cartItemNo);
//		int count = cartDAO.delete(cartVO);
//
//		if (count > 0) {
//			System.out.println("장바구니 삭제 성공");
//		} else {
//			System.out.println("장바구니 삭제 실패");
//		}
	}
}