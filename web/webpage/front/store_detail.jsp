<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- context path -->
<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.src.js"></script>
<%--<script type="text/javascript" src="js/amcharts.js"></script>--%>
<script type="text/javascript" src="js/amcharts1.js"></script>
<script type="text/javascript"  src="js/serial.js"></script>
<script type="text/javascript"  src="js/light.js"></script>
<script type="text/javascript"  src="js/buildChart.js" charset="UTF-8"></script>
<script src="js/sort.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/xcConfirm.css" type="text/css">
<script type="text/javascript" src="js/xcConfirm.js"></script>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css" type="text/css"></link>
<%--<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">--%>
<style>
	body{ padding: 0 100px;}
	#head{width: auto;height: 100px;font-size: large;margin-top: 1px;}
	#title{float: left;    font-size: 28px; margin: 40px 20px;}
	#back{float: right;margin: 40px 20px;}
	#btnPanel{ clear: both;border-top: solid 1px #ccc;padding-top: 20px;overflow-y:hidden;min-height: 10000px;}
	#lastUp,#lastDown,#zero{width: 90px;margin-right: 100px}
	#DSR{width: 100px;margin-right: 100px;}
	#lastWeek{width: 120px;margin-right: 100px}
	#execption{width: 110px;}
	.btn{background-color: #fff;}
	.title{
		background-color: #f5f5f5;
		height: 20px;
		line-height: 20px;
		padding: 10px;
		border-top-left-radius: 10px;
		border-top-right-radius: 10px;
	}
	table{
		text-align: center;
	}
	.radius{
		border: solid 1px #EAEAEA;
		border-radius: 10px;
		margin-top: 50px;
	}
	#tb2 tr th{
		height: 40px;
		line-height: 40px;
		border: solid 1px #EAEAEA;
	}
	#tb2 tr td{
		height: 40px;
		line-height: 24px;
		min-width: 100px;
		max-width: 150px;
		border: solid 1px #EAEAEA;
	}
	#chartdiv{
		width: 100%;
		height: 450px;
	}
	.scroll{
		overflow-y: auto;
		height: 600px;
	}
	.floatDiv{
		position:fixed;top:0;width:100%;height:100px;line-height:100px;background-color:white;z-index: 1000;
	}
</style>
<script>

	function back(){location.href="frontController.do?store";}

	//	chart("chartdiv","销售额","访客数","销售额汇总","访客数汇总","","orders","person");
	chart("chartdiv","销售额","访客数","销售额汇总","访客数汇总",${chartdivDataProvider},"orders","person");

	function makeOrders(value){
		var chartData = eval(value);

		var chart = new AmCharts.AmSerialChart();
		chart.dataProvider = chartData;
		chart.categoryField = "date";
		var graph = new AmCharts.AmGraph();
		graph.valueField = "order";
		graph.type = "column";
		chart.addGraph(graph);

		var catAxis = chart.categoryAxis;
		catAxis.gridCount = chartData.length;
		var catAxis = chart.categoryAxis;
		catAxis.gridCount = chartData.length;
		catAxis.labelRotation = 90;
		chart.marginTop = 15;
		chart.marginLeft = 55;
		chart.marginRight = 15;
		chart.marginBottom = 80;

		graph.lineAlpha = 0;
		graph.fillAlphas = 0.8;
		chart.angle = 30;
		chart.depth3D = 15;
		chart.write('chartContainer');
	}

	function lightTo(id){
		var scroll_offset = $("#"+id).offset(); //得到pos这个div层的offset，包含两个值，top和left
		$("body,html").animate({
			scrollTop:scroll_offset.top-135 //让body的scrollTop等于pos的top，就实现了滚动
		},0);
	}

	function goto(url){
		location.href = url;
	}

	function myScroll()
	{
//前边是获取chrome等一般浏览器 如果获取不到就是ie了 就用ie的办法获取
		var x=document.body.scrollTop||document.documentElement.scrollTop;
		/*var timer=setInterval(function(){
		 x=x-1000;
		 if(x<1000)
		 {
		 x=0;
		 window.scrollTo(x,x);
		 clearInterval(timer);
		 }
		 window.scrollTo(x,x);
		 },"250");*/
		window.scrollTo(0,0);
	}




	window.onload=function(){
		var oDiv = document.getElementById("float"),
				H = 0,
				Y = oDiv;
		while (Y) {H += Y.offsetTop; Y = Y.offsetParent;}
		window.onscroll = function()
		{
			var s = document.body.scrollTop || document.documentElement.scrollTop;
			if(s>H) {
				oDiv.className = 'floatDiv';
//				Y.style = "position:fixed;top:0;width:100%;height:100px;line-height:100px;background-color:white;";
			} else {
				oDiv.className = '';
			}
		}
	}
</script>
<body>
<div id="head">
	<div id="title">${store.nickName}整店分析</div>

	<div id="back">
		<button type="button" class="btn btn-default btn-sm" onclick="back()">
			<span class="glyphicon glyphicon-share-alt"></span> 返回整店分析首页
		</button>
	</div>
</div>
<jsp:useBean id="nowDate" class="java.util.Date"/>

