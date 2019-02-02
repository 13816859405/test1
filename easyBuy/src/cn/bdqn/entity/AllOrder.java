package cn.bdqn.entity;

import java.sql.Date;

public class AllOrder {
	private Integer eodId;
	private String eoId;
	private Integer epId;
	private Integer quantity;
	private double cost;
	private String userName;
	private String address;
	private Date createTime;
	private double totalcost;
	private Integer status;
	private Integer type;
	private String epName;
	private String fileName;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEodId() {
		return eodId;
	}
	public void setEodId(Integer eodId) {
		this.eodId = eodId;
	}
	public String getEoId() {
		return eoId;
	}
	public void setEoId(String eoId) {
		this.eoId = eoId;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public double getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
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
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public AllOrder(Integer eodId, String eoId, Integer epId, Integer quantity,
			double cost, String userName, String address, Date createTime,
			double totalcost, Integer status, Integer type, String epName,
			String fileName, String description) {
		super();
		this.eodId = eodId;
		this.eoId = eoId;
		this.epId = epId;
		this.quantity = quantity;
		this.cost = cost;
		this.userName = userName;
		this.address = address;
		this.createTime = createTime;
		this.totalcost = totalcost;
		this.status = status;
		this.type = type;
		this.epName = epName;
		this.fileName = fileName;
		this.description = description;
	}
	
	
	
}
