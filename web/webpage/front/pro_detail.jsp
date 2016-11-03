<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- context path -->
<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.src.js"></script>
<%--<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/modules/exporting.src.js"></script>--%>
<script type="text/javascript" src="js/amcharts.js"></script>
<link rel="stylesheet" href="css/xcConfirm.css" type="text/css">
<script type="text/javascript" src="js/xcConfirm.js"></script>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<style>
	#left{
		float: left;
		width: 20%;
		padding: 50px 0 10px 100px;
		margin: 50px 100px;
		font-size: 24px;
		FONT-FAMILY: "宋体";
		border:solid #b59090 4px;
		-moz-border-radius: 15px;
		-webkit-border-radius: 15px;
		border-radius:15px;
	}
	.left{
		float: left;
		width: 28%;
		border: solid #ccc 4px;
		font-size: 24px;
		margin: 20px;
		padding: 5px;
		-moz-border-radius: 15px;
		-webkit-border-radius: 15px;
		border-radius:15px;
	}
	th{
		width: 100px;
		text-align: center;
	}
	.more{
		float: right;
		padding: 0 20px;;
	}
	#main{
		float: right;
		margin-right: 100px;;
		/*width: 60%;*/
		FONT-FAMILY: "宋体";
	}
	.btn-default {
		color: #333;
		background-color: #fff;
		border-color: #ccc;
	}
	a{
		color:blue;
		font-size: 24px;;
	}
	a:hover {
		color: #000;
	}
	.btn {
		display: inline-block;
		padding: 6px 12px;
		margin-bottom: 0;
		font-size: 14px;
		font-weight: normal;
		line-height: 1.428571429;
		text-align: center;
		white-space: nowrap;
		vertical-align: middle;
		cursor: pointer;
		background-image: none;
		border: 1px solid transparent;
		border-radius: 4px;
		-webkit-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		-o-user-select: none;
		user-select: none;
	}
	.btn-sm {
		padding: 5px 10px;
		font-size: 12px;
		line-height: 1.5;
		border-radius: 3px;
		border: solid #ccc 1px;
	}

	/**弹出列表**/
	.xcConfirm .popBox {
		left: 40%;
		top: 30%;
		width: 870px;
		height: 600px;
	}
	.xcConfirm .popBox .ttBox {
		height: 30px;
		line-height: 30px;
	}
	.popBox td img{
		width: 80px;
		height: 80px;
	}
	.popBox td{
		width:300px;
		padding: 10px;
	}
	.popBox tr{
		height: 60px;
	}
	.xcConfirm .popBox .txtBox {
		margin: 0px 0px;
		height: 540px;
		overflow-y: auto;
	}
