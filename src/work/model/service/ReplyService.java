package work.model.service;

import java.util.ArrayList;

import work.model.dao.FreeReplyDAO;
import work.model.dto.FreeReply;

public abstract class ReplyService {
  /**
   * ��� ��� ��ȸ
   * @return
   */
  public abstract ArrayList<FreeReply> selectList();
  
  /** ��� ��� */
  public abstract void register(FreeReply dto);
  
  /** �۹�ȣ �ִ� +1 �������� */
  public abstract int selectMaxNo();
  
  /** ��� ���� */
  public abstract int update(FreeReply dto);
  
  /** ��� ���� */
  public abstract int delete(int articleNo);
  /**
   * ��� �� ����
   * @param articleNo
   * @return
   */
  public abstract int countReply(int articleNo);
}
