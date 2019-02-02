<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
 <c:if test="${empty newsPageBean }">
<script type="text/javascript">
	location.href="NewsServlet?pro=findAll";
</script>
</c:if>
<%@include file="head.jsp" %>
<div id="childNav">
	<div class="welcome wrap">
		管理员${userLogin.userName}您好，今天是${today}，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<%@include file="lefter.jsp" %>
	<div class="main">
		<h2>新闻管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>新闻标题</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${newsPageBean.pageList }" var="news">
					<tr>
						<td class="first w4 c">${news.enId}</td>
						<td>${news.title}</td>
						<td class="w1 c"><a href="NewsServlet?pro=showUpdateNews&enId=${news.enId }">修改</a> 
							<a class="manageDel" href="NewsServlet?pro=delNews&enId=${news.enId }">删除</a>
						</td>
					</tr>
					
				</c:forEach>
				
				
			</table>
		</div>
	</div>
	<div class="clear"></div>
    <div style="text-align:right;">
					当前页/总页数：${newsPageBean.pageNo}/${newsPageBean.totalPages}
					<a href="NewsServlet?pageNo=1&pro=findAll">首页</a>
					<a href="NewsServlet?pageNo=${newsPageBean.pageNo-1}&pro=findAll">上一页</a>
					<a href="NewsServlet?pageNo=${newsPageBean.pageNo+1}&pro=findAll">下一页</a>
					<a href="NewsServlet?pageNo=${newsPageBean.totalPages}&pro=findAll">尾页</a>
			</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

