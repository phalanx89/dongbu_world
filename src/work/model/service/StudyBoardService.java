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
	
	/** 게시판 글 목록 조회 */
	public ArrayList<StudyBoard> selectList() {
		return dao.selectList();
	}
	
	/** 글 등록 */
	public void register(StudyBoard dto) {
		dao.insert(dto);
	}
	
	/** 글 검색 */
	public ArrayList<StudyBoard> search(String columnName, String keyword) {
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
