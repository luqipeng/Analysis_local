<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mExpressList" checkbox="true" fitColumns="false" title="m_express" actionUrl="mExpressController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物流方案"  field="name"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="运送范围"  field="scope"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="重量限制"  field="limitWeight"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单金额限制"  field="limitAmount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否接受带电"  field="isAcceptEle"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物流跟踪"  field="logisticsTracking"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="揽收范围"  field="takingExpress"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="时效"  field="aging"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="赔付上限"  field="payoffCostLimit"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="起重(g)"  field="lifting"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="物流类型"  field="expressType"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="折扣"  field="rate"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mExpressController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mExpressController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mExpressController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mExpressController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mExpressController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/m/mExpressList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mExpressController.do?upload', "mExpressList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mExpressController.do?exportXls","mExpressList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mExpressController.do?exportXlsByT","mExpressList");
}
 </script>