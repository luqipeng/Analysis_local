<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mReportList" checkbox="true" fitColumns="false" title="m_report" actionUrl="mReportController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类目ID"  field="cId" query="true"   width="120"></t:dgCol>
   <t:dgCol title="类目名称"  field="cName" query="true"   width="120"></t:dgCol>
   <t:dgCol title="日期"  field="date" formatter="yyyy-MM-dd"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="销售总数"  field="totalCount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="销售总金额"  field="totalMoney"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客单价"  field="unitPrice"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mReportController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mReportController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mReportController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mReportController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mReportController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="统计销售" icon="icon-search" url="mReportController.do?doStatistics" funname="doSubmit"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/report/mReportList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#mReportListtb").find("input[name='date_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#mReportListtb").find("input[name='date_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mReportController.do?upload', "mReportList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mReportController.do?exportXls","mReportList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mReportController.do?exportXlsByT","mReportList");
}
 </script>