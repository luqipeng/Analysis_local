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
<script charset="UTF-8" src="js/query"></script>
<script src="js/client.js" language="JavaScript"></script>
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
	background-image: -webkit-gradient(linear,0 0,0 100%,from(#454545),to(#454545));
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
}
input {
  color: #ee00;
  margin-bottom: 10px;
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
    
    <a class="brand" href="">俄罗斯团购采集</a>
    
    <div class="nav-collapse">
		 <p class="tagline" style="float:left;"> 
			点击下方日期快速查看当天结束俄团情况
		</p>  
		</br>
		<div style="clear:both;"></div>
		<div style="float:left;margin-left: 160px;max-width:800px;">
			<ul id="tu_date"></ul>
		</div>
      
<%-- <p class="tagline" style="float:right;"> <a class="brand" href="loginController.do?login">首页</a><a class="brand" href="frontController.do?day">今日销量排行榜</a>&lt;%&ndash;<a class="brand" href="frontController.do?week">周销量排行榜</a>&ndash;%&gt;<span class="brand">业务员:${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> <a class="brand" href="loginController.do?admin">系统设置</a>
 
 </p>--%>

        
    </div>
</div>
</div>
</div>

<div class="container">

<div id="title_block">
<div class="pull-right" id="title_right_block">

</div>

</div>




<form id="search_in_category_and_all_form" action="#" class="pull-right">
	
	<select style="width: 104px" width="100" id="time">
			<option value="">---范围---</option>
			<option value="now">当前</option>
			<option value="all">所有</option>
		</select>
    俄团结束日期:<input id="endTime" name="endTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()" onchange="search(1)">
	<select style="width: 104px" width="100" id="paixu">
			<option value="">---排序---</option>
			<option value="up">升序</option>
			<option value="down">降序</option>
	</select>
	<select style="width: 104px" width="100" id="condition">
			<option value="">---条件---</option>
			<option value="count">按销量</option>
			<option value="activityPrice">按价格</option>
			<option value="categoryGr">按类目</option>
	</select>
	
	<select style="width: 104px;display:none;" width="100" id="categorys">
			<option value="">---类目---</option>
			<option value="37684">消费电子</option>
			<option value="37688">孕婴童</option>
			<option value="37690">化妆品</option>
			<option value="37692">家居</option>
			<option value="37696">服饰配饰</option>
			<option value="39054">汽摩配</option>
			<option value="42491">秒杀</option>
			<option value="186758">女装</option>
			<option value="186759">男装</option>
			<option value="187014">饰品</option>
			<option value="188540">箱包</option>
			<option value="188541">鞋</option>
	</select>
	<div style="float:right;margin-right:100px">
		<a href="#" class="btn btn-default btn-sm" style="float: left;" onclick="doReset();">
			重置
		</a>
		<%--<input type="button" value="重置" onClick="doReset()">--%>
	</div>
	<div style="display:none;float:right;margin-right:100px" id="scope">
		<input id="begin" name="begin" type="text" style="width: 150px" class="inputxt">~<input id="end" name="end" type="text" style="width: 150px" class="inputxt">
	</div>
	
</form>

<p>已查到符合该条件记录数<span id="total"></span>条</p>
</div>

<div class="container">
<div class="row">
   
    <div class="panel tree hidden-phone">
    
    <script type="text/javascript">
        /*热品榜右边栏2*/
        var cpro_id = "u1650562";
    </script>
    <script src="js/c.js" type="text/javascript"></script>
    
    </div>
    
</div>

<div class="span2 left">
<ul class="nav nav-list" id="category_left">
</ul>
<ul class="nav nav-list" id="tag_left">
</ul>
</div>

<div class="span8 middle" id="main_block">


</div>
<div style="text-align: center;">
	<ul id="page" class="pagination">

	</ul>
</div>
</div>


<footer>




</footer>


<link rel="stylesheet" href="css/5bd0a22d3d80.css" type="text/css">

<script type="text/javascript" src="js/794ebf34faae.js"></script>




<div id="cnzz_stat" style="display:none">
</div>
<script type="text/javascript">
    $(document).ready(function(){
		$(document).on("click", "#highchart", function(obj) {
				 window.open("reportProductController.do?productStatisticTabs&reportType=line&productId="+obj.currentTarget.parentNode.id);
			});
        });
</script>
<script>
	var xhr;
	function doReset(){
		document.getElementById("time").value = null;
		document.getElementById("endTime").value = null;
		document.getElementById("condition").value = null;
		document.getElementById("paixu").value = null;
		document.getElementById("categorys").value = null;
		document.getElementById("begin").value = null;
		document.getElementById("end").value = null;
		document.getElementById("scope").style.display="none";
		document.getElementById("categorys").style.display="none";
		search(1);
		document.getElementById("search_in_category_and_all_form").reset();
	}

    function init() {
		$('#search_in_category_and_all_form').bind('input propertychange', function(obj) {			
				search(1);
		});
		$('#condition').bind('input propertychange', function(obj) {
			document.getElementById("begin").value = null;
			document.getElementById("end").value = null;
			if(obj.currentTarget.value=="count"||obj.currentTarget.value=="activityPrice"){
				document.getElementById("scope").style.display="block";
				document.getElementById("categorys").style.display="none";
			}else if(obj.currentTarget.value=="categoryGr"){
				document.getElementById("scope").style.display="none";
				document.getElementById("categorys").style.display="inline";
			}else{
				document.getElementById("scope").style.display="none";
				document.getElementById("categorys").style.display="none";
			}		
		});
		
		$.ajax({
            url: "frontController.do?ru_date_list&lastDate=${date}",
            success: function (data) {
				data = eval(data);
                $('#tu_date').html("");
				var str_left = "";
                for (var i = 0, o; i < data.length; i++) {
                    o = data[i];
					str_left += "<li id=\"dropdown-menu-apparel-accessories-women\" ><a href=\"frontController.do?ru&date="+o.date+"\">"+o.date+"("+o.count+")</a></li>";
                }
				$('#tu_date').append(str_left);
            },
            error: function (e) {
            }
        });
		
    }
	function fillData(data) {
		$('#total').html(data.total);
		$('#main_block').html("");
		var str = "<div id=\"category\">";
		if(data.rows.length>4){
			str += "<ul class=\"thumbnails\">";
			
		}else{
			str += "<ul class=\"thumbnails1\">";
		}
        for (var i = 0, l, tr, title; i < data.rows.length; i++) {
				l = data.rows[i];
				if(l.isSoldout==0){
					str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\""+l.url+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.img+"\" alt=\""+l.productId+"\"><h6></h6>俄团订单数:<span class=\"count\">"+l.count+"</span>&nbsp;&nbsp;&nbsp;原价:US $"+l.price+"<br>俄团价格:<span class=\"price\">"+l.activityPrice+l.unit+"&nbsp;&nbsp;&nbsp;["+l.discount+"]</span></br>俄团活动ID:<span class=\"sale_count\">"+l.activityId+"</span><br>活动结束时间:<span class=\"sale_count\">"+l.endTime.substr(0,10)+"</span><br>最后采集时间:<span class=\"sale_count\">"+l.lastDate.substr(0,19)+"</span></a><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span></li>";
				}else{
					str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\""+l.url+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.img+"\" alt=\""+l.productId+"\"><h6></h6>俄团订单数:<span class=\"count\">"+l.count+"</span>&nbsp;&nbsp;&nbsp;原价:US $"+l.price+"<br>俄团价格:<span class=\"price\">"+l.activityPrice+l.unit+"&nbsp;&nbsp;&nbsp;["+l.discount+"]</span></br>俄团活动ID:<span class=\"sale_count\">"+l.activityId+"</span><span class=\"price\">已售罄</span><br>活动结束时间:<span class=\"sale_count\">"+l.endTime.substr(0,10)+"</span><br>最后采集时间:<span class=\"sale_count\">"+l.lastDate.substr(0,19)+"</span></a><span id=\"highchart\"  class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span></li>";
				}
				
			}
		str += "</ul></div>";
		$('#main_block').append(str);
    }
	
	
	function search(page) {
			if(xhr!=null){
				xhr.abort();
			}
			var date = "${date}";
			var time = document.getElementById("time").value;
			var endTime = document.getElementById("endTime").value;
			var condition = document.getElementById("condition").value;
			var orders = document.getElementById("paixu").value;
			var categorys = document.getElementById("categorys").value;
			var begin = document.getElementById("begin").value;
			var end = document.getElementById("end").value;
			var urlstr = "frontController.do?list_ru&field=productId,url,img,activityId,discount,count,activityPrice,price,unit,endTime,lastDate,isSoldout&page=" + page;
			if(time != ""){
				urlstr += "&time="+time;
			}
			if(condition != ""){
				urlstr += "&condition="+condition;
			}
			if(orders != ""){
				urlstr += "&paixu="+orders;
			}
			if(categorys != ""){
				urlstr += "&categorys="+categorys;
			}
			if(begin != ""){
				urlstr += "&begin="+begin;
			}
			if(end != ""){
				urlstr += "&end="+end;
			}
			if(endTime != ""){
				urlstr += "&endTimes="+endTime;
			}else if(date!=""){
				urlstr += "&endTime=${date}";
			}
			xhr = $.ajax({
				url: urlstr,
				success: function (data) {
					$('#page').jqPaginator('option', {
						totalCounts: data.total
					});
					fillData(data);
				},
				error: function (e) {

				}
			});
		}
	//设置分页控件  
	 function initPage(page) {
			var date = "${date}";
			var endTime = document.getElementById("time").value;
			var condition = document.getElementById("condition").value;
			var orders = document.getElementById("paixu").value;
			var categorys = document.getElementById("categorys").value;
			var begin = document.getElementById("begin").value;
			var end = document.getElementById("end").value;
			var urlstr = "frontController.do?list_ru&field=productId,url,img,activityId,discount,count,activityPrice,price,unit,endTime,lastDate,isSoldout&page=" + page;
			if(endTime != ""){
				urlstr += "&time="+endTime;
			}
			if(condition != ""){
				urlstr += "&condition="+condition;
			}
			if(orders != ""){
				urlstr += "&paixu="+orders;
			}
			if(categorys != ""){
				urlstr += "&categorys="+categorys;
			}
			if(begin != ""){
				urlstr += "&begin="+begin;
			}
			if(end != ""){
				urlstr += "&end="+end;
			}
			if(date!=""){
				urlstr += "&endTime=${date}";
			}
			$.ajax({
				url:urlstr,
				success: function (data) {
					$('#page').jqPaginator('option', {
						totalCounts: data.total
					});
					fillData(data);

					
				},
				error: function (e) {

				}
			});
		}

		$('#page').jqPaginator({
			totalCounts: 10,
			pageSize: 36,
			visiblePages: 10,
			currentPage: 1,
			onPageChange: function (num, type) {
				initPage(num);
			}
		});
    init();
</script>
</body></html>