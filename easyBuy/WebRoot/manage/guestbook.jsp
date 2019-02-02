<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manage/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
</head>
<body>
<%@include file="head.jsp" %>
<div id="childNav">
	<div class="welcome wrap">
		管理员${userLogin.userName}您好，今天是${today}，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<%@include file="lefter.jsp" %>
	<div class="main">
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${PageBean.pageList }" var="com">
					<c:if test="${com.reply==null }">
						<tr>
							<td class="first w4 c">${com.ecId}</td>
							<td class="w1 c">${com.nickName}</td>
							<td>${com.content}</td>
							<td class="w1 c">未回复</td>
							<td class="w1 c"><a href="CommentServlet?pro=showUpdateCom&ecId=${com.ecId}">回复</a> 
							<a class="manageDel" href="CommentServlet?pro=delCom&ecId=${com.ecId}">删除</a></td>
						</tr>
					</c:if>
					<c:if test="${com.reply!=null }">
						<tr>
							<td class="first w4 c">${com.ecId}</td>
							<td class="w1 c">${com.nickName}</td>
							<td>${com.content}</td>
							<td class="w1 c">已回复</td>
							<td class="w1 c"><a href="CommentServlet?pro=showUpdateCom&ecId=${com.ecId}">修改</a> 
							<a class="manageDel" href="CommentServlet?pro=delCom&ecId=${com.ecId}">删除</a></td>
						</tr>
					</c:if>
				</c:forEach>
				
				<!-- <tr>
					<td class="first w4 c">1</td>
					<td class="w1 c">张三丰</td>
					<td>高老庄的货发了没？</td>
					<td class="w1 c">已回复</td>
					<td class="w1 c"><a href="guestbook-modify.jsp">修改</a> <a class="manageDel" href="javascript:void(0)">删除</a></td>
				</tr>
				<tr>
					<td class="first w4 c">1</td>
					<td class="w1 c">张三丰</td>
					<td>北京的货发了没？</td>
					<td class="w1 c"></td>
					<td class="w1 c"><a href="guestbook-modify.jsp">回复</a> <a class="manageDel" href="javascript:void(0)">删除</a></td>
				</tr> -->
			</table>
			 <div style="text-align:right;">
					当前页/总页数：${PageBean.pageNo}/${PageBean.totalPages}
					<a href="CommentServlet?pageNo=1&pro=findComPage">首页</a>
					<a href="CommentServlet?pageNo=${PageBean.pageNo-1}&pro=findComPage">上一页</a>
					<a href="CommentServlet?pageNo=${PageBean.pageNo+1}&pro=findComPage">下一页</a>
					<a href="CommentServlet?pageNo=${PageBean.totalPages}&pro=findComPage">尾页</a>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

