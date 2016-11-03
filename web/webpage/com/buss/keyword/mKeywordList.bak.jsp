<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mKeywordList" checkbox="true" fitColumns="false" title="m_keyword" actionUrl="mKeywordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="用户id"  field="userId"    queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="关键字"  field="keyword"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="工作组id"  field="wid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mKeywordController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mKeywordController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mKeywordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mKeywordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mKeywordController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/keyword/mKeywordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mKeywordController.do?upload', "mKeywordList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mKeywordController.do?exportXls","mKeywordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mKeywordController.do?exportXlsByT","mKeywordList");
}
 </script>