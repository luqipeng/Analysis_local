<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mGroupList" checkbox="true" fitColumns="false" title="m_group" actionUrl="mGroupController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="店铺名称"  field="storeName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="产品ID"  field="productId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="店铺产品链接"  field="url"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="活动名称"  field="activityName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="活动开始"  field="timeStart" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="活动结束"  field="timeEnd" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="历史最低"  field="historyLow"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="报名价格"  field="price"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="报名数量"  field="num"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="采购预估"  field="estimated"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="实际采购"  field="actual"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mGroupController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mGroupController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mGroupController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mGroupController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mGroupController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/group/mGroupList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#mGroupListtb").find("input[name='timeStart_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#mGroupListtb").find("input[name='timeStart_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#mGroupListtb").find("input[name='timeEnd_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#mGroupListtb").find("input[name='timeEnd_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mGroupController.do?upload', "mGroupList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mGroupController.do?exportXls","mGroupList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mGroupController.do?exportXlsByT","mGroupList");
}
 </script>