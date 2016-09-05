/**
 * Created by ajit on 03.12.15.
 */
forumServices.service('authService',['$resource','$http', '$location', 'efoRestService', function($resource, $http, $location, efoRestService){

    this.login = function(){
        redirectToLogin("");
    }

    this.logOut = function(){
        globalSettings.loggedInUserInfo = null;
        efoRestService.authenticateApi.logout(function(value){
            redirectToLogout();//logout at immo as well
        },function(err){
            console.debug(err);
        });
    }

    this.authenticate = function(){
        //debugger;
        efoRestService.authenticateApi.authenticate({sso:getUrlParam()["sso"],ssoHmac:getUrlParam()["ssoHmac"]},function(value){
            globalSettings.loggedInUserInfo = value;
            if(!value.authenticated) {
                redirectToLogin($location.path());
            }
        },function(err){
            console.debug(err);
            toastr.error(err.data.message);
        });
    }

    function redirectToLogin(angularPath){
        var localPath = location.href;//globalSettings.applicationPath +"#"+ angularPath; //get current url + angular path
        //debugger;
        localPath = encodeURIComponent(localPath);
        var fs24AuthApi = globalSettings.fs24AuthApiPath.format(localPath );//add local path as return url
        var immoLoginPath = globalSettings.loginPath.format(fs24AuthApi );//add fs24 auth api path as return url for immo sso login
        console.debug(immoLoginPath);
        window.location.href=immoLoginPath;
    }

    function redirectToLogout(){
        var localPath = globalSettings.applicationPath; //+"#"+ $location.path(); //get current url + angular path
        var logOutPath = globalSettings.logOutPath.format(localPath );//add local path as return url
        console.debug(logOutPath);
        window.location.href=logOutPath;
    }
// Get Parameter from URL
    function getUrlParam() {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            if(hash && hash.length>=2) {//remove angular #
                hash[1]=hash[1].split('#')[0];//take everything left of # in string
            }
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

}]);