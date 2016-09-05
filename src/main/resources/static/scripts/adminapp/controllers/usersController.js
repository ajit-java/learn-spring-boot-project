forumAdminControllers.controller('usersController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {

        //todo implement get users by role etc
        efoRestService.forumUsersApi.getPaged({"page":0,"size":20},
            function(value){
                if(value._embedded) {
                    $scope.usersList = value._embedded.forumUsers;
                }
            },function(httpResponse){
                console.debug(httpResponse);//logging error messages
            });
    }]);

