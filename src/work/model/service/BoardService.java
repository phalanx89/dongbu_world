/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dto.Board;

/**
 * @author DB
 *
 */
public abstract class BoardService {
  /** 게시판 글 목록 조회 */
  public abstract ArrayList<Board> selectList();
  
  /**
   * 게시판 글 상세 조회
   * @param articleNo
   * @return
   */
  public abstract Board selectOne(int articleNo);
  
  /**
   * 조회수 +1
   * @param articleNo
   */
  public abstract void plusHits(int articleNo);
  
  /** 글 등록 */
  public abstract void register(Board dto);
  
  /** 글 검색 */
  public abstract ArrayList<Board> search(String columnName, String keyword);
  
  /** 글번호 최댓값 +1 가져오기 */
  public abstract int selectMaxNo();
  
  /** 관리자의 글 수정 */
  public abstract int update(Board dto);
  
  /** 글 삭제 */
  public abstract int delete(int articleNo);
}
