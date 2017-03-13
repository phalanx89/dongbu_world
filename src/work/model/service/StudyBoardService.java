/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.StudyBoardDAO;
import work.model.dto.StudyBoard;

/**
 * @author DB
 *
 */
public class StudyBoardService {
	private StudyBoardDAO dao = StudyBoardDAO.getInstance();
	
	/** �Խ��� �� ��� ��ȸ */
	public ArrayList<StudyBoard> selectList() {
		return dao.selectList();
	}
	
	/** �� ��� */
	public void register(StudyBoard dto) {
		dao.insert(dto);
	}
	
	/** �� �˻� */
	public ArrayList<StudyBoard> search(String columnName, String keyword) {
		return dao.selectListByColumn(columnName, keyword);
	}
	
	/** �������� �� ���� */
	public int update(int articleNo, String title, String content, String isNotice) {
		return dao.update(articleNo, title, content, isNotice);
	}
	
	/** ȸ���� ���� �� ���� */
	public int update(int articleNo, String title, String content) {
		return dao.update(articleNo, title, content);
	}
	
	/** �� ���� */
	public int delete(int articleNo) {
		return dao.delete(articleNo);
	}
}
