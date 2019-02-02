package cn.bdqn.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.entity.EasyBuyUser;


public class EasyBuyUserDao extends BaseDao{
	//µÇÂ¼ÑéÖ¤
	public EasyBuyUser findByNamePad(String name,String pwd){
		EasyBuyUser user=null;
		String sql="SELECT * FROM easybuyuser WHERE userName=? AND userPwd=?";
		try {
			rs=super.executeQuery(sql, name,pwd);
			while(rs.next()){
				Integer userId=rs.getInt("userId");
				name=rs.getString("userName");
				String nickName=rs.getString("nickName");
				pwd=rs.getString("userPwd"); 
				Integer userSex=rs.getInt("userSex");
				Date birthday=rs.getDate("birthday");
				String identityCode=rs.getString("identityCode");
				String email=rs.getString("email"); 
				String mobile=rs.getString("mobile");
				String address=rs.getString("address");
				Integer status=rs.getInt("status");
				user=new EasyBuyUser(userId,name,nickName,pwd,userSex,birthday,identityCode,email,mobile,address,status);
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
		
		return user;
	}
	//×¢²á
	public int addUser(String userName,String nickName,String userPwd,
			Integer userSex,Date birthday,String identityCode,String email,
			String mobile,String address,Integer status){
		
		String sql="INSERT INTO easybuyuser (userName, nickName, userPwd, userSex," +
				" birthday, identityCode, email, mobile, address, `status`)" +
				"VALUES(?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
		Object[] args={userName,nickName,userPwd,userSex,birthday,identityCode,email,mobile,address,status};
		return super.executeUpdate(sql, args);
	}
	public String findByNamePad(String userName) {
		String user=null;
		String sql="SELECT * FROM easybuyuser WHERE userName=?";
		try {
			rs=super.executeQuery(sql, userName);
			while(rs.next()){
				user=rs.getString("userName");
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
		
		return user;
	}
	public int getTotalCount() {
		int count=0;
		String sql="SELECT COUNT(1) FROM easybuyuser";
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
	public List<EasyBuyUser> findByPage(int pageNo, int pageSize) {
		List<EasyBuyUser> list=new ArrayList<EasyBuyUser>();
		String sql="SELECT * FROM easybuyuser LIMIT ?,?";
		try {
			rs=super.executeQuery(sql, (pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				Integer userId=rs.getInt("userId");
				String userName=rs.getString("userName"); 
				String nickName=rs.getString("nickName"); 
				String userPwd=rs.getString("userPwd"); 
				Integer userSex=rs.getInt("userSex"); 
				Date birthday=rs.getDate("birthday"); 
				String identityCode=rs.getString("identityCode"); 
				String email=rs.getString("email"); 
				String mobile=rs.getString("mobile");
				String address=rs.getString("address"); 
				Integer status=rs.getInt("status");
				
				list.add(new EasyBuyUser(userId,userName,nickName,userPwd,userSex,birthday,identityCode,email,mobile,address,status));
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
	public EasyBuyUser findById(Integer userId) {
		EasyBuyUser user=null;
		String sql="SELECT * FROM easybuyuser WHERE userId=?";
		try {
			rs=super.executeQuery(sql, userId);
			while(rs.next()){
				userId=rs.getInt("userId");
				String userName=rs.getString("userName"); 
				String nickName=rs.getString("nickName"); 
				String userPwd=rs.getString("userPwd"); 
				Integer userSex=rs.getInt("userSex"); 
				Date birthday=rs.getDate("birthday"); 
				String identityCode=rs.getString("identityCode"); 
				String email=rs.getString("email"); 
				String mobile=rs.getString("mobile");
				String address=rs.getString("address"); 
				Integer status=rs.getInt("status");
				
				user=new EasyBuyUser(userId,userName,nickName,userPwd,userSex,birthday,identityCode,email,mobile,address,status);
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
		return user;
	}
	public int updateUser(EasyBuyUser user) {
		String sql="UPDATE easybuyuser SET userName =?, nickName = ?," +
				" userPwd = ?,userSex =?, birthday =?, mobile = ?, address =?" +
				" WHERE userId = ?";
		Object[] args={user.getUserName(),user.getNickName(),user.getUserPwd(),user.getUserSex(),user.getBirthday(),user.getMobile(),user.getAddress(),user.getUserId()};
		return super.executeUpdate(sql, args);
	}
	public int delUser(Integer userId) {
		String sql="DELETE FROM easybuyuser WHERE userId = ? ";
		
		return super.executeUpdate(sql, userId);
	}
	
}
