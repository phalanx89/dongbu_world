/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.FreeBoardDAO;
import work.model.dto.FreeBoard;

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
	
	/** �������� �� ��� */
	public void register(FreeBoard dto) {
		dao.insert(dto);
	}
	
	public void register(int articleNo, String title, int empNo, String regDate, String content, int hits, String userName) {
		dao.insert(articleNo, title, empNo, regDate, content, hits, userName);
	}
	
	/** �� �˻� */
	public ArrayList<FreeBoard> search(String columnName, String keyword) {
		return dao.selectListByColumn(columnName, keyword);
	}
	
	/** �۹�ȣ �ִ� +1 �������� */
	public int selectMaxNo() {
		return dao.selectMaxNo();
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
