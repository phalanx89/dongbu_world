package work.model.service;

import java.util.ArrayList;

import work.model.dao.FreeReplyDAO;
import work.model.dto.FreeReply;

public class FreeReplyService extends ReplyService {
  private FreeReplyDAO dao = FreeReplyDAO.getInstance();
  
  /**
   * 댓글 목록 조회
   * @return
   */
  public ArrayList<FreeReply> selectList() {
    return dao.selectList();
  } 
  
  /** 댓글 등록 */
  public void register(FreeReply dto) {
    dao.insert(dto);
  }
  
  /** 글번호 최댓값 +1 가져오기 */
  public int selectMaxNo() {
    return dao.selectMaxNo();
  }
  
  /** 댓글 수정 */
  public int update(FreeReply dto) {
    return dao.update(dto);
  } 
  
  /** 댓글 삭제 */
  public int delete(int articleNo) {
    return dao.delete(articleNo);
  }
  
  /**
   * 댓글 수 세기
   * @param articleNo
   * @return
   */
  public int countReply(int articleNo) {
    return dao.countReply(articleNo);
  }
}
