forumAdminControllers.controller('notificationRulesController', ['$scope', '$filter', '$routeParams', 'efoRestService',
    function ($scope, $filter, $routeParams, efoRestService) {

        function getAllRules() {
            efoRestService.notificationRulesApi.getAll(
                function (value) {
                    $scope.notificationRulesList = value;
                }, function (httpResponse) {
                    console.debug(httpResponse);//logging error messages
                });
        }

        getAllRules();

        $scope.updateNotificationRule = function (notificationRule) {
            toastr.clear();
            console.debug(notificationRule);
            notificationRule.$save(function () {
                toastr.success('Rule Updated Successfully.')
            }, function (err) {
                toastr.error('Can not update rule details!');
                console.error(err);
            });
        }

    }]);

forumAdminControllers.controller('notificationRulesAddEditController', ['$scope', '$filter', '$routeParams', 'efoRestService', '$location',
    function ($scope, $filter, $routeParams, efoRestService, $location) {

        $scope.model={
            submitted:false,
            notificationRuleId: $routeParams.id
        };

        $scope.notificationRuleModel = efoRestService.notificationRulesApi.get({verb1:$scope.model.notificationRuleId},function(value){
            $scope.notificationRuleModel=value;
            console.debug(value);
        });

        $scope.recipientTypeNames = efoRestService.enumsApi.getEnumNames({"verb1":"recipientType"},function(value){
            $scope.recipientTypeNames = value;
        });
        $scope.notificationEventTypeNames = efoRestService.enumsApi.getEnumNames({"verb1":"notificationEventType"},function(value){
            $scope.notificationEventTypeNames = value;
        });
        $scope.exfoRoleNames = efoRestService.enumsApi.getEnumNames({"verb1":"exfoRole"},function(value){
            $scope.exfoRoleNames = value;
        });

        $scope.updateNotificationRule= function(formObject){
            $scope.model.submitted=true;
            toastr.clear();
            if(formObject.$invalid === false)
            {
                $scope.notificationRuleModel.$save(function(){
                    toastr.success('Rule saved/Updated Successfully.')
                    $scope.model.submitted=false;
                    $location.path("/notificationRules");
                },function(err){
                    toastr.error('Can not saved/Updated rule details!');
                    console.error(err);
                });
            }
            else
            {
                toastr.error('Please fill in all the required fields!');
            }
        }

    }]);
