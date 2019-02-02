package cn.bdqn.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.entity.EasyBuyComment;




public class CommentDao extends BaseDao {
	public List<EasyBuyComment> findcomByPage(int pageNo, int pageSize) {
		List<EasyBuyComment> list=new ArrayList<EasyBuyComment>();
		String sql="SELECT * FROM easybuycomment ORDER BY createTime DESC LIMIT ?,?";
		try {
			rs=super.executeQuery(sql, (pageNo-1)*pageSize,pageSize);
			while(rs.next()){
				Integer ecId=rs.getInt("ecId");
				String reply=rs.getString("reply");
				String content=rs.getString("content"); 
				Date createTime=rs.getDate("createTime");
				Date replyTime=rs.getDate("replyTime");
				String nickName=rs.getString("nickName");
				list.add(new EasyBuyComment(ecId,reply,content,createTime,replyTime,nickName));
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

	public int getTotalCount() {
		int count=0;
		String sql="SELECT COUNT(1) FROM easybuycomment";
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

	public int addComment(String nickName, String guestContent) {
		String sql="INSERT INTO easybuycomment (content,createTime,replyTime,nickName)VALUES(?, NOW(),NOW(),?)";
		return super.executeUpdate(sql, guestContent,nickName);
	}

	public EasyBuyComment findById(Integer ecId) {
		EasyBuyComment com=null;
		String sql="SELECT * FROM easybuycomment WHERE ecId=?";
		try {
			rs=super.executeQuery(sql, ecId);
			while(rs.next()){
				ecId=rs.getInt("ecId");
				String reply=rs.getString("reply");
				String content=rs.getString("content"); 
				Date createTime=rs.getDate("createTime");
				Date replyTime=rs.getDate("replyTime"); 
				String nickName=rs.getString("nickName");
				com=new EasyBuyComment(ecId,reply,content,createTime,replyTime,nickName);
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
		return com;
	}

	public int updateCom(EasyBuyComment updateCom) {
		String sql="UPDATE easybuycomment SET reply =?,content =?,replyTime=NOW(),nickName =? WHERE ecId =?";
		return super.executeUpdate(sql, updateCom.getReply(),updateCom.getContent(),updateCom.getNickName(),updateCom.getEcId());
	}

	public int delCom(Integer ecId) {
		String sql="DELETE FROM easybuycomment WHERE ecId = ? ";
		
		return super.executeUpdate(sql, ecId);
	}
}
