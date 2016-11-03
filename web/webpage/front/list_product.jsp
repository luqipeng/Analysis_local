<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title><t:mutiLang langKey="jeect.platform"/></title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>

<link rel="stylesheet" href="css/6b94a81a5fe8.css" type="text/css">
<link rel="stylesheet" href="css/Style.css" type="text/css">

<script type="text/javascript" src="js/jqPaginator.min.js"></script>
<script charset="UTF-8" src="/js/query"></script>
<link rel="stylesheet" href="/plug-in/bootstrap/css/bootstrap.css" type="text/css"></link>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/xcConfirm.css" type="text/css">
	<script type="text/javascript" src="js/xcConfirm.js"></script>
<style>
#main_block{
	TEXT-ALIGN: center;
}
#category{
	margin-left:auto;
	margin-right:auto;
}
.navbar-inner{
	min-height: 120px;
	background-color: #454545;
	/*background-image: -webkit-gradient(linear,0 0,0 100%,from(#454545),to(#454545));*/
}
ul li{
	float:left;
	margin-right: 20px;
	font-size:14px;
	color:#EE7600;
}
.pull-right {
	float: right; 
	background-color: #6E6E6E;
}
.navbar {
  overflow: visible;
  margin-bottom: 0px;
}
#homepage_body .thumbnails a {
  width: 250px;
  height: 380px;
  color: #666;
  padding: 14px;
  font-weight: bold;
  font-size: 12px;
  margin-left: auto;
  margin-right: auto;
}
 .sale_amount1 li{
  margin-bottom: 10px;
  position: relative;
  margin-left: auto;
  margin-right: 50px;
  width: 25%;
}
.price{
  color:red;
}
.thumbnails1 a {
  width: 250px;
  /*height: 380px;*/
  color: #666;
  padding: 14px;
  font-weight: bold;
  font-size: 12px;
  margin-left: auto;
  margin-right: auto;
}
#tu_date a{
	color:#FF7F00;
}
.count{
	color:red;
}
#homepage_body form#search_in_category_and_all_form {
  float: none;
  padding-top: 10px;
	width: 100%;
}
input {
  color: #ee00;
  margin-bottom: 10px;
}
	.panel{
		float: left;
		margin-left: 50px;
		padding: 0;
		width: 200px;
	}
	.span12{
		width: 100%;
	}
	.container {
		width: 100%;
	}
.navbar .btn, .navbar .btn-group {
	margin-top: 5px;
	margin-left: 5px;
}
#add {
	float: right;
	margin-top: -20px;
}
tr.alter{
	background-color:#f5fafe;
}
td {padding:4px 6px 6px; border-bottom:1px solid #c6d5e1; border-right:1px solid #c6d5e1}
table{width: 220px;}
.glyphicon-remove:before {
	content: "\e014";
}
a:hover {text-decoration: none;}
body{
	margin:0;
	padding:0;
	background:url(../bg.gif) 0 0 repeat #f7f5f5;
	color:#333;
	font-family:Cambria, Georgia, serif;
	font-size:15px;
	overflow-x:hidden;
	overflow-y: auto;
}
#care{
	overflow-x: hidden;
}
.closeimg
{
	position : absolute;
	/*top : -25px;*/
	/*right : -16px;*/
	width : 50px;
	height : 50px;
	display:none;
	float: right;
}
#container
{
	position:relative;
	margin:0 auto 50px;
	padding-top: 10px;
	padding-bottom: 10px;
	margin-left: auto;
	margin-right: auto;

}



.grid{
	position: relative;
	min-height:100px;
	height:435px;
	padding: 15px;
	background:#fff;
	margin:8px;
	margin-left: 30px;
	font-size:12px;
	float:left;
	box-shadow: 0 1px 3px rgba(34,25,25,0.4);
	-moz-box-shadow: 0 1px 3px rgba(34,25,25,0.4);
	-webkit-box-shadow: 0 1px 3px rgba(34,25,25,0.4);

	-webkit-transition: top 1s ease, left 1s ease;
	-moz-transition: top 1s ease, left 1s ease;
	-o-transition: top 1s ease, left 1s ease;
	-ms-transition: top 1s ease, left 1s ease;
}

.border
{
	box-shadow: 2px 2px 3px  rgba(34,25,25,0.4) ;
	-moz-box-shadow: 2px 2px 3px  rgba(34,25,25,0.4) ;
	-webkit-box-shadow: 2px 2px 3px rgba(34,25,25,0.4) ;
	cursor:pointer;
}


.grid .closeimg
{
	position : absolute;
	top : -25px;
	right : -16px;
	width : 50px;
	height : 50px;
	display:none;
}

.grid strong {
	border-bottom:1px solid #ccc;
	margin:10px 0;
	display:block;
	padding:0 0 5px;
	font-size:17px;
}
.grid .meta{
	text-align:right;
	color:#777;
	font-style:italic;
}
/*.grid .imgholder img{
	max-width:100%;
	background:#ccc;
	display:block;
}*/
	#closeBtn{
		background-image: url('img/close.png');
		width: 50px;
		height: 50px;
		top: -15px;
		position: absolute;
		margin-left: 200px;
	}
	.order{
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		overflow-y: none;
	}
	.form-horizontal .form-group{
		margin-left: 200px;
	}
	fieldset{
		padding: .35em .625em .75em;
		margin: 0 2px;
		border: 1px solid #c0c0c0;
	}
	img{
		max-height: 220px;;
	}
</style>

</head>
<body id="homepage_body">

<div class="navbar">
<div class="navbar-inner">
<div class="container">
    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </a>

    <a class="brand" href="">${mWorkspace.workName}(${count})</a>
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="addProduct();">
		<span class="glyphicon glyphicon-plus"></span> 添加产品
	</a>
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="addKey();">
		<span class="glyphicon glyphicon-plus"></span> 添加关键词
	</a>
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="back();">
		<span class="glyphicon glyphicon-share-alt"></span> 返回工作组
	</a>
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="backProduct();">
		<span class="glyphicon glyphicon-share-alt"></span> 返回产品组
	</a>
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="refresh();">
		<span class="glyphicon glyphicon-repeat"></span> 更新产品组
	</a>
	<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="refreshPage();">
		<span class="glyphicon glyphicon-refresh"></span> 刷新
	</a>
    <div class="nav-collapse">


		</br>
		<div style="clear:both;"></div>

		<div style="float:left;" id="ws">
				123
		</div>

<%-- <p class="tagline" style="float:right;"> <a class="brand" href="loginController.do?login">首页</a><a class="brand" href="frontController.do?day">今日销量排行榜</a>&lt;%&ndash;<a class="brand" href="frontController.do?week">周销量排行榜</a>&ndash;%&gt;<span class="brand">业务员:${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> <a class="brand" href="loginController.do?admin">系统设置</a>

 </p>--%>


    </div>
</div>
</div>
</div>

<div id="care">
	<div class="order" style="overflow-y: none;">
		<form class="form-horizontal" role="form" id="search">
			<fieldset>
				<div class="form-group">
					<label  class="col-sm-2 control-label">
						选择排序目标：
					</label>
					<div class="col-sm-2">

							<select class="form-control" id="order">
								<option value="viewed">变更</option>
								<option value="count">总销量</option>
								<option value="day1">1天销量</option>
								<option value="day3">3天销量</option>
								<option value="day7">7天销量</option>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-2 control-label">
						选择排序方式：
					</label>
					<div class="col-sm-2">
						<select class="form-control" id="asc">
							<option value="asc">升序</option>
							<option value="desc">降序</option>
						</select>
					</div>
				</div>
			</fieldset>
		</form>
	</div>


	<div class="container">

		<div class="span12" id="container">


		</div>

	</div>

</div>

<div id="searchPanel" style="display: none;">
	<div>
		<form id="search_in_category_and_all_form" action="#" class="pull-right">

			<div style="margin-right:100px" id="scope">
				关键字：<input id="keyword" name="keyword" type="text" style="width: 150px;height: 30px;line-height: 30px;" class="form-control" value="${keyword}">
				<%--<a class="btn btn-primary" href="#" onclick="search(1)">提交</a>--%>
				<input type="button" class="btn btn-primary" onclick="searchByKeyword(0)" value="搜索">
				<p style="float: right;margin-left: 100px;">耗时：<i id="time">0</i>毫秒</p>
			</div>

		</form>

	</div>

	<div class="container">


		<div class="span12" id="main_block">


		</div>

		<%--<div style="text-align: center;">
			<ul id="page" class="pagination">

			</ul>
		</div>--%>
	</div>
</div>

<div style="text-align: center;">
	<ul id="page" class="pagination">

	</ul>
</div>

<script>
	$(document).ready(function(){
		$('#search').bind('input propertychange', function(obj) {
			search(1);
		});
	});
	var xhr;
	var start;
	var productList;
	var proList;//定义一个产品列表变量来存储
	var current = 1;//分页  1:表示产品组；2:表示搜索
	var ws;//定义工作组列表
	function back(){location.href="frontController.do?ws";}
	function backProduct(){
		document.getElementById("care").style.display = "block";
		document.getElementById("searchPanel").style.display = "none";
		current = 1;
	}
	//刷新界面
	function refreshPage(){
		search(1);
	}
	function refresh(){
		$.ajax({
			type : "get",
			timeout : 1000000,
			dataType: 'text',
			url : "http://120.26.228.123:8080/jeecg/mProductCatchController.do?startCatchByWID&wId=${mWorkspace.id}",
			success : function(jsondata) {
				$.ajax({
					type: "post",
					data:{json:jsondata},
					dataType:"json",
					url:"frontController.do?updateMProducts",
					success:function(){
						window.location.reload();
					}
				});
			}
		});
	}
	function changeSearchText(keyword){
		document.getElementById("care").style.display = "none";
		document.getElementById("searchPanel").style.display = "block";
		document.getElementById("keyword").value=keyword;
		current = 2;
		searchByKeyword(0);

		//location.href="frontController.do?ws&keyword="+keyword;
	}
	function cancel(pid,wid){
		var txt=  "确认是否取消关注？";
		var option = {
			title: "",
			btn: parseInt("确认是否取消关注？",2),
			onOk: function(){
				$.ajax({
					url: "frontController.do?delProduct&pid="+pid+"&wid="+wid,
					success: function (data) {
						var joinOnclick = "";
						for (var i = 0, l; i < proList.length; i++) {
							l = proList[i];
							if(l.productId == pid){
								joinOnclick = "join('"+l.productId+"');";
							}
						}
						$("#"+pid).find("#cancel").html("加入工作组");
						$("#"+pid).find("#cancel").attr("onclick",joinOnclick);
						$("#"+pid).find("#cancel").attr("id","join");
					},
					error: function (e) {

					}
				});
			}
		}
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,option);
	}
	function join(pid){
		var m;
		for (var i = 0,l; i < proList.length; i++) {
			l = proList[i];
			if(l.productId == pid){
				m = l;
			}
		}
		var p = encodeURIComponent(m.productUrl.split("?")[0]);
		var txt=  "工作组：";
		var option = {
			title: "加入关注",
			data:ws,
			btn: parseInt("工作组：",2),
			onOk: function(o){
				$.ajax({
					url: "frontController.do?addProduct&pid="+pid+"&wid="+o+"&name="+ encodeURIComponent(m.productName)+"&img="+ m.productImg+"&price="+ m.productPrice+"&sName="+ m.storeName+"&url="+p,
					success: function (data) {
						$("#"+pid).find("#join").html("取消关注");
						$("#"+pid).find("#join").attr("onclick","cancel('"+pid+"','"+o+"')");
						$("#"+pid).find("#join").attr("id","cancel");
					},
					error: function (e) {

					}
				});
			}
		}
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.select,option);
	}
	function link(id){
		$("#time"+ id).html("已查看");
		window.open("frontController.do?proDetail&pid="+id+"&wid=${mWorkspace.id}");
	}
	//添加关键词
	function addKey(){
		window.wxc.xcConfirm("关键字", window.wxc.xcConfirm.typeEnum.input,{
			onOk:function(v){
				$.ajax({
					url: "frontController.do?addKeyword&id=${mWorkspace.id}&keyword="+encodeURI(encodeURI(v)),
					success: function (data) {
						init();
					},
					error: function (e) {

					}
				});
			}
		});
	}
	//删除关键字
	function dileteKey(id){
		var txt=  "确认是否删除？";
		var option = {
			title: "",
			btn: parseInt("确认是否删除？",2),
			onOk: function(){
				$.ajax({
					url: "frontController.do?delKeyword&id="+id,
					success: function (data) {
						init();
					},
					error: function (e) {

					}
				});
			},
			onCancel:function(){

			},
			onClose:function(){

			}
		}
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,option);
	}
	function delProduct(id){
		var txt=  "确认是否删除？";
		var option = {
			title: "",
			btn: parseInt("确认是否删除？",2),
			onOk: function(){
				$.ajax({
					url: "frontController.do?delProduct&wid=${mWorkspace.id}&pid="+id,
					success: function (data) {
						document.getElementById("container").removeChild(document.getElementById(id));
						//search(0);
					},
					error: function (e) {

					}
				});
			},
			onCancel:function(){

			},
			onClose:function(){

			}
		}
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,option);
	}
	function addProduct(){
		window.wxc.xcConfirm("产品ID", window.wxc.xcConfirm.typeEnum.input,{
			onOk:function(v){
				$.ajax({
					url: "frontController.do?addProduct&pid="+v+"&wid=${mWorkspace.id}",
					success: function (data) {
						debugger;
						data=eval("("+data+")");
						if(data.success){
							search(0);
						}else{
							window.wxc.xcConfirm(data.msg, window.wxc.xcConfirm.typeEnum.warning);
						}
					},
					error: function (e) {

					}
				});
			}
		});
	}
    function init() {

		$('#search_in_category_and_all_form').bind('input propertychange', function(obj) {			
				search(1);
		});
		//初始化关键字
		$.ajax({
			url: "frontController.do?ws_key_list&wid=${mWorkspace.id}",
			success: function (data) {
				data = eval(data);
				$('#ws').html("");
				var str_left = "";
				for (var i = 0, o; i < data.length; i++) {
					o = data[i];
					str_left += "<button type=\"button\" class=\"btn btn-default\" onclick=\"changeSearchText('"+ o.keyword+"')\">"+ o.keyword+"</button><span class=\"glyphicon glyphicon-remove\" onclick=\"dileteKey("+ o.id+")\"></span>";

				}
				$('#ws').append(str_left);
			},
			error: function (e) {
			}
		});
		//初始化工作空间
		$.ajax({
			url: "frontController.do?ws_list",
			success: function (data) {
				data = eval(data);
				if(data.length>2){
					for(var j = 0,w;j<data.length;j++){
						w = data[j];
						if(w.userName == "admin") {
							ws = w.mwList;
						}
					}
				}else{
					for(var j = 0,w;j<data.length;j++) {
						w = data[j];
						//另一个是共享的
						if (w.userName != "") {
							ws = w.mwList;
						}
					}
				}
			},
			error: function (e) {
			}
		});
    }
	function fillData(data) {
		data = eval(data);
		productList = data;
		$('#container').html("");
		var str = "";

        for (var i = 0, l, tr, title,imgurl; i < data.length; i++) {
				l = data[i];
			imgurl = "img/loading.gif";
			if(l.img!=""){
				imgurl = l.img;
			}
			str +="<div class=\"grid\" id=\""+l.pid+"\">";
			str += "<div id='closeBtn' onclick='delProduct("+l.pid+")'></div>";
			str += "<div><img width=\"220\" src=\""+imgurl+"\" alt=\""+l.pid+"\" onclick='link("+l.pid+")'>";
			str += "<table>" +
					"<tr class='alter'><td>昨日："+ l.day1+"</td><td>7天："+ l.day7+"</td></tr>" +
					"<tr><td>3天："+ l.day3+"</td><td>总："+ l.count+"</td></tr>" +
					"<tr class='alter'><td>产品ID:</td><td>"+ l.pid+"</td></tr>" +
					"<tr><td>售价:</td><td>"+ l.price+"</td></tr>" +
					"<tr><td>店铺名:</td><td value='"+l.sname+"'>"+ cutstr(l.sname,14)+"</td></tr>" +
					"<tr><td>最近变动:</td><td id='time"+ l.pid+"'></td></tr>" +
					"</table>";
			str += "</div></div>";

				
			}
		$('#container').append(str);
		var end=new Date().getTime();
		$('#time').html("");
		$('#time').append(end-start);

    }
	

	function search(page) {
		if(xhr!=null){
			xhr.abort();
		}
		var order = document.getElementById("order").value;
		var asc = document.getElementById("asc").value;
		var urlstr = "frontController.do?wspo_list&wid=${mWorkspace.id}&page=" + page+"&orderName="+order+"&orderAD="+asc;

		$('#container').html("正在拼命加载中......");
		xhr = $.ajax({
			url: urlstr,
			success: function (data) {
				data = eval(data);
				$('#page').jqPaginator('option', {
					totalCounts: data[0].total
				});
				fillData(data[0].results);
			},
			error: function (e) {

			}
		});
	}

	function searchByKeyword(page) {
		if(xhr!=null){
			xhr.abort();
		}

		var keyword = document.getElementById("keyword").value;
		var urlstr = "mWorkspaceController.do?buildHtml&keyword="+keyword+"&page=" + page;
		if(keyword==""){
			return;
		}
		start=new Date().getTime();
		$('#main_block').html("正在拼命加载中......");
		xhr = $.ajax({
			url: urlstr,
			success: function (data) {

				$('#page').jqPaginator('option', {
					totalCounts: data.length
				});
				fillDataToSearch(data);
			},
			error: function (e) {

			}
		});
	}

	function fillDataToSearch(data) {
		data = eval(data);
		proList = data;
//		$('#total').html(data.total);
		$('#main_block').html("");
		var str = "<div id=\"category\">";
		if(data.length>4){
			str += "<ul class=\"thumbnails\">";

		}else{
			str += "<ul class=\"thumbnails1\">";
		}
		for (var i = 0, l, tr, title; i < data.length; i++) {
			l = data[i];
			if(l.exist=="1"){
				str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\"javascript:void(0);\" class=\"thumbnail\">" +
				"<span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.productImg+"\" alt=\""+l.productId+"\">&nbsp;&nbsp;&nbsp;价格:US $"+l.productPrice+"<br>" +
				"<span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;"+cutstr(l.productName,80)+"" +
				"<br><span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;销量："+l.productOrdersAll+"" +
				"<br><span id=\"cancel\" class=\"label label-info\" style=\"cursor:hand\" onclick=\"cancel('"+l.productId+"','"+ l.wid+"');\">取消关注</span></a></li>";
			}else{
				str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\"javascript:void(0);\" class=\"thumbnail\">" +
				"<span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.productImg+"\" alt=\""+l.productId+"\">&nbsp;&nbsp;&nbsp;价格:US $"+l.productPrice+"<br>" +
				"<span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;"+cutstr(l.productName,80)+"" +
				"<br><span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;销量："+l.productOrdersAll+"" +
				"<br><span id=\"join\" class=\"label label-info\" style=\"cursor:hand\" onclick=\"join('"+l.productId+"');\">加入工作组</span></a></li>";
//				onclick=\"join('"+l.productId+"','"+l.productName+"','"+l.productImg+"');\"
			}


		}
		str += "</ul></div>";
		$('#main_block').append(str);
		var end=new Date().getTime();
		$('#time').html("");
		$('#time').append(end-start);
	}

		$('#page').jqPaginator({
			totalCounts: 10,
			pageSize: 100,
			visiblePages: 10,
			currentPage: 1,
			onPageChange: function (num, type) {
				if(current==1){
					search(num);
				}else{
					searchByKeyword(num);
				}

			}
		});
    init();
	/**/

	var GetLength = function (str) {
		///<summary>获得字符串实际长度，中文2，英文1</summary>
		///<param name="str">要获得长度的字符串</param>
		var realLength = 0, len = str.length, charCode = -1;
		for (var i = 0; i < len; i++) {
			charCode = str.charCodeAt(i);
			if (charCode >= 0 && charCode <= 128) realLength += 1;
			else realLength += 2;
		}
		return realLength;
	};

	//js截取字符串，中英文都能用
	//如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。
	//字符串，长度

	/**
	 * js截取字符串，中英文都能用
	 * @param str：需要截取的字符串
	 * @param len: 需要截取的长度
	 */
	function cutstr(str, len) {
		var str_length = 0;
		var str_len = 0;
		str_cut = new String();
		str_len = str.length;
		for (var i = 0; i < str_len; i++) {
			a = str.charAt(i);
			str_length++;
			if (escape(a).length > 4) {
				//中文字符的长度经编码之后大于4
				str_length++;
			}
			str_cut = str_cut.concat(a);
			if (str_length >= len) {
				str_cut = str_cut.concat("...");
				return str_cut;
			}
		}
		//如果给定字符串小于指定长度，则返回源字符串；
		if (str_length < len) {
			return str;
		}
	}
	var interval = 1000;
	/**
	* 显示时间倒计时
	* @param year
	* @param month
	* @param day
	* @param divname
	* @constructor
	 */
	function ShowCountDown(date,pid)
	{
		var now = new Date();
		if(date==null||date.time==null){
			return;
		}
		//var endDate = new Date(2016, date.month, date.date, date.hours, date.minutes, date.seconds);
		var leftTime=date.time-now.getTime()+172800000;
		var leftsecond = parseInt(leftTime/1000);
		var day1=Math.floor(leftsecond/(60*60*24));
		var hour=Math.floor((leftsecond-day1*24*60*60)/3600);
		var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60);
		var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
		if($("#time"+pid)!=null){
			$("#time"+pid).html(day1+"天"+hour+"小时"+minute+"分"+second+"秒");
		}

	}
	window.setInterval(function(){
		if(productList!=null&&current==1){
			for (var i = 0, l; i < productList.length; i++) {
				l = productList[i];
				if(l.viewed == 0){
					ShowCountDown(l.lastDate, l.pid);
				}else{
					$("#time"+ l.pid).html("没有变动记录");
				}
			}
		}

	}, interval);
</script>

</body></html>