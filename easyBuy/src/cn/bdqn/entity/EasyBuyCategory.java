package cn.bdqn.entity;

public class EasyBuyCategory {
	private Integer epcId; //��Ʒ�������
	private String epcName;//��Ʒ������
	private Integer parentId;//������
	public Integer getEpcId() {
		return epcId;
	}
	public void setEpcId(Integer epcId) {
		this.epcId = epcId;
	}
	public String getEpcName() {
		return epcName;
	}
	public void setEpcName(String epcName) {
		this.epcName = epcName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public EasyBuyCategory(Integer epcId, String epcName, Integer parentId) {
		super();
		this.epcId = epcId;
		this.epcName = epcName;
		this.parentId = parentId;
	}
	public EasyBuyCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
