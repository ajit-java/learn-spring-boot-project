forumDirectives.directive("answerQuestion", function() {
    return {
        restrict: "AE",
        templateUrl: globalSettings.getDirectivesTemplatePath("answerQuestion"),
        replace: true,
        scope: {
            parentObj: '=',
        },
        controller: function ($scope, efoRestService) {

            $scope.model = {
                parentObject: $scope.parentObj,
                showSaveAnswer: false,
            };

            $scope.answerModel = new  efoRestService.answersApi();

            $scope.saveAnswer= function(formObject){
                $scope.model.submitted=true;
                toastr.clear();
                if(formObject.$invalid === false)
                {
                    $scope.answerModel.questionId=$scope.parentObj.id;
                    $scope.answerModel.forumUserId=globalSettings.loggedInUserInfo.forumUserId;//todo review after login impl

                    $scope.answerModel.$save(function(){
                        toastr.success('Vielen Dank! Ihre Antwort ist nun online.')
                        $scope.answerModel = new efoRestService.answersApi();
                        $scope.model.submitted=false;
                        $scope.model.showSaveAnswer= false;
                    },function(err){
                        toastr.error('Can not save answers details!');
                        console.error(err);
                    });
                }
                else
                {
                    toastr.error('Please fill in answer text!');
                }
            }
        }
    }
});