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
		<h2>订单管理</h2>
		<div class="manage">
			<div class="search">				
			</div>
			<div class="spacer"></div>
            <form id="orderForm" method="post"  action="OrderServlet?pro=findOrder">
                 订单号：<input type="text" class="text" name="entityId" id="entityId" />
                 订货人：<input type="text" class="text" name="userName" />
                 <label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
            </form>
			<table class="list">
			<c:forEach items="${pageBean.pageList }" var="ord">
				<tr>
					<th colspan="2">单号：${ord.eoId }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 时间：${ord.createTime }</th>					
					<th colspan="2"><c:if test="${ord.status==1 }">状态:待审核</c:if>
									<c:if test="${ord.status==2 }">状态:审核通过</c:if>
									<c:if test="${ord.status==3 }">状态:配货</c:if>
									<c:if test="${ord.status==4 }">状态:发货</c:if>
									<c:if test="${ord.status==5 }">状态:收货确认</c:if><select name="status" >						    
								<option value="1"  >待审核</option>
								<option value="2"  >审核通过</option>
								<option value="3"  >配货</option>
								<option value="4" >发货</option>
								<option value="5"  >收货确认</option>
							
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>总计：${ord.cost }</th>						
				</tr>
				<c:forEach items="${ord.allOrder }" var="prod">
				<tr>
					<td class="first w4 c"><img src="../images/product/${prod.fileName }" />${prod.epName }</td>
					<td >${prod.description}</td>
					<td>${prod.quantity }</td>
					<td class="w1 c" >金额：${prod.cost }</td>					
				</tr>
				</c:forEach>
			</c:forEach>
				<!-- <tr>
					<th colspan="2">单号：1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 时间：2013-3-16</th>					
					<th colspan="2">状态:收货确认</th>					
				</tr>
				<tr>
					<td class="first w4 c"><img src="../images/product/1.jpg" />画册</td>
					<td >100</td>
					<td>1</td>
					<td class="w1 c" rowspan="2">总计：140</td>					
				</tr>
				<tr>
					<td class="first w4 c"><img src="../images/product/2.jpg" />项链</td>
					<td >40</td>
					<td>1</td>			
				</tr>
                	<tr>
					<th colspan="2">单号：2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：2013-5-16</th>
					<th colspan="2">状态:待审核<select name="status" >						    
								<option value="1"  >待审核</option>
								<option value="2"  >审核通过</option>
								<option value="3"  >配货</option>
								<option value="4" >发货</option>
								<option value="5"  >收货确认</option>
							
						</select></th>					
				</tr>
				<tr>
					<td class="first w4 c"><img src="../images/product/3.jpg" />护肤王</td>
					<td >400</td>
					<td>1</td>
					<td class="w1 c">总计：400</td>					
				</tr> -->				
			</table>
			 <div style="text-align:right;">
					当前页/总页数：${pageBean.pageNo}/${pageBean.totalPages}
					<a href="OrderServlet?pageNo=1&pro=findOrder&entityId=${eoId}&userName=${userName}">首页</a>
					<a href="OrderServlet?pageNo=${pageBean.pageNo-1}&pro=findOrder&entityId=${eoId}&userName=${userName}">上一页</a>
					<a href="OrderServlet?pageNo=${pageBean.pageNo+1}&pro=findOrder&entityId=${eoId}&userName=${userName}">下一页</a>
					<a href="OrderServlet?pageNo=${pageBean.totalPages}&pro=findOrder&entityId=${eoId}&userName=${userName}">尾页</a>
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

