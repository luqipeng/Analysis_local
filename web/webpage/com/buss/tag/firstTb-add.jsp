<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>first_tb</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="firstTbController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${firstTbPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类别
						</label>
					</td>
					<td class="value">
						<select id="type" name="type">
								<option value="0">类目</option>
								<option value="1">标签</option>
						</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">0:类目；1：标签</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="tabId" name="tabId" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">tabId</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/com.buss.tag/firstTb.js"></script>		