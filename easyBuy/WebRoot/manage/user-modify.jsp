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
	您现在的位置：<a href="index.html">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<%@include file="lefter.jsp" %>
	<div class="main">
		<h2>修改用户</h2>
		<div class="manage">
			<form action="UserServlet?pro=updateUser&userId=${user.userId}" method="post">
				<table class="form">
					<tr>
						<td class="field">用户名(*)：</td>
						<td><input type="text" class="text" name="userName" value="${user.userName }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">真实姓名(*)：</td>
						<td><input type="text" class="text" name="name" value="${user.nickName }" /></td>
					</tr>
					<tr>
						<td class="field">登录密码(*)：</td>
						<td><input type="text" class="text" name="passWord" value="${user.userPwd }" /></td>
					</tr>
                    <tr>
						<td class="field">确认密码(*)：</td>
						<td><input type="text" class="text" name="passWord" value="${user.userPwd }" /></td>
					</tr>
					<tr>
						<td class="field">性别(*)：</td>
						<c:if test="${user.userSex==1 }">
							<td><input type="radio" name="sex" value="1" checked="checked"/>男 
							<input type="radio" name="sex" value="0" />女</td>
						</c:if>
						<c:if test="${user.userSex==0 }">
							<td><input type="radio" name="sex" value="1" />男 
							<input type="radio" name="sex" value="0" checked="checked"/>女</td>
						</c:if>
						
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<input type="text" class="text" name="birthday" value="${user.birthday }" />
							<!-- <select name="birthyear">
								<option value="2000">2000</option>
								<option value="1999" selected="selected">1999</option>
							</select>年
							<select name="birthmonth">
								<option value="12">12</option>
								<option value="11" selected="selected">11</option>
							</select>月
							<select name="birthday">
								<option value="2">2</option>
								<option value="1" selected="selected">1</option>
							</select>日 -->
						</td>
					</tr>
					<tr>
						<td class="field">手机(*)：</td>
						<td><input type="text" class="text" name="mobile" value="${user.mobile }" /></td>
					</tr>
					<tr>
						<td class="field">地址(*)：</td>
						<td><input type="text" class="text" name="address" value="${user.address }" /></td>
					</tr>					
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

