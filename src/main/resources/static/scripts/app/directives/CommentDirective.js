/**
 * Created by ajitchahal on 16/09/15.
 */
forumDirectives.directive("fs24Comments", function(){
    return {
        restrict: "AE",
        templateUrl: globalSettings.getDirectivesTemplatePath("comments"),
        replace: true,
        scope: {
            parentObj:'=',
        },
        controller: function($scope, efoRestService){

            $scope.model ={
                parentObject:$scope.parentObj,
                answerIdOfExpandedComments:-1,
                answerIdOfSaveComment:-1,
                idOfEditComment:-1,
                editComment:null,
            };

            var getAnswerComments = function(answerId)
            {
                $scope.comments = efoRestService.answersApi.getComments({verb1: answerId}, function(value){
                    if(value._embedded) {
                        $scope.comments = value._embedded.comments;
                    }
                });
            }

            $scope.getComments = function(answerId)
            {
                toggleSelectedAnswerId(answerId);
                getAnswerComments(answerId);
            }
            function toggleSelectedAnswerId(answerId)
            {
                if($scope.model.answerIdOfExpandedComments === answerId)//close comments shown
                {
                    $scope.model.answerIdOfExpandedComments = -1;
                    $scope.model.answerIdOfSaveComment = -1;
                    return;
                }

                $scope.model.answerIdOfExpandedComments = answerId;

            }

            $scope.showComments = function(answerId)
            {
              return ($scope.model.answerIdOfExpandedComments == answerId);
            }

            $scope.commentModel = new  efoRestService.commentsApi();

            $scope.saveComment= function(formObject){
                $scope.model.submitted=true;
                toastr.clear();
                if(formObject.$invalid === false)
                {
                    $scope.commentModel.questionId=$scope.parentObj.questionId;//since parent of comment is always an answer
                    $scope.commentModel.answerId=$scope.parentObj.id;
                    $scope.commentModel.forumUserId=globalSettings.loggedInUserInfo.forumUserId;//todo review after login impl

                    $scope.commentModel.$save(function(){
                        toastr.success('Vielen Dank! Ihre kommentar ist nun online.')
                        getAnswerComments($scope.commentModel.answerId);
                        $scope.toggleSaveComment($scope.commentModel.answerId);
                        $scope.commentModel = new efoRestService.commentsApi();
                        $scope.model.submitted=false;
                    },function(err){
                        toastr.error('Can not save comments details!');
                        console.error(err);
                    });
                }
                else
                {
                    toastr.error('Please fill in comment text!');
                }
            }

            $scope.toggleSaveComment = function(answerId){
                if($scope.model.answerIdOfSaveComment === answerId)//close comments shown
                {    $scope.model.answerIdOfSaveComment = -1; return;}

                $scope.model.answerIdOfSaveComment = answerId;
            }

            $scope.showSaveComment = function(answerId)
            {
                return ($scope.model.answerIdOfSaveComment == answerId);
            }

            var validateForm = function(frm){
                isValidForm =  !(frm.txtCommentText.$error.required);

                return isValidForm;
            }

            //admin change status of comment -start

            $scope.stateChangeHelper = {
                edit: function(comment){
                    $scope.model.editComment=comment;
                    $scope.toggleEditComment(comment.id);
                },
                approve: function(comment){
                  $scope.stateChangeHelper.updateComment(comment, globalSettings.enums.PublishingStatus.Approve,
                               "Comment is now approved and visible.", "Can not approve comment!")
                },
                reject: function(comment){
                    $scope.stateChangeHelper.updateComment(comment, globalSettings.enums.PublishingStatus.Rejected,
                        "Comment is marked as rejected.", "Can not update comment as rejected!")
                },
                spam: function(comment){
                    $scope.stateChangeHelper.updateComment(comment, globalSettings.enums.PublishingStatus.Spam,
                        "Comment is marked as spam.", "Can not update comment as spam!")
                },
                updateComment: function(comment, newPublishingStatus, successmessage, errorMessage) {

                    if(comment.publishingStatus != newPublishingStatus) {//if the comment already is not of same status
                        comment.publishingStatus = newPublishingStatus;

                        efoRestService.commentsApi.updatePublishingStatus(comment, function (value) {
                            comment = value;
                            toastr.success(successmessage)
                        }, function (err) {
                            toastr.error(errorMessage);
                            console.error(err);
                        });
                    }
                }
            }
            $scope.toggleEditComment = function(commentId){
                if($scope.model.idOfEditComment === commentId)//close comments shown
                {
                    $scope.model.editComment=null;
                    $scope.model.idOfEditComment = -1; return;
                }

                $scope.model.idOfEditComment = commentId;
            }
            $scope.showEditComment = function(comment){
                return $scope.model.idOfEditComment == comment.id;
            }

            $scope.updateComment= function(formObject){
                toastr.clear();
                if(formObject.$invalid === false)
                {
                    //step 1: get latest comment from db
                    efoRestService.commentsApi.getOne({verb1:$scope.model.editComment.id}, function(value){
                        var dbComment = value;
                        //step 2: update props of db comment object from edited comment object in angular
                        dbComment.commentText = $scope.model.editComment.commentText;

                        //step 3: update comment object
                        dbComment.$save(function(){
                            toastr.success('Comment Updated.')
                            $scope.model.editComment=null;
                            $scope.model.idOfEditComment=-1
                        },function(err){
                            toastr.error('Can not update comments details!');
                            console.error(err);
                        });

                    });
                }
                else
                {
                    toastr.error('Please fill in comment text!');
                }
            }
            //admin change status of comment -end
        }
    }
});