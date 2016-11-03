<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
    <title><t:mutiLang langKey="jeect.platform"/></title>
    <t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
    <link rel="stylesheet" href="css/4a727f2835a2.css" type="text/css">

    <script src="js/hm.js"></script>
    <script type="text/javascript" async="" src="js/ga.js"></script>
    <script type="text/javascript" src="js/4300e8d50956.js"></script>
    <script src="js/wb.js" type="text/javascript" charset="utf-8"></script>
    <script charset="UTF-8" src="js/query"></script>
    <script src="js/client.js" language="JavaScript"></script>

<style>
    #container {
        width: 1170px;
    }
</style>

</head>
<body id="homepage_body">



<div class="container" id="container">

    <div id="title_block">
        <div class="pull-right" id="title_right_block">

            <form id="search_in_category_and_all_form" action="#" class="pull-right">
                <select style="width: 104px" width="100" id="condition">
                    <option value="productId">产品ID</option>
                    <option value="categoryId">分类ID</option>
                    <option value="storeId">店铺ID</option>
                    <option value="productName">产品名称</option>
                </select>
                <input class="inputxt" id="search" style="width: 300px" type="text">

                <input type="button" class="btn btn-primary" id="search_in_category_and_all_all_search" value="在所有品类中查询" onclick="gotoSearch()">
            </form>
        </div>
        <h1>

            外贸热品榜

        </h1>
    </div>


    <ul class="breadcrumb">


    </ul>

</div>

<div class="container">
    <div class="row">

        <!--
        <div class="span2 left">

            <div class="panel tree hidden-phone">
        <ul class="nav nav-list" id="category_left">
        </ul>
        </div>

        -->
        <%--<div class="panel tree hidden-phone">

        <script type="text/javascript">
            /*热品榜右边栏2*/
            var cpro_id = "u1650562";
        </script>
        <script src="js/c.js" type="text/javascript"></script>

        </div>--%>

    </div>


    <div class="span2 left">
        <ul class="nav nav-list" id="category_left">
        </ul>
        <ul class="nav nav-list" id="tag_left">
        </ul>
    </div>

    <div class="span12 middle" id="main_block">


    </div>

</div>
</div>





<div id="cnzz_stat" style="display:none">
</div>


