<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
        	<form method="get" action="${pageContext.request.contextPath }/pro/providerlist.html">
				<input name="method" value="query" type="hidden">
				<span>供应商编码：</span>
				<input name="queryProCode" type="text" value="${queryProCode }">
				
				<span>供应商名称：</span>
				<input name="queryProName" type="text" value="${queryProName }">
				
				<input value="查 询" type="submit" id="searchbutton">
				<a href="${pageContext.request.contextPath }/pro/proadd.html">添加供应商</a>
			</form>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="20%">供应商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach var="provider" items="${pageBean.pageList }" varStatus="status">
				<tr>
					<td>
					<span>${provider.proCode }</span>
					</td>
					<td>
					<span>${provider.name }</span>
					</td>
					<td>
					<span>${provider.proContact}</span>
					</td>
					<td>
					<span>${provider.proPhone}</span>
					</td>
					<td>
					<span>${provider.proFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewProvider" href="javascript:;" proid=${provider.id } proname=${provider.name }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyProvider" href="javascript:;" proid=${provider.id } proname=${provider.name }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteProvider" href="javascript:;" proid=${provider.id } proname=${provider.name }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
        </table>
	<p>
		  		[当前页/总页数：${pageBean.pageNo }/${pageBean.totalPages}]
		  		<a href="${pageContext.request.contextPath }/pro/providerlist.html?pageNo=1&queryProCode=${queryProCode}&queryProName=${queryProName}">首页</a>
		  		<c:if test="${pageBean.pageNo!=1 }">
		  		<a href="${pageContext.request.contextPath }/pro/providerlist.html?pageNo=${pageBean.pageNo-1 }&queryProCode=${queryProCode}&queryProName=${queryProName}">上一页</a>
		  		</c:if>
		  		<c:if test="${pageBean.totalPages!=pageBean.pageNo&&pageBean.totalPages!=0 }">
		  		<a href="${pageContext.request.contextPath }/pro/providerlist.html?pageNo=${pageBean.pageNo+1 }&queryProCode=${queryProCode}&queryProName=${queryProName}">下一页</a>
		  		</c:if>
		  		<a href="${pageContext.request.contextPath }/pro/providerlist.html?pageNo=${pageBean.totalPages}&queryProCode=${queryProCode}&queryProName=${queryProName}">尾页</a>
	</p>
		<div class="providerAdd">
		  		<div>
		  			<label>供应商编码</label>
		  			<input type="text" id="proCode" value="" readonly="readonly">
		  		</div>
		  		<div>
		  			<label>供应商名称</label>
		  			<input type="text" id="proName" value="" readonly="readonly">
		  		</div>
		  		<div>
		  			<label>联系人</label>
		  			<input type="text" id="proContact" value="" readonly="readonly">
		  		</div>
		  		<div>
		  			<label>联系电话</label>
		  			<input type="text" id="proPhone" value="" readonly="readonly">
		  		</div>
		  		<div>
		  			<label>传真</label>
		  			<input type="text" id="proFax" value="" readonly="readonly">
		  		</div>
		  		<div>
		  			<label>描述</label>
		  			<input type="text" id="proDesc" value="" readonly="readonly">
		  		</div>
		  		
		  		
		</div>
    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>

<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/providerlist.js"></script>
