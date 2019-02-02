package cn.bdqn.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.entity.AllOrder;
import cn.bdqn.entity.EasyBuyOrder;
import cn.bdqn.entity.EasyBuyOrderDetail;



public class OrderDao extends BaseDao {

	public List<EasyBuyOrder> fingByPage(int pageNo, int pageSize,String eoId,String userName) {
		StringBuffer sql = new StringBuffer("SELECT * FROM `easybuyorder` WHERE 1=1");
		if (eoId != null) {
			sql.append(" AND eoId like '%" +eoId+ "%' ");
		}
		if (userName != null) {
			sql.append(" AND userName like '%" +userName+ "%'");
		}
		sql.append(" order by createTime desc limit ?,?");
		List<EasyBuyOrder> list=new ArrayList<EasyBuyOrder>();
		
		try {
			rs=super.executeQuery(sql.toString(),(pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				eoId=rs.getString("eoId");
				Integer userId=rs.getInt("userId"); 
				String address=rs.getString("address"); 
				Date createTime=rs.getDate("createTime");
				double cost=rs.getDouble("totalcost");
				Integer status=rs.getInt("status"); 
				Integer type=rs.getInt("type");
				userName=rs.getString("userName");
				list.add(new EasyBuyOrder(eoId,userId,address,createTime,cost,status,type,userName));
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
	
	public int getTotalCount(String eoId,String userName) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(1) FROM `easybuyorder` WHERE 1=1");
		if (eoId != null && eoId != "") {
			sql.append(" AND eoId like '%" + eoId + "%' ");
		}
		if (userName != null && userName != "") {
			sql.append(" AND userName like '%" + userName + "%' ");
		}
		int count=0;
		try {
			rs=super.executeQuery(sql.toString());
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

	public List<AllOrder> findOr(String eoId) {
		List<AllOrder> list=new ArrayList<AllOrder>();
		String sql="SELECT D.eodId,O.eoId,D.epId,D.quantity,D.cost,U.userName," +
				"O.address,O.createTime,O.totalcost,O.status,O.type,P.epName," +
				"P.fileName,P.description FROM easybuyorder O,easybuyorderdetail D," +
				"easybuyproduct P,easybuyuser U WHERE O.eoId=D.eoId AND " +
				"D.epId=P.epId AND U.userId=O.userId AND D.eoId=?";
		try {
			rs=super.executeQuery(sql,eoId);
			while(rs.next()){
				Integer eodId=rs.getInt("eodId");
				eoId=rs.getString("eoId");
				Integer epId=rs.getInt("epId");
				Integer quantity=rs.getInt("quantity");
				double cost=rs.getDouble("cost");
				String userName=rs.getString("userName");
				String address=rs.getString("address");
				Date createTime=rs.getDate("createTime");
				double totalcost=rs.getDouble("totalcost");
				Integer status=rs.getInt("status");
				Integer type=rs.getInt("type");
				String epName=rs.getString("epName");
				String fileName=rs.getString("fileName");
				String description=rs.getString("description");
				list.add(new AllOrder(eodId,eoId,epId,quantity,cost,userName,address,createTime,totalcost,status,type,epName,fileName,description));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally{
			super.closeAll(rs, pstmt, conn);
		}
		return list;
	}

	public int addOrder(String eoId, Integer userId, String address,
			double price, int status, int type,String userName) {
		String sql="INSERT INTO easybuyorder (eoId, userId, address, createTime, totalcost, `status`, `type`,userName)VALUES(?,?,?,NOW(),?,?,?,?)";
		Object[] args={eoId,userId,address,price,status,type,userName};
		return super.executeUpdate(sql, args);
	}

	public int addOrderDetail(String eoId, Integer epId, int quantity,
			double price) {
		String sql="INSERT INTO easybuyorderdetail(eoId,epId,quantity,cost)VALUES(?,?,?,?)";
		return super.executeUpdate(sql, eoId,epId,quantity,price);
	}

	

	
}
