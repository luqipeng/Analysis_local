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
				<a class="brand" href="">我关注的店铺</a>
				<div id="add">
					<button id='detailBtn' class="btn btn-default" onclick='openuploadwin("Excel导入", "commodityAnalysisController.do?upload", "commodityAnalysisList")'>导入周销量</button>
					<button type="button" class="btn btn-default btn-sm" onclick="add('添加店铺','storeController.do?goAdd','storeList',null,null)">
						<span class="glyphicon glyphicon-plus"></span> 添加店铺
					</button>
				</div>
			</div>

		</div>
	</div>

	<div>
		<table id="tb">
			<thead>
				<tr style="border-bottom: 2px solid #000;font-size: 14px;font-weight: bolder;" id="oper">
					<td id="nickName">店铺别名<span class="glyphicon glyphicon-sort-by-attributes"></span></td>
					<td id="storeName">店铺名称<span class="glyphicon glyphicon-sort"></span></td>
					<td id="level">等级<span class="glyphicon glyphicon-sort"></span></td>
					<td id="storeFaceback">好评率<span class="glyphicon glyphicon-sort"></span></td>
					<td id="storeFacebackper">好评分<span class="glyphicon glyphicon-sort"></span></td>
					<td id="totalNum">产品数<span class="glyphicon glyphicon-sort"></span></td>
					<td id="collectNUm">收藏量<span class="glyphicon glyphicon-sort"></span></td>
					<td id="createTime">创建时间<span class="glyphicon glyphicon-sort"></span></td>
					<td id="threeFeedback">三个月好评数<span class="glyphicon glyphicon-sort"></span></td>
					<td width="210px">操作</td>
				</tr>
			</thead>

			<tbody id="mainBody">

			</tbody>
		</table>

	</div>





<%--<div id="cnzz_stat" style="display:none">
</div>--%>

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
		//alert($(this).children("td:eq(1)").text());
	});


	function init() {

		$.ajax({
			url: "frontController.do?storeList&field=id,storeId,nickName,storeName,level,storeFaceback,storeFacebackper,totalNum,collectNum,createTime,threeFeedback",
			success: function (data) {
				data = eval(data);
				var str = "";
				for(var i= 0,o;i<data.rows.length;i++){
					o = data.rows[i];
					str+="<tr id='"+o.storeId+"'><td>"+ o.nickName+"</td><td><a href=\"https://unice.aliexpress.com/store/"+ o.storeId+"\" target='_blank'>"+ o.storeName+"</a></td><td>"+ o.level+"</td>" +
					"<td>"+ o.storeFacebackper+"</td><td>"+ o.storeFaceback+"</td><td>"+ o.totalNum+"</td><td>"+ o.collectNum+"</td><td>"+ o.createTime+"</td><td>"+ o.threeFeedback+"</td>" +
					"<td><button class=\"btn btn-default\" onclick='delObj(\"storeController.do?doDel&id="+o.id+"\",\"storeList\");'>删除</button><button id='delBtn' class=\"btn btn-default\" onclick='createwindow(\"编辑\",\"storeController.do?goUpdate&id="+ o.id+"\",null,null);'>修改</button>" +
					"<button id='detailBtn' class=\"btn btn-default\" onclick='goTo(\"frontController.do?storeDetail&id="+o.id+"\")'>查看</button></td></tr>";
				}
				$("#mainBody").html(str);
				new TableSorter("tb");
				/*var doms = document.getElementsByTagName('tr');
				for(i=0;i<doms.length;i++)
				{
					doms[i].onmouseover = function()
					{
						this.style.backgroundColor = "#CCC";
					}
					doms[i].onmouseout = function()
					{
						this.style.backgroundColor = "";
					}
					doms[i].onmousedown = function(){
						if(this.id != ''){
							location.href="frontController.do?storeDetail&storeId="+this.id;
						}
					}
				}*/
			}
		});


	}

    init();

</script>
</body></html>