<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="caijiStoreList" checkbox="true" fitColumns="false" title="店铺" actionUrl="caijiStoreController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="店铺ID"  field="storeId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="链接地址"  field="storeUrl"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="店铺名称"  field="storeName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="storeFb"  field="storeFaceback"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="storeFbper"  field="storeFacebackper"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否采集"  field="isCatch"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="caijiStoreController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="caijiStoreController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="caijiStoreController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="caijiStoreController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="caijiStoreController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/caiji/caijiStoreList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'caijiStoreController.do?upload', "caijiStoreList");
}

//导出
function ExportXls() {
	JeecgExcelExport("caijiStoreController.do?exportXls","caijiStoreList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("caijiStoreController.do?exportXlsByT","caijiStoreList");
}
 </script>