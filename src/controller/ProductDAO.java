package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductVO;

public class ProductDAO {
	// 디생
	// 멤버변수
	private String selectSQL = "SELECT P.P_NO, P.P_NAME, P.P_PRICE, P.P_QTY, P.P_EXPDATE, C.C_NAME, S.S_NAME FROM PRODUCT P, S_CATEGORY S, \"CATEGORY\" C \r\n"
			+ "WHERE P.P_SUBCATEGORY_NO = S.S_NO \r\n"
			+ "AND S.C_NO = C.C_NO ORDER BY P.P_NO";
	private String selectNameSQL = "SELECT *  FROM PRODUCT WHERE P_NAME = ?";
	private String insertSQL = "INSERT INTO PRODUCT(P_NO, P_NAME, P_PRICE, P_QTY, P_EXPDATE, P_SUBCATEGORY_NO) VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	private String updateSQL = "UPDATE PRODUCT SET P_NAME = ?, P_PRICE = ?, P_QTY = ?, P_EXPDATE = ?, P_CATEGORY = ?, P_SUBCATEGORY = ? WHERE P_NAME = ?";
	private String deleteSQL = "DELETE FROM PRODUCT WHERE P_NO = ?";
	private String orderByCategoryAscSQL = "SELECT * FROM PRODUCT ORDER BY P_CATEGORY ASC";
	private String orderByCategoryDescSQL = "SELECT * FROM PRODUCT ORDER BY P_CATEGORY DESC";
	
	// 멤버함수
	public ArrayList<ProductVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pNo = rs.getInt("P_NO");
				String pName = rs.getString("P_NAME");
				int pPrice = rs.getInt("P_PRICE");
				int pQty = rs.getInt("P_QTY");
				String pExpdate = rs.getString("P_EXPDATE");
				String pCategory = rs.getString("C_NAME");
				String pSubcategory = rs.getString("S_NAME");
				ProductVO productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pCategory, pSubcategory);
				productList.add(productVO);

			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return productList;
	}
	
	public ArrayList<ProductVO> selectDistinct() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String pSubcategory = rs.getString("S_NAME");
				String pName = rs.getString("P_NAME");
				int pPrice = rs.getInt("P_PRICE");
				int pQty = rs.getInt("P_QTY");
				ProductVO productVO = new ProductVO(pSubcategory, pName, pPrice, pQty);
				productList.add(productVO);
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return productList;
	}


	public ArrayList<ProductVO> orderByCategoryAsc() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(orderByCategoryAscSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pNo = rs.getInt("P_NO");
				String pName = rs.getString("P_NAME");
				int pPrice = rs.getInt("P_PRICE");
				int pQty = rs.getInt("P_QTY");
				String pExpdate = rs.getString("P_EXPDATE");
				String pCategory = rs.getString("P_CATEGORY");
				String pSubcategory = rs.getString("P_SUBCATEGORY");
				ProductVO productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pCategory, pSubcategory);
				productList.add(productVO);

			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return productList;
	}

	public ArrayList<ProductVO> orderByCategoryDesc() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(orderByCategoryDescSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pNo = rs.getInt("P_NO");
				String pName = rs.getString("P_NAME");
				int pPrice = rs.getInt("P_PRICE");
				int pQty = rs.getInt("P_QTY");
				String pExpdate = rs.getString("P_EXPDATE");
				String pCategory = rs.getString("P_CATEGORY");
				String pSubcategory = rs.getString("P_SUBCATEGORY");
				ProductVO productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pCategory, pSubcategory);
				productList.add(productVO);

			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return productList;
	}

	public ArrayList<ProductVO> selectByName(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> booksList = new ArrayList<ProductVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt = con.prepareStatement(selectNameSQL);
			pstmt.setString(1, productVO.getpName());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pNo = rs.getInt("P_NO");
				String pName = rs.getString("P_NAME");
				int pPrice = rs.getInt("P_PRICE");
				int pQty = rs.getInt("P_QTY");
				String pExpdate = rs.getString("P_EXPDATE");
				String pCategory = rs.getString("P_CATEGORY");
				String pSubcategory = rs.getString("P_SUBCATEGORY");
				ProductVO _productVO = new ProductVO(pNo, pName, pPrice, pQty, pExpdate, pCategory, pSubcategory);
				booksList.add(_productVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return booksList;
	}

	public int insert(ProductVO productVO) {
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
			pstmt.setString(1, productVO.getpName());
			pstmt.setInt(2, productVO.getpPrice());
			pstmt.setInt(3, productVO.getpQty());
			pstmt.setString(4, productVO.getpExpdate());
			pstmt.setInt(5, productVO.getpSubcategoryInt());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	public int update(ProductVO productVO) {
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
			pstmt.setString(1, productVO.getpName());
			pstmt.setInt(2, productVO.getpPrice());
			pstmt.setInt(3, productVO.getpQty());
			pstmt.setString(4, productVO.getpExpdate());
			pstmt.setString(5, productVO.getpCategory());
			pstmt.setString(6, productVO.getpSubcategory());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

	public int delete(ProductVO productVO) {
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
			pstmt.setInt(1, productVO.getpNo());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}

}
