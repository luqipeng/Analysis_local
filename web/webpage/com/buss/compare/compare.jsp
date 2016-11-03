<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="north" style="padding: 1px; height: 30px;"><span style="display: -moz-inline-box; display: inline-block;"> <span
	style="display: -moz-inline-box; display: inline-block; width: 80px; text-align: right;">选择店铺：</span> <select style="width: 104px"
	width="100" id="storeS">
</select>
 </span> <span
	style="display: -moz-inline-box; display: inline-block;"> <span style="display: -moz-inline-box; display: inline-block; width: 120px; text-align: right;">
	选择当前时间：</span> <select style="width: 104px"
	width="100" id="curTime">
</select> </span> <span style="display: -moz-inline-box; display: inline-block;"> <span style="display: -moz-inline-box; display: inline-block; width: 120px; text-align: right;">选择上期时间：</span> <select style="width: 104px"
	width="100" id="priTime">
</select> </span> 
<span style="display: -moz-inline-box; display: inline-block;"> <input type="button" value="开始对比" onclick="start();"> </span>
<span style="float: right;height:28px;"> 
			 	<a href="javascript:void(0)" class="button b_fr" id="search" data-options="plain:true" onclick="mutiLangListsearch()">搜索</a>
				<input id="langContext" name="langContext" class="input_text b_fr mr5" type="text" value="" size="30" placeholder="请输入搜索内容"  />
			</span>
</div>

  <div region="center" style="padding:1px;">
  <t:datagrid name="commodityAnalysisList" checkbox="true" fitColumns="false" title="商品对比" actionUrl="commodityAnalysisController.do?datagrid2" idField="productId" fit="false" queryMode="group">

   <t:dgCol title="产品ID"  field="productId"    queryMode="group"  width="100"></t:dgCol>
   <t:dgCol title="SKU"  field="sku"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="名称"  field="productName"    queryMode="group"  width="200"></t:dgCol>
   
   <t:dgCol title="当前曝光量"  field="searchExposureCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期曝光量"  field="searchExposurePri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="searchExposureRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="searchExposureDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前浏览量"  field="viewNumCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期浏览量"  field="viewNumPri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="viewNumRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="viewNumDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前转化率"  field="buyerRateCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期转化率"  field="buyerRatePri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="buyerRateRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="buyerRateDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前点击率"  field="searchHitsCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期点击率"  field="searchHitsPri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="searchHitsRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="searchHitsDif"    queryMode="group"  width="80"></t:dgCol>
   
   
   <t:dgCol title="当前加购数"  field="addShoppingCartPersonCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期加购数"  field="addShoppingCartPersonPri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="addShoppingCartPersonRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="addShoppingCartPersonDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前收藏数"  field="collectionPersonCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期收藏数"  field="collectionPersonPri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="collectionPersonRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="collectionPersonDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前支付订单数"  field="payOrdersCur"    queryMode="group"  width="100"></t:dgCol>
   <t:dgCol title="上期支付订单数"  field="payOrdersPri"    queryMode="group"  width="100"></t:dgCol>
   <t:dgCol title="比率"  field="payOrdersRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="payOrdersDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前支付金额"  field="payMoneyCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期支付金额"  field="payMoneyPri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="payMoneyRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="payMoneyDif"    queryMode="group"  width="80"></t:dgCol>
   
   
   <t:dgCol title="当前退款订单数"  field="refundOrdersCur"    queryMode="group"  width="100"></t:dgCol>
   <t:dgCol title="上期退款订单数"  field="refundOrdersPri"    queryMode="group"  width="100"></t:dgCol>
   <t:dgCol title="比率"  field="refundOrdersRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="refundOrdersDif"    queryMode="group"  width="80"></t:dgCol>
   
   <t:dgCol title="当前退款金额"  field="refundAmountCur"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="上期退款金额"  field="refundAmountPri"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="比率"  field="refundAmountRate"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="差值"  field="refundAmountDif"    queryMode="group"  width="80"></t:dgCol>
   
   
   
   
   <t:dgToolBar title="copy" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="曝光量与浏览量" icon="icon-putout" funname="showSearchExposure(0)"></t:dgToolBar>
   <t:dgToolBar title="转化率与点击率" icon="icon-putout" funname="showSearchExposure(1)"></t:dgToolBar>
   <t:dgToolBar title="加购数与收藏数" icon="icon-putout" funname="showSearchExposure(2)"></t:dgToolBar>
   <t:dgToolBar title="支付订单数与支付金额" icon="icon-putout" funname="showSearchExposure(3)"></t:dgToolBar>
   <t:dgToolBar title="退款订单数与退款金额" icon="icon-putout" funname="showSearchExposure(4)"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/compare/commodityAnalysisList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
		initDate();
		initStore();
 });
 var curTab = 0;
 function showSearchExposure(id){
	 curTab = id;
	 var ids = new Array("searchExposure","viewNum","buyerRate","searchHits","addShoppingCartPerson","collectionPerson","payOrders","payMoney","refundOrders","refundAmount");
	 for(var i=0;i<ids.length;i++){
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Cur']").hide();
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Pri']").hide();
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Rate']").hide();
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Dif']").hide();
		 
		 
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Cur']").hide();
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Pri']").hide();
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Rate']").hide();
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Dif']").hide();
	 }
	 switch(id){
		 case 0:  ids = new Array("searchExposure","viewNum");
		 break;
		 case 1: ids =  new Array("buyerRate","searchHits");
		 break;
		 case 2: ids =  new Array("addShoppingCartPerson","collectionPerson");
		 break;
		 case 3: ids =  new Array("payOrders","payMoney");
		 break;
		 case 4: ids =  new Array("refundOrders","refundAmount");
		 break;
	 }
	  for(var i=0;i<ids.length;i++){
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Cur']").show();
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Pri']").show();
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Rate']").show();
		 $(".datagrid-view2").find(".datagrid-header td[field='"+ids[i]+"Dif']").show();
		 
		 
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Cur']").show();
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Pri']").show();
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Rate']").show();
		 $(".datagrid-view2").find(".datagrid-body td[field='"+ids[i]+"Dif']").show();
	  }
 }
 
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

