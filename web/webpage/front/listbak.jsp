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
		.week{
			color:red;
		}
		.container{
			padding-top: 20px;
			width:100%;
		}
		.searchForm{
			text-align: center;
			width: 1170px;;
		}
	</style>

</head>
<body id="homepage_body">



<div class="container searchForm">





	<form id="search_in_category_and_all_form" action="#">
		<div>
			<select style="width: 104px" width="100" id="condition">
				<option value="productId">产品ID</option>
				<option value="categoryId">分类ID</option>
				<option value="storeId">店铺ID</option>
				<option value="productName">产品名称</option>
			</select>
			<input class="inputxt" id="search" style="width: 300px" type="text" value="${search}">
		</div>
		<br>
		<div class="search2">

			总销量区间:<input id="productOrdersAllBegin" name="productOrdersAllBegin" type="text" style="width: 100px" class="inputxt">~<input id="productOrdersAllEnd" name="productOrdersAllEnd" type="text" style="width: 100px" class="inputxt">
			当日销量区间:<input id="productOrdersBegin" name="productOrdersBegin" type="text" style="width: 100px" class="inputxt">~<input id="productOrdersEnd" name="productOrdersEnd" type="text" style="width: 100px" class="inputxt">

			<br/>
			评价数量区间:<input id="productFacebackBegin" name="productFacebackBegin" type="text" style="width: 100px" class="inputxt">~<input id="productFacebackEnd" name="productFacebackEnd" type="text" style="width: 100px" class="inputxt">
			评价得分区间:<input id="productDsrBegin" name="productDsrBegin" type="text" style="width: 100px" class="inputxt">~<input id="productDsrEnd" name="productDsrEnd" type="text" style="width: 100px" class="inputxt">
			<label><input id="productDsrZero" type="checkbox"/>打勾说明选择包含得分为0</label>
			<br/>
			价格区间:<input id="productPriceBegin" name="productPriceBegin" type="text" style="width: 100px" class="inputxt">~<input id="productPriceEnd" name="productPriceEnd" type="text" style="width: 100px" class="inputxt">

			<br/>
			<div style="float:right;margin-right:100px">
				<input type="button" value="重置" onClick="doReset()">
				<input type="button" value="查询" onClick="search(1);">
			</div>
			<br/>
			<p>已查到符合该条件记录数<span id="total"></span>条</p>
		</div>


		<!--
        <input type="submit" class="btn btn-primary" id="search_in_category_and_all_all_search" value="在当前分类中查询" onclick="search(1);return false;">
        <input type="submit" class="btn btn-primary" id="search_in_category_and_all_all_search" value="在所有分类中查询" onclick="searchAll(1);return false;">
        -->
	</form>



</div>

<div class="container">
	<ul class="breadcrumb" id="path">


	</ul>
	<div class="row">

		<!--
        <div class="span2 left">

            <div class="panel tree hidden-phone">
        <ul class="nav nav-list" id="category_left">
        </ul>
        </div>

        -->
		<%--<div class="panel tree hidden-phone">

        <script type="text/javascript">
            /*热品榜右边栏2*/
            var cpro_id = "u1650562";
        </script>
        <script src="js/c.js" type="text/javascript"></script>

        </div>--%>

	</div>


	<div class="span2 left">
		<ul class="nav nav-list" id="category_left">
		</ul>
		<ul class="nav nav-list" id="tag_left">
		</ul>
	</div>

	<div class="span12 middle" id="main_block">


	</div>
	<div style="text-align: center;">
		<ul id="page" class="pagination">

		</ul>
	</div>
</div>
</div>


<link rel="stylesheet" href="css/5bd0a22d3d80.css" type="text/css">

<script type="text/javascript" src="js/794ebf34faae.js"></script>


<div id="cnzz_stat" style="display:none">
</div>


