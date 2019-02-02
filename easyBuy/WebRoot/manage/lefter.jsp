<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="UserServlet?pro=findUserPage">用户管理</a></dd>
			  <dt>商品信息</dt>
				<dd><em><a href="productClass-add.jsp">新增</a></em><a href="productClass.jsp">分类管理</a></dd>
				<dd><em><a href="product-add.jsp">新增</a></em><a href="ProductServlet?pro=proPageMan">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="OrderServlet?pro=findOrder">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="CommentServlet?pro=findComPage">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.jsp">新增</a></em><a href="news.jsp">新闻管理</a></dd>
			</dl>
		</div>
	</div>
