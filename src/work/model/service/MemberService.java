package work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import work.model.dao.FactoryDAO;
import work.model.dao.MemberDAO;
import work.model.dto.Board;
import work.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = MemberDAO.getInstance();
	
	/**
	 * 사번으로 이름 가져오기
	 * @param empNo
	 * @return
	 */
	public String getUserName(int empNo) {
	  return dao.getUserName(empNo);
	}

	/** 로그인 */
	public HashMap<String, String> login(int empNo, String userPw) {
		HashMap<String, String> loginmap = dao.login(empNo, userPw);
		return loginmap;
	}
	
	/** 관리자의 회원 등록 */
	public void join(Member dto) {
		dao.insert(dto);
	}
	
	/** 전체 회원 정보 조회 */
	public ArrayList<Member> selectList() {
		return dao.selectList();
	}
	
	
	/** 회원정보 변경 */
	public int update(Member dto) {
		return dao.update(dto);
	}
	
	/**
	 * 임시비밀번호로 비밀번호 변경
	 * @param empNo
	 * @param userPw
	 * @return
	 */
	public int changePw(String userPw, String email) {
	  return dao.changePw(userPw, email);
	}
	
	/** 사번(아이디)으로 정보 조회(내정보 조회) */
	public Member myInfo(int empNo) {
		Member dto = dao.selectOne(empNo);
		return dto;
	}
}
