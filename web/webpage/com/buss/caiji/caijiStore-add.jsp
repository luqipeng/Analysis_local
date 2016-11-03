<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>店铺</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="caijiStoreController.do?doAdd" tiptype="1">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							店铺ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeId" name="storeId" type="text" style="width: 150px" class="inputxt"
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">店铺ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							链接地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeUrl" name="storeUrl" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">链接地址</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							店铺名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeName" name="storeName" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">店铺名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							storeFb:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeFaceback" name="storeFaceback" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">storeFb</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							storeFbper:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeFacebackper" name="storeFacebackper" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">storeFbper</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否采集:
						</label>
					</td>
					<td class="value">
					     	 <input id="isCatch" name="isCatch" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否采集</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/caiji/caijiStore.js"></script>		