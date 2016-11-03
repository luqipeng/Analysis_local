<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_ws_pro</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wsProController.do?doAdd" tiptype="1" beforeSubmit="window.close();">
					<input id="id" name="id" type="hidden" value="${wsProPage.id }">
	  <input id="name" name="name" type="hidden" value="${name }">
	  <input id="img" name="img" type="hidden" value="${img }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作组:
						</label>
					</td>
					<td class="value">
						<select id="wid" name="wid" datatype="*">
							<c:forEach items="${wsList}" var="ws">
								<option value="${ws.id }">${ws.workName}</option>
							</c:forEach>
						</select>
						<%--<t:dictSelect dictTable="m_workspace" dictText="w_name" dictField="id" field="wid" hasLabel="false"></t:dictSelect>--%>
					     	<%-- <input id="wid" name="wid" type="text" style="width: 150px" class="inputxt"

								               >--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工作组</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品id:
						</label>
					</td>
					<td class="value">
					     	 <input id="pid" name="pid" type="text" style="width: 150px" class="inputxt" readonly
								               value="${productId}"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品id</label>
						</td>
				</tr>
				<tr style="margin-top: 50px;">
					<td align="center" colspan="2">
						<input type="submit" value="提交" id="submit" onclick="window.close();"/>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/wspro/wsPro.js"></script>
