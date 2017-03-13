/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.FreeBoardDAO;
import work.model.dto.FreeBoard;
import work.model.dto.Member;

/**
 * @author DB
 *
 */
public class FreeBoardService {
	private FreeBoardDAO dao = FreeBoardDAO.getInstance();
	
	/** �Խ��� �� ��� ��ȸ */
	public ArrayList<FreeBoard> selectList() {
		return dao.selectList();
	}
	
	/** �� ��� */
	public void register(FreeBoard dto) {
		dao.insert(dto);
	}
	
	/** �� �˻� */
	public ArrayList<FreeBoard> search(String columnName, String keyword) {
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