<script>

    function gotoSearch(){
        var search = document.getElementById("search").value;
        var condition = document.getElementById("condition").value;
        window.open("frontController.do?list&condition="+condition+"&search='"+search+"'");
//        showContent('','frontController.do?list');
    }

    function init() {
        $.ajax({
            url: "frontController.do?category",
            success: function (data) {
                data = eval(data);
                $('#category_left').html("");

                var str_left = "<li class=\" active\" id=\"dropdown-menu-apparel-accessories\"><a href=\"\">首页</a></li><li class=\"divider\"></li>";
                for (var i = 0, o; i < data.length; i++) {
                    o = data[i];
                    if(o.pid == 0){
                        str_left += "<li class=\"dropdown-submenu\" id=\"dropdown-menu-apparel-accessories\"><a href=\"frontController.do?list&categoryId="+o.CId+"\">"+o.CName+"</a><ul class=\"dropdown-menu\">";
                        for(var j = 0,l; j < data.length; j++){
                            l = data[j];
                            if(l.pid == o.id){
                                str_left += "<li class=\"dropdown-submenu\" id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+l.CId+"\">"+l.CName+"</a><ul class=\"dropdown-menu\">";
                                for(var k = 0,m; k < data.length; k++){
                                    m = data[k];
                                    if(m.pid == l.id){
                                        str_left += "<li class=\"dropdown-submenu\" id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+m.CId+"\">"+m.CName+"</a><ul class=\"dropdown-menu\">";
                                        for(var u = 0,n; u < data.length; u++){
                                            n = data[u];
                                            if(n.pid == m.id){
                                                str_left += "<li id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+n.CId+"\">"+n.CName+"</a><ul class=\"dropdown-menu\">";
                                                for(var q = 0,p; q < data.length; q++){
                                                    p = data[q];
                                                    if(p.pid == n.id){
                                                        str_left += "<li id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list&categoryId="+p.CId+"\">"+p.CName+"</a></li>";
                                                    }
                                                }
                                                str_left += "</ul></li>";
                                            }
                                        }
                                        str_left += "</ul></li>";
                                    }
                                }
                                str_left += "</ul></li>";
                            }
                        }
                        str_left += "</ul></li><li class=\"divider\"></li>";
                    }
                }
                $('#category_left').append(str_left);
            },
            error: function (e) {
            }
        });

        $.ajax({
            url: "frontController.do?index_tag",
            success: function (data) {
                data = eval(data);
                $('#main_block').html("");
                var str = "";
                for(var i = 0, o; i < data.length; i++){
                    o = data[i];
                    str += "<div class=\"category-top-list\"><ul class=\"nav nav-pills category-title\"><li><a href=\""+o.mtp.url+"\">"+o.mtp.name+"</a></li></ul><ul class=\"thumbnails\">";
                    for(var j = 0, l, s;j < o.products.length; j++){
                        l = o.products[j];
                        s = l.productName;
                        if(l.productName.length > 30){
                            s = l.productName.substring(0,27)+"...";
                        }
                        if(o.mtp.type==1){

                            if(l.isSeries!=2){
                                str += "<li><a href=\"reportProductController.do?productStatisticTabs&reportType=line&categoryId="+l.categoryId+"&productId="+l.productId+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\">"+(j+1)+"</span><img width=\"70\" src=\""+l.productImg+"\" alt=\""+l.productName+"\"><h6>"+s+"</h6>总销量<span class=\"sale_count\">"+l.productOrders+"件</span><br>单价:<span class=\"price\">"+l.productPrice+"</span><span style=\"float:right;\" onclick=\"window.open('userTagProController.do?goUpdate&productId="+l.productId+"&tagId="+o.mtp.id+"','修改标签','height=200, width=400, top=200, left=500, location=no,resizable=no,toolbar=no,scrollbars=no,status=no  ');return false;\">修改</span><br>排名:<span class=\"sale_count\">"+l.allIndex+"</span><br>今日销量:<span class=\"sale_count\">"+l.todayOrders+"</span><br>昨日销量:<span class=\"sale_count\">"+l.yesterdayOrders+"</span><br></a></li>";
                            }else{
                                str += "<li><a href=\"reportProductController.do?productStatisticTabs&reportType=line&categoryId="+l.categoryId+"&productId="+l.productId+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\">"+(j+1)+"</span><img width=\"70\" src=\""+l.productImg+"\" alt=\""+l.productName+"\"><h6>"+s+"</h6>总销量<span class=\"sale_count\">"+l.productOrders+"件</span><br>单价:<span class=\"price\">"+l.productPrice+"</span><span style=\"float:right;\" onclick=\"window.open('userTagProController.do?goUpdate&productId="+l.productId+"&tagId="+o.mtp.id+"','修改标签','height=200, width=400, top=200, left=500, location=no,resizable=no,toolbar=no,scrollbars=no,status=no  ');return false;\">修改</span><br>排名:<span class=\"sale_count\">"+l.allIndex+"</span><br>今日销量:<span class=\"sale_count\">"+l.todayOrders+"</span><br>昨日销量:<span class=\"sale_count\">"+l.yesterdayOrders+"</span><br>最后采集时间:<br><span class=\"price\">"+format(l.lastDate.time)+"</span><br></a></li>";
                            }

                        }else{
                            str += "<li><a href=\"reportProductController.do?productStatisticTabs&reportType=line&categoryId="+l.categoryId+"&productId="+l.productId+"\" class=\"thumbnail\" target=\"view_window\"><span class=\"badge badge-info sort_order\">"+(j+1)+"</span><img width=\"70\" src=\""+l.productImg+"\" alt=\""+l.productName+"\"><h6>"+s+"</h6>总销量<span class=\"sale_count\">"+l.productOrders+"件</span><br>单价:<span class=\"price\">"+l.productPrice+"</span><br>排名:<span class=\"sale_count\">"+l.allIndex+"</span><br>今日销量:<span class=\"sale_count\">"+l.todayOrders+"</span><br>昨日销量:<span class=\"sale_count\">"+l.yesterdayOrders+"</span><br></a></li>";
                        }

                    }
                    str += "</ul><p class=\"more\"><a href=\""+o.mtp.url+"\"> 更多>>> </a></p></div>";
                }
                $('#main_block').append(str);

            },
            error: function (e) {
            }
        });
        $.ajax({
            url: "frontController.do?tag_list",
            success: function (data) {
                data = eval(data);
                $('#tag_left').html("");
                var str_left = "<li class=\"divider\"></li>";
                for (var i = 0, o; i < data.length; i++) {
                    o = data[i];
                    str_left += "<li id=\"dropdown-menu-apparel-accessories-women\"><a href=\"frontController.do?list_tag&tagId="+o.id+"\">"+o.name+"</a></li><li class=\"divider\"></li>";
                }
                $('#tag_left').append(str_left);
            },
            error: function (e) {
            }
        });
    }
    function format(da)
    {
        var time = new Date(da);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        return y+'-'+add0(m)+'-'+add0(d);
    }
    function add0(m){return m<10?'0'+m:m }
    init();
</script>

</body></html>