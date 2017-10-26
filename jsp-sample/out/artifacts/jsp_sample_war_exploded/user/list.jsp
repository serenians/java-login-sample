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
            ${Message}

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
                                                    ${user.firstName}<br/>${user.lastName}<br/>
                                            </div>
                                            <div class="content-row">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-4 col-md-2">
                                                    <a href="/Admin/User/Edit?id=${user.userId}"><i class="glyphicon glyphicon-pencil"></i>&nbsp; Edit </a>
                                                </div>
                                                    <div class="col-xs-6 col-sm-4 col-md-2">
                                                        <a href="javascript:void();" data-role="delete" data-id="${user.userId}"><i class="glyphicon glyphicon-remove"></i>&nbsp; Delete </a>
                                                    </div>
                                                </div>
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
<script type="text/javascript">
    $(function(){
        $(document).on('click','[data-role=delete]', function(){
            $.post('/Admin/User/Delete',{id: $(this).attr('data-id')}, function(response){
                console.log(response);
            })
        });
    })
</script>
<!--footer-->
</body>
</html>
