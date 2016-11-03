<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>m_group</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mGroupController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mGroupPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								店铺名称:
							</label>
						</td>
						<td class="value">
							<select name="storeName">
								<c:forEach items="${storeList}" var="store">
									<option value="${store.nickName}" <c:if test="${store.nickName==mGroupPage.storeName}">selected="selected"</c:if>>
										<t:mutiLang langKey="${store.nickName}"/>
									</option>
								</c:forEach>
							</select>
						     	<%-- <input id="storeName" name="storeName" type="text" style="width: 150px" class="inputxt"
									               
										       value='${mGroupPage.storeName}'>--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">店铺名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								产品ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.productId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								店铺产品链接:
							</label>
						</td>
						<td class="value">
						     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.url}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">店铺产品链接</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								活动名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="activityName" name="activityName" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.activityName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								活动开始:
							</label>
						</td>
						<td class="value">
									  <input id="timeStart" name="timeStart" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${mGroupPage.timeStart}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动开始</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								活动结束:
							</label>
						</td>
						<td class="value">
									  <input id="timeEnd" name="timeEnd" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${mGroupPage.timeEnd}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">活动结束</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								历史最低:
							</label>
						</td>
						<td class="value">
						     	 <input id="historyLow" name="historyLow" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.historyLow}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">历史最低</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								报名价格:
							</label>
						</td>
						<td class="value">
						     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.price}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报名价格</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								报名数量:
							</label>
						</td>
						<td class="value">
						     	 <input id="num" name="num" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.num}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报名数量</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								采购预估:
							</label>
						</td>
						<td class="value">
						     	 <input id="estimated" name="estimated" type="text" style="width: 150px" class="inputxt"  
									               
										       value='${mGroupPage.estimated}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购预估</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								实际采购:
							</label>
						</td>
						<td class="value">
							<input id="actual" name="actual" type="text" style="width: 150px" class="inputxt"
								   <c:if test="${disabled=='false'}">disabled="disabled"</c:if>
								   value='${mGroupPage.actual}'>

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实际采购</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/buss/group/mGroup.js"></script>		