<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:1px;">
        <t:datagrid name="mRechargeList" checkbox="true" fitColumns="false" title="运费试算"
                    actionUrl="mRechargeController.do?datagrid" idField="id" fit="true" queryMode="group">
            <t:dgCol title="id" field="id" hidden="true" queryMode="group" width="120"></t:dgCol>
            <t:dgCol title="渠道名称" field="name" queryMode="group" width="160"></t:dgCol>
            <t:dgCol title="目的地" field="country" queryMode="single" width="120"></t:dgCol>
            <t:dgCol title="结算重量" field="weight" queryMode="group" width="120"></t:dgCol>
            <t:dgCol title="金额" field="amount" queryMode="group" width="120"></t:dgCol>
            <t:dgCol title="包裹个数" field="packageCount" queryMode="group" width="120"></t:dgCol>
            <%--<t:dgCol title="燃油附加" field="baf" queryMode="group" width="120"></t:dgCol>--%>
            <%--<t:dgCol title="币种" field="currency" queryMode="group" width="120"></t:dgCol>--%>
            <t:dgCol title="预计递送时间" field="predictTime" queryMode="group" width="120"></t:dgCol>
            <t:dgCol title="可走物品" field="canGoods" queryMode="group" width="200"></t:dgCol>
            <t:dgCol title="报价备注" field="remark" queryMode="group" width="120"></t:dgCol>
            <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
            <%--<t:dgDelOpt title="删除" url="mRechargeController.do?doDel&id={id}"/>
            <t:dgToolBar title="录入" icon="icon-add" url="mRechargeController.do?goAdd" funname="add"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="icon-edit" url="mRechargeController.do?goUpdate"
                         funname="update"></t:dgToolBar>
            <t:dgToolBar title="批量删除" icon="icon-remove" url="mRechargeController.do?doBatchDel"
                         funname="deleteALLSelect"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="icon-search" url="mRechargeController.do?goUpdate"
                         funname="detail"></t:dgToolBar>
            <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
            <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
            <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
            <t:dgToolBar title="查看" icon="icon-search" url="mRechargeController.do?goUpdate"
                         funname="detail"></t:dgToolBar>
        </t:datagrid>
        <form id="search_in_category_and_all_form" action="#">
            <div id="userListtb" style="padding: 3px; height: 75px">
                <div align="left">
                    目的地：<input name="country" class="inputxt" value="${cnName }" id="cnName" datatype="*" />
                    <t:choose hiddenName="id" hiddenid="id" url="mCountryController.do?countrys" name="countryList"
                              icon="icon-search" title="目的地列表" textname="cnName" isclear="true" isInit="true" width="1200px" height="600px"></t:choose>
                    实重：<input name="weight" class="inputxt" value="" id="weight" datatype="s2-10" />&nbsp;克<span class="Validform_checktip"></span>&nbsp;&nbsp;
                    长宽高：<input name="length" class="inputxt" value="" id="length" datatype="*" style="width: 50px;" />*<input name="width" class="inputxt" value="" id="width" datatype="*" style="width: 50px;"/>*
                    <input name="height" class="inputxt" value="" id="height" datatype="*" style="width: 50px;"/>CM&nbsp;&nbsp;&nbsp;
                    物流类型：<t:dictSelect field="expressType" typeGroupCode="expreType" hasLabel="false" defaultVal="standard"></t:dictSelect>
                </div>
                <div  style="float: right;">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchRechargeList();">查询</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="reset();">重置</a>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="webpage/com/buss/m/mRechargeList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //给时间控件加上样式
        $("#mRechargeListtb").find("input[name='predictTime_begin']").attr("class", "Wdate").attr("style", "height:20px;width:90px;").click(function () {
            WdatePicker({dateFmt: 'yyyy-MM-dd'});
        });
        $("#mRechargeListtb").find("input[name='predictTime_end']").attr("class", "Wdate").attr("style", "height:20px;width:90px;").click(function () {
            WdatePicker({dateFmt: 'yyyy-MM-dd'});
        });
    });
   function searchRechargeList(){
       debugger;
       var cnName = document.getElementById("cnName").value;
       var weight = document.getElementById("weight").value;
       var expressType = document.getElementsByName("expressType")[0].value;
       if(weight == ""){
           return;
       }
       $('#mRechargeList').datagrid( { url:"mRechargeController.do?datagrid",queryParams:{cnName:cnName,weight:weight,expressType:expressType	},method:"post"});
    }
    function reset(){
        document.getElementById("search_in_category_and_all_form").reset();
    }

    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'mRechargeController.do?upload', "mRechargeList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("mRechargeController.do?exportXls", "mRechargeList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("mRechargeController.do?exportXlsByT", "mRechargeList");
    }
</script>