package cn.bdqn.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.bdqn.entity.EasyBuyCategory;


public class EasyBuyCategoryDao extends BaseDao{
	//查询一级目录
	public List<EasyBuyCategory> findAllCategory(){
		List<EasyBuyCategory> list=new ArrayList<EasyBuyCategory>();
		String sql="SELECT * FROM easybuycategory WHERE epcId=parentId";
		try {
			rs=super.executeQuery(sql);
			while(rs.next()){
				Integer epcId=rs.getInt("epcId");
				String epcName=rs.getString("epcName");
				Integer parentId=rs.getInt("parentId");
				list.add(new EasyBuyCategory(epcId,epcName,parentId));
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
	//查询子目录根据一级目录
	public List<EasyBuyCategory> findAllCategory(int id){
		List<EasyBuyCategory> list = new ArrayList<EasyBuyCategory>();
		String sql="SELECT *FROM easybuycategory WHERE parentId=? AND parentId!=epcId";
		try {
			rs=super.executeQuery(sql, id);
			while(rs.next()){
				Integer epcId=rs.getInt("epcId");
				String epcName=rs.getString("epcName");
				Integer parentId=rs.getInt("parentId");
				list.add(new EasyBuyCategory(epcId,epcName,parentId));
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
	
	public int addChildCat(String className, Integer paretId) {
		String sql="INSERT INTO easybuycategory (epcName, parentId)VALUES(?, ?)";
		return super.executeUpdate(sql, className,paretId);
	}
	public int addParenCat(String parentNew, int parentId) {
		String sql="INSERT INTO easybuycategory (epcName, parentId)VALUES(?, ?)";
		return super.executeUpdate(sql, parentNew,parentId);
	}
	public int findCount() {
		int lastepcId=0;
		String sql="SELECT *FROM easybuycategory ORDER BY epcId DESC LIMIT 1";
		try {
			rs=super.executeQuery(sql);
			while(rs.next()){
				lastepcId=rs.getInt("epcId");
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
		
		return lastepcId;
	}
	public int getTotalCount() {
		int count=0;
		String sql="SELECT COUNT(1) FROM easybuycategory";
		try {
			rs=super.executeQuery(sql);
			while(rs.next()){
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
	public List<EasyBuyCategory> findPage(int pageNo, int pageSize) {
		List<EasyBuyCategory> list=new ArrayList<EasyBuyCategory>();
		String sql="SELECT * FROM easybuycategory ORDER BY parentId,epcId LIMIT ?,?";
		try {
			rs=super.executeQuery(sql,(pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				Integer epcId=rs.getInt("epcId");
				String epcName=rs.getString("epcName");
				Integer parentId=rs.getInt("parentId");
				list.add(new EasyBuyCategory(epcId,epcName,parentId));
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
	public EasyBuyCategory findById(int epcId) {
		EasyBuyCategory category=null;
		String sql="SELECT * FROM easybuycategory WHERE epcId=?";
		try {
			rs=super.executeQuery(sql, epcId);
			while(rs.next()){
				epcId=rs.getInt("epcId");
				String epcName=rs.getString("epcName");
				Integer parentId=rs.getInt("parentId");
				category=new EasyBuyCategory(epcId,epcName,parentId);
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
		return category;
	}
	public int upateCategory(EasyBuyCategory upateCategory) {
		String sql="UPDATE easybuycategory SET epcName = ?,parentId = ? WHERE epcId = ?";
		return super.executeUpdate(sql, upateCategory.getEpcName(),upateCategory.getParentId(),upateCategory.getEpcId());
	}
	public int delCategory(int epcId) {
		String sql="DELETE FROM easybuycategory WHERE epcId = ?";
		
		return super.executeUpdate(sql, epcId);
	}
	
	
}
