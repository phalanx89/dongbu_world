/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.BlindBoardDAO;
import work.model.dto.Board;

/**
 * @author DB
 *
 */
public class BlindBoardService extends BoardService {
	private BlindBoardDAO dao = BlindBoardDAO.getInstance();

	/** 게시판 글 목록 조회 */
  public ArrayList<Board> selectList() {
    return dao.selectList();
  }
  
  /**
   * 게시판 글 상세 조회
   * @param articleNo
   * @return
   */
  public Board selectOne(int articleNo) {
    return dao.selectOne(articleNo);
  }
  
  /**
   * 조회수 +1
   * @param articleNo
   */
  public void plusHits(int articleNo) {
    dao.plusHits(articleNo);
  }
  
  /** 글 등록 */
  public void register(Board dto) {
    dao.insert(dto);
  }
  
  /** 글 검색 */
  public ArrayList<Board> search(String columnName, String keyword) {
    return dao.selectListByColumn(columnName, keyword);
  }
  
  /** 글번호 최댓값 +1 가져오기 */
  public int selectMaxNo() {
    return dao.selectMaxNo();
  }
  
  /** 관리자의 글 수정 */
  public int update(Board dto) {
    return dao.update(dto);
  }
  
  /** 글 삭제 */
  public int delete(int articleNo) {
    return dao.delete(articleNo);
  }
}
