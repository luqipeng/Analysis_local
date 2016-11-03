<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_recharge</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mRechargeController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mRechargePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">渠道名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							目的地:
						</label>
					</td>
					<td class="value">
						<%--<input name="id" name="id" type="hidden" value="${id}" id="id">--%>
						<input name="country" class="inputxt" value="${country }" id="enName" readonly="readonly" datatype="*" />
						<t:choose hiddenName="id" hiddenid="id" url="mCountryController.do?countrys" name="countryList"
								  icon="icon-search" title="目的地列表" textname="enName" isclear="true" isInit="true" width="1200px" height="600px"></t:choose>
						<span class="Validform_checktip"></span>
					     	 <%--<input id="country" name="country" type="text" style="width: 150px" class="inputxt"
								               
								               >--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">目的地</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结算重量:
						</label>
					</td>
					<td class="value">
					     	 <input id="weight" name="weight" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结算重量</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							燃油附加:
						</label>
					</td>
					<td class="value">
					     	 <input id="baf" name="baf" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">燃油附加</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							币种:
						</label>
					</td>
					<td class="value">
					     	 <input id="currency" name="currency" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">币种</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预计递送时间:
						</label>
					</td>
					<td class="value">
							   <input id="predictTime" name="predictTime" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
								                
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预计递送时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							可走物品:
						</label>
					</td>
					<td class="value">
					     	 <input id="canGoods" name="canGoods" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">可走物品</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报价备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报价备注</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/m/mRecharge.js"></script>		