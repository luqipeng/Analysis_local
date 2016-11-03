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
label {
  display: inline;
  margin-bottom: 5px;
  font-size:12px;
}
.search2 {
	padding:10px;
    border: 5px solid #dedede;
    -moz-border-radius: 15px;      /* Gecko browsers */
    -webkit-border-radius: 15px;   /* Webkit browsers */
    border-radius:15px;            /* W3C syntax */	
	background-color: #6E6E6E;
}
.navbar-inner{
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
  height: 320px;
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
table {
	margin:20px;
	text-align:left;
}
</style>

</head>
<body id="homepage_body">

<%--<div class="navbar">
<div class="navbar-inner">
<div class="container">
    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </a>
    
    <a class="brand" href="">今日销量排行榜</a>
    
    <div class="nav-collapse">     
 <p class="tagline" style="float:right;"> <a class="brand" href="loginController.do?login">首页</a><a class="brand" href="frontController.do?ru">团购</a>&lt;%&ndash;<a class="brand" href="frontController.do?week">周销量排行榜</a>&ndash;%&gt;<span class="brand">业务员:${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> <a class="brand" href="loginController.do?admin">系统设置</a>
 
 </p>  

        
    </div>
</div>
</div>
</div>--%>

<div class="container">

<div id="title_block">
<div class="pull-right" id="title_right_block">

</div>

</div>




<form id="search_in_category_and_all_form" action="#">
	

	总销量区间:<input id="productOrdersAllBegin" name="productOrdersAllBegin" type="text" style="width: 100px" class="inputxt">~<input id="productOrdersAllEnd" name="productOrdersAllEnd" type="text" style="width: 100px" class="inputxt">
	当日销量区间:<input id="productOrdersBegin" name="productOrdersBegin" type="text" style="width: 100px" class="inputxt">~<input id="productOrdersEnd" name="productOrdersEnd" type="text" style="width: 100px" class="inputxt">	
	销量排名:<select style="width: 104px" width="100" id="orders">
			<option value="">---请选择---</option>
			<option value="up">升序</option>
			<option value="down">降序</option>
		</select>
	<br/> 
	评价数量区间:<input id="productFacebackBegin" name="productFacebackBegin" type="text" style="width: 100px" class="inputxt">~<input id="productFacebackEnd" name="productFacebackEnd" type="text" style="width: 100px" class="inputxt">
	评价得分区间:<input id="productDsrBegin" name="productDsrBegin" type="text" style="width: 100px" class="inputxt">~<input id="productDsrEnd" name="productDsrEnd" type="text" style="width: 100px" class="inputxt">
	<label><input id="productDsrZero" type="checkbox"/>打勾说明选择包含得分为0</label>
	<br/>
	价格区间:<input id="productPriceBegin" name="productPriceBegin" type="text" style="width: 100px" class="inputxt">~<input id="productPriceEnd" name="productPriceEnd" type="text" style="width: 100px" class="inputxt">
	排名:<input id="categoryOrdersBegin" name="categoryOrdersBegin" type="text" style="width: 100px" class="inputxt">~<input id="categoryOrdersEnd" name="categoryOrdersEnd" type="text" style="width: 100px" class="inputxt">	
	排名排序:<select style="width: 104px" width="100" id="categoryOrders">
			<option value="">---请选择---</option>
			<option value="up">升序</option>
			<option value="down">降序</option>
		</select>
	<br/>
	<div style="float:right;margin-right:100px">
		<input type="button" value="重置" onClick="doReset()">
	</div>
	<br/>
	<p>已查到符合该条件记录数<span id="total"></span>条</p>	
	</div>

</form>

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


   
<div class="container">

<!--
<ul><li><ul><li><a href="http://repinbang.com/about/faq/">常见问题</a></li></ul></li><li><ul><li><a href="http://repinbang.com/about/contactus/">联系我们</a></li></ul></li><li><ul><li><a href="http://repinbang.com/about/aboutus/">关于外贸热品榜</a></li></ul></li><li><ul><li><a href="http://repinbang.com/hotsale/ranklist_index/">所有排行榜</a></li></ul></li><li><ul><li><a href="http://repinbang.com/special_ranklist/">外贸特色排行榜</a></li></ul></li></ul>
-->
<br style="clear:both">
<p>
    
© 2014 热品榜, All Rights Reserved. <a href="http://www.miitbeian.gov.cn/" rel="nofollow">京ICP备13022555号-1</a>

</p>
</div>



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
			$('#search_in_category_and_all_form').bind('input propertychange', function(obj) {			
				search(1);
		});
   });
