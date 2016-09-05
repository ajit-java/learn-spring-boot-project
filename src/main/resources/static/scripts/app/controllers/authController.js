/**
 * Created by ajit on 07.12.15.
 */
forumControllers.controller('loginController', ['$scope', 'authService',
    function($scope, authService) {
        authService.login();
    }]);
forumControllers.controller('logoutController', ['$scope', 'authService',
    function($scope, authService) {
        authService.logOut();
    }]);