<div id="btnPanel" style="overflow-y: hidden">
	<div id="float">
		<button class="btn btn-default" id='lastUp' onclick="lightTo('up')">最近2周上架产品</button>
		<button class="btn btn-default" id='lastDown' onclick="lightTo('down')">最近2周下架产品</button>
		<button class="btn btn-default" id='zero' onclick="lightTo('lastZero')">最近30天销量为0</button>
		<button class="btn btn-default" id='DSR'  onclick="lightTo('top10')">最近一周销量前十</button>
		<button class="btn btn-default" id='lastWeek' onclick="lightTo('newOrders')">最近一周新出单的产品</button>
		<button class="btn btn-default" id='execption' onclick="lightTo('twoWeekChange')">最近两周产品变动</button>
	</div>
	<div style="height:auto;" class="radius">
		<div class="title">店铺最近60天表现</div>
		<div id="chartdiv"></div>
	</div>
	<div style="height:auto;" class="radius" id="top10">
		<div class="title">最近一周销量前十的listing</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>SKU</th>
					<th>名称</th>
					<th>总订单数</th>
					<th>周销售额</th>
					<th>周曝光量</th>
					<th>周浏览量</th>
					<th>周点击率</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${commodityAnalysisEntites}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.c_Id}');">${row.c_Id}</a></td>
						<td>${row.storeProduct.sku}</td>
						<td>${row.storeProduct.productName}</td>
						<td>${row.placingOrders}</td>
						<td>${row.payMoney}</td>
						<td>${row.searchExposure}</td>
						<td>${row.viewNum}</td>
						<td>${row.searchHits}</td>
						<td><fmt:parseDate var="someDate" value="${row.storeProduct.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.storeProduct.type==0}">下架</c:if>
							<c:if test="${row.storeProduct.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.time}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="changes">
		<div class="title">最近一周本店listing变动情况(共${changesTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>产品名称</th>
					<th>日期</th>
					<th>类型</th>
					<th>变化前</th>
					<th>变化后</th>
				</tr>
				<c:forEach var="row" items="${changes}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.pId}');">${row.pId}</a></td>
						<td>${row.pName}</td>
						<td><fmt:formatDate value="${row.time}" pattern="yyyy年MM月dd日" /></td>
						<td>${row.type}</td>
						<td><c:if test="${row.type=='主图'}"><img src="${row.old}" width="120" height="120"></c:if><c:if test="${row.type!='主图'}">${row.old}</c:if></td>
						<td><c:if test="${row.type=='主图'}"><img src="${row.now}"  width="120" height="120"></c:if><c:if test="${row.type!='主图'}">${row.now}</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="up">
		<div class="title">最近两周上架产品(共${storeProductUpTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${storeProductUp}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="down">
		<div class="title">最近两周下架产品(共${storeProductDownTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${storeProductDown}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="lastZero">
		<div class="title">最近30天销量表现 月销量为0(共${lastZeroTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${lastZero}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="lastZeroToThirty">
		<div class="title">最近30天销量表现 月销量为0~30(共${lastZeroToThirtyTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>月销量</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${lastZeroToThirty}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.productOrders}</td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="lastThirtyTo100">
		<div class="title">最近30天销量表现 月销量为31~100(共${lastThirtyTo100Total}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>月销量</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${lastThirtyTo100}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.productOrders}</td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="last100To300">
		<div class="title">最近30天销量表现 月销量为101~300(共${last100To300Total}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>月销量</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${last100To300}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.productOrders}</td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="last300">
		<div class="title">最近30天销量表现 月销量为301以上(共${last300Total}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>月销量</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${last300}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.productOrders}</td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="newOrders">
		<div class="title">最近一周新出单的产品(共${newOrdersTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>SKU</th>
					<th>名称</th>
					<th>产品状态</th>
					<th>更新日期</th>
				</tr>
				<c:forEach var="row" items="${newOrders}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.productId}');">${row.productId}</a></td>
						<td>${row.sku}</td>
						<td>${row.productName}</td>
						<td><fmt:parseDate var="someDate" value="${row.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 							<c:set var="interval" value="${nowDate.time - someDate.time}"/><c:if test="${row.type==0}">下架</c:if> 							<c:if test="${row.type==1}">上架</c:if><br/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/>天</td>
						<td>${row.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="height:auto;" class="radius" id="twoWeekChange">
		<div class="title">最近两周产品变动(共${changesTotal}条)</div>
		<div class="scroll">
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>产品ID</th>
					<th>产品名称</th>
					<th>日期</th>
					<th>类型</th>
					<th>变化前</th>
					<th>变化后</th>
				</tr>
				<c:forEach var="row" items="${changes}" varStatus="j">
					<tr>
						<td><a href="#" onclick="goto('frontController.do?storeProDetail&id=${store.id}&pid=${row.pId}');">${row.pId}</a></td>
						<td>${row.pName}</td>
						<td><fmt:formatDate value="${row.time}" pattern="yyyy年MM月dd日" /></td>
						<td>${row.type}</td>
						<td><c:if test="${row.type=='主图'}"><img src="${row.old}" width="120" height="120"></c:if><c:if test="${row.type!='主图'}">${row.old}</c:if></td>
						<td><c:if test="${row.type=='主图'}"><img src="${row.now}"  width="120" height="120"></c:if><c:if test="${row.type!='主图'}">${row.now}</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>
</div>

<div style="height:1000px">
	<div
			style="position:fixed;right:10px;bottom:50px;width:20px;background-color:red;cursor:hand;"
			onclick="myScroll()">返回顶端</div>
</div>
</body>




