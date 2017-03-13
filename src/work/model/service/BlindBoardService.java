/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.BlindBoardDAO;
import work.model.dto.BlindBoard;

/**
 * @author DB
 *
 */
public class BlindBoardService {
	private BlindBoardDAO dao = BlindBoardDAO.getInstance();

	/** �Խ��� �� ��� ��ȸ */
	public ArrayList<BlindBoard> selectList() {
		return dao.selectList();
	}

	/** �� ��� */
	public void register(BlindBoard dto) {
		dao.insert(dto);
	}

	/** �� �˻� */
	public ArrayList<BlindBoard> search(String columnName, String keyword) {
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
