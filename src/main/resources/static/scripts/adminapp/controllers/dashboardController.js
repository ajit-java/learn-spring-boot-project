forumAdminControllers.controller('dashboardController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {

        var dashboard = efoRestService.dashboardApi.getAdmin(null,function(value){
            dashboard = value;
            setupDashboardButtons();
            console.debug($scope.forumButtonsAufgaben);

        });

        var setupDashboardButtons = function() {
            var dCO = "grid-item one-whole margin-bottom-xl align-center desk-one-half";
            var dCB = "grid-item one-whole margin-bottom-xl align-center desk-one-third";
            var bCP = "eleven-twelfths height-l button-primary";
            var bCS = "eleven-twelfths height-l button-Secondary";

            $scope.forumButtonsAufgaben = [];
            $scope.forumButtonsAufgaben[0] = { designClass: dCO, buttonClass: bCP, titleText: "Fragen", subTitle: "(warten auf freigabe)", link:"#/questions/pstatus/"+globalSettings.enums.PublishingStatus.New, info:dashboard.newQuestionCount }
            $scope.forumButtonsAufgaben[1] = { designClass: dCO, buttonClass: bCP, titleText: "Experten", subTitle: "(warten auf freigabe)", link:"#/users/role/"+globalSettings.enums.ExfoRole.RequestedExpert, info:dashboard.newExpertsCount }

            $scope.forumButtonsStatistics = [];
            $scope.forumButtonsStatistics[0] = { designClass: dCB, buttonClass: bCS, titleText: "Fragen", subTitle: "", link:"#/questionDashboard", info:dashboard.approvedQuestionCount }
            $scope.forumButtonsStatistics[1] = { designClass: dCB, buttonClass: bCS, titleText: "Antworten", subTitle: null, link:"#/", info:dashboard.answersCount }
            $scope.forumButtonsStatistics[2] = { designClass: dCB, buttonClass: bCS, titleText: "Kommentare", subTitle: null, link:"#/", info:dashboard.commentsCount }
            $scope.forumButtonsStatistics[3] = { designClass: dCB, buttonClass: bCS, titleText: "Benutzer", subTitle: null, link:"#/users/role/"+globalSettings.enums.ExfoRole.LoggedInUser, info:dashboard.userCount }
            $scope.forumButtonsStatistics[4] = { designClass: dCB, buttonClass: bCS, titleText: "Experten", subTitle: null, link:"#/users/role/"+globalSettings.enums.ExfoRole.ApprovedExpert, info:dashboard.expertsCount }
            $scope.forumButtonsStatistics[5] = { designClass: dCB, buttonClass: bCS, titleText: "Kategorien", subTitle: null, link:"#/categories", info:dashboard.categoryCount }
            $scope.forumButtonsStatistics[6] = { designClass: dCB, buttonClass: bCS, titleText: "Geschlossene Fragen", subTitle: null, link:"#/questions/qstatus/"+globalSettings.enums.QuestionStatus.Closed, info:dashboard.closedQuestionCount }
            $scope.forumButtonsStatistics[7] = { designClass: dCB, buttonClass: bCS, titleText: "Als Spam markiert", subTitle: null, link:"#/questions/pstatus/"+globalSettings.enums.PublishingStatus.Spam, info:dashboard.spamQuestionCount }
        }
    }]);

forumAdminControllers.controller('questionDashboardController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {

        var qDashboard = efoRestService.dashboardApi.getQuestion(null,function(value){
            qDashboard = value;
            setupQuestionDashboardButtons();
        });

        var setupQuestionDashboardButtons = function() {
            var dCO = "grid-item one-whole margin-bottom-xl align-center desk-one-half";
            var dCB = "grid-item one-whole margin-bottom-xl align-center desk-one-third";
            var bCP = "eleven-twelfths height-l button-primary";
            var bCS = "eleven-twelfths height-l button-Secondary";

            $scope.forumButtonsAufgaben = [];
            $scope.forumButtonsAufgaben[0] = { designClass: dCO, buttonClass: bCP, titleText: "Fragen", subTitle: "(warten auf freigabe)", link:"#/questions/pstatus/"+globalSettings.enums.PublishingStatus.New, info:qDashboard.newQuestionCount }
            $scope.forumButtonsAufgaben[1] = { designClass: dCO, buttonClass: bCP, titleText: "Stellen Sie eine neue Frage", link:"#/questions/add/", fontAwesomeIconClass:"float-right fa fa-plus-circle font-l font-white" }

            $scope.forumButtonsFragen = [];
            $scope.forumButtonsFragen[0] = { designClass: dCB, buttonClass: bCS, titleText: "Fragen", link:"#/questions/pstatus/"+globalSettings.enums.PublishingStatus.Approve, info:qDashboard.approvedQuestionCount }
            $scope.forumButtonsFragen[1] = { designClass: dCB, buttonClass: bCS, titleText: "Zurückgewiesene Fragen", link:"#/questions/pstatus/"+globalSettings.enums.PublishingStatus.Rejected, info:qDashboard.rejectedQuestionCount }
            $scope.forumButtonsFragen[2] = { designClass: dCB, buttonClass: bCS, titleText: "Als Spam markiert", link:"#/questions/pstatus/"+globalSettings.enums.PublishingStatus.Spam, info:qDashboard.spamQuestionCount }
        }
    }]);