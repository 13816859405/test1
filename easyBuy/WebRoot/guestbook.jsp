<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
 <c:if test="${empty comPageBean }">
<script type="text/javascript">
	location.href="CommentServlet?pro=findPage";
</script>
</c:if>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="shopping.jsp" class="shopping">购物车X件</a><a href="login.jsp">登录</a><a href="register.jsp">注册</a><a href="guestbook.jsp">留言</a><a href="manage/index.jsp">后台管理</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
	<%@include file="lefter.jsp" %>
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
				<c:forEach items="${comPageBean.pageList }" var="comment">
					<li>
						<dl>
							<dt>${comment.content}</dt>
							<dd class="author">网友：${comment.nickName} <span class="timer">${comment.createTime}</span></dd>
							<dd>${comment.reply}</dd>
						</dl>
					</li>
				</c:forEach>
				
			</ul>
			<div class="clear"></div>
			<div style="text-align:right;">
					当前页/总页数：${comPageBean.pageNo}/${comPageBean.totalPages}
					<a href="CommentServlet?pageNo=1&pro=findPage">首页</a>
					<a href="CommentServlet?pageNo=${comPageBean.pageNo-1}&pro=findPage">上一页</a>
					<a href="CommentServlet?pageNo=${comPageBean.pageNo+1}&pro=findPage">下一页</a>
					<a href="CommentServlet?pageNo=${comPageBean.totalPages}&pro=findPage">尾页</a>
			</div>
			<div id="reply-box">
				<form id="guestBook" method="post" action="CommentServlet?pro=addComm">
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" readonly="readonly" value="${userLogin.userName}"/></td>
						</tr>						
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea><span></span></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
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

