<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>attention_product</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="attentionProductController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${attentionProductPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								产品名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="pid" name="pid" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.pid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								wid:
							</label>
						</td>
						<td class="value">
						     	 <input id="wid" name="wid" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.wid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">wid</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								name:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">name</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总单数:
							</label>
						</td>
						<td class="value">
						     	 <input id="count" name="count" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.count}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总单数</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								1天销量:
							</label>
						</td>
						<td class="value">
						     	 <input id="day1" name="day1" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.day1}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">1天销量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								3天销量:
							</label>
						</td>
						<td class="value">
						     	 <input id="day3" name="day3" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.day3}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">3天销量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								7天销量:
							</label>
						</td>
						<td class="value">
						     	 <input id="day7" name="day7" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.day7}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">7天销量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户等级占比:
							</label>
						</td>
						<td class="value">
						     	 <input id="customerlever" name="customerlever" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.customerlever}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户等级占比</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								国家占比:
							</label>
						</td>
						<td class="value">
						     	 <input id="countrys" name="countrys" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${attentionProductPage.countrys}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国家占比</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/attention/attentionProduct.js"></script>		