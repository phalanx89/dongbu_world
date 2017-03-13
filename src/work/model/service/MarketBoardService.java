/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.MarketBoardDAO;
import work.model.dto.MarketBoard;

/**
 * @author DB
 *
 */
public class MarketBoardService {
	private MarketBoardDAO dao = MarketBoardDAO.getInstance();
	
	/** �Խ��� �� ��� ��ȸ */
	public ArrayList<MarketBoard> selectList() {
		return dao.selectList();
	}
	
	/** �� ��� */
	public void register(MarketBoard dto) {
		dao.insert(dto);
	}
	
	/** �� �˻� */
	public ArrayList<MarketBoard> search(String columnName, String keyword) {
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
