<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>commodity_manage</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="commodityManageController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${commodityManagePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">编号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品Logo:
						</label>
					</td>
					<td class="value">
					     	 <input id="logo" name="logo" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品Logo</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							平台商品标识:
						</label>
					</td>
					<td class="value">
					     	 <input id="platformLogo" name="platformLogo" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平台商品标识</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							平台商品名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="platformName" name="platformName" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平台商品名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							店铺名:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeName" name="storeName" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">店铺名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							已确认:
						</label>
					</td>
					<td class="value">
					     	 <input id="isCheck" name="isCheck" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">已确认</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							平台来源:
						</label>
					</td>
					<td class="value">
					     	 <input id="platform" name="platform" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平台来源</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							关联产品名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="productName" name="productName" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联产品名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							关联产品SKU:
						</label>
					</td>
					<td class="value">
					     	 <input id="sku" name="sku" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联产品SKU</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/compare/commodityManage.js"></script>		