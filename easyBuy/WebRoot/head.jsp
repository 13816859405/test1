<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help">
		<c:if test="${empty userLogin }">
			<a href="login.jsp">登录</a>
			<a href="register.jsp">注册</a>
		</c:if>
		<c:if test="${not empty userLogin }">
			<a href="shopping.jsp" id="shoppingBag" class="shopping">购物车<c:choose><c:when test="${cart==null}">0</c:when><c:otherwise>${cart.totalQuantity}</c:otherwise></c:choose>件</a>
			
			<a href="register.jsp">注册</a>
			<a class="button" id="logout" href="UserLoginServlet?action=exite">注销</a>
			<a href="guestbook.jsp">留言</a>
			<c:if test="${userLogin.stutas==1 }">
				<a href="manage/index.jsp">后台管理</a>
			</c:if>
		</c:if>
	</div>
    <div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="index.jsp">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
