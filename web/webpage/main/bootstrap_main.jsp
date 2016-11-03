<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title><t:mutiLang langKey="jeect.platform"/></title>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css" type="text/css"></link>
<style type="text/css">
	body{
		overflow-x: hidden;
		overflow-y: hidden;
	}
.bootstrap-menu i {
	display: inline-block;
	width: 14px;
	height: 14px;
	margin-top: 1px;
	*margin-right: .3em;
	line-height: 14px;
	vertical-align: text-top;
	background-image: url("plug-in/bootstrap/img/glyphicons-halflings.png");
	background-repeat: no-repeat;
}

.bootstrap-center {
	height: 92%;
	width: 100%;
	position: absolute;
	overflow-y: no;
	margin-top: -20px;
	margin-left: 2px;
	margin-right: 2px;
}

.bootstrap-icon {
	display: inline-block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	vertical-align: text-top;
	background-repeat: no-repeat;
	background-image: url("plug-in/accordion/images/pictures.png");
}

.footer {
	margin-top: 10px;
}
/*.navbar-fixed-top {*/
	/*/!* top: 0; *!/*/
	/*margin-top: 45px;*/
/*}*/
</style>
	<script>
		var to = function(id){
			var $ul = $('#nav');
			$ul.find('li').each(function(i, dom) {
				$(this).removeClass('active');
			});
			$("#"+id).addClass('active');
		}
		window.onload=function(){
			showContent('','loginController.do?home');
		}

	</script>
</head>
<body>
<!-- 头部菜单导航-->
<div id="header">
<div class="navbar">
<div class="navbar-inner">
<div class="container-fluid">
	<%--<a class="brand" href="" target="_blank"><t:mutiLang langKey="common.platform"/> &nbsp;&nbsp;<span class="slogan"></span></a>--%>
	<%--<ul class="nav pull-right usernav">
		<li style="line-height: 43px;"><span style="color: #CC33FF"><t:mutiLang langKey="common.user"/>:</span><span style="color: #666633">(${userName })</span> <span style="color: #CC33FF"><t:mutiLang langKey="common.role"/></span>:<span style="color: #666633">${roleName
				}</span></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle avatar" data-toggle="dropdown"> <i class="icon-wrench"></i> <span class="txt"><t:mutiLang langKey="common.control.panel"/></span> <b class="caret"></b> </a>
			<ul class="dropdown-menu">
				<li onclick="openwindow('<t:mutiLang langKey="common.profile"/>','userController.do?userinfo')"><a href="javascript:;"><i class="icon-user"></i> <t:mutiLang langKey="common.profile"/></a></li>
				<li onclick="add('<t:mutiLang langKey="common.change.password"/>','userController.do?changepassword')"><a href="javascript:;"><i class="icon-pencil"></i> <t:mutiLang langKey="common.change.password"/></a></li>
				<li onclick="openwindow('<t:mutiLang langKey="common.ssms.getSysInfos"/>','tSSmsController.do?getSysInfos')"><a href="javascript:;"><i class="icon-pencil"></i> <t:mutiLang langKey="common.ssms.getSysInfos"/></a></li>
				<li onclick="add('<t:mutiLang langKey="common.change.style"/>','userController.do?changestyle')"><a href="javascript:;"><i class="icon-retweet"></i> <t:mutiLang langKey="common.change.style"/></a></li>
			</ul>
		</li>
		<li class="dropdown"><a href="#" class="dropdown-toggle avatar" data-toggle="dropdown"> <i class="icon-arrow-left"></i> <span class="txt"><t:mutiLang langKey="common.logout"/></span> <b class="caret"></b> </a>
			<ul class="dropdown-menu">
				<li onclick="exit('loginController.do?logout','<t:mutiLang langKey="common.exit.confirm"/>',1);"><a href="javascript:;"><i class="icon-off"></i><t:mutiLang langKey="common.exit"/></a></li>
				<li onclick="exit('loginController.do?admin','<t:mutiLang langKey="common.exit.confirm"/>',1);"><a href="javascript:;"><i class="icon-off"></i><t:mutiLang langKey="common.admin"/></a></li>
			</ul>
		</li>
	</ul>--%>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="#" class="brand">采集管理系统</a>
				<div class="nav-collapse">
					<ul class="nav" id="nav">
						<li class="active" id="home"  onclick="to('home');showContent('','loginController.do?home')"><a>首页</a></li>
						<li id="today" onclick="to('today');showContent('','frontController.do?list')"><a>今日销量</a></li>
						<li id="ru" onclick="to('ru');showContent('','frontController.do?ru')"><a>俄团</a></li>
						<li id="ws" onclick="to('ws');showContent('','frontController.do?ws')"><a>工作组</a></li>
						<li id="store" onclick="to('store');showContent('','frontController.do?store')"><a>店铺</a></li>
						<li id="group" onclick="to('group');showContent('','frontController.do?group')"><a>团购管理</a></li>
						<li id="key" onclick="to('key');showContent('','http://www.aliexpress.com/')"><a>速卖通</a></li>
					</ul>
					<a class="btn btn-primary" target="_blank" href="loginController.do?admin">后台管理</a>
				</div>
			</div>
		</div>
	</div>
	<div class="nav-no-collapse bootstrap-menu">


</div>
<!-- /.nav-collapse --></div>
</div>
<!-- /navbar-inner --></div>
<!-- /navbar --></div>
<!-- End #header -->

<!-- 中间 -->
<div id="wrapper" class="bootstrap-center"></div>

<!-- 底部 -->
<footer class="footer">
<div class="container">
<%--<ul class="footer-links">
	<li style="display: inline;">&copy; <t:mutiLang langKey="common.copyright"/><a href="http://www.jeecg.org" title="JEECG Framework  <t:mutiLang langKey="system.version.number"/>">JEECG Framework  <t:mutiLang langKey="system.version.number"/></a></li>
	<li style="display: inline;">&nbsp;<t:mutiLang langKey="common.copyright"/><t:mutiLang langKey="common.browser"/>:<a title="JEECG Framework  <t:mutiLang langKey="system.version.number"/>" href="http://www.jeecg.org">JEECG Framework  <t:mutiLang langKey="system.version.number"/></a></li>
</ul>--%>
</div>
</footer>
<script type="text/javascript" src="plug-in/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="plug-in/accordion/js/bootstrap_main.js"></script>
</body>
</html>