<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_workspace</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mWorkspaceController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mWorkspacePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">


					<tr>
						<td align="right">
							<label class="Validform_label">
								工作组名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="wName" name="wName" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mWorkspacePage.wName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工作组名称</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/ws/mWorkspace.js"></script>