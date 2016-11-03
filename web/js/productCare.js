/**
 * Created by LuQP on 2016/8/16.
 */
var xhr;
var start;
var workspace;//定义一个工作组变量来存储
var proList;//定义一个产品列表变量来存储
function cancel(pid,wid){
    var txt=  "确认是否取消关注？";
    var option = {
        title: "",
        btn: parseInt("确认是否取消关注？",2),
        onOk: function(){
            $.ajax({
                url: "frontController.do?delProduct&pid="+pid+"&wid="+wid,
                success: function (data) {
                    var joinOnclick = "";
                    for (var i = 0, l; i < proList.length; i++) {
                        l = proList[i];
                        if(l.productId == pid){
                            joinOnclick = "join('"+l.productId+"');";
                        }
                    }
                    $("#"+pid).find("#cancel").html("加入工作组");
                    $("#"+pid).find("#cancel").attr("onclick",joinOnclick);
                    $("#"+pid).find("#cancel").attr("id","join");
                },
                error: function (e) {

                }
            });
        }
    }
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,option);
}
function join(pid){
    var m;
    for (var i = 0,l; i < proList.length; i++) {
        l = proList[i];
        if(l.productId == pid){
            m = l;
        }
    }
    var txt=  "工作组：";
    var option = {
        title: "加入关注",
        data:workspace,
        btn: parseInt("工作组：",2),
        onOk: function(o){
            $.ajax({
                url: "frontController.do?addProduct&pid="+pid+"&wid="+o+"&name="+ encodeURIComponent(m.productName)+"&img="+ m.productImg+"&price="+ m.productPrice+"&sName="+ m.storeName,
                success: function (data) {
                    $("#"+pid).find("#join").html("取消关注");
                    $("#"+pid).find("#join").attr("onclick","cancel('"+pid+"','"+o+"')");
                    $("#"+pid).find("#join").attr("id","cancel");
                },
                error: function (e) {

                }
            });
        }
    }
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.select,option);
}


