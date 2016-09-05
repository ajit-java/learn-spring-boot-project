/**
 * Created by ajitchahal on 16/09/15.
 */

forumDirectives.directive("dashboardButton", function(){
    return {
        restrict: "AE",
        templateUrl: globalSettings.getDirectivesTemplatePath("dashboardButton"),
        replace: true,
        scope: {
            forumButtons: '='
        }
    }
});

forumDirectives.directive("lastActivity", function(){
    return {
        restrict: "E",
        template: "<span><span class=\"font-xs\">{{text}}</span> {{calculatedTime.number}} <span class=\"font-xs\">{{calculatedTime.string}}</span></span>",   //{{calculatedTime.number}} + ' <span class=\"font-xs\">' + {{calculatedTime.string}} +'</span>'
        replace: true,
        scope: {
            parentObj: '=',
            text: "@"
        },
        link: function(scope){
            scope.$watch("parentObj.lastActivity", function(newValue,oldValue){
                if (newValue){
                    scope.calculatedTime = globalSettings.utitlities.calcTimespan(scope.parentObj.lastActivity);
                }
            });
        }
    }
});