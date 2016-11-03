<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="caijiCategoryList" checkbox="true" fitColumns="false" title="类目表" actionUrl="caijiCategoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类目ID"  field="cId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类目名称"  field="cName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="父级ID"  field="pid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否采集"  field="isCatch"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="链接地址"  field="url"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="采集页数"  field="catchPage"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="caijiCategoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="caijiCategoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="caijiCategoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="caijiCategoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="caijiCategoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/caiji/caijiCategoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'caijiCategoryController.do?upload', "caijiCategoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("caijiCategoryController.do?exportXls","caijiCategoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("caijiCategoryController.do?exportXlsByT","caijiCategoryList");
}
 </script>