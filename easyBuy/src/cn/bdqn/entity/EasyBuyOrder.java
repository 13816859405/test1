package cn.bdqn.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EasyBuyOrder {
	private String eoId;// ��������
	private Integer userId;//�û����
	private String address;//�û���ַ
	private Date createTime;// ����ʱ��
	private double cost;// ���
	private Integer status;// ״̬
	private Integer type;//֧����ʽ
	private String userName;
	
	public List<AllOrder> allOrder=new ArrayList<AllOrder>();
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//��Ʒ���� private String epName; private String fileName private double totale;
	public String getEoId() {
		return eoId;
	}
	public void setEoId(String eoId) {
		this.eoId = eoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public List<AllOrder> getAllOrder() {
		return allOrder;
	}
	public void setAllOrder(List<AllOrder> allOrder) {
		this.allOrder = allOrder;
	}
	
	
	public EasyBuyOrder(String eoId, Integer userId, String address,
			Date createTime, double cost, Integer status, Integer type,
			String userName) {
		super();
		this.eoId = eoId;
		this.userId = userId;
		this.address = address;
		this.createTime = createTime;
		this.cost = cost;
		this.status = status;
		this.type = type;
		this.userName = userName;
	}
	public EasyBuyOrder(String eoId, Integer userId, String address,
			Date createTime, double cost, Integer status, Integer type,
			List<AllOrder> allOrder) {
		super();
		this.eoId = eoId;
		this.userId = userId;
		this.address = address;
		this.createTime = createTime;
		this.cost = cost;
		this.status = status;
		this.type = type;
		this.allOrder = allOrder;
	}

	
	
	
}
