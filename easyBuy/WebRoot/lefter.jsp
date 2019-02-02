<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<c:forEach items="${map}" var="pCate">
					<dt>${pCate.key.epcName}</dt>
					<c:forEach items="${pCate.value}" var="cCate">
						<dd><a href="ProductServlet?pro=proList&epcId=${cCate.epcId }">${cCate.epcName}</a></dd>
					</c:forEach>
				</c:forEach>
			</dl>
		</div>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			
			<dl class="clearfix">
				<c:forEach items="${proList }" var="product">
					<dt><a href="ProductServlet?pro=findProduct&epId=${product.epId }"  target="_self"><img src="images/product/${product.fileName }" width=60px/></a></dt>
					<dd><a href="ProductServlet?pro=findProduct&epId=${product.epId }" target="_self">${product.epName}</a></dd>
				</c:forEach>
		  	</dl>
			
			<!-- <dl class="clearfix">
				<dt><img src="images/product/10_small.jpg" /></dt>
				<dd><a href="product-view.html"  target="_self">利仁2018M福满堂电饼铛 好用实惠</a><a href="product-view.html"></a></dd>
				<dt>&nbsp;</dt>
				<dd>&nbsp;</dd>
		  </dl> -->
	  </div>
	</div>
