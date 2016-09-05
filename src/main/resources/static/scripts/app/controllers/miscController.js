forumControllers.controller('miscController', ['$scope', '$filter','$routeParams','efoRestService','$location', '$anchorScroll',
    function($scope, $filter, $routeParams, efoRestService, $location, $anchorScroll) {

        $scope.gotoTop = function(idOfElementToScrollTo)
        {
            // set the location.hash to the id of
            // the element you wish to scroll to.
            $location.hash(idOfElementToScrollTo);

            // call $anchorScroll()
            $anchorScroll();
        }
    }]);
