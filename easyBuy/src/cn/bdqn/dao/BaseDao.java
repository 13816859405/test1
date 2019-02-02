package cn.bdqn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BaseDao {
	private String url="jdbc:mysql://localhost:3306/easybuy?useUnicode=true&amp;characterEncoding=UTF-8";
	private String user="root";
	private String password="root";
	private String driverName="com.mysql.jdbc.Driver";
	
	protected Connection conn=null;
	protected PreparedStatement pstmt=null;
	protected ResultSet rs=null;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		
		Connection conn=null;
		Class.forName(driverName);
		conn=DriverManager.getConnection(url,user,password);
		return conn;
	}
	
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int executeUpdate(String sql,Object... args){
		int ret=0;
		try {
			conn=this.getConnection();
			pstmt=conn.prepareStatement(sql);
			if(args!=null){
				for(int i=0;i<args.length;i++){
					pstmt.setObject((i+1), args[i]);
				}
			}
			ret=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(rs, pstmt, conn);
		}
		
		return ret;
	}
	
	public ResultSet executeQuery(String sql,Object...args) throws ClassNotFoundException, SQLException{
		conn=this.getConnection();
		pstmt=conn.prepareStatement(sql);
		if(args!=null){
			for(int i=0;i<args.length;i++){
				pstmt.setObject((i+1),args[i]);
			}
		}
		return pstmt.executeQuery();
	}
}
