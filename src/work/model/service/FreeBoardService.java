/**
 * 
 */
package work.model.service;

import java.util.ArrayList;

import work.model.dao.FreeBoardDAO;
import work.model.dto.FreeBoard;

/**
 * @author DB
 *
 */
public class FreeBoardService {
	private FreeBoardDAO dao = FreeBoardDAO.getInstance();
	
	/** �Խ��� �� ��� ��ȸ */
	public ArrayList<FreeBoard> selectList() {
		return dao.selectList();
	}
	
	/**
	 * �Խ��� �� �� ��ȸ
	 * @param articleNo
	 * @return
	 */
	public FreeBoard selectOne(int articleNo) {
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
	public void register(FreeBoard dto) {
		dao.insert(dto);
	}
	
//	/**
//	 * ȸ���� �� ���
//	 * @param articleNo
//	 * @param title
//	 * @param empNo
//	 * @param regDate
//	 * @param content
//	 * @param hits
//	 * @param userName
//	 */
//	public void registerByMember(FreeBoard dto) {
//		if (userName != null && userName.length() > 14) {
//			System.out.println("userName's length is too long>> " + userName);
//			userName = userName.substring(0, 14);			
//			System.out.println("converted userName is >> " + userName);
//		}
//		dao.insertByMember(dto);
//	}
	
	/** �� �˻� */
	public ArrayList<FreeBoard> search(String columnName, String keyword) {
		return dao.selectListByColumn(columnName, keyword);
	}
	
	/** �۹�ȣ �ִ� +1 �������� */
	public int selectMaxNo() {
		return dao.selectMaxNo();
	}
	
	/** �������� �� ���� */
	public int update(FreeBoard dto) {
		return dao.update(dto);
	}
	
//	/** ȸ���� ���� �� ���� */
//	public int update(int articleNo, String title, String content) {
//		return dao.update(articleNo, title, content);
//	}
	
	/** �� ���� */
	public int delete(int articleNo) {
		return dao.delete(articleNo);
	}
}
