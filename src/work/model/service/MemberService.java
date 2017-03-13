package work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import work.model.dao.FactoryDAO;
import work.model.dao.MemberDAO;
import work.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = MemberDAO.getInstance();

	/** �α��� */
	public HashMap<String, String> login(int empNo, String userPw) {
		HashMap<String, String> loginmap = dao.login(empNo, userPw);
		return loginmap;
	}
	
	/** �������� ȸ�� ��� */
	public void join(Member dto) {
		dao.insert(dto);
	}
	
	/** ��ü ȸ�� ���� ��ȸ */
	public ArrayList<Member> selectList() {
		return dao.selectList();
	}
	
	/** �������� ȸ������ ���� */
	public int update(Member dto) {
		return dao.update(dto);
	}
	
	/** ���(���̵�)���� ���� ��ȸ */
	public Member myInfo(int empNo) {
		Member dto = dao.selectOne(empNo);
		return dto;
	}
}
