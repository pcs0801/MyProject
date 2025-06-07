package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SubCategoryVO;

public class SubCategoryDAO {

	public ArrayList<SubCategoryVO> subCategoryList(int cNo) {
		String subCategoryListSQL = "SELECT S.S_NO, C.C_NAME, S.S_NAME \r\n"
				+ "FROM S_CATEGORY S JOIN \"CATEGORY\" C ON S.C_NO = C.C_NO\r\n"
				+ "WHERE C.C_NO =  ?";
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		ArrayList<SubCategoryVO> subCategoryList = new ArrayList<SubCategoryVO>();
		try {
			con = DBUtil.getConnection();
			if(con == null) {
				System.out.println("DB 연결 문제 발생");
				return null;
			}
			pstmt =  con.prepareStatement(subCategoryListSQL); 
			pstmt.setInt(1, cNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				int sNo = rs.getInt("S_NO");
				String cName = rs.getString("C_NAME");
				String sName = rs.getString("S_NAME");
				SubCategoryVO subCategoryVO = new SubCategoryVO(sNo, cName, sName);
				subCategoryList.add(subCategoryVO);
				
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류 발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return subCategoryList;
	}
}