</style>
<script type="text/javascript">
	var list = null;
	window.onload=function(){
		$.ajax({
			type : "get",
			url : "frontController.do?customerLevel&pid=${mProduct.pid}",
			success : function(jsondata) {
				MakeChart(jsondata);
			}
		});
		$.ajax({
			type : "get",
			url : "frontController.do?changeList&pid=${mProduct.pid}",
			success : function(data) {
				list = data;
			}
		});
		//MakeChart(json);
		$.ajax({
			type : "get",
			url : "frontController.do?orders30&pid=${mProduct.pid}&wid=${wid}",
			success : function(jsondata) {
				makeOrders(jsondata);
			}
		});
		document.getElementById("pricemore").onclick = function(){
			var txt=  "";
			var option = {
				title: "价格变更列表",
				btn: null,
				type:1,
				data:eval(list)
			}
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.view,option);
		};
		document.getElementById("imgmore").onclick = function(){
			var txt=  "";
			var option = {
				title: "主图变更列表",
				type:2,
				btn: null,
				data:eval(list)
			}
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.view,option);
		};
		document.getElementById("titlemore").onclick = function(){
			var txt=  "";
			var option = {
				title: "主题变更列表",
				type:3,
				btn: null,
				data:eval(list)
			}
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.view,option);
		};
	}

	//根据json数据生成饼状图，并将饼状图显示到div中
	function MakeChart(value) {
		chartData = eval(value);
		//饼状图
		chart = new AmCharts.AmPieChart();
		chart.dataProvider = chartData;
		//标题数据
		chart.titleField = "name";
		//值数据
		chart.valueField = "value";
		//边框线颜色
		chart.outlineColor = "#fff";
		//边框线的透明度
		chart.outlineAlpha = .8;
		//边框线的狂宽度
		chart.outlineThickness = 1;
		chart.depth3D = 20;
		chart.angle = 30;
		chart.write("mainDiv");
	}

	function refresh(){
		$.ajax({
			type : "get",
			dataType: 'text',
			url : "http://120.26.228.123:8080/jeecg/mProductCatchController.do?startCatchNow&productId=${pid}&wId=${wid}",
			success : function(jsondata) {
				console.log(jsondata);
				$.ajax({
					type: "post",
					url:"frontController.do?updateMProducts&json="+encodeURI(jsondata.replace('&','')),
					success:function(){
						window.location.reload();
					}
				});
			}
		});
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
</script>
<div style="margin: 20px 0 0 100px;">
	产品ID:<a href="http://www.aliexpress.com/item/${mProduct.url}" target="_blank">${pid}</a><br>
	已采集天数：${mProduct.days}
</div>

<div id="left">
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="refresh();">
		 更新最新数据
	</a>
	</br>
	</br>
	总销量：${mProduct.count}
	</br>
	昨天销量：${mProduct.day1}
	</br>
	3天销量：${mProduct.day3}
	</br>
	7天销量：${mProduct.day7}
	</br>
	折后价格：${mProduct.price}
	</br>
	最低价（原价）：${mProduct.minPrice}
	</br>
	最高价（原价）：${mProduct.maxPrice}
</div>
<div id="Main" style="min-height: 400px;">
	<div style="min-height: 400px;"><img src="${mProduct.img}" width="400px"/></div>
</div>

<div style="clear: both"/>
<!--发生变化-->
<div>
	<div class="left" style=" height: 320px;overflow: hidden;">
		<div style="height: 30px;">
			<h1 style="float: left">价格变化</h1>
			<span class="more" id="pricemore">更多</span>
		</div>
		<table style="float: inherit;min-height: 20px;width: 100%">
			<tbody>
			<tr>
				<th>时间</th>
				<th>变动前</th>
				<th>变动后</th>
			</tr>
			<c:forEach var="row" items="${mChange}" varStatus="j">
				<c:choose>
					<c:when test="${row.type==1}">
						<tr>
							<th><c:out value="${row.date}"/></th>
							<th><c:out value="${row.old}"/></th>
							<th><c:out value="${row.now}"/></th>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="left" style=" height: 320px;overflow: hidden;">
		<div style="height: 30px;">
			<h1 style="float: left">图片变化</h1>
			<span class="more" id="imgmore">更多</span>
		</div>
		<table style="float: inherit;min-height: 20px;width: 100%">
			<tbody>
			<tr>
				<th>时间</th>
				<th>变动前</th>
				<th>变动后</th>
			</tr>
			<c:forEach var="row" items="${mChange}" varStatus="k">
				<c:choose>
					<c:when test="${row.type==2}">
						<tr>
							<th><c:out value="${row.date}"/></th>
							<th><img src="${row.old}" width="100px"></th>
							<th><img src="${row.now}" width="100px"></th>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="left" style=" height: 320px;overflow: hidden;">
		<div style="height: 30px;">
			<h1 style="float: left">主题变化</h1>
			<span class="more" id="titlemore">更多</span>
		</div>
		<table style="float: inherit;min-height: 20px;width: 100%">
			<tbody>
			<tr>
				<th>时间</th>
				<th>变动前</th>
				<th>变动后</th>
			</tr>
			<c:forEach var="row" items="${mChange}" varStatus="j">
				<c:choose>
					<c:when test="${row.type==3}">
						<tr>
							<th><c:out value="${row.date}"/></th>
							<th><c:out value="${row.old}"/></th>
							<th><c:out value="${row.now}"/></th>
						</tr>
					</c:when>
				</c:choose>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<br>
<div style="clear: both"/>
<div style="height:auto;">
	<h1>销量变化</h1>
	<div id="chartContainer" style="width: 90%; height: 300px;"></div>
</div>
<%--<span id="containerline" style="float: left; width: 30%; height: 60%"></span>
<span id="containerCol" style="float: left; width: 38%; height: 60%"></span>--%>
<%--<span id="containerPie" style="width: 30%; height: 60%;display: none;"></span>--%>

<div style="width: 50%;float: right">
	<div style="text-align: center;font-size: 20px;">买家各级别分布图</div>
	<div id="mainDiv" style="min-height: 400px;"> </div>
</div>
<div style="width: 40%; height: 280px;float: right;margin-top: 50px;"><t:datagrid name="studentStatisticList" title="国家占比" actionUrl="frontController.do?attCountry&pid=${mProduct.pid}" idField="id" fit="true">
	<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="国家" field="country" width="130"></t:dgCol>
	<t:dgCol title="订单数" field="orders" width="130"></t:dgCol>
	<t:dgCol title="common.proportion" field="rate" width="130"></t:dgCol>
</t:datagrid></div>

