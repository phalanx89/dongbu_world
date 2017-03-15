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

	/** �Խ��� �� ��� ��ȸ */
  public ArrayList<Board> selectList() {
    return dao.selectList();
  }
  
  /**
   * �Խ��� �� �� ��ȸ
   * @param articleNo
   * @return
   */
  public Board selectOne(int articleNo) {
    return dao.selectOne(articleNo);
  }
  
  /**
   * ��ȸ�� +1
   * @param articleNo
   */
  public void plusHits(int articleNo) {
    dao.plusHits(articleNo);
  }
  
  /** �� ��� */
  public void register(Board dto) {
    dao.insert(dto);
  }
  
  /** �� �˻� */
  public ArrayList<Board> search(String columnName, String keyword) {
    return dao.selectListByColumn(columnName, keyword);
  }
  
  /** �۹�ȣ �ִ� +1 �������� */
  public int selectMaxNo() {
    return dao.selectMaxNo();
  }
  
  /** �������� �� ���� */
  public int update(Board dto) {
    return dao.update(dto);
  }
  
  /** �� ���� */
  public int delete(int articleNo) {
    return dao.delete(articleNo);
  }
}
