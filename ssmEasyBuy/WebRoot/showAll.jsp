<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table border="1" width="800">
    	<tr>
    		<th>编号</th>
    		<th>主题</th>
    		<th>内容</th>
    		<th>日起</th>
    	</tr>
    	<c:forEach items="${newList }" var="news">
    	<tr>
    		<td>${news.enId }</td>
    		<td>${news.title }</td>
    		<td>${news.content }</td>
    		<td>${news.createTime }</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