</script>
<script>
	var xhr;
	function doReset(){
		document.getElementById("search_in_category_and_all_form").reset(); 
		initDate();
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
				str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\"http://www.aliexpress.com/item/"+l.productUrl+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.productImg+"\" alt=\""+l.productId+"\">";
				str += "<table><tr><td>今日销量:<span class=\"price\">"+l.productOrdersToday+"</span></td><td>价格:<span class=\"price\">US $"+l.productPrice+"</span></td></tr>";
				str	+= "<tr><td>总销量:<span class=\"sale_count\">"+l.productOrdersAll+"</span></td><td>类目排名:<span class=\"sale_count\">"+l.categoryOrders+"</span></td></tr>";
				str	+= "<tr><td>销售额:<span class=\"sale_count\">"+l.amount+"</span></td><td>评价数:<span class=\"sale_count\">"+l.productFaceback+"</span></td></tr>";
				str	+= "<tr><td>店铺ID:<span class=\"sale_count\">"+l.storeId+"</span></td><td>评价得分:<span class=\"sale_count\">"+l.productDsr+"</span></td></tr></table>";
				str	+= "</a><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span></li>";
				//str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\"http://www.aliexpress.com/item/"+l.productUrl+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.productImg+"\" alt=\""+l.productId+"\"><h6></h6>今日销量:<span class=\"price\">"+l.productOrdersToday+"</span>&nbsp;&nbsp;&nbsp;价格:<span class=\"price\">US $"+l.productPrice+"</span><br>总销量:<span class=\"sale_count\">"+l.productOrdersAll+"</span>&nbsp;&nbsp;&nbsp;类目排名:<span class=\"sale_count\">"+l.categoryOrders+"</span><br>销售额:<span class=\"sale_count\">"+l.amount+"</span>&nbsp;&nbsp;&nbsp;评价数:<span class=\"sale_count\">"+l.productFaceback+"</span><br>店铺ID:<span class=\"sale_count\">"+l.storeId+"</span>评价得分:<span class=\"sale_count\">"+l.productDsr+"</span></a><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span></li>";
			}
		str += "</ul></div>";
		$('#main_block').append(str);
    }
	
	
	function search(page){
			if(xhr!=null){
				xhr.abort();
			}
			searchCurrent(page);
	}
	
	function searchCurrent(page) {
			var productPriceBegin = document.getElementById("productPriceBegin").value;
			var productPriceEnd = document.getElementById("productPriceEnd").value;
			var productDsrBegin = document.getElementById("productDsrBegin").value;
			var productDsrEnd = document.getElementById("productDsrEnd").value;
			var productFacebackBegin = document.getElementById("productFacebackBegin").value;
			var productFacebackEnd = document.getElementById("productFacebackEnd").value;
			var productOrdersBegin = document.getElementById("productOrdersBegin").value;
			var productOrdersEnd = document.getElementById("productOrdersEnd").value;
			var productOrdersAllBegin = document.getElementById("productOrdersAllBegin").value;
			var productOrdersAllEnd = document.getElementById("productOrdersAllEnd").value;
			var categoryOrdersBegin = document.getElementById("categoryOrdersBegin").value;
			var categoryOrdersEnd = document.getElementById("categoryOrdersEnd").value;
			var categoryOrders = document.getElementById("categoryOrders").value;
			var orders = document.getElementById("orders").value;
			var productDsrZero = document.getElementById("productDsrZero").checked;
			var urlstr = "frontController.do?list_day&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,storeId,productDsr,productFaceback,categoryName,categoryOrders,amount,weekAmount&page=" + page+"&productDsrZero="+productDsrZero;
			if(productPriceBegin != ""){
				urlstr += "&productPriceBegin="+productPriceBegin;
			}
			if(productPriceEnd != ""){
				urlstr += "&productPriceEnd="+productPriceEnd;
			}
			if(productDsrBegin != ""){
				urlstr += "&productDsrBegin="+productDsrBegin;
			}
			if(productDsrEnd != ""){
				urlstr += "&productDsrEnd="+productDsrEnd;
			}
			if(productFacebackBegin != ""){
				urlstr += "&productFacebackBegin="+productFacebackBegin;
			}
			if(productFacebackEnd != ""){
				urlstr += "&productFacebackEnd="+productFacebackEnd;
			}
			if(productOrdersBegin != ""){
				urlstr += "&productOrdersBegin="+productOrdersBegin;
			}
			if(productOrdersEnd != ""){
				urlstr += "&productOrdersEnd="+productOrdersEnd;
			}
			if(productOrdersAllBegin != ""){
				urlstr += "&productOrdersAllBegin="+productOrdersAllBegin;
			}
			if(productOrdersAllEnd != ""){
				urlstr += "&productOrdersAllEnd="+productOrdersAllEnd;
			}
			if(categoryOrdersBegin != ""){
				urlstr += "&categoryOrdersBegin="+categoryOrdersBegin;
			}
			if(categoryOrdersEnd != ""){
				urlstr += "&categoryOrdersEnd="+categoryOrdersEnd;
			}
			if(categoryOrders != ""){
				urlstr += "&categoryOrdersBy="+categoryOrders;
			}
			if(orders != ""){
				urlstr += "&orders="+orders;
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
		function initDate(){
			var urlstr = "frontController.do?list_day&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,storeId,productDsr,productFaceback,categoryName,categoryOrders,amount,weekAmount&page=" + 1;
			$.ajax({
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

		$('#page').jqPaginator({
			totalCounts: 10,
			pageSize: 36,
			visiblePages: 10,
			currentPage: 1,
			onPageChange: function (num, type) {
				search(num);
			}
		});
</script>
</body></html>