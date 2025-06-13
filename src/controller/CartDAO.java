package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartVO;
import model.ProductVO;

public class CartDAO {
	// 디생
	// 멤버변수
	private String selectSQL = "SELECT C.CART_ITEM_NO, P.P_NO, P.P_NAME, C.P_QTY, C.P_PRICE, C.TOTAL_PRICE FROM CART C JOIN PRODUCT P ON P.P_NO = C.P_NO WHERE DEL_YN = 0";
	private String selectNameSQL = "SELECT P_NAME, SUM(P_QTY) FROM PRODUCT WHERE P_NAME = ? GROUP BY P_NAME";
	private String insertSQL = "INSERT INTO CART(P_NO, P_QTY, P_PRICE, TOTAL_PRICE) VALUES (?, ?, ?, ?)";
	private String updateSQL = "UPDATE CART SET P_NO = ?, P_QTY = ?, P_PRICE = ?, TOTAL_PRICE = ? WHERE CART_ITEM_NO = ?";
	private String deleteSQL = "DELETE FROM CART WHERE CART_ITEM_NO = ?";

	// 멤버함수
	public static void productList() {
		ProductDAO productDAO = new ProductDAO();
		ArrayList<ProductVO> _productList = productDAO.selectAll();
		if (_productList.size() <= 0 || _productList == null) {
			System.out.println("값이 없거나 오류가 발생하였습니다.");
			return;
		}
		System.out.println(" 번호  |  제품명  |  가격  |  수량  ");
		for (ProductVO data : _productList) {
			System.out.println("  " + data.getpNo() + "  |  " + data.getpName() + "  |  " + data.getpPrice() + "  |  "
					+ data.getpQty());
		}
		System.out.println("● 메뉴로 돌아가기(0 입력)");
	}

	public ArrayList<CartVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int cartItemNo = rs.getInt("CART_ITEM_NO");
	            int pNo = rs.getInt("P_NO");
	            String pName = rs.getString("P_NAME");
	            int pQty = rs.getInt("P_QTY");
	            int pPrice = rs.getInt("P_PRICE");
	            int totalPrice = rs.getInt("TOTAL_PRICE");

	            CartVO cartVO = new CartVO();
	            cartVO.setCartItemNo(cartItemNo);
	            cartVO.setpNo(pNo);
	            cartVO.setpName(pName);
	            cartVO.setpQty(pQty);
	            cartVO.setpPrice(pPrice);
	            cartVO.setTotalPrice(totalPrice);

	            cartList.add(cartVO);
	        }

		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return cartList;
	}

	public ArrayList<CartVO> selectByName(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(selectNameSQL);
			pstmt.setString(1, cartVO.getpName());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pNo = rs.getInt("P_NO");
				String pName = rs.getString("P_NAME");
				int pPrice = rs.getInt("P_PRICE");
				int pQty = rs.getInt("P_QTY");
				String pExpdate = rs.getString("P_EXPDATE");
				String pCategory = rs.getString("P_CATEGORY");
				String pSubcategory = rs.getString("P_SUBCATEGORY");
				CartVO _cartVO = new CartVO(pNo, pName, pPrice, pQty, pExpdate, pCategory, pSubcategory);
				cartList.add(_cartVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return cartList;
	}

	public int insert(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return -1;
			}
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, cartVO.getpNo());
			pstmt.setInt(2, cartVO.getpQty());
			pstmt.setInt(3, cartVO.getpPrice());
			pstmt.setInt(4, cartVO.getTotalPrice());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	public int update(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return -1;
			}
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setInt(1, cartVO.getCartItemNo());
			pstmt.setInt(2, cartVO.getpNo());
			pstmt.setInt(3, cartVO.getpQty());
			pstmt.setInt(4, cartVO.getpPrice());
			pstmt.setInt(5, cartVO.getTotalPrice());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	public int delete(CartVO cartVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return -1;
			}
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, cartVO.getCartItemNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
