/**
 * Created by fnowothnick on 13.10.2015.
 */
(function () {
    'use strict';


    //todo decide in ftl instead
    var templateUrl = "";
    //// naviagtion bar - depending on the user role
    //if(globalSettings.loggedInUserInfo.role === globalSettings.enums.ExfoRole.LoggedInUser)
    //{
        templateUrl = "navigationBarUser";
    //}
    //else if(globalSettings.loggedInUserInfo.role === globalSettings.enums.ExfoRole.ApprovedExpert)
    //{
    //    templateUrl = "navigationBarExpert";
    //}
    //else if(globalSettings.loggedInUserInfo.role === globalSettings.enums.ExfoRole.Admin)
    //{
    //    templateUrl = "navigationBarAdmin";
    //}

    angular
        .module('ExpertForum')
        .directive('navigationBar', function () {
            return {
                restrict: 'E',   // only matches element name
                templateUrl: globalSettings.getDirectivesTemplatePath(templateUrl),
                controller: function($scope, efoRestService){

                    $scope.showSearchBar = function(answerId)
                    {
                        alert("show");
                        return ($scope.model.answerIdOfExpandedComments == answerId);
                    }

                    $scope.model={
                        displayNotificationCount:0,
                    };

                    $scope.getDisplayNotificationCount = function() {
                        efoRestService.forumUsersApi.getDisplayNotificationsCount(function(value){
                            if(!isNaN(value)) {
                                $scope.model.displayNotificationCount = value;
                            }
                        },function(httpResponse){
                            console.debug(httpResponse);//logging error messages
                        });
                    }

                    //$scope.getDisplayNotificationCount();
                }
            };
        });

})();
