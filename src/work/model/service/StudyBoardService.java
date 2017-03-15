/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.StudyBoardDAO;
import work.model.dto.Board;
import work.model.dto.StudyBoard;

/**
 * @author DB
 *
 */
public class StudyBoardService extends BoardService {
	private StudyBoardDAO dao = StudyBoardDAO.getInstance();
	
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
