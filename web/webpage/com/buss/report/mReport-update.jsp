<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_report</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mReportController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mReportPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								cId:
							</label>
						</td>
						<td class="value">
						     	 <input id="cId" name="cId" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mReportPage.cId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">cId</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								date:
							</label>
						</td>
						<td class="value">
									  <input id="date" name="date" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${mReportPage.date}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">date</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								totalCount:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalCount" name="totalCount" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mReportPage.totalCount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">totalCount</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								totalMoney:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalMoney" name="totalMoney" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mReportPage.totalMoney}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">totalMoney</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								unitPrice:
							</label>
						</td>
						<td class="value">
						     	 <input id="unitPrice" name="unitPrice" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mReportPage.unitPrice}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">unitPrice</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/report/mReport.js"></script>		