function changeSearchText(text){
    document.getElementById("keyword").value=text;
    search(0);
}
function dileteKey(id){
    var txt=  "确认是否删除？";
    var option = {
        title: "",
        btn: parseInt("确认是否删除？",2),
        onOk: function(){
            $.ajax({
                url: "frontController.do?delKeyword&id="+id,
                success: function (data) {
                    init();
                },
                error: function (e) {

                }
            });
        },
        onCancel:function(){

        },
        onClose:function(){

        }
    }
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,option);
}
function removeWs(id){
    var txt=  "确认是否删除？";
    var option = {
        title: "",
        btn: parseInt("确认是否删除？",2),
        onOk: function(){
            document.getElementById("ws"+id).style.display = "none";
            $.ajax({
                url: "frontController.do?removeWs&id="+id,
                success: function (data) {

                },
                error: function (e) {

                }
            });
        },
        onCancel:function(){

        },
        onClose:function(){

        }
    }
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,option);
}
function addKey(id){
    window.wxc.xcConfirm("关键字", window.wxc.xcConfirm.typeEnum.input,{
        onOk:function(v){
            $.ajax({
                url: "frontController.do?addKeyword&id="+id+"&keyword="+encodeURI(encodeURI(v)),
                success: function (data) {
                    init();
                },
                error: function (e) {

                }
            });
        }
    });
}
function addWorkspace(levelId){
    window.wxc.xcConfirm("工作组名称", window.wxc.xcConfirm.typeEnum.input,{
        onOk:function(v){
            $.ajax({
                url: "frontController.do?addWorkspace&name="+encodeURI(encodeURI(v))+"&levelId="+levelId,
                success: function (data) {
                    init();
                },
                error: function (e) {

                }
            });
        }
    });
}
function init() {

    $('#search_in_category_and_all_form').bind('input propertychange', function(obj) {
        search(1);
    });

    //初始化工作组
    $.ajax({
        url: "frontController.do?ws_list",
        success: function (data) {
            data = eval(data);
            workspace = data;
            $('#care').html("");
            $('#care1').html("");
            $('#care2').html("");
            $('#care3').html("");
            var str_left = "";
            var care="",care1="",care2="",care3="";
            for (var i = 0, o; i < data.length; i++) {
                o = data[i];
                switch(o.levelId){
                    case "1":
                        care += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
                        break;
                    case "2":
                        care1 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
                        break;
                    case "3":
                        care2 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
                        break;
                    case "4":
                        care3 += "<li class=\"list-group-item\" id=\"ws"+ o.id+"\"><span  onclick='goPD("+ o.id+")'>"+ o.workName+"</span><span class=\"glyphicon glyphicon-remove\" onclick=\"removeWs('"+ o.id+"')\" id='remove'></span></li>";
                        break;
                }
            }
            $('#care').append(care);
            $('#care1').append(care1);
            $('#care2').append(care2);
            $('#care3').append(care3);
        },
        error: function (e) {
        }
    });

}
function fillData(data) {
    data = eval(data);
    proList = data;
//		$('#total').html(data.total);
    $('#main_block').html("");
    var str = "<div id=\"category\">";
    if(data.length>4){
        str += "<ul class=\"thumbnails\">";

    }else{
        str += "<ul class=\"thumbnails1\">";
    }
    for (var i = 0, l, tr, title; i < data.length; i++) {
        l = data[i];
        if(l.exist=="1"){
            str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\"javascript:void(0);\" class=\"thumbnail\">" +
            "<span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.productImg+"\" alt=\""+l.productId+"\">&nbsp;&nbsp;&nbsp;价格:US $"+l.productPrice+"<br>" +
            "<span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;"+cutstr(l.productName,80)+"" +
            "<br><span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;销量："+l.productOrdersAll+"" +
            "<br><span id=\"cancel\" class=\"label label-info\" style=\"cursor:hand\" onclick=\"cancel('"+l.productId+"','"+ l.wid+"');\">取消关注</span></a></li>";
        }else{
            str += "<li class=\"sale_amount \" id=\""+l.productId+"\"><a href=\"javascript:void(0);\" class=\"thumbnail\">" +
            "<span class=\"badge badge-info sort_order\"></span><img width=\"220\" src=\""+l.productImg+"\" alt=\""+l.productId+"\">&nbsp;&nbsp;&nbsp;价格:US $"+l.productPrice+"<br>" +
            "<span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;"+cutstr(l.productName,80)+"" +
            "<br><span class=\"badge badge-info sort_order\"></span>&nbsp;&nbsp;&nbsp;销量："+l.productOrdersAll+"" +
            "<br><span id=\"join\" class=\"label label-info\" style=\"cursor:hand\" onclick=\"join('"+l.productId+"');\">加入工作组</span></a></li>";
//				onclick=\"join('"+l.productId+"','"+l.productName+"','"+l.productImg+"');\"
        }


    }
    str += "</ul></div>";
    $('#main_block').append(str);
    var end=new Date().getTime();
    $('#time').html("");
    $('#time').append(end-start);
}


function search(page) {
    if(xhr!=null){
        xhr.abort();
    }


    var keyword = document.getElementById("keyword").value;
    var urlstr = "mWorkspaceController.do?buildHtml&keyword="+keyword+"&page=" + page;
    if(keyword==""){
        return;
    }
    start=new Date().getTime();
    $('#main_block').html("正在拼命加载中......");
    xhr = $.ajax({
        url: urlstr,
        success: function (data) {

            $('#page').jqPaginator('option', {
                totalCounts: data.length
            });
            fillData(data);
        },
        error: function (e) {

        }
    });
}


$('#page').jqPaginator({
    totalCounts: 10,
    pageSize: 48,
    visiblePages: 10,
    currentPage: 1,
    onPageChange: function (num, type) {
        search(num);
    }
});
init();


var GetLength = function (str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};

//js截取字符串，中英文都能用
//如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。
//字符串，长度

/**
 * js截取字符串，中英文都能用
 * @param str：需要截取的字符串
 * @param len: 需要截取的长度
 */
function cutstr(str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}