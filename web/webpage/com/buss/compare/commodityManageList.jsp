<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="commodityManageList" checkbox="true" fitColumns="false" title="commodity_manage" actionUrl="commodityManageController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="ID"  field="id"  hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="code" query="true"   width="120"></t:dgCol>
   <t:dgCol title="商品Logo"  field="logo"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="平台商品标识"  field="platformLogo"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="平台商品名称"  field="platformName"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="店铺名"  field="storeName" query="true"  width="120"></t:dgCol>
   <t:dgCol title="已确认"  field="isCheck" query="true"   width="120"></t:dgCol>
   <t:dgCol title="平台来源"  field="platform"  query="true"   width="120"></t:dgCol>
   <t:dgCol title="关联产品名称"  field="productName" query="true"  width="120"></t:dgCol>
   <t:dgCol title="关联产品ID"  field="productId" query="true"  width="120"></t:dgCol>
   <t:dgCol title="关联产品SKU"  field="sku" query="true"    width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="commodityManageController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="commodityManageController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="commodityManageController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="commodityManageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="commodityManageController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/compare/commodityManageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'commodityManageController.do?upload', "commodityManageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("commodityManageController.do?exportXls","commodityManageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("commodityManageController.do?exportXlsByT","commodityManageList");
}
 </script>