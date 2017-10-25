<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Slick</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="favicon_16.ico"/>
    <link rel="bookmark" href="favicon_16.ico"/>
    <!-- site css -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/site.css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,800,700,400italic,600italic,700italic,800italic,300italic" rel="stylesheet" type="text/css">
    <!-- <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'> -->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/assets/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/assets/js/html5shiv.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.fs.selecter.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.fs.stepper.min.js"></script>
    <script type="text/javascript" src="/assets/js/icheck.min.js"></script>
    <script type="text/javascript" src="/assets/js/respond.min.js"></script>
    <script type="text/javascript" src="/assets/js/application.js"></script>
</head>
<body>
<!--nav-->
<nav role="navigation" class="navbar navbar-custom">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button data-target="#bs-content-row-navbar-collapse-5" data-toggle="collapse" class="navbar-toggle" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand"> Slick-Admin</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div id="bs-content-row-navbar-collapse-5" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="getting-started.html">Getting Started</a></li>
                <li class="active"><a href="index.html">Documentation</a></li>
                <!-- <li class="disabled"><a href="#">Link</a></li> -->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">Silverbux <b class="caret"></b></a>
                    <ul role="menu" class="dropdown-menu">
                        <li class="dropdown-header">Setting</li>
                        <li><a href="#">Action</a></li>
                        <li class="divider"></li>
                        <li class="active"><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li class="disabled"><a href="#">Signout</a></li>
                    </ul>
                </li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--header-->
<div class="container-fluid">
    <!--documents-->
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
            <%@include file="/layout/navigation.html"%>
        </div>
        <div class="col-xs-12 col-sm-9 content">


            <div class ="row">
                <div class ="col-xs-12 text-right">
                    <a class="btn btn-primary btn-sm" href="/Admin/User/Create"><i class="glyphicon glyphicon-plus-sign"></i>&nbsp; Add New </a>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a> Users</h3>
                </div>
                <div class="panel-body">
                    <div class="content-row">

                        <div class="panel-group panel-group-lists" id="accordion2" style="">
                            <c:forEach var="user" items="${UserList}">
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion2" href="#${user.userId}" class="collapsed">${user.username}</a>
                                        </h4>
                                    </div>
                                    <div id="${user.userId}" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="content-row">

                                                    ${user.firstName}
                                                <br/>                                                        ${user.lastName}
                                                <br/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>

                    </div>
                </div><!-- panel body -->
            </div>
        </div><!-- content -->
    </div>
</div>
<!--footer-->
</body>
</html>
