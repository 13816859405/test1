package cn.bdqn.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.entity.EasybuyNews;

public class EasyBuyNewsDao extends BaseDao{

	public int addNews(String title, String content) {
		String sql="INSERT INTO easybuynews ( title, content, createTime)VALUES( ?, ?, NOW())";
		return super.executeUpdate(sql, title,content);
	}

	public int getTotalCount() {
		int count=0;
		String sql="SELECT COUNT(1) FROM easybuynews";
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

	public List<EasybuyNews> findByPage(int pageNo, int pageSize) {
		List<EasybuyNews> list=new ArrayList<EasybuyNews>();
		String sql="SELECT enId, title, content, createTime FROM easybuynews LIMIT ?,?";
		try {
			rs=super.executeQuery(sql, (pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				int enId=rs.getInt("enId");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date createTime=rs.getDate("createTime");
				list.add(new EasybuyNews(enId,title,content,createTime));
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

	public List<EasybuyNews> findNews() {
		List<EasybuyNews> list=new ArrayList<EasybuyNews>();
		String sql="SELECT *FROM easybuynews ORDER BY createTime DESC LIMIT 8";
		try {
			rs=super.executeQuery(sql);
			while(rs.next()){
				Integer enId=rs.getInt("enId");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date createTime=rs.getDate("createTime");
				list.add(new EasybuyNews(enId,title,content,createTime));
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

	public EasybuyNews findById(Integer enId) {
		EasybuyNews news=null;
		String sql="SELECT * FROM easybuynews WHERE enId=?";
		try {
			rs=super.executeQuery(sql, enId);
			while(rs.next()){
				String title=rs.getString("title"); 
				String content=rs.getString("content");  
				Date createTime=rs.getDate("createTime");
				news=new EasybuyNews(enId,title,content,createTime);
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
		return news;
	}

	public int updateNews(EasybuyNews updateNews) {
		String sql="UPDATE easybuynews SET title = ? ,content = ? ," +
				"createTime = NOW() WHERE enId = ?";
		Object[] args={updateNews.getTitle(),updateNews.getContent(),updateNews.getEnId()};
		return super.executeUpdate(sql, args);
	}

	public int delNews(Integer enId) {
		String sql="DELETE FROM easybuynews WHERE enId = ? ";
		
		return super.executeUpdate(sql, enId);
	}
	
}
