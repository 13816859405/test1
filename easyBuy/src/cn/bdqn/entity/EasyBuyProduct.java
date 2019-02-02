package cn.bdqn.entity;

public class EasyBuyProduct {
	private Integer epId;// 商品信息表
	private String epName;//商品名
	private String description;// 描述
	private double price;//价格
	private Integer stock;//库存
	private Integer epcId;// 分类id
	private String fileName;//文件名
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getEpName() {
		return epName;
	}
	public void setEpName(String epName) {
		this.epName = epName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getEpcId() {
		return epcId;
	}
	public void setEpcId(Integer epcId) {
		this.epcId = epcId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public EasyBuyProduct(Integer epId, String epName, String description,
			double price, Integer stock, Integer epcId, String fileName) {
		super();
		this.epId = epId;
		this.epName = epName;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.epcId = epcId;
		this.fileName = fileName;
	}
	public EasyBuyProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
