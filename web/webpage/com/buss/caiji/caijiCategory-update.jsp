<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>类目表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="caijiCategoryController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${caijiCategoryPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								类目ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="cId" name="cId" type="text" style="width: 150px" class="inputxt"  
									               
										       value="${caijiCategoryPage.CId}">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类目ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								类目名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="cName" name="cName" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${caijiCategoryPage.CName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类目名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								父级ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="pid" name="pid" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${caijiCategoryPage.pid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">父级ID</label>
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
									               
										       value='${caijiCategoryPage.isCatch}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否采集</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								链接地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${caijiCategoryPage.url}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">链接地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								采集页数:
							</label>
						</td>
						<td class="value">
						     	 <input id="catchPage" name="catchPage" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${caijiCategoryPage.catchPage}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采集页数</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/caiji/caijiCategory.js"></script>		