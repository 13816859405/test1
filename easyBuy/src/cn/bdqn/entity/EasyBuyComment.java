package cn.bdqn.entity;

import java.sql.Date;

public class EasyBuyComment {
	private Integer ecId;//���Ա���
	private String reply;//�ظ�
	private String content; //����
	private Date createTime; //����ʱ��
	private Date replyTime; //�ظ�ʱ��
	private String nickName;//�ǳ�
	public Integer getEcId() {
		return ecId;
	}
	public void setEcId(Integer ecId) {
		this.ecId = ecId;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public EasyBuyComment(Integer ecId, String reply, String content,
			Date createTime, Date replyTime, String nickName) {
		super();
		this.ecId = ecId;
		this.reply = reply;
		this.content = content;
		this.createTime = createTime;
		this.replyTime = replyTime;
		this.nickName = nickName;
	}
	public EasyBuyComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
