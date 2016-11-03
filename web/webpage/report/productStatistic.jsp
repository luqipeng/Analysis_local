<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- context path -->
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.src.js"></script>
<script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/modules/exporting.src.js"></script>
<IFRAME marginWidth=0 
      marginHeight=0 src="../productBroswer.jsp?reportType=line&categoryId=${categoryId}&productId=${productId}" frameBorder=0 
      width=100% scrolling=yes height=210></IFRAME>
	  <IFRAME marginWidth=0 
      marginHeight=0 src="productController.do?productBroswer&reportType=price&categoryId=${categoryId}&productId=${productId}" frameBorder=0 
      width=100% scrolling=yes height=210></IFRAME>
	  <IFRAME marginWidth=0 
      marginHeight=0 src="productController.do?productBroswer&reportType=index&categoryId=${categoryId}&productId=${productId}" frameBorder=0 
      width=100% scrolling=yes height=210></IFRAME>