<script>

	var xhr;
	function doReset(){
		document.getElementById("search_in_category_and_all_form").reset();
		initDate();
	}
	function init() {
		debugger;
		$.ajax({
			url: "frontController.do?category",
			success: function (data) {
				debugger;
				data = eval(data);
				$('#category_left').html("");
				var str_left = "<li class=\" active\" id=\"dropdown-menu-apparel-accessories\"><a href=\"loginController.do?login\">首页</a></li><li class=\"divider\"></li>";
				for (var i = 0, o; i < data.length; i++) {
					o = data[i];
					if(o.pid == 0){
						str_left += "<li class=\"dropdown-submenu\" id=\"dropdown-menu-apparel-accessories\"><a href=\"frontController.do?list&categoryId="+o.CId+"\">"+o.CName+"</a><ul class=\"dropdown-menu\">";
						for(var j = 0,l; j < data.length; j++){
							l = data[j];
							if(l.pid == o.id){
								str_left += "<li class=\"dropdown-submenu\" id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+l.CId+"\">"+l.CName+"</a><ul class=\"dropdown-menu\">";
								for(var k = 0,m; k < data.length; k++){
									m = data[k];
									if(m.pid == l.id){
										str_left += "<li class=\"dropdown-submenu\" id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+m.CId+"\">"+m.CName+"</a><ul class=\"dropdown-menu\">";
										for(var u = 0,n; u < data.length; u++){
											n = data[u];
											if(n.pid == m.id){
												str_left += "<li id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+n.CId+"\">"+n.CName+"</a><ul class=\"dropdown-menu\">";
												for(var q = 0,p; q < data.length; q++){
													p = data[q];
													if(p.pid == n.id){
														str_left += "<li id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+p.CId+"\">"+p.CName+"</a></li>";
													}
												}
												str_left += "</ul></li>";
											}
										}
										str_left += "</ul></li>";
									}
								}
								str_left += "</ul></li>";
							}
						}
						str_left += "</ul></li><li class=\"divider\"></li>";
					}
				}
				$('#category_left').append(str_left);
			},
			error: function (e) {
			}
		});

		$.ajax({
			url: "frontController.do?path&categoryId=${categoryId}",
			success: function (data) {
				data = eval(data);
				$('#path').html("");
				var str = "<li><a href=\"loginController.do?login\">首页</a><span class=\"divider\">/</span></li>";
				for (var i = data.length-1, o; i >= 0; i--) {
					o = data[i];
					if(i > 0){
						str += "<li><a href=\"frontController.do?list&categoryId="+o.CId+"\">"+o.CName+"</a><span class=\"divider\">/</span></li>";
					}else{
						str += "<li class=\"active\">"+o.CName+"</li>";
					}
				}
				$('#path').append(str);
			},
			error: function (e) {
			}
		});
		$.ajax({
			url: "frontController.do?tag_list",
			success: function (data) {
				data = eval(data);
				$('#tag_left').html("");
				var str_left = "<li class=\"divider\"></li>";
				for (var i = 0, o; i < data.length; i++) {
					o = data[i];
					str_left += "<li id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list_tag&tagId="+o.id+"\">"+o.name+"</a></li><li class=\"divider\"></li>";
				}
				$('#tag_left').append(str_left);
			},
			error: function (e) {
			}
		});

		$('#search_in_category_and_all_form').bind('input propertychange', function() {
			search(1);
		});

	}
	function fillData(data) {
		$('#total').html(data.total);
		$('#main_block').html("");
		var str = "<div id=\"category\"><ul class=\"product_list\">";
		for (var i = 0, o, tr, title; i < data.rows.length; i++) {
			o = data.rows[i];
			str += "<li class=\"product_thumbnail\"><a href=\"http://www.aliexpress.com/item/"+o.productUrl.replace("//www.aliexpress.com/item/","")+"\" target=\"_blank\" title=\""+o.productName+"\"><img src=\""+o.productImg+"\" alt=\""+o.productName+"\"><h6>"+o.productName+"</h6></a><ul class=\"attribute\"><li class=\"add_tag\" id=\""+o.productId+"\"><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">加入标签</span></li><li class=\"sale_amount \" id=\""+o.productId+"\"><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span></li><li class=\"sale_count \">今日销量"+o.productOrdersToday+"件<br>店铺ID"+o.storeId+"<br>评价得分"+o.productDsr+"<br></li><li class=\"price\">价格US $"+o.productPrice+"<br><span class=\"badge badge-info\">排名"+o.categoryOrders+"<br>评价数"+o.productFaceback+"<br>总销量"+o.productOrdersAll+"</span></li></ul></li>";
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
		var search = document.getElementById("search").value;
		var condition = document.getElementById("condition").value;
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
		var productDsrZero = document.getElementById("productDsrZero").checked;
		var urlstr = "frontController.do?list_category&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,storeId,productDsr,productFaceback,categoryName,categoryOrders,weekOrders&categoryId=${categoryId}&page=" + page+"&productDsrZero="+productDsrZero;
		if(search != ""){
			urlstr = "frontController.do?list_category&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,storeId,productDsr,productFaceback,categoryName,categoryOrders,weekOrders&page=" + page+"&productDsrZero="+productDsrZero;
			urlstr += "&"+condition+"='"+search+"'";
		}
		if(productPriceBegin != ""){
			urlstr += "&productPriceBegin="+productPriceBegin;
		}
		if(productPriceEnd != ""){
			urlstr += "&productPriceEnd="+productPriceEnd;
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

		$('#main_block').html("");
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
		var urlstr = "frontController.do?list_category&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,storeId,productDsr,productFaceback,categoryName,categoryOrders,weekOrders&categoryId='${categoryId}'&page=" + 1;
		$('#main_block').html("");
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
		pageSize: 100,
		visiblePages: 10,
		currentPage: 1,
		onPageChange: function (num, type) {
			search(num);
		}
	});
	init();
</script>

</body></html>