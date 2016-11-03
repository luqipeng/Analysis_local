<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    .GridViewStyle th, .GridViewStyle td {
        padding: 8px;
        line-height: 1.42857143;
        vertical-align: top;
        border: 1px solid #ddd;
    }</style>
<div class="listBoxGen">
    <div class="listBoxTitleGen">
        <div class="showTitle">賣出记录</div>
    </div>
    <div class="listBoxContentGen">


        <div style="padding-top:10px; padding-bottom:10px; line-height:150%;">
            說明：1、會員提交金幣賣出，針對不同收款方式審核處理時間也有所不同，不過一般都會在24小時內到帳！推荐使用財付通收款，更快到帳！<br>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">2、如果您提交的記錄提示已賣出在24小時卻未到帳，請檢查您的收款帳號是否正确！如果正确可以提交留言給我們進行查詢！
            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、交易系統24小時開放，玩家可以在任何時侯提交記錄，系統處理程序將在每日上午9:00-晚上21:00期間統一自動處理
        </font>
        </div>
        <div>
            <table class="GridViewStyle" cellspacing="0" cellpadding="4" align="Center" rules="all" border="1"
                   id="ctl00_ContentPlaceHolder1_GridView1" style="width:100%;border-collapse:collapse;">
                <tbody id="list">
                <tr class="HeaderStyle" style="font-weight:bold;">
                    <th scope="col">購買會員</th>
                    <th scope="col">搶購金幣數量</th>
                    <th scope="col">操作时间</th>
                    <th scope="col">狀態</th>
                    <th scope="col">操作</th>
                </tr>
                <%--<tr><td align="center" colspan="7">暫無購買記錄！</td></tr>--%>
                </tbody>
            </table>
        </div>
        <div id="ShowZLPager">

            <div class="ZLPagerCss">

            </div>
        </div>

    </div>
    <br><br>
</div>
<script>
    $.ajax({
        url: "frontController.do?datagrid",
        success: function (r) {
            if (r.total > 0) {
                fillData(r);
            } else {
                var str = "<tr><td align=\"center\" colspan=\"7\">暫無卖出記錄！</td></tr>";
                $('#list').append(str);
            }
        },
        error: function (e) {

        }
    });
    
</script>