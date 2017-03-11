package work.model.dto;

import java.io.Serializable;

/** 
 *	## Member Ŭ����
 *	-- ������ Ŭ���� 
 *	-- �������, �޼���, �ߺ����� ������, this
 *	-- Encapsulation ���� ����
 *  -- ��� : ȸ���鿡 ���� �Ϲ�ȭ��Ų �θ�Ŭ����
 */
public class Member implements Serializable {  // extends java.lang.Object �ڵ� ���
	/** ȸ�� ���̵� */
	private String userId = "Guest";
	/** ȸ�� ��й�ȣ */
	private String userPw;
	/** ȸ�� �̸� */
	private String username;
	/** ȸ�� �޴��� */
	private String mobile;
	/** ȸ�� �̸��� */
	private String email;
	/** ȸ�� ������ */
	private String entryDate;
	/** ȸ�� ��� */
	private String grade;
	/** ���ϸ��� ���� */
	private int mileage;
	/** ����� ���� */
	private String manager;
	
	
	/** �⺻������ 
	 * -- java.lang.Object �ڵ����
	 * -- {@link java.lang.Object#getClass()}
	 * */
	public Member() {

	}

	/**
	 * <pre>
	 * �ʼ� ������ �ʱ�ȭ ������
	 * </pre>
	 * @param userId
	 * @param userPw
	 * @param username
	 * @param mobile
	 * @param email
	 */
	public Member(String userId, String userPw, String username, String mobile, String email) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
	}
	
	/**
	 * <pre>
	 * ��� ������ �ʱ�ȭ ������
	 * </pre>
	 * @param userId
	 * @param userPw
	 * @param username
	 * @param mobile
	 * @param email
	 * @param entryDate
	 * @param grade
	 */
	public Member(String userId, String userPw, String username, String mobile, String email, String entryDate,
			String grade, int mileage, String manager) {
		this(userId, userPw, username, mobile, email);
		this.entryDate = entryDate;
		this.grade = grade;
		this.mileage = mileage;
		this.manager = manager;
	}
	
	/**
	 * �ӽ� ������ ������
	 * �� DB�� username�� ��� �׳� ����~
	 * @param userId
	 * @param userPw
	 * @param mobile
	 * @param email
	 * @param entryDate
	 * @param grade
	 * @param mileage
	 * @param manager
	 */
	public Member(String userId, String userPw, String mobile, String email, String entryDate, String grade, int mileage, String manager) {
		this(userId, userPw, "�赿��", mobile, email);
		this.entryDate = entryDate;
		this.grade = grade;
		this.mileage = mileage;
		this.manager = manager;
	}

	//ȸ���� ������
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userPw
	 */
	public String getUserPw() {
		return userPw;
	}

	/**
	 * @param userPw the userPw to set
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	/**
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userId);
		builder.append(", ");
		builder.append(userPw);
		builder.append(", ");
		builder.append(username);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(email);
		builder.append(", ");
		builder.append(entryDate);
		builder.append(", ");
		builder.append(grade);
		builder.append(", ");
		builder.append(mileage);
		builder.append(", ");
		builder.append(manager);
		return builder.toString();
	}
}
