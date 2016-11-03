<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="storeProductChangeList" checkbox="true" fitColumns="false" title="store_product_change" actionUrl="storeProductChangeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品ID"  field="pId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品名称"  field="pName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="店铺ID"  field="storeId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="time" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="变更前"  field="old"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="变更后"  field="now"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="storeProductChangeController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="storeProductChangeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="storeProductChangeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="storeProductChangeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="storeProductChangeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/compare/storeProductChangeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#storeProductChangeListtb").find("input[name='time_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#storeProductChangeListtb").find("input[name='time_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'storeProductChangeController.do?upload', "storeProductChangeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("storeProductChangeController.do?exportXls","storeProductChangeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("storeProductChangeController.do?exportXlsByT","storeProductChangeList");
}
 </script>