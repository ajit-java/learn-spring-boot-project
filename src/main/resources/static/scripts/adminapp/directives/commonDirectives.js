forumAdminDirectives.directive("publishingStatusChangeButtonsLarge", function(){
    return {
        restrict: "AE",
        templateUrl: globalSettings.getAdminDirectivesTemplatePath("publishingStatusChangeButtonsLarge"),
        replace: true,
        scope: {
            stateChangeHelper: '=',
            parentObj: '='
        },
        controller: function($scope, efoRestService){

            $scope.model = {
                submitted:false,
                showContactUserForm:false,
            };


            $scope.$watch('parentObj.publishingStatus', function(newValue, oldValue) {
                $scope.approved = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Approve;
                $scope.rejected = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Rejected;
                $scope.spam = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Spam;
            });

            var resetContactModel = function() {
                $scope.notificationModel = new  efoRestService.notificationApi();
            }
            resetContactModel();


            $scope.sendEmailToUser = function(formObject)
            {

                $scope.model.submitted=true;
                toastr.clear();
                if(formObject.$invalid === false)
                {
                    $scope.notificationModel.triggeredOnId = $scope.parentObj.forumUserId;//user id of user who asked the question

                    efoRestService.notificationApi.supportContact($scope.notificationModel,function(){
                        toastr.success('Email Queued Successfully.')
                        resetContactModel();
                        $scope.model.submitted=false;
                        $scope.model.showContactUserForm=false;
                    },function(err){
                        toastr.error('Can not send email!');
                        console.error(err);
                    });
                }
                else
                {
                    toastr.error('Please fill in all the required fields!');
                }
            }
        }
    }
});

forumAdminDirectives.directive("adminEditIcon", function() {
    return {
        restrict: "AE",
        template: '<a class="float-right" href="{{href}}"><i title="Edit question" class="font-xxl font-lightgray fa fa-pencil"></i></a>',
        replace: true,
        scope: {
            href: '@',
        },
    }
});

forumDirectives.directive("publishingStatusChangeButtons", function(){
    return {
        restrict: "AE",
        templateUrl: globalSettings.getAdminDirectivesTemplatePath("publishingStatusChangeButtons"),
        replace: true,
        scope: {
            stateChangeHelper: '=',
            parentObj: '='
        },
        controller: function($scope, efoRestService){
            $scope.$watch('parentObj.publishingStatus', function(newValue, oldValue) {
                $scope.approved = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Approve;
                $scope.rejected = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Rejected;
                $scope.spam = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Spam;
            });
        }
    }
});

//forumDirectives.directive("publishingStatusChangeButtons", function(){
//    return {
//        restrict: "AE",
//        templateUrl: globalSettings.getDirectivesTemplatePath("publishingStatusChangeButtons"),
//        replace: true,
//        scope: {
//            stateChangeHelper: '=',
//            parentObj: '='
//        },
//        controller: function($scope, efoRestService){
//            $scope.$watch('parentObj.publishingStatus', function(newValue, oldValue) {
//                $scope.approved = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Approve;
//                $scope.rejected = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Rejected;
//                $scope.spam = $scope.parentObj.publishingStatus == globalSettings.enums.PublishingStatus.Spam;
//            });
//        }
//    }
//});