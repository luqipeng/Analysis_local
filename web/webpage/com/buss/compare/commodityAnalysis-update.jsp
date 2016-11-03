<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>commodity_analysis</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="commodityAnalysisController.do?doUpdate" tiptype="1">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="cId" name="cId" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.cId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								商品标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="cTitle" name="cTitle" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.cTitle}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								老买家支付买家数:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldPayBuyer" name="oldPayBuyer" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldPayBuyer}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家支付买家数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								老买家下单买家数:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldOrderBuyer" name="oldOrderBuyer" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldOrderBuyer}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家下单买家数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								老买家支付金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldPayMoney" name="oldPayMoney" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldPayMoney}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家支付金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								老买家支付订单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldPayBuyerOrders" name="oldPayBuyerOrders" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldPayBuyerOrders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家支付订单数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								老买家下单订单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldBuyerOrders" name="oldBuyerOrders" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldBuyerOrders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家下单订单数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								老买家浏览-下单转化率:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldBuyerRate" name="oldBuyerRate" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldBuyerRate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家浏览-下单转化率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								老买家商品页访客数:
							</label>
						</td>
						<td class="value">
						     	 <input id="oldBuyerVisitors" name="oldBuyerVisitors" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.oldBuyerVisitors}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">老买家商品页访客数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								搜索曝光量:
							</label>
						</td>
						<td class="value">
						     	 <input id="searchExposure" name="searchExposure" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.searchExposure}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">搜索曝光量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品页浏览量:
							</label>
						</td>
						<td class="value">
						     	 <input id="viewNum" name="viewNum" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.viewNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品页浏览量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								商品页访客数:
							</label>
						</td>
						<td class="value">
						     	 <input id="visitorsNum" name="visitorsNum" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.visitorsNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品页访客数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								buyerRate:
							</label>
						</td>
						<td class="value">
						     	 <input id="buyerRate" name="buyerRate" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.buyerRate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">buyerRate</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								搜索点击率:
							</label>
						</td>
						<td class="value">
						     	 <input id="searchHits" name="searchHits" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.searchHits}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">搜索点击率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								平均停留时长:
							</label>
						</td>
						<td class="value">
						     	 <input id="avgStayTime" name="avgStayTime" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.avgStayTime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平均停留时长</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								添加购物车次数:
							</label>
						</td>
						<td class="value">
						     	 <input id="addShoppingCartCount" name="addShoppingCartCount" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.addShoppingCartCount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">添加购物车次数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								加购物车人数:
							</label>
						</td>
						<td class="value">
						     	 <input id="addShoppingCartPerson" name="addShoppingCartPerson" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.addShoppingCartPerson}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">加购物车人数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								添加收藏次数:
							</label>
						</td>
						<td class="value">
						     	 <input id="collectionNum" name="collectionNum" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.collectionNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">添加收藏次数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								加收藏夹人数:
							</label>
						</td>
						<td class="value">
						     	 <input id="collectionPerson" name="collectionPerson" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.collectionPerson}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">加收藏夹人数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								下单买家数:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderBuyer" name="orderBuyer" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.orderBuyer}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">下单买家数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								下单订单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="placingOrders" name="placingOrders" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.placingOrders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">下单订单数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								支付买家数:
							</label>
						</td>
						<td class="value">
						     	 <input id="payBuyer" name="payBuyer" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.payBuyer}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付买家数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付订单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="payOrders" name="payOrders" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.payOrders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付订单数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								支付金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="payMoney" name="payMoney" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.payMoney}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								风控订单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="riskControlOrders" name="riskControlOrders" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.riskControlOrders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">风控订单数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								风控金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="riskControlMoney" name="riskControlMoney" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.riskControlMoney}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">风控金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								退款金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="refundAmount" name="refundAmount" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.refundAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">退款金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								退款订单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="refundOrders" name="refundOrders" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.refundOrders}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">退款订单数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								日期:
							</label>
						</td>
						<td class="value">
						     	 <input id="time" name="time" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${commodityAnalysisPage.time}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/compare/commodityAnalysis.js"></script>		