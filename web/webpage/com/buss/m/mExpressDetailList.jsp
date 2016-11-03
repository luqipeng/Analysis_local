<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mExpressDetailList" checkbox="true" fitColumns="false" title="m_express_detail" actionUrl="mExpressDetailController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="expressId"  field="expressId"    queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="物流方案"  field="expressName"    queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="countryId"  field="countryId"    queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="国家名称"  field="countryName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="配送服务费"  field="deliveryCharge"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="挂号服务费"  field="payCharge"    queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="2KG首重"  field="firstWeight"    queryMode="group"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="续重(每500G)"  field="continueWeight"    queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="续重"  field="addedWeight"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计费重量"  field="billingWeight"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mExpressDetailController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mExpressDetailController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mExpressDetailController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mExpressDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mExpressDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/m/mExpressDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mExpressDetailController.do?upload', "mExpressDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mExpressDetailController.do?exportXls","mExpressDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mExpressDetailController.do?exportXlsByT","mExpressDetailList");
}
 </script>