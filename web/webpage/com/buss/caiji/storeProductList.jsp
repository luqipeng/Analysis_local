<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="storeProductList" checkbox="true" fitColumns="false" title="店铺产品" actionUrl="storeProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品ID"  field="productId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品名称"  field="productName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品地址"  field="productUrl"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="图片"  field="productImg"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="价格"  field="productPrice"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="productDsr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="productFB"  field="productFaceback"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分类序号"  field="categoryOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品序号"  field="productOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="店铺ID"  field="storeId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="店铺名称"  field="storeName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="storeFB"  field="storeFaceback"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="storeFBper"  field="storeFacebackper"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分类ID"  field="categoryId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分类序号"  field="categoryIndex"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分类分页数"  field="categoryPageno"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="折扣"  field="discount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="date" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="序号"  field="allIndex"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="storeProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="storeProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="storeProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="storeProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="storeProductController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/caiji/storeProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#storeProductListtb").find("input[name='date_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#storeProductListtb").find("input[name='date_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'storeProductController.do?upload', "storeProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("storeProductController.do?exportXls","storeProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("storeProductController.do?exportXlsByT","storeProductList");
}
 </script>