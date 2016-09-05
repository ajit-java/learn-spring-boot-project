<div ng-app="ExpertForum" id="ExpertForum" ng-cloak>
<!-- find a cms solution -->
<div> <#--head-->
    <title>Scout24 Expert Forum Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="//www.static-immobilienscout24.de/fro/core/2.1.0/css/core.min.css" />
    <!--[if (lt IE 9) & (!IEMobile)]>
    <link rel="stylesheet" type="text/css" href="//www.static-immobilienscout24.de/fro/core/2.1.0/css/core_no-mq.min.css" />
    <![endif]-->
    <script type="text/javascript" src="//www.static-immobilienscout24.de/fro/core/2.1.0/js/core.min.js"></script>

    <script src="${request.requestURL}scripts/libs/angular/angular.min.js"></script>
    <script src="scripts/libs/angular/angular-route.min.js"></script>
    <script src="scripts/libs/angular/angular-sanitize.min.js"></script>
    <script src="scripts/libs/angular/angular-resource.min.js"></script>

    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />
    <link rel="stylesheet" type="text/css" href="css/forum.css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</div>
<body>
<div id="topElement"></div>

<div id="wrapper">
    <#include "./partial/nav.ftl">

</div>
<div class="viewport">
    <div class="page-wrapper">
        <navigation-bar></navigation-bar>
        <div class="content-wrapper">
            <div class="grid">
                <div class="grid-item desk-eight-twelfths ng-view"> </div>
                <div class="grid-item four-twelfths palm-hide lap-hide">
                    <div class="float-right margin-top-xxl">
                        <img src="images/Scout24Media.png">
                    </div>
                </div>
            </div>
        </div>
        <div class="margin-l margin-top-xl" ng-controller="miscController">
            <span onclick="history.go(-1);" class="pointer">
                <i class="fa fa-chevron-left"></i>
                zurück
            </span>
            <a href="#topElement" class="float-right " ng-click="gotoTop('topElement')">
                <i class="fa fa-chevron-up"></i>
                nach oben
            </a>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

<script src="scripts/app/common/globalSettings.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="scripts/app/modules/ExpertForum.js"></script>

<script src="scripts/app/common/models.js"></script>

<script src="scripts/app/services/efoRestService.js"></script>
<script src="scripts/app/services/authService.js"></script>

<script src="scripts/app/directives/CommonDirectives.js"></script>
<script src="scripts/app/directives/CommentDirective.js"></script>
<script src="scripts/app/directives/NavigationBarDirective.js"></script>
<script src="scripts/app/directives/answerQuestionDirective.js"></script>

<script src="scripts/app/controllers/questionsController.js"></script>
<script src="scripts/app/controllers/miscController.js"></script>
<script src="scripts/app/controllers/homeController.js"></script>
<script src="scripts/app/controllers/profileController.js"></script>
<script src="scripts/app/controllers/authController.js"></script>

<script src="scripts/app/modules/navigation.js"></script>

</body>
</div>
