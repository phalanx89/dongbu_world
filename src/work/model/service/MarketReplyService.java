package work.model.service;

import java.util.ArrayList;

import work.model.dao.MarketReplyDAO;
import work.model.dto.FreeReply;

public class MarketReplyService extends ReplyService {
  private MarketReplyDAO dao = MarketReplyDAO.getInstance();
  
  /**
   * ��� ��� ��ȸ
   * @return
   */
  public ArrayList<FreeReply> selectList() {
    return dao.selectList();
  } 
  
  /** ��� ��� */
  public void register(FreeReply dto) {
    dao.insert(dto);
  }
  
  /** �۹�ȣ �ִ� +1 �������� */
  public int selectMaxNo() {
    return dao.selectMaxNo();
  }
  
  /** ��� ���� */
  public int update(FreeReply dto) {
    return dao.update(dto);
  } 
  
  /** ��� ���� */
  public int delete(int articleNo) {
    return dao.delete(articleNo);
  }
  
  /**
   * ��� �� ����
   * @param articleNo
   * @return
   */
  public int countReply(int articleNo) {
    return dao.countReply(articleNo);
  }
}