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
 <c:if test="${empty newList}">
<script type="text/javascript">
	location.href="IndexServlet";
</script>
</c:if> 


<div id="welcomeImage">
    <img width="100%" height="150" src="images/banner.jpg" alt="welcome"/>
</div>
<%@include file="head.jsp" %>
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
<div id="main" class="wrap">
	<%@include file="lefter.jsp" %>
	<div class="main">
		<div class="price-off">
            <div class="slideBox">
                <ul id="slideBox">
                    <li><img src="images/product/banner_1.jpg"/></li>
                    <li><img src="images/product/banner_2.jpg"/></li>
                    <li><img src="images/product/banner_3.jpg"/></li>
                    <li><img src="images/product/banner_4.jpg"/></li>
                </ul>
            </div>
			<h2>商品列表</h2>
			<ul class="product clearfix">
			<c:forEach items="${pageBean.pageList}" var="product">
				<li>
					<dl>
						<dt><a href="ProductServlet?pro=findProduct&epId=${product.epId }"  target="_self"><img src="images/product/${product.fileName }" /></a></dt>
						<dd class="title"><a href="ProductServlet?pro=findProduct&epId=${product.epId }" target="_self">${product.epName}</a></dd>
						<dd class="price">￥${product.price}</dd>
					</dl>
				</li>
			</c:forEach>
			</ul>
			<div style="text-align:right;">
					当前页/总页数：${pageBean.pageNo}/${pageBean.totalPages}
					<a href="IndexServlet?pageNo=1">首页</a>
					<a href="IndexServlet?pageNo=${pageBean.pageNo-1}">上一页</a>
					<a href="IndexServlet?pageNo=${pageBean.pageNo+1}">下一页</a>
					<a href="IndexServlet?pageNo=${pageBean.totalPages}">尾页</a>
			</div>
		</div>
		<div class="side">			
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<ul>
					
					<c:forEach items="${newList}" var="news">
						<li><a href="news-view.jsp"  target="_self">${news.title}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="spacer clear"></div>
    </div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

