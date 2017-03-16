package work.model.service;

import java.util.ArrayList;

import work.model.dao.FreeReplyDAO;
import work.model.dto.FreeReply;

public abstract class ReplyService {
  /**
   * 댓글 목록 조회
   * @return
   */
  public abstract ArrayList<FreeReply> selectList();
  
  /**
   * 댓글 목록 조회(게시글 번호 기준)
   * @param articleNo
   * @return
   */
  public abstract ArrayList<FreeReply> selectList(int articleNo);
  
  /** 댓글 등록 */
  public abstract void register(FreeReply dto);
  
  /** 글번호 최댓값 +1 가져오기 */
  public abstract int selectMaxNo();
  
  /** 댓글 수정 */
  public abstract int update(FreeReply dto);
  
  /** 댓글 삭제 */
  public abstract int delete(int articleNo);
  /**
   * 댓글 수 세기
   * @param articleNo
   * @return
   */
  public abstract int countReply(int articleNo);
}
