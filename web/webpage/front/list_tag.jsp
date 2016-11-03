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
.week{
	color:red;
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
    
    <a class="brand" href="">数据采集系统-V1.0</a>
    <p class="tagline"></p>
    
    <div class="nav-collapse">
      
 <p class="tagline" style="float:right;"> <a class="brand" href="frontController.do?day">今日销量排行榜</a><a class="brand" href="frontController.do?week">周销量排行榜</a><a class="brand" href="frontController.do?ru">俄团</a><span class="brand">业务员:${userName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span> <a class="brand" href="loginController.do?admin">系统设置</a>  </p>  

        
    </div>
</div>
</div>
</div>

<div class="container">

<div id="title_block">
<div class="pull-right" id="title_right_block">

</div>

</div>




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
	日期:<label><input id="searchTime" name="searchTime" type="radio" value="day" checked="checked">今日</label>
		 <label><input id="searchTime" name="searchTime" type="radio" value="week">本周</label>
		 <label><input id="productOrdersZero" type="checkbox" checked="checked"/>是否显示销量为零</label>
    <br/>   <br/> 
	总销量区间:<input id="productOrdersAllBegin" name="productOrdersAllBegin" type="text" style="width: 100px" class="inputxt">~<input id="productOrdersAllEnd" name="productOrdersAllEnd" type="text" style="width: 100px" class="inputxt">
	当日销量区间:<input id="productOrdersBegin" name="productOrdersBegin" type="text" style="width: 100px" class="inputxt">~<input id="productOrdersEnd" name="productOrdersEnd" type="text" style="width: 100px" class="inputxt">	
	<%--销量排名:<select style="width: 104px" width="100" id="orders">
			<option value="">---请选择---</option>
			<option value="up">升序</option>
			<option value="down">降序</option>
		</select>--%>
	<br/> 
	评价数量区间:<input id="productFacebackBegin" name="productFacebackBegin" type="text" style="width: 100px" class="inputxt">~<input id="productFacebackEnd" name="productFacebackEnd" type="text" style="width: 100px" class="inputxt">
	评价得分区间:<input id="productDsrBegin" name="productDsrBegin" type="text" style="width: 100px" class="inputxt">~<input id="productDsrEnd" name="productDsrEnd" type="text" style="width: 100px" class="inputxt">
	<label><input id="productDsrZero" type="checkbox"/>打勾说明选择包含得分为0</label>
	<br/>
	价格区间:<input id="productPriceBegin" name="productPriceBegin" type="text" style="width: 100px" class="inputxt">~<input id="productPriceEnd" name="productPriceEnd" type="text" style="width: 100px" class="inputxt">
	<%--排名:<input id="categoryOrdersBegin" name="categoryOrdersBegin" type="text" style="width: 100px" class="inputxt">~<input id="categoryOrdersEnd" name="categoryOrdersEnd" type="text" style="width: 100px" class="inputxt">
	排名排序:<select style="width: 104px" width="100" id="categoryOrders">
			<option value="">---请选择---</option>
			<option value="up">升序</option>
			<option value="down">降序</option>
		</select>--%>
	<br/>
	<div style="float:right;margin-right:100px">
		<input type="button" value="重置" onClick="doReset()">
	</div>
	<br/>
	<p>已查到符合该条件记录数<span id="total"></span>条</p>	
	</div>
	
    
	<!--
    <input type="submit" class="btn btn-primary" id="search_in_category_and_all_all_search" value="在当前分类中查询" onclick="search(1);return false;">
	<input type="submit" class="btn btn-primary" id="search_in_category_and_all_all_search" value="在所有分类中查询" onclick="searchAll(1);return false;">
	-->
</form>

<ul class="breadcrumb" id="path">


</ul>
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
    
© 2015 热品榜, All Rights Reserved. 

</p>
</div>



</footer>


<link rel="stylesheet" href="css/5bd0a22d3d80.css" type="text/css">

<script type="text/javascript" src="js/794ebf34faae.js"></script>




<div id="cnzz_stat" style="display:none">
</div>
<script type="text/javascript">
    $(document).ready(function(){

			$(document).on("click", ".sale_amount", function(obj) {
				 window.open("reportProductController.do?productStatisticTabs&reportType=line&categoryId=${categoryId}&productId="+obj.currentTarget.id);
			});
			$(document).on("click", ".add_tag", function(obj) {
				 window.open('userTagProController.do?goAdd&productId='+obj.currentTarget.parentElement.id+'&tagId=${tagId}','修改标签','height=200, width=400, top=200, left=500, location=no,resizable=no,toolbar=no,scrollbars=no,status=no');
			});
			$(document).on("click", ".update_tag", function(obj) {
				 window.open('userTagProController.do?goUpdate&productId='+obj.currentTarget.parentElement.id+'&tagId=${tagId}','修改标签','height=200, width=400, top=200, left=500, location=no,resizable=no,toolbar=no,scrollbars=no,status=no');
			});
			$(document).on("click", ".delete_tag", function(obj) {
				$.ajax({
					cache: true,
					type: "POST",
					url: "userTagProController.do?doDelete",
					data: {tagId:${tagId},productId:obj.currentTarget.id},
					async: false,
					dataType: 'json',
					error: function (request) {
						showContent('', 'frontController.do?list');
					},
					success: function (tt) {
						var msg = tt.msg;
						var success = tt.success;
						if (success == true) {
							var parent=document.getElementById("product_list");
							var child=document.getElementById("li_"+obj.currentTarget.id);
							parent.removeChild(child);
							//showContent('', 'frontController.do?list_tag&tagId=${tagId}');
						} else {
							alert(msg);
						}

					}
				});
			});
        });
</script>
<script>
	var xhr;
	function doReset(){
		document.getElementById("search_in_category_and_all_form").reset(); 
		initDate();
	}
    function init() {
        $.ajax({
            url: "frontController.do?category",
            success: function (data) {
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
            url: "frontController.do?path&tagId=${tagId}",
            success: function (data) {
				debugger;
				
                $('#path').html("");
				var str = "<li><a href=\"loginController.do?login\">首页</a><span class=\"divider\">/</span></li>";
                str += "<li class=\"active\">"+data+"</li>";
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
		var str = "<div id=\"category\"><ul class=\"product_list\" id=\"product_list\" >";
        for (var i = 0, o, tr, title; i < data.rows.length; i++) {
            o = data.rows[i];
			if(o.isSeries!=2){
				str += "<li class=\"product_thumbnail\" id=\"li_"+o.productId+"\"><a href=\"http://www.aliexpress.com/item/"+o.productUrl.replace("//www.aliexpress.com/item/","")+"\" target=\"_blank\" title=\""+o.productName+"\"><img src=\""+o.productImg+"\" alt=\""+o.productName+"\"><h6>"+o.productName+"</h6></a><ul class=\"attribute\"><li id=\""+o.productId+"\"><span id=\"highchart\" class=\"label label-info update_tag\" style=\"cursor:hand\">修改标签</span><span class=\"delete_tag\" id=\""+o.productId+"\"><img src=\"img/delete.gif\" style=\"width:12px;position:static;\"></span></li><li class=\"sale_amount \" id=\""+o.productId+"\"><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span></li><li class=\"sale_count \">今日销量"+o.productOrdersToday+"件<br>店铺ID"+o.storeId+"<br>评价得分"+o.productDsr+"<br></li><li class=\"price\">价格US $"+o.productPrice+"<br><span class=\"badge badge-info\">排名"+o.categoryOrders+"<br>评价数"+o.productFaceback+"<br>总销量"+o.productOrdersAll+"</span></li></ul></li>";
			}else{
				str += "<li class=\"product_thumbnail\" id=\"li_"+o.productId+"\"><a href=\"http://www.aliexpress.com/item/"+o.productUrl.replace("//www.aliexpress.com/item/","")+"\" target=\"_blank\" title=\""+o.productName+"\"><img src=\""+o.productImg+"\" alt=\""+o.productName+"\"><h6>"+o.productName+"</h6></a><ul class=\"attribute\"><li id=\""+o.productId+"\"><span id=\"highchart\" class=\"label label-info update_tag\" style=\"cursor:hand\">修改标签</span><span class=\"delete_tag\" id=\""+o.productId+"\"><img src=\"img/delete.gif\" style=\"width:12px;position:static;\"></span></li><li class=\"sale_amount \" id=\""+o.productId+"\"><span id=\"highchart\" class=\"label label-info\" style=\"cursor:hand\">查看产品统计趋势</span><br><span class=\"week\">最后采集时间:<br>"+o.lastDate.substring(0,10)+"</span></li><li class=\"sale_count \">今日销量"+o.productOrdersToday+"件<br>店铺ID"+o.storeId+"<br>评价得分"+o.productDsr+"<br></li><li class=\"price\">价格US $"+o.productPrice+"<br><span class=\"badge badge-info\">排名"+o.categoryOrders+"<br>评价数"+o.productFaceback+"<br>总销量"+o.productOrdersAll+"</span></li></ul></li>";
			}
            
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
			var searchTime = document.getElementById("searchTime").value;
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
			var categoryOrdersBegin = document.getElementById("categoryOrdersBegin").value;
			var categoryOrdersEnd = document.getElementById("categoryOrdersEnd").value;
			var categoryOrders = document.getElementById("categoryOrders").value;
			var orders = document.getElementById("orders").value;
			var productDsrZero = document.getElementById("productDsrZero").checked;
			var productOrdersZero = document.getElementById("productOrdersZero").checked;
			var urlstr = "frontController.do?list_tag_category&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,allIndex,storeId,productDsr,productFaceback,categoryName,categoryOrders,weekOrders,isSeries,lastDate&tagId=${tagId}&page=" + page+"&productDsrZero="+productDsrZero+"&productOrdersZero="+productOrdersZero;
			if(search != ""){
				urlstr += "&"+condition+"="+search;
			}
			if(searchTime != ""){
				urlstr += "&searchTime="+searchTime;
			}
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
			var urlstr = "frontController.do?list_tag_category&field=id,productId,productUrl,productImg,productName,productOrdersAll,productOrdersToday,productPrice,allIndex,storeId,productDsr,productFaceback,categoryName,categoryOrders,weekOrders,isSeries,lastDate&tagId=${tagId}&page=" + 1;
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
    init();
</script>
</body></html>