<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>产品表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="productController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${productPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							产品名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="productName" name="productName" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品链接地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="productUrl" name="productUrl" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品链接地址</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							图片:
						</label>
					</td>
					<td class="value">
					     	 <input id="productImg" name="productImg" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
					     	 <input id="productPrice" name="productPrice" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">价格</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="productDsr" name="productDsr" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							productFB:
						</label>
					</td>
					<td class="value">
					     	 <input id="productFaceback" name="productFaceback" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">productFB</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							分类序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="categoryOrders" name="categoryOrders" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类序号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="productOrders" name="productOrders" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品序号</label>
						</td>
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
							店铺名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeName" name="storeName" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">店铺名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							storeFB:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeFaceback" name="storeFaceback" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">storeFB</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							storeFBper:
						</label>
					</td>
					<td class="value">
					     	 <input id="storeFacebackper" name="storeFacebackper" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">storeFBper</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							分类ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="categoryId" name="categoryId" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							分类序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="categoryIndex" name="categoryIndex" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类序号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							分类分页号:
						</label>
					</td>
					<td class="value">
					     	 <input id="categoryPageno" name="categoryPageno" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类分页号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							折扣:
						</label>
					</td>
					<td class="value">
					     	 <input id="discount" name="discount" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">折扣</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							日期:
						</label>
					</td>
					<td class="value">
							   <input id="date" name="date" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
								                
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="allIndex" name="allIndex" type="text" style="width: 150px" class="inputxt"
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">序号</label>
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
  <script src = "webpage/com/buss/caiji/product.js"></script>		