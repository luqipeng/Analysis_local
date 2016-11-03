<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mProductList" checkbox="true" fitColumns="false" title="m_product" actionUrl="mProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品id"  field="pid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品名称"  field="name"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="图片"  field="img"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="url"  field="url"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="昨天销量"  field="day1"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="3天销量"  field="day3"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="7天销量"  field="day7"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="总销量"  field="count"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mProductController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/attention/mProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mProductController.do?upload', "mProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mProductController.do?exportXls","mProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mProductController.do?exportXlsByT","mProductList");
}
 </script>