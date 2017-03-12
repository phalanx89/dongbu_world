/**
 * 
 */
package work.model.dto;

/**
 * @author DB
 *
 */
public class Member {
	private int empNo;
	private String userPw;
	private String userName;
	private String email;
	private String mobile;
	private String dept;
	private String position;
	private String isAdmin;

	/**
	 * 
	 */
	public Member() {
		super();
	}

	/**
	 * @param empNo
	 * @param userPw
	 * @param userName
	 * @param email
	 * @param mobile
	 * @param isAdmin
	 */
	public Member(int empNo, String userPw, String userName, String email, String mobile, String isAdmin) {
		super();
		this.empNo = empNo;
		this.userPw = userPw;
		this.userName = userName;
		this.email = email;
		this.mobile = mobile;
		this.isAdmin = isAdmin;
	}

	/**
	 * @param empNo
	 * @param userPw
	 * @param userName
	 * @param email
	 * @param mobile
	 * @param dept
	 * @param position
	 * @param isAdmin
	 */
	public Member(int empNo, String userPw, String userName, String email, String mobile, String dept, String position,
			String isAdmin) {
		super();
		this.empNo = empNo;
		this.userPw = userPw;
		this.userName = userName;
		this.email = email;
		this.mobile = mobile;
		this.dept = dept;
		this.position = position;
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the empNo
	 */
	public int getEmpNo() {
		return empNo;
	}

	/**
	 * @param empNo
	 *            the empNo to set
	 */
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	/**
	 * @return the userPw
	 */
	public String getUserPw() {
		return userPw;
	}

	/**
	 * @param userPw
	 *            the userPw to set
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the isAdmin
	 */
	public String getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Member [empNo=" + empNo + ", userPw=" + userPw + ", userName=" + userName + ", email=" + email
				+ ", mobile=" + mobile + ", dept=" + dept + ", position=" + position + ", isAdmin=" + isAdmin + "]";
	}

}
