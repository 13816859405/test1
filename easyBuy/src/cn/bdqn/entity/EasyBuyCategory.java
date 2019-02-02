package cn.bdqn.entity;

public class EasyBuyCategory {
	private Integer epcId; //商品分类表编号
	private String epcName;//商品类名字
	private Integer parentId;//父分类
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
