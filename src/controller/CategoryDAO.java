package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CategoryVO;

public class CategoryDAO {
	private String categoryListSQL = "SELECT * FROM CATEGORY ORDER BY C_NO";

	public ArrayList<CategoryVO> categoryList() {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		ArrayList<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt =  con.prepareStatement(categoryListSQL); 
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int cNo = rs.getInt("C_NO");
				String cName = rs.getString("C_NAME");
				CategoryVO categoryVO = new CategoryVO(cNo, cName);
				categoryList.add(categoryVO);
				
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return categoryList;
	}
}