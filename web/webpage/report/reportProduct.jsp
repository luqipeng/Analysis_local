<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- context path -->
<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/exporting.js"></script>
<link rel="stylesheet" href="css/6b94a81a5fe8.css" type="text/css">
<script language="javascript" type="text/javascript" src="webpage/com/buss/caiji/statistics/myChartTheme.js" ></script>  
<script language="javascript" type="text/javascript" src="webpage/com/buss/caiji/statistics/myChart.js" ></script>
<style>
.purple{
	font-size:14px;
	color:purple;
}
</style>

<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    // 初始化线状图对象  
    var line = new MyChart(0);
	var price = new MyChart(0);
	var index = new MyChart(0);	
 
          
    // 初始化柱状图对象  
    var column = new MyChart(1).cloneAttr(line);  
      
    // 初始化饼状图对象  
    var pie = new MyChart(2);  

var myHighcharts = new MyHighcharts({  
        title:"产品销量统计",  
        subTitle:"",  
        xTitle:"时间",  
        yTitle:"销量",  
        line:line,  
        column:column,  
        pie:pie  
    });
	myHighcharts.draw(0,"all",${categoryId},${productId});

	var categoryIds = ${categoryIds};
    for(var i=0;i<categoryIds.length;i++){
		var id = categoryIds[i];
		var indexCharts = new MyHighcharts({  
			title:"产品排名统计",  
			subTitle:"",  
			xTitle:"时间",  
			yTitle:"排名",  
			line:price,  
			column:column,  
			pie:pie  
		});
		indexCharts.draw(1,id+"index",id,${productId});
		
		$.ajax({
			url: "frontController.do?path&categoryId="+id,
			success: function (data) {
				debugger;
				data = eval(data);
				$('#'+data[0].CId+'path').html("");
				var str = "类目：<span class='purple'>"+data[0].CId+"</span> <li><a href=\"loginController.do?login\">首页</a><span class=\"divider\">/</span></li>";
				for (var j = data.length-1, o; j >= 0; j--) {
					o = data[j];
					str += "<li><a href=\"frontController.do?list&categoryId="+o.CId+"\">"+o.CName+"</a><span class=\"divider\">/</span></li>";
				}
				$('#'+data[0].CId+'path').append(str);
			},
			error: function (e) {
			}
		});	
	}

	$.ajax({
            url: "frontController.do?tagLink&productId="+${productId},
            success: function (data) {
				data = eval(data);
                $('#tagList').html("");
				var str_left = "";
                for (var i = 0, o; i < data.length; i++) {
                    o = data[i];
					str_left += "<a href=\"frontController.do?list_tag&tagId="+o.id+"\">"+o.name+"</a><br>";
                }
				$('#tagList').append(str_left);
            },
            error: function (e) {
            }
        });
		
		$(document).on("click", "#add_tag", function() {
				 window.open('userTagProController.do?goAdd&productId='+${productId},'加入标签','height=200, width=400, top=200, left=500, location=no,resizable=no,toolbar=no,scrollbars=no,status=no');
			});
	
</script>
<div style="margin-left:10%;">
<span style="font-size:20px;">${product.productName}<span>
<a href="http://www.aliexpress.com/item/${product.productUrl}" style="font-size:20px;">产品链接</a>
<div id="tagList"></div>

<span id="add_tag" class="label label-info" style="cursor:hand">加入标签</span>
<span id="allContainer" style="margin-top:50px; width: 80%; height: 60%"></span>

<div id="categoryContainer">

</div>
<c:forEach var="item" items="${categoryIds}" varStatus="status"> 
	<ul class="breadcrumb" id="${item}path">

	</ul> 
	<span id="${item}indexContainer" style="margin-top:50px; width: 80%; height: 60%"></span>
</c:forEach>

</div>
<!--
<div style="width: 98%; height: 280px"><t:datagrid name="studentStatisticList" title="class.count.statistics" actionUrl="reportDemoController.do?listAllStatisticByJdbc" idField="id" fit="true">
	<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="lang.class" field="classname" width="130"></t:dgCol>
	<t:dgCol title="number.ofpeople" field="personcount" width="130"></t:dgCol>
	<t:dgCol title="common.proportion" field="rate" width="130"></t:dgCol>
</t:datagrid></div>-->