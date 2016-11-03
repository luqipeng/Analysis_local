<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title><t:mutiLang langKey="jeect.platform"/></title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>

<link rel="stylesheet" href="css/6b94a81a5fe8.css" type="text/css">

<script charset="UTF-8" src="/js/query"></script>
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css" type="text/css"></link>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/sort.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/xcConfirm.css"/>
<style>
	body{text-align: center;}
	.navbar {
		z-index: 500;
		margin-top: 1px;
	}
table{width: 99%;border: solid 2px #ccc}
tr{height: 40px;border-bottom-width: 2px;
	border-bottom-style: solid;
	border-bottom-color: rgb(c, c, c);}
	td{
		border-left:2px solid #ccc ;

	}
	#add{float:right;margin-right: 50px;}
	#delBtn,#detailBtn{margin-left: 10px;}
	#tb .glyphicon{
		float: right;
		margin-right: 20px;
		font-size: 18px;
	}
	.glyphicon-sort-by-attributes{
		color: #888;
	}
	.glyphicon-sort{
		color: #ccc;
	}
</style>

</head>
<body id="homepage_body">

	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="">团购汇总统计</a>
				<div id="add">
					<button type="button" class="btn btn-default btn-sm" onclick="add('添加活动','mGroupController.do?goAdd','storeList',null,null)">
						<span class="glyphicon glyphicon-plus"></span> 添加团购活动
					</button>
				</div>
			</div>

		</div>
	</div>

	<div>
		<table id="tb">
			<thead>
				<tr style="border-bottom: 2px solid #000;font-size: 14px;font-weight: bolder;" id="oper">
					<td id="storeName">店铺名称<span class="glyphicon glyphicon-sort-by-attributes"></span></td>
					<td id="productId">产品ID<span class="glyphicon glyphicon-sort"></span></td>
					<td id="productName">产品名称<span class="glyphicon glyphicon-sort"></span></td>
					<td id="activityName">活动名称<span class="glyphicon glyphicon-sort"></span></td>
					<td id="time">活动时间<span class="glyphicon glyphicon-sort"></span></td>
					<td id="historyLow">历史最低<span class="glyphicon glyphicon-sort"></span></td>
					<td id="price">报名价格<span class="glyphicon glyphicon-sort"></span></td>
					<td id="num">报名数量<span class="glyphicon glyphicon-sort"></span></td>
					<td id="estimated">采购预估<span class="glyphicon glyphicon-sort"></span></td>
					<td id="actual">实际采购<span class="glyphicon glyphicon-sort"></span></td>
					<td width="200px">操作</td>
				</tr>
			</thead>

			<tbody id="mainBody">

			</tbody>
		</table>

	</div>


<script>
	var xhr;
	var start;
	var workspace;//定义一个工作组变量来存储
	var proList;//定义一个产品列表变量来存储

	function goTo(url){
		location.href=url;
	}

	$("#oper td").each(function (i) {
		switch(i){

		}
	});


	function init() {

		$.ajax({
			url: "frontController.do?groupList&field=id,storeName,productId,url,productName,activityName,timeStart,timeEnd,historyLow,price,num,estimated,actual",
			success: function (data) {
				data = eval(data);
				var str = "";
				for(var i= 0,o;i<data.rows.length;i++){
					o = data.rows[i];
					str+="<tr id='"+o.id+"'><td>"+ o.storeName+"</td><td><a href=\""+ o.url+"\" target='_blank'>"+ o.productId+"</a></td><td>"+ o.productName+"</td><td>"+ o.activityName+"</td><td>"+ FormatDate(o.timeStart) + " - " + FormatDate(o.timeEnd) + "</td><td>"+ o.historyLow+"</td><td>"+ o.price+"</td><td>"+ o.num+"</td><td>"+ o.estimated+"</td><td>"+ o.actual+"</td>" +
					"<td><button class=\"btn btn-default\" onclick='delObj(\"mGroupController.do?doDel&id="+o.id+"\",\"storeList\");'>删除</button><button id='delBtn' class=\"btn btn-default\" onclick='createwindow(\"编辑\",\"mGroupController.do?goUpdate&id="+ o.id+"\",null,null);'>修改</button>" +
					"</td></tr>";
				}
				$("#mainBody").html(str);
				new TableSorter("tb");
			}
		});


	}

	function FormatDate (strTime) {
		var date = new Date(strTime);
		return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}

    init();

</script>
</body></html>