forumAdminControllers.controller('categoriesController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {

        //todo implement
        efoRestService.categoryApi.getPaged({"page":0,"size":20},
            function(value){
                if(value._embedded) {
                    $scope.categoriesList = value._embedded.categories;
                }
            },function(httpResponse){
                console.debug(httpResponse);//logging error messages
            });
    }]);

