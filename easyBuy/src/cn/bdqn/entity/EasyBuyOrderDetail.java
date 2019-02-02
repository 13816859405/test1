package cn.bdqn.entity;

public class EasyBuyOrderDetail {
	private Integer eodId;// ������ϸ����
	private String eoId;//������
	private Integer epId;//��Ʒid
	private Integer quantity;// ����
	private double cost;//���
	private EasyBuyProduct product;
	
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
		this.cost=product.getPrice()*quantity;
	}
	public double getCost() {
		return cost;
	}
	
	public EasyBuyProduct getProduct() {
		return product;
	}
	public void setProduct(EasyBuyProduct product) {
		this.product = product;
	}
	public EasyBuyOrderDetail(Integer eodId, String eoId, Integer epId,
			Integer quantity, double cost, EasyBuyProduct product) {
		super();
		this.eodId = eodId;
		this.eoId = eoId;
		this.epId = epId;
		this.quantity = quantity;
		this.cost = cost;
		this.product = product;
	}
	public EasyBuyOrderDetail(Integer eodId, String eoId, Integer epId,
			Integer quantity, double cost) {
		super();
		this.eodId = eodId;
		this.eoId = eoId;
		this.epId = epId;
		this.quantity = quantity;
		this.cost = cost;
	}
	
	
}
