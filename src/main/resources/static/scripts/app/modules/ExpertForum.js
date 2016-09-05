var forumApp = angular.module("ExpertForum", ['ngRoute','ngResource','forumDirectives','forumControllers','forumServices']);
var forumAdminApp = angular.module('ExpertForumAdmin', ['ngRoute','ngResource','forumDirectives','forumAdminDirectives', 'forumControllers', 'forumAdminControllers','forumServices']);

var forumDirectives = angular.module("forumDirectives", ['ngResource']);
var forumAdminDirectives = angular.module("forumAdminDirectives", ['ngResource']);
var forumServices = angular.module("forumServices", ['ngResource']);

var forumControllers = angular.module("forumControllers", ['ngRoute','ngResource','ngSanitize']);
var forumAdminControllers = angular.module("forumAdminControllers", ['ngRoute','ngResource']);

/*
////todo impl login service
//app.run(['$rootScope', '$location', 'authService',
//    function ($rootScope, $location, authService) {
//
//        //Client-side security. Server-side framework MUST add it's
//        //own security as well since client-based security is easily hacked
//        $rootScope.$on("$routeChangeStart", function (event, next, current) {
//            if (next && next.$$route && next.$$route.secure) {
//                if (!authService.user.isAuthenticated) {
//                    $rootScope.$evalAsync(function () {
//                        authService.redirectToLogin();
//                    });
//                }
//            }
//        });
//
//    }]);
*/


forumApp.run(['$rootScope', '$location', 'authService',
     function ($rootScope, $location, authService) {
         $rootScope.$on("$routeChangeStart", function (event, next, current) {
             if (next && next.$$route && next.$$route.secure) {
                 if (!globalSettings.loggedInUserInfo.authenticated)
                 {
                    //$rootScope.$evalAsync(function ()                    {
                         authService.authenticate();
                    //});
                 }
            }
         });

     }]);
forumAdminApp.run(['$rootScope', '$location', 'authService',
    function ($rootScope, $location, authService) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (next && next.$$route && next.$$route.secure) {
                if (!globalSettings.loggedInUserInfo.authenticated)
                {
                    //$rootScope.$evalAsync(function ()                    {
                    authService.authenticate();
                    //});
                }
            }
        });

    }]);

forumApp.provider('routeResolver', routeResolverProvider);
forumAdminApp.provider('routeResolver', routeResolverProvider);