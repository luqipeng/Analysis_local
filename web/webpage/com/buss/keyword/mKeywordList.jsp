<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
 <div region="center" style="padding: 1px;">
  <table id="jeecgEasyUIList" style="width: 700px; height: 300px">
   <thead>
   <tr>
    <th field="id" hidden="hidden">编号</th>
    <th field="keyword" width="50">关键字</th>
    <th field="wid" width="50" replace="${workspaceReplace}">工作组名称</th>
    <th field="opt" width="100">操作</th>
   </tr>
   </thead>
  </table>
  <div id="tb2" style="height: 30px;" class="datagrid-toolbar"><span style="float: left;">
<a href="#" id='add' class="easyui-linkbutton" plain="true" icon="icon-add"
   onclick="add('录入','mKeywordController.do?goAdd','jeecgEasyUIList')" id="">录入</a>
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit"
       onclick="update('编辑','mKeywordController.do?goUpdate','jeecgEasyUIList')" id="">编辑</a>
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search"
       onclick="detail('查看','mKeywordController.do?goUpdate','jeecgEasyUIList')" id="">查看</a> </span>
</div>
  <script type="text/javascript">
   // 编辑初始化数据
   function getData(data){
    debugger;
    var rows = [];
    var total = data.total;
    for(var i=0; i<data.rows.length; i++){
     rows.push({
      id: data.rows[i].id,
      keyword: data.rows[i].keyword,
      wid: data.rows[i].wid,
      opt: "[<a href=\"#\" onclick=\"delObj('mKeywordController.do?doDel&id="+data.rows[i].id+"','jeecgEasyUIList')\">删除</a>]"
     });
    }
    var newData={"total":total,"rows":rows};
    return newData;
   }
   // 筛选
   function jeecgEasyUIListsearchbox(value,name){
    var queryParams=$('#jeecgEasyUIList').datagrid('options').queryParams;
    queryParams[name]=value;
    queryParams.searchfield=name;
    $('#jeecgEasyUIList').datagrid('load');
   }
   // 刷新
   function reloadTable(){
    $('#jeecgEasyUIList').datagrid('reload');
   }

   // 设置datagrid属性
   $('#jeecgEasyUIList').datagrid({
    title: '页面不用自定义标签',
    idField: 'id',
    fit:true,
    loadMsg: '数据加载中...',
    pageSize: 10,
    pagination:true,
    sortOrder:'asc',
    rownumbers:true,
    singleSelect:true,
    fitColumns:true,
    showFooter:true,
    url:'mKeywordController.do?datagrid&field=id,keyword,wid',
    toolbar: '#tb2',
    loadFilter: function(data){
     return getData(data);
    }

   });
   //设置分页控件
   $('#jeecgEasyUIList').datagrid('getPager').pagination({
    pageSize: 10,
    pageList: [10,20,30],
    beforePageText: '',
    afterPageText: '/{pages}',
    displayMsg: '{from}-{to}共{total}条',
    showPageList:true,
    showRefresh:true,
    onBeforeRefresh:function(pageNumber, pageSize){
     $(this).pagination('loading');
     $(this).pagination('loaded');
    }
   });
   // 设置筛选
   $('#jeecgEasyUIListsearchbox').searchbox({
    searcher:function(value,name){
     jeecgEasyUIListsearchbox(value,name);
    },
    menu:'#jeecgEasyUIListmm',
    prompt:'请输入查询关键字'
   });
  </script></div>
</div>
