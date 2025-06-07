package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.SubCategoryVO;

public class SubCategoryManager {
	public static Scanner scan = new Scanner(System.in);

	public static ArrayList<SubCategoryVO> subCategoryList(int cNo) {
		SubCategoryDAO subCategoryDAO = new SubCategoryDAO();
		ArrayList<SubCategoryVO> _subCategoryList = subCategoryDAO.subCategoryList(cNo);
		if (_subCategoryList.size() <= 0 || _subCategoryList == null) {
			System.out.println("값이 없거나 오류가 발생하였습니다.");
			return null;
		}
		System.out.println("");
		System.out.println("───── 중분류 목록 ─────");
		System.out.println("[  번호  |  항목\t]");
		for (SubCategoryVO data : _subCategoryList) {
			System.out.println(data.toString());
		}
		System.out.println("───────────────────");
		
		return _subCategoryList;
	}
}