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
  /** �Խ��� �� ��� ��ȸ */
  public abstract ArrayList<Board> selectList();
  
  /**
   * �Խ��� �� �� ��ȸ
   * @param articleNo
   * @return
   */
  public abstract Board selectOne(int articleNo);
  
  /**
   * ��ȸ�� +1
   * @param articleNo
   */
  public abstract void plusHits(int articleNo);
  
  /** �� ��� */
  public abstract void register(Board dto);
  
  /** �� �˻� */
  public abstract ArrayList<Board> search(String columnName, String keyword);
  
  /** �۹�ȣ �ִ� +1 �������� */
  public abstract int selectMaxNo();
  
  /** �������� �� ���� */
  public abstract int update(Board dto);
  
  /** �� ���� */
  public abstract int delete(int articleNo);
}