function initStore(){
	var urlstr = "commodityAnalysisController.do?getStores";
	$.ajax({
		url: urlstr,
		success: function (data) {
			data = data.replace("[","").replace("]","").split(",");
			$('#storeS').html("");
			var str = "<option value=\"\">---请选择---</option>";
			for (var i = 0, l; i < data.length; i++) {
					l = data[i];
					str += "<option value=\""+l+"\">"+l+"</option>";
				}
			$('#storeS').append(str);
		},
		error: function (e) {

		}
	});
}

function initDate(){
	var urlstr = "commodityAnalysisController.do?getTimes";
	$.ajax({
		url: urlstr,
		success: function (data) {
			fillData(data);
			
		},
		error: function (e) {

		}
	});
}
		
function fillData(data) {
		$('#curTime').html("");
		$('#priTime').html("");
		var str = "<option value=\"\">---请选择---</option>";
		for (var i = 0, l, tr, title; i < data.length; i++) {
				l = data[i];
				str += "<option value=\""+l+"\">"+l+"</option>";
			}
		$('#curTime').append(str);
		$('#priTime').append(str);
		showSearchExposure(curTab);
    }
function start(){
		//var queryParams=$('#mutiLangList').datagrid('options').queryParams;
		$('#commodityAnalysisList').find('*').each(function(){
			//queryParams[$(this).attr('name')]=$(this).val();
			});
		var store = $("#storeS").val();
		var curTime = $("#curTime").val();
		var priTime = $('#priTime').val();
		
		//$('#mutiLangList').datagrid({url:'mutiLangController.do?datagrid&field=id,langKey,langContext,langCode,',pageNumber:1});
		$("#commodityAnalysisList").datagrid({"onLoadSuccess":function(data){
			showSearchExposure(curTab);
		}}).datagrid('load', {
			store:store,
			curTime:curTime,
			priTime:priTime,
			page:1
	    });
	}
	
 </script>
 
 <script language="javascript" for="window" event="onload"> 
function openTheIndexPage() { 
showSearchExposure(curTab);
}; 
if(document.readyState=="complete"){ 
showSearchExposure(curTab); 
} 
</script>