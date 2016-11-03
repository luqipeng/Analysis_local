<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title><t:mutiLang langKey="jeect.platform"/></title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>

<link rel="stylesheet" href="css/6b94a81a5fe8.css" type="text/css">
<link rel="stylesheet" href="css/Style.css" type="text/css">
<script src="js/hm.js"></script>
<script type="text/javascript" async="" src="js/ga.js"></script>
<script type="text/javascript" src="js/4300e8d50956.js"></script>
<script type="text/javascript" src="js/jqPaginator.min.js"></script>
<script src="js/wb.js" type="text/javascript" charset="utf-8"></script>
<script charset="UTF-8" src="/js/query"></script>
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css" type="text/css"></link>
	<%--<link href="css/boot.css" rel="stylesheet">--%>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="js/client.js" language="JavaScript"></script>
	<%--<script src="js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>--%>
	<script src="js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/xcConfirm.css"/>
<script type="text/javascript">
if ( !(window.self === window.top) ) {
    $('head').append('<link rel="stylesheet" href="css/hotsale_renren_insite.css">');    
           
}
</script>
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
  height: 380px;
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
		width: 300px;
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
#remove {
	float: right;
}
#edit {
	float: right;
	margin-right: 10px;
}
/**,*:before, *:after {*/
	 /*-webkit-box-sizing: null;*/
	/*-moz-box-sizing: null;*/
	 /*box-sizing: null;*/
/*}*/
.glyphicon-remove:before {
	content: "\e014";
}
.list-group-item {
	width: 100%;
}
ul,ol{
	margin: 0;
}
.xcConfirm .popBox .txtBox p select{font-size: 15px;}
.panel-heading+.list-group .list-group-item:first-child {
	border-top-width: 0;
	border-bottom-width: 0;
}
</style>

</head>
<body id="homepage_body">

	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">

				<a class="brand" href="">我的工作组</a>
				<div class="nav-collapse">
					</br>
					<div style="clear:both;"></div>
					<div style="float:left;" id="ws">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">普通关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(1)" id='add'></span>
							</div>

							<ul class="list-group" id="care">

							</ul>
						</div>

						<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">一级关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(2)" id='add'></span>
							</div>
							<ul class="list-group" id="care1">

							</ul>
						</div>

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">二级关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(3)" id='add'></span>
							</div>
							<ul class="list-group" id="care2">

							</ul>
						</div>

						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">三级关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(4)" id='add'></span>
							</div>
							<ul class="list-group" id="care3">

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="other">

	</div>

	<!--共享关注-->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">

				<a class="brand" href="">共享工作组</a>
				<div class="nav-collapse">
					</br>
					<div style="clear:both;"></div>
					<div style="float:left;" id="ws">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">普通关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(5)" id='add'></span>
							</div>

							<ul class="list-group" id="care00">

							</ul>
						</div>

						<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">一级关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(6)" id='add'></span>
							</div>
							<ul class="list-group" id="care10">

							</ul>
						</div>

						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">二级关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(7)" id='add'></span>
							</div>
							<ul class="list-group" id="care20">

							</ul>
						</div>

						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">三级关注</h3>
								<span class="glyphicon glyphicon-plus" onclick="addWorkspace(8)" id='add'></span>
							</div>
							<ul class="list-group" id="care30">

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<link rel="stylesheet" href="css/5bd0a22d3d80.css" type="text/css">

<script type="text/javascript" src="js/794ebf34faae.js"></script>




<%--<div id="cnzz_stat" style="display:none">
</div>--%>

