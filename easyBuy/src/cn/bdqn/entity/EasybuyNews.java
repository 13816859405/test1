package cn.bdqn.entity;

import java.sql.Date;

public class EasybuyNews {
	private Integer enId;//��Ѷ����
	private String title;//����
	private String content;// ����
	private Date createTime;//����ʱ��
	public Integer getEnId() {
		return enId;
	}
	public void setEnId(Integer enId) {
		this.enId = enId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public EasybuyNews(Integer enId, String title, String content,
			Date createTime) {
		super();
		this.enId = enId;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
	public EasybuyNews() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
