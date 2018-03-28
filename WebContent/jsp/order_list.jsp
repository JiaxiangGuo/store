<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

			<!-- 
			静态包含
 		-->
		<%@include file="/jsp/head.jsp" %>

		</nav>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${orderpage.list }" var="order">
							<tbody>
								<tr class="success">
									<th colspan="5">订单编号:${order.oid } </th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<c:forEach items="${order.items }" var="i">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${i.product.pimage }" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${i.product.pname }</a>
										</td>
										<td width="20%">
											￥${i.product.shop_price }
										</td>
										<td width="10%">
											${i.count }
										</td>
										<td width="15%">
											<span class="subtotal">￥${i.subtotal }</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<!-- 上一页 -->
				<c:if test="${orderpage.currentPage == 1}">
					<li class="disabled"><a href="javaScript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
				<c:if test="${orderpage.currentPage != 1}">
					<li><a href="${pageContext.request.contextPath}/order?method=findAllPage&currentPage=${orderpage.currentPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				</c:if>
			
				<!-- 展示所有页码  -->
				<c:forEach begin="${orderpage.currentPage-5>0?orderpage.currentPage-5:1 }" end="${orderpage.currentPage+4>orderpage.totalPage?orderpage.totalPage:orderpage.currentPage+4 }" var="n">
					<!-- 判断是否是当前页 -->
					<c:if test="${orderpage.currentPage==n }">
						<li class="active"><a href="javascript:void(0)">${n }</a></li>
					</c:if>
					<c:if test="${orderpage.currentPage!=n }">
						<li><a href="${pageContext.request.contextPath}/order?method=findAllPage&currentPage=${n}">${n }</a></li>
					</c:if>
				</c:forEach>

				<!-- 下一页-->
				<c:if test="${orderpage.totalPage != orderpage.currentPage}">
					<li>
						<a href="${pageContext.request.contextPath}/order?method=findAllPage&currentPage=${orderpage.currentPage+1}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
				<c:if test="${orderpage.totalPage == orderpage.currentPage }">
					<li class="disabled">
						<a href="javaScript:void(0)" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
			</ul>
		</div>
		<!-- 分页结束=======================        -->


		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有
		</div>
	</body>

</html>