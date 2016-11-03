<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="commodityAnalysisList" checkbox="true" fitColumns="false" title="commodity_analysis" actionUrl="commodityAnalysisController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="商品ID"  field="sku.productId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商品标题"  field="cTitle"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家支付买家数"  field="oldPayBuyer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家下单买家数"  field="oldOrderBuyer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家支付金额"  field="oldPayMoney"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家支付订单数"  field="oldPayBuyerOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家下单订单数"  field="oldBuyerOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家浏览-下单转化率"  field="oldBuyerRate"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="老买家商品页访客数"  field="oldBuyerVisitors"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="搜索曝光量"  field="searchExposure"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商品页浏览量"  field="viewNum"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="商品页访客数"  field="visitorsNum"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="buyerRate"  field="buyerRate"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="搜索点击率"  field="searchHits"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="平均停留时长"  field="avgStayTime"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="添加购物车次数"  field="addShoppingCartCount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="加购物车人数"  field="addShoppingCartPerson"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="添加收藏次数"  field="collectionNum"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="加收藏夹人数"  field="collectionPerson"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="下单买家数"  field="orderBuyer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="下单订单数"  field="placingOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付买家数"  field="payBuyer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付订单数"  field="payOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付金额"  field="payMoney"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="风控订单数"  field="riskControlOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="风控金额"  field="riskControlMoney"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="退款金额"  field="refundAmount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="退款订单数"  field="refundOrders"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="time"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="commodityAnalysisController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="commodityAnalysisController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="commodityAnalysisController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="commodityAnalysisController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="commodityAnalysisController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/compare/commodityAnalysisList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'commodityAnalysisController.do?upload', "commodityAnalysisList");
}

//导出
function ExportXls() {
	JeecgExcelExport("commodityAnalysisController.do?exportXls","commodityAnalysisList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("commodityAnalysisController.do?exportXlsByT","commodityAnalysisList");
}
 </script>