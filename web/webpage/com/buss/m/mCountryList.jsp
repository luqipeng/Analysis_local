<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mCountryList" checkbox="true" fitColumns="false" title="m_country" actionUrl="mCountryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="目的地编码"  field="code"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="英文名称"  field="enName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="英文简称"  field="enShort"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="中文名称"  field="cnName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="扫描字符串"  field="scanStr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mCountryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mCountryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mCountryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mCountryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mCountryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/m/mCountryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mCountryController.do?upload', "mCountryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mCountryController.do?exportXls","mCountryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mCountryController.do?exportXlsByT","mCountryList");
}
 </script>