<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- context path -->
<t:base type="jquery,easyui"></t:base>
<%--<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.src.js"></script>--%>
<%--
<link rel="stylesheet" href="css/xcConfirm.css" type="text/css">
<script type="text/javascript" src="js/xcConfirm.js"></script>--%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css" type="text/css"></link>
<script type="text/javascript" src="js/amcharts1.js"></script>
<script type="text/javascript"  src="js/serial.js"></script>
<script type="text/javascript"  src="js/light.js"></script>

<script type="text/javascript"  src="js/buildChart.js" charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
	body{ padding: 0 100px;min-height: 200px;}
	#head{width: auto;height: 100px;font-size: large;margin-top: 1px;}
	#title{float: left; font-size: 28px; margin: 40px 20px;line-height: 40px;}
	#back{float: right;margin: 40px 20px;margin-top: -60px;}
	/*#proInfo{border-top: solid 1px #ccc;}*/
	#proPanel{ clear: both;border-top: solid 1px #ccc;padding-top: 20px;min-height: 2500px;overflow-y: hidden;}
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
		line-height: 40px;
		border: solid 1px #EAEAEA;
	}
	.left{
		float: left;
	}
	.right{
		float: right;
	}
	#info{
		margin-top: 20px;
		width: 25%;
		height: 500px;
		border: solid 1px #EAEAEA;
		border-radius: 10px;
		margin-top: 50px;
	}
	#info img{
		border-radius: 10px;
	}
	#info p{
		padding: 10px;
	}
	#info button{
		margin-left: 40px;
	}
	#lastShow{
		width: 70%;
	}
	#change{
		margin-top: 50px;
	}
	#lastShow1,#lastShow2,#lastShow3,#lastShow4{
		width: 45%;
		height: 500px;
		margin-right: 50px;
	}
	.probg{
		background-image: url(img/probg.png);
		width: 150px;
		height: 150px;
		margin-left: 80px;
	}
	#proInfo{
		color: #31B2D2;
		text-align: center;
	}
	#proInfo .t{
		margin: 50px;
		font-size: 20px;
		line-height: 26px;
	}
	#proInfo p{
		font-size: 30px;
		font-weight: bolder;
	}
</style>
<style>
	#chartdiv,#chartdiv1,#chartdiv2,#chartdiv3,#chartdiv4 {
		width: 100%;
		height: 450px;
	}
</style>
<script>


	function back(){location.href="frontController.do?storeDetail&id=${store.id}";}
	function goTo(url){
		location.href=url;
	}

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



	chart("chartdiv","销售额","访客数","销售额汇总","访客数汇总",${chartdivDataProvider},"orders","person");
	chart("chartdiv1","曝光量","浏览量","","",${chartdiv1DataProvider},"exposure","visitors");
	chart4("chartdiv2","点击率","百分比","","",${chartdiv2DataProvider},"","hits");
	chart_t("chartdiv3","数量",${chartdiv3DataProvider});
	chart4("chartdiv4","数量","百分比","老买家商品页访客数","老买家浏览-下单转化率",${chartdiv4DataProvider},"visitors","rate");


</script>
<body>
	<div id="head">
		<div id="title">【${store.nickName}企业店】${product.productName}  ${product.productId}</div>

		<div id="back">
			<button type="button" class="btn btn-default btn-sm" onclick="back()">
				<span class="glyphicon glyphicon-share-alt"></span> 返回店铺
			</button>
		</div>
	</div>
	<%--<div style="clear: both;"/>--%>
	<jsp:useBean id="nowDate" class="java.util.Date"/>
	<div id="proPanel" style="overflow-y: hidden">
		<div id="proInfo">
			<div class="probg left"><div class="t">销量<p>${storeProduct.productOrders}</p></div></div>
			<div class="probg left"><div class="t">价格<br/><span>${storeProduct.productPrice}</span></div></div>
			<div class="probg left"><div class="t">DSR<p>${storeProduct.productFaceback}</p></div></div>
			<div class="probg left"><div style="margin: 50px 20px;line-height: 34px;font-size: 20px;">在线天数<p><fmt:parseDate var="someDate" value="${storeProduct.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/><c:set var="interval" value="${nowDate.time - someDate.time}"/><fmt:formatNumber value="${interval/1000/24/3600}" pattern="#0"/></p></div></div>
			<div class="probg left"><div style="margin: 50px 20px;line-height: 34px;font-size: 20px;">俄团次数<p>${ruNum}</p></div></div>
		</div>
		<div style="clear: both"></div>
		<div>
			<div class="left" id="info">
				<img src="${storeProduct.productImg}" style="width: 300px;height: 300px;padding: 10px;"/>
				<p>${productInfo.platformName}</p>
				<button type="button" class="btn btn-default btn-sm" onclick="goTo('http://www.aliexpress.com/item/ss-ss/${product.productId}.html')">
					<span class="glyphicon glyphicon-share-alt"></span> 前台链接
				</button>
				<button type="button" class="btn btn-default btn-sm" onclick="goTo('frontController.do?proDetail&pid=${product.productId}')">
					<span class="glyphicon glyphicon-share-alt"></span> 每日销量
				</button>
			</div>
			<div class="radius right" id="lastShow">
				<div class="title">单品最近90天表现</div>
				<div id="chartdiv"></div>
			</div>
		</div>
		<div style="clear: both;"></div>

		<div class="radius left" id="lastShow1">
			<div class="title">单品最近90天表现详情1</div>
			<div id="chartdiv1"></div>
		</div>

		<div class="radius left" id="lastShow2">
			<div class="title">单品最近90天表现详情2</div>
			<div id="chartdiv2"></div>
		</div>

		<div class="radius left" id="lastShow3">
			<div class="title">单品最近90天表现详情3</div>
			<div id="chartdiv3"></div>
		</div>

		<div class="radius left" id="lastShow4">
			<div class="title">单品最近90天表现详情4</div>
			<div id="chartdiv4"></div>
		</div>

		<div style="clear: both;"></div>

		<div class="radius" id="change">
			<div class="title">最近180天当前listing变动情况(共2条)</div>
			<table border="0" style="width:100%;" cellpadding="0" cellspacing="0" id="tb2">
				<tr>
					<th>日期</th>
					<th>类型</th>
					<th>变化前</th>
					<th>变化后</th>
				</tr>
				<c:forEach var="row" items="${changes}" varStatus="j">
						<tr>
							<td><fmt:formatDate value="${row.time}" pattern="yyyy年MM月dd日" /> </td>
							<td>${row.type}</td>
							<td><c:if test="${row.type=='主图'}"><img src="${row.old}" width="120" height="120"></c:if><c:if test="${row.type!='主图'}">${row.old}</c:if></td>
							<td><c:if test="${row.type=='主图'}"><img src="${row.now}"  width="120" height="120"></c:if><c:if test="${row.type!='主图'}">${row.now}</c:if></td>
						</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>




