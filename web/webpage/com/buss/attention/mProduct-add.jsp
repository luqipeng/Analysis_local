<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_product</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mProductController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mProductPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品id:
						</label>
					</td>
					<td class="value">
					     	 <input id="pid" name="pid" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片:
						</label>
					</td>
					<td class="value">
					     	 <input id="img" name="img" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							url:
						</label>
					</td>
					<td class="value">
					     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">url</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							昨天销量:
						</label>
					</td>
					<td class="value">
					     	 <input id="day1" name="day1" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">昨天销量</label>
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
								               
								               >
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
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">7天销量</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							总销量:
						</label>
					</td>
					<td class="value">
					     	 <input id="count" name="count" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总销量</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/attention/mProduct.js"></script>		