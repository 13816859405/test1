package cn.bdqn.entity;

import java.sql.Date;

public class EasyBuyUser {
	private Integer userId;// �û���Ϣ�� �û�id
	private String userName;// �û���
	private String nickName;//�ǳ�
	private String userPwd;//����
	private Integer userSex;//�û��Ա�
	private Date birthday;//����
	private String identityCode;//���֤����
	private String email;//�����ʼ�
	private String mobile;//�ֻ���
	private String address;//��ַ
	private Integer stutas;//״̬������Ա1����ͨ0
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStutas() {
		return stutas;
	}
	public void setStutas(Integer stutas) {
		this.stutas = stutas;
	}
	public EasyBuyUser(Integer userId, String userName, String nickName,
			String userPwd, Integer userSex, Date birthday,
			String identityCode, String email, String mobile, String address,
			Integer stutas) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.birthday = birthday;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.stutas = stutas;
	}
	public EasyBuyUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
