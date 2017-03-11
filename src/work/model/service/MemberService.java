/**
 * 
 */
package work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import work.model.dao.MemberDAO;
import work.model.dto.Member;

/**
 * Service Class 
 *  - Business logic
 * 
 * @author DB
 *
 */
public class MemberService {
	/**  */
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public MemberService() {
		
	}
	
	/**
	 * 
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public HashMap<String, String> login(String userId, String userPw) {
		return memberDAO.login(userId, userPw);
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Member selectOneInfo(String userId) {
		return memberDAO.selectOne(userId);
	}
	
	public ArrayList<Member> selectList() {
		return memberDAO.selectList();
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteOne(String userId) {
		return memberDAO.deleteOne(userId);
	}
	
	public int updateOne(Member dto) {
		return memberDAO.update(dto);
	}
}
