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
		<h2>商品管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>编号</th>
					<th>商品名称</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageBean.pageList }" var="product">
					<tr>
						<td class="first w4 c">${product.epId}</td>
						<td class="thumb"><img src="../images/product/${product.fileName }" width=80px height=80px/><a href="ProductServlet?pro=findProduct&epId=${product.epId }" target="_self">${product.epName}</a></td>
						<td class="w1 c"><a href="ProductServlet?pro=showUpate&epId=${product.epId }">修改</a> 
						<a class="manageDel" href="ProductServlet?pro=delProduct&epId=${product.epId }">删除</a></td>
					</tr>
				</c:forEach>
				
				
			</table>
		</div>
	</div>
	<div class="clear"></div>
   <div style="text-align:right;">
					当前页/总页数：${pageBean.pageNo}/${pageBean.totalPages}
					<a href="ProductServlet?pageNo=1&pro=proPageMan">首页</a>
					<a href="ProductServlet?pageNo=${pageBean.pageNo-1}&pro=proPageMan">上一页</a>
					<a href="ProductServlet?pageNo=${pageBean.pageNo+1}&pro=proPageMan">下一页</a>
					<a href="ProductServlet?pageNo=${pageBean.totalPages}&pro=proPageMan">尾页</a>
			</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

