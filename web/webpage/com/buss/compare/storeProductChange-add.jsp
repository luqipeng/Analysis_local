<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>store_product_change</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="storeProductChangeController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${storeProductChangePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							日期:
						</label>
					</td>
					<td class="value">
							   <input id="time" name="time" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
								                
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="type" name="type" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品ID:
						</label>
					</td>
					<td class="value">
						<input id="pId" name="pId" type="text" style="width: 150px" class="inputxt"

								>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">产品ID</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品名称:
						</label>
					</td>
					<td class="value">
						<input id="pName" name="pName" type="text" style="width: 150px" class="inputxt"

							  >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">产品名称</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							店铺ID:
						</label>
					</td>
					<td class="value">
						<input id="storeId" name="storeId" type="text" style="width: 150px" class="inputxt"

								>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">店铺ID</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							变更前:
						</label>
					</td>
					<td class="value">
					     	 <input id="old" name="old" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">变更前</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							变更后:
						</label>
					</td>
					<td class="value">
					     	 <input id="now" name="now" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">变更后</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/compare/storeProductChange.js"></script>		