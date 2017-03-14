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
	
	/** 게시판 글 목록 조회 */
	public ArrayList<FreeBoard> selectList() {
		return dao.selectList();
	}
	
	/**
	 * 게시판 글 상세 조회
	 * @param articleNo
	 * @return
	 */
	public FreeBoard selectOne(int articleNo) {
	  return dao.selectOne(articleNo);
	}
	
	/**
	 * 조회수 +1
	 * @param articleNo
	 */
	public void plusHits(int articleNo) {
	  dao.plusHits(articleNo);
	}
	
	/** 관리자의 글 등록 */
	public void register(FreeBoard dto) {
		dao.insert(dto);
	}
	
	public void register(int articleNo, String title, int empNo, String regDate, String content, int hits, String userName) {
		if (userName != null && userName.length() > 14) {
			System.out.println("userName's length is too long>> " + userName);
			userName = userName.substring(0, 14);			
			System.out.println("converted userName is >> " + userName);
		}
		dao.insert(articleNo, title, empNo, regDate, content, hits, userName);
	}
	
	/** 글 검색 */
	public ArrayList<FreeBoard> search(String columnName, String keyword) {
		return dao.selectListByColumn(columnName, keyword);
	}
	
	/** 글번호 최댓값 +1 가져오기 */
	public int selectMaxNo() {
		return dao.selectMaxNo();
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
