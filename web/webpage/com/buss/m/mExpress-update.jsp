<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_express</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mExpressController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mExpressPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								物流方案:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物流方案</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								运送范围:
							</label>
						</td>
						<td class="value">
						     	 <input id="scope" name="scope" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.scope}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">运送范围</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								重量限制:
							</label>
						</td>
						<td class="value">
						     	 <input id="limitWeight" name="limitWeight" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.limitWeight}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">重量限制</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单金额限制:
							</label>
						</td>
						<td class="value">
						     	 <input id="limitAmount" name="limitAmount" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.limitAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单金额限制</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否接受带电:
							</label>
						</td>
						<td class="value">
						     	 <input id="isAcceptEle" name="isAcceptEle" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.isAcceptEle}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否接受带电</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物流跟踪:
							</label>
						</td>
						<td class="value">
						     	 <input id="logisticsTracking" name="logisticsTracking" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.logisticsTracking}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物流跟踪</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								揽收范围:
							</label>
						</td>
						<td class="value">
						     	 <input id="takingExpress" name="takingExpress" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.takingExpress}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">揽收范围</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								时效:
							</label>
						</td>
						<td class="value">
						     	 <input id="aging" name="aging" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.aging}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时效</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								赔付上限:
							</label>
						</td>
						<td class="value">
						     	 <input id="payoffCostLimit" name="payoffCostLimit" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressPage.payoffCostLimit}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">赔付上限</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								起重（克）:
							</label>
						</td>
						<td class="value">
							<input id="lifting" name="lifting" type="text" style="width: 150px" class="inputxt"

								   value='${mExpressPage.lifting}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">起重</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物流类型:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="expressType" typeGroupCode="expreType" hasLabel="false" defaultVal="${mExpressPage.expressType}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物流类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								折扣:
							</label>
						</td>
						<td class="value">
							<input id="rate" name="rate" type="text" style="width: 150px" class="inputxt"

								   value='${mExpressPage.rate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">折扣</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/m/mExpress.js"></script>		