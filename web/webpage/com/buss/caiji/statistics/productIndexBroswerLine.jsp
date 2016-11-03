<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script language="javascript" type="text/javascript" src="webpage/com/buss/caiji/statistics/myChart.js" ></script>  
<script language="javascript" type="text/javascript" src="webpage/com/buss/caiji/statistics/myChartTheme.js" ></script>  
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
        title:"产品排名变化统计",  
        subTitle:"",  
        xTitle:"时间",  
        yTitle:"排名",  
        line:line,  
        column:column,  
        pie:pie  
    });  
      
    myHighcharts.draw(2,"index",${categoryId},${productId});     
});  
</script>
<div id="containerline" style="width: 80%; height: 80%; margin-left:100px;">
<div id="myChart">  
      
    <!-- 统计图 -->  
    <div id="indexContainer"></div>  
</div>  
</div>


