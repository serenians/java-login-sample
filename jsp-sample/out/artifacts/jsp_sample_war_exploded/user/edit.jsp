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
<%@ include file="/layout/top_navigation.html"%>
<!--header-->
<div class="container-fluid">
    <!--documents-->
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
            <%@include file="/layout/navigation.html"%>
        </div>
        <div class="col-xs-12 col-sm-9 content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a> Update User</h3>
                </div>
                <div class="panel-body">
                    <div class="content-row">
                        <form method="post" action="/Admin/User/Edit" role="form" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label">Username</label>
                                <div class="col-md-10">
                                    <input type="text" readonly placeholder="User Name" id="UserName" class="form-control" name="UserName" value="${User.username}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">First name</label>
                                <div class="col-md-10">
                                    <input type="hidden" name="UserId" value="${User.userId}">
                                    <input type="text" required="" placeholder="First Name" id="FirstName" class="form-control" name="FirstName" value="${User.firstName}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Last name</label>
                                <div class="col-md-10">
                                    <input type="text" required="" placeholder="Last Name" id="LastName" class="form-control" name="LastName" value="${User.lastName}">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button class="btn btn-info" type="submit">Update</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div><!-- panel body -->
            </div>
        </div><!-- content -->
    </div>
</div>
<!--footer-->
</body>
</html>
