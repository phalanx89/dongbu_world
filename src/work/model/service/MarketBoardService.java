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
	
	/** 게시판 글 목록 조회 */
	public ArrayList<MarketBoard> selectList() {
		return dao.selectList();
	}
	
	/** 글 등록 */
	public void register(MarketBoard dto) {
		dao.insert(dto);
	}
	
	/** 글 검색 */
	public ArrayList<MarketBoard> search(String columnName, String keyword) {
		return dao.selectListByColumn(columnName, keyword);
	}
	
	/** 관리자의 글 수정 */
	public int update(int articleNo, String title, String content, String isNotice) {
		return dao.update(articleNo, title, content, isNotice);
	}
	
	/** 회원의 본인 글 수정 */
	public int update(int articleNo, String title, String content) {
		return dao.update(articleNo, title, content);
	}
	
	/** 글 삭제 */
	public int delete(int articleNo) {
		return dao.delete(articleNo);
	}
}
