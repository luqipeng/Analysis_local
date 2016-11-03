<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="attentionProductList" checkbox="true" fitColumns="false" title="attention_product" actionUrl="attentionProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品名称"  field="pid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="wid"  field="wid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="name"  field="name"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="总单数"  field="count"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="1天销量"  field="day1"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="3天销量"  field="day3"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="7天销量"  field="day7"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户等级占比"  field="customerlever"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="国家占比"  field="countrys"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="attentionProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="attentionProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="attentionProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="attentionProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="attentionProductController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/attention/attentionProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'attentionProductController.do?upload', "attentionProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("attentionProductController.do?exportXls","attentionProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("attentionProductController.do?exportXlsByT","attentionProductList");
}
 </script>