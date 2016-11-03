<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 
<script language="javascript" type="text/javascript" src="webpage/com/buss/caiji/statistics/myChartTheme.js" ></script>  
<script language="javascript" type="text/javascript" src="webpage/com/buss/caiji/statistics/myChart.js" ></script>  

<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
$(function(){  
    var sql = "select count(*) from tb_interac_consultative where time_treatment between ? and ? ";  
      
    // 初始化线状图对象  
    var line = new MyChart(0);  
    line.xAppend("已回复",sql+"and ct_treatment_status=?",new Array("1"));  
    line.xAppend("回复中",sql+"and ct_treatment_status=?",new Array("2"));  
    line.xAppend("未回复",sql+"and ct_treatment_status=?",new Array("0"));  
    line.setTime("timeStart","timeEnd","timetype");  
          
    // 初始化柱状图对象  
    var column = new MyChart(1).cloneAttr(line);  
      
    // 初始化饼状图对象  
    var pie = new MyChart(2);  
    pie.fAppend("已回复",sql+"and ct_treatment_status=?",new Array("1"));  
    pie.fAppend("回复中",sql+"and ct_treatment_status=?",new Array("2"));  
    pie.fAppend("未回复",sql+"and ct_treatment_status=?",new Array("0"));  
    pie.setTime("timeStart","timeEnd","timetype");  
      
    var myHighcharts = new MyHighcharts({  
        title:"产品销量统计",  
        subTitle:"",  
        xTitle:"时间",  
        yTitle:"销量",  
        line:line,  
        column:column,  
        pie:pie  
    });  
      
    myHighcharts.draw(0,"line",${categoryId},${productId});     
});  
/*
	$(function() {
		$(document).ready(function() {
			var chart;
			$.ajax({
				type : "POST",
				url : "productController.do?getBroswerBar&reportType=${reportType}",
				success : function(jsondata) {
					data = eval(jsondata);
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'containerline',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title: {
							text: 'Monthly Average Temperature',
							x: -20 //center
						},
						subtitle: {
							text: 'Source: WorldClimate.com',
							x: -20
						},
						xAxis: {
							categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
						},
						yAxis: {
							title: {
								text: 'Temperature (°C)'
							},
							plotLines: [{
								value: 0,
								width: 1,
								color: '#808080'
							}]
						},
						tooltip: {
							valueSuffix: '°C'
						},
						legend: {
							layout: 'vertical',
							align: 'right',
							verticalAlign: 'middle',
							borderWidth: 0
						},
						series: data
					});
				}
			});
		});
	});
	*/
</script>
<div id="containerline" style="width: 80%; height: 80%; margin-left:100px;">
<div id="myChart">  
      
    <!-- 统计图 -->  
    <div id="lineContainer"></div>  
      
    <!-- 如果没有用到时间动态统计则删除 -->  
	<!--
    <div id="timeselect">  
        时间类型：  
        <select id="timetype">  
            <option value="2" selected="selected">月份</option>   
        </select>  
        时间段：<input type="text" class="Wdate" id="timeStart" size="12" onclick="WdatePicker()" value="2012-01-01"/>  
                    至<input type="text" class="Wdate" id="timeEnd" size="12" onclick="WdatePicker()" value="2012-12-30"/>  
    </div>  
	-->
</div>  
</div>


