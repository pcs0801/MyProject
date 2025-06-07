package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.CategoryVO;

public class CategoryManager {
	public static Scanner scan = new Scanner(System.in);

	public static ArrayList<CategoryVO> categoryList() {
		CategoryDAO categoryDAO = new CategoryDAO();
		ArrayList<CategoryVO> _categoryList = categoryDAO.categoryList();
		if (_categoryList.size() <= 0 || _categoryList == null) {
			System.out.println("값이 없거나 오류가 발생하였습니다.");
			return null;
		}
		System.out.println("");
		System.out.println("───── 대분류 목록 ─────");
		System.out.println("[  번호  |  항목\t]");
		for (CategoryVO data : _categoryList) {
			System.out.println(data.toString());
		}
		System.out.println("───────────────────");
		return _categoryList;
	}
}