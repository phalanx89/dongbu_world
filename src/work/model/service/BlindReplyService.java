package work.model.service;

import java.util.ArrayList;

import work.model.dao.BlindReplyDAO;
import work.model.dto.FreeReply;

public class BlindReplyService extends ReplyService {
  private BlindReplyDAO dao = BlindReplyDAO.getInstance();
  
  /**
   * ��� ��� ��ȸ
   * @return
   */
  public ArrayList<FreeReply> selectList() {
    return dao.selectList();
  } 
  
  /**
   * ��� ��� ��ȸ(�Խñ� ��ȣ ����)
   */
  public ArrayList<FreeReply> selectList(int articleNo) {
    return dao.selectList(articleNo);
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
