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
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>Email</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${PageBean.pageList }" var="user">
					<tr>
						<td class="first w4 c">${user.userName}</td>
						<td class="w1 c">${user.nickName}</td>
						 <c:if test="${user.userSex==0 }"><td class="w2 c">女</td></c:if>
						 <c:if test="${user.userSex==1 }"><td class="w2 c">男</td></c:if>
						<td>${user.email}</td>
						<td class="w4 c">${user.mobile}</td>
						<td class="w1 c"><a href="UserServlet?pro=showUpdateUser&userId=${user.userId}">修改</a> 
						<a class="manageDel" href="UserServlet?pro=delUser&userId=${user.userId}">删除</a></td>
					</tr>
				</c:forEach>
				<!-- <tr>
					<td class="first w4 c">admin</td>
					<td class="w1 c">张三丰</td>
					<td class="w2 c">男</td>
					<td>fengsan.zhang@prd.com</td>
					<td class="w4 c">13888888888</td>
					<td class="w1 c"><a href="user-modify.html">修改</a> <a class="manageDel" href="javascript:void(0)">删除</a></td>
				</tr> -->
				
			</table>
		</div>
	</div>
	<div class="clear"></div>
     <div style="text-align:right;">
					当前页/总页数：${PageBean.pageNo}/${PageBean.totalPages}
					<a href="UserServlet?pageNo=1&pro=findUserPage">首页</a>
					<a href="UserServlet?pageNo=${PageBean.pageNo-1}&pro=findUserPage">上一页</a>
					<a href="UserServlet?pageNo=${PageBean.pageNo+1}&pro=findUserPage">下一页</a>
					<a href="UserServlet?pageNo=${PageBean.totalPages}&pro=findUserPage">尾页</a>
			</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

