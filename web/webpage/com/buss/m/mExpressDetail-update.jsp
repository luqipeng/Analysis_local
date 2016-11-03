<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_express_detail</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mExpressDetailController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mExpressDetailPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								expressId:
							</label>
						</td>
						<td class="value">
						     	 <input id="expressId" name="expressId" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.expressId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">expressId</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物流方案:
							</label>
						</td>
						<td class="value">
						     	 <input id="expressName" name="expressName" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.expressName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物流方案</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								countryId:
							</label>
						</td>
						<td class="value">
						     	 <input id="countryId" name="countryId" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.countryId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">countryId</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								国家名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="countryName" name="countryName" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.countryName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国家名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								配送服务费:
							</label>
						</td>
						<td class="value">
						     	 <input id="deliveryCharge" name="deliveryCharge" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.deliveryCharge}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">配送服务费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								挂号服务费:
							</label>
						</td>
						<td class="value">
						     	 <input id="payCharge" name="payCharge" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.payCharge}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">挂号服务费</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								2KG首重:
							</label>
						</td>
						<td class="value">
						     	 <input id="firstWeight" name="firstWeight" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.firstWeight}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">2KG首重</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								续重(每500G):
							</label>
						</td>
						<td class="value">
						     	 <input id="continueWeight" name="continueWeight" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mExpressDetailPage.continueWeight}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">续重(每500G)</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								续重:
							</label>
						</td>
						<td class="value">
							<input id="addedWeight" name="addedWeight" type="text" style="width: 150px" class="inputxt"

								   value='${mExpressDetailPage.addedWeight}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">续重</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								计费重量:
							</label>
						</td>
						<td class="value">
							<input id="billingWeight" name="billingWeight" type="text" style="width: 150px" class="inputxt"

								   value='${mExpressDetailPage.billingWeight}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计费重量</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/m/mExpressDetail.js"></script>		