<script>
	var xhr;
	var start;
	var workspace;//定义一个工作组变量来存储
	var proList;//定义一个产品列表变量来存储
	function goPD(id){
		location.href="frontController.do?pd&id="+id;
	}
	function removeWs(id){
		var txt=  "确认是否删除？";
		var option = {
			title: "",
			btn: parseInt("确认是否删除？",2),
			onOk: function(){
				$.ajax({
					url: "frontController.do?removeWs&id="+id,
					success: function (data) {
						data=eval("("+data+")");
						if(data.success){
							document.getElementById("ws"+id).style.display = "none";
						}else{
							window.wxc.xcConfirm(data.msg, window.wxc.xcConfirm.typeEnum.warning);
						}
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

	function addWorkspace(levelId){
		window.wxc.xcConfirm("工作组名称", window.wxc.xcConfirm.typeEnum.input,{
			onOk:function(v){
				$.ajax({
					url: "frontController.do?addWorkspace&name="+encodeURI(encodeURI(v))+"&levelId="+levelId,
					success: function (data) {
						init();
					},
					error: function (e) {

					}
				});
			}
		});
	}
	function editWs(id,name){
		var option = {
			title: "修改工作组名称",
			btn: parseInt("工作组：",2),
			onOk: function(v){
				$.ajax({
					url: "frontController.do?editWorkspace&name="+encodeURI(encodeURI(v))+"&wid="+id,
					success: function (data) {
						data=eval("("+data+")");
						if(data.success){
							init();
						}else{
							window.wxc.xcConfirm(data.msg, window.wxc.xcConfirm.typeEnum.warning);
						}
					},
					error: function (e) {

					}
				});
			}
		}
		window.wxc.xcConfirm("旧的："+name, window.wxc.xcConfirm.typeEnum.input,option);

	}
	function changeWs(id,type){
		var option = {
			title: "移动工作组",
			data:type,
			btn: parseInt("工作组：",2),
			onOk: function(v){
				debugger;
				$.ajax({
					url: "frontController.do?editWorkspace&levelId="+v+"&wid="+id,
					success: function (data) {
						data=eval("("+data+")");
						if(data.success){
							init();
						}else{
							window.wxc.xcConfirm(data.msg, window.wxc.xcConfirm.typeEnum.warning);
						}
					},
					error: function (e) {

					}
				});
			}
		}
		window.wxc.xcConfirm("", window.wxc.xcConfirm.typeEnum.selectLevel,option);
	}
    function init() {

		//初始化工作组
		$.ajax({
			url: "frontController.do?ws_list",
			success: function (data) {

				data = eval(data);
				$('#care').html("");
				$('#care1').html("");
				$('#care2').html("");
				$('#care3').html("");
				$('#care00').html("");
				$('#care10').html("");
				$('#care20').html("");
				$('#care30').html("");
				var str_left = "";
				var care="",care1="",care2="",care3="";
				var care00="",care10="",care20="",care30="";
				if(data.length>2){
					//是admin 管理员
					var other = "";
					$('#other').html("");
					for(var j = 0,w;j<data.length;j++){
						w = data[j];
						if(w.userName == "admin"){
							workspace = w.mwList;
							for (var i = 0, o; i < w.mwList.length; i++) {
								o = w.mwList[i];
								switch(o.levelId){
									case "1":
										care += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
										"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"')\" id='edit'></span></li>";
										break;
									case "2":
										care1 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
										"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"')\" id='edit'></span></li>";
										break;
									case "3":
										care2 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
										"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"')\" id='edit'></span></li>";
										break;
									case "4":
										care3 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
										"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"')\" id='edit'></span></li>";
										break;
								}
							}
						}else if(w.mwList.length > 0 && w.userName != ""){
							//开始其他用户的工作组展示
							other += "<div class=\"navbar\"><div class=\"navbar-inner\"><div class=\"container\"><a class=\"brand\">"+ w.userName+"的工作组</a><div class=\"nav-collapse\">" +
							"<div style=\"float:left;\" id=\"ws\"><div class=\"panel panel-default\"><div class=\"panel-heading\"><h3 class=\"panel-title\">普通关注</h3></div>" +
							"<ul class=\"list-group\">";
							for (var i = 0, o; i < w.mwList.length; i++) {
								o = w.mwList[i];
								if(o.levelId == "1"){
									other += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span></li>";
								}
							}
							other += "</ul></div><div class=\"panel panel-danger\"><div class=\"panel-heading\"><h3 class=\"panel-title\">一级关注</h3></div><ul class=\"list-group\">";
							for (var i = 0, o; i < w.mwList.length; i++) {
								o = w.mwList[i];
								if(o.levelId == "2"){
									other += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span></li>";
								}
							}
							other += "</ul></div><div class=\"panel panel-info\"><div class=\"panel-heading\"><h3 class=\"panel-title\">二级关注</h3></div><ul class=\"list-group\">";
							for (var i = 0, o; i < w.mwList.length; i++) {
								o = w.mwList[i];
								if(o.levelId == "3"){
									other += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span></li>";
								}
							}
							other += "</ul></div><div class=\"panel panel-success\"><div class=\"panel-heading\"><h3 class=\"panel-title\">三级关注</h3></div><ul class=\"list-group\">";
							for (var i = 0, o; i < w.mwList.length; i++) {
								o = w.mwList[i];
								if(o.levelId == "4"){
									other += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span></li>";
								}
							}
							other += "</ul></div></div></div></div></div></div>";
						}
					}
					$('#other').html(other);
				}else{
					for(var j = 0,w;j<data.length;j++){
						w = data[j];
						//另一个是共享的
						if(w.userName != ""){
							workspace = w.mwList;
							for (var i = 0, o; i < w.mwList.length; i++) {
								o = w.mwList[i];
								switch(o.levelId){
									case "1":
										care += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
										break;
									case "2":
										care1 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
										break;
									case "3":
										care2 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
										break;
									case "4":
										care3 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
										break;
								}
							}
						}
					}
				}
				//共享
				for(var j = 0,w;j<data.length;j++){
					w = data[j];
					if(w.userName == ""){
						for (var i = 0, o; i < w.mwList.length; i++) {
							o = w.mwList[i];
							switch(o.levelId){
								case "5":
									care00 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
									"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"','s')\" id='edit'></span></li>";
									break;
								case "6":
									care10 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
									"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"','s')\" id='edit'></span></li>";
									break;
								case "7":
									care20 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
									"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"','s')\" id='edit'></span></li>";
									break;
								case "8":
									care30 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span>" +
									"<span class=\"	glyphicon glyphicon-pencil\" onclick=\"editWs('"+ o.id+"','"+ o.workName+"')\" id='edit'></span><span class=\"glyphicon glyphicon-transfer\" onclick=\"changeWs('"+ o.id+"','s')\" id='edit'></span></li>";
									break;
							}
						}
					}
				}

				$('#care').append(care);
				$('#care1').append(care1);
				$('#care2').append(care2);
				$('#care3').append(care3);
				$('#care00').append(care00);
				$('#care10').append(care10);
				$('#care20').append(care20);
				$('#care30').append(care30);
			},
			error: function (e) {
			}
		});

    }

    init();


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
</script>
</body></html>