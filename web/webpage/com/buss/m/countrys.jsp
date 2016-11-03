<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>目的地集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden;" scroll="no">
<t:datagrid  pagination="false"  name="countryList" title="目的地选择"  actionUrl="mCountryController.do?datagridCountry" idField="id" checkbox="true" showRefresh="false"  fit="true"  queryMode="group" onLoadSuccess="initCheck">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="目的地编码"  field="code"    queryMode="single"  width="120" query="true"></t:dgCol>
	<t:dgCol title="英文名称"  field="enName"    queryMode="single"  width="120" query="true"></t:dgCol>
	<t:dgCol title="英文简称"  field="enShort"    queryMode="single"  width="120" query="true"></t:dgCol>
	<t:dgCol title="中文名称"  field="cnName"    queryMode="single"  width="120" query="true"></t:dgCol>
	<t:dgCol title="扫描字符串"  field="scanStr"    queryMode="single"  width="120" query="true"></t:dgCol>
</t:datagrid>
</body>
</html>
<script type="text/javascript">
function initCheck(data){
	var ids = "${ids}";
	var idArr = ids.split(",");
	for(var i=0;i<idArr.length;i++){
		if(idArr[i]!=""){
			$("#countryList").datagrid("selectRecord",idArr[i]);
		}
	}
}
</script>