package cn.bdqn.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.entity.EasyBuyProduct;

public class EasyBuyProductDao extends BaseDao {
	public int getProductCount() {
		int count=0;
		String sql=" SELECT COUNT(1) FROM easybuyproduct";
		try {
			rs=super.executeQuery(sql);
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.closeAll(rs, pstmt, conn);
		}
		return count;
	}
	public List<EasyBuyProduct> findByProductPage(int pageNo, int pageSize) {
		List<EasyBuyProduct> list=new ArrayList<EasyBuyProduct>();
		String sql="SELECT * FROM easybuyproduct LIMIT ?,?";
		try {
			rs=super.executeQuery(sql, (pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				Integer epId=rs.getInt("epId");
				String epName=rs.getString("epName"); 
				String description=rs.getString("description"); 
				double price=rs.getDouble("price"); 
				Integer stock=rs.getInt("stock"); 
				Integer epcId=rs.getInt("epcId"); 
				String fileName=rs.getString("fileName");
				list.add(new EasyBuyProduct(epId,epName,description,price,stock,epcId,fileName));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.closeAll(rs, pstmt, conn);
		}
		return list;
	}
	public EasyBuyProduct findById(Integer epId) {
		EasyBuyProduct product=null;
		String sql="SELECT * FROM easybuyproduct WHERE epId=?";
		try {
			rs=super.executeQuery(sql, epId);
			while(rs.next()){
				String epName=rs.getString("epName");
				String description=rs.getString("description"); 
				double price=rs.getDouble("price"); 
				Integer stock=rs.getInt("stock"); 
				Integer epcId=rs.getInt("epcId"); 
				String fileName=rs.getString("fileName");
				product=new EasyBuyProduct(epId,epName,description,price,stock,epcId,fileName);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.closeAll(rs, pstmt, conn);
		}
		return product;
	}
	public List<EasyBuyProduct> findProductList(int pageNo, int pageSize,
			Integer epcId) {
		List<EasyBuyProduct> list=new ArrayList<EasyBuyProduct>();
		String sql="SELECT * FROM easybuyproduct WHERE epcId=? LIMIT ?,?";
		try {
			rs=super.executeQuery(sql,epcId,(pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				Integer epId=rs.getInt("epId");
				String epName=rs.getString("epName"); 
				String description=rs.getString("description"); 
				double price=rs.getDouble("price"); 
				Integer stock=rs.getInt("stock"); 
				epcId=rs.getInt("epcId"); 
				String fileName=rs.getString("fileName");
				list.add(new EasyBuyProduct(epId,epName,description,price,stock,epcId,fileName));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int classProducCount(Integer epcId) {
		int count=0;
		String sql=" SELECT COUNT(1) FROM easybuyproduct WHERE epcId=?";
		try {
			rs=super.executeQuery(sql,epcId);
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.closeAll(rs, pstmt, conn);
		}
		return count;
	}
	public int addProduct(String epName, String description, Integer epcId,
			double price, Integer stock,String pic) {
		String sql="INSERT INTO easybuy.easybuyproduct ( epName, description, price, stock, epcId, fileName)VALUES(?,?,?,?,?,?)";
		return super.executeUpdate(sql,epName,description,price,stock,epcId,pic);
	}
	public int upateProduct(String epName, String description, Integer epcId,
			double price, Integer stock,Integer epId) {
		String sql="UPDATE easybuyproduct SET epName =?,description =?,price =?," +
				"stock = ?,epcId = ?,fileName =NULL WHERE epId =?";
		Object[] args={epName,description,epcId,price,stock,epId};
		return super.executeUpdate(sql, args);
	}
	public int delProduct(Integer epId) {
		String sql="DELETE FROM easybuyproduct WHERE epId = ? ";
		return super.executeUpdate(sql, epId);
	}
	public int upateProduct(EasyBuyProduct pro) {
		String sql="UPDATE easybuyproduct SET epName = ?,description =?,price =?,stock =?,epcId =?,fileName=?WHERE epId=?";
		Object[] args={pro.getEpName(),pro.getDescription(),pro.getPrice(),pro.getStock(),pro.getEpcId(),pro.getFileName(),pro.getEpId()};
		return super.executeUpdate(sql, args);
	}
	
}
