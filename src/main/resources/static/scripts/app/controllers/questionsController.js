forumControllers.controller('questionsUserController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {
        $scope.headline = "Meine <b>Fragen</b>";
        $scope.userId = globalSettings.loggedInUserInfo.forumUserId;

        $scope.getquestionPublishingStatus = function(question){
            return question.publishingStatus;
        }

        efoRestService.forumUsersApi.getQuestions({"page":0,"size":20, verb1:$scope.userId},function(value){
            if(value._embedded) {
                $scope.questionsList = value._embedded.questions;
                // load the first column with the special icons/functionality
                $scope.columnTemplateUrl = globalSettings.applicationPath+'view?viewName=/{0}/{1}'.format("views/questions/actionColumns", "myQuestionsColumn");
            }
        },function(httpResponse){
            console.debug(httpResponse);//logging error messages
        });
    }]);

forumControllers.controller('questionsStatusController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {

        $scope.headline = "Die neuesten <b>Fragen</b>";
        // load the first column with the special icons/functionality
        $scope.columnTemplateUrl = globalSettings.applicationPath+'view?viewName=/{0}/{1}'.format("views/questions/actionColumns", "myFavoritesColumn");

        //console.log("questionsStatusController");
        var questionPublishingStatus= $routeParams.id==null?1:$routeParams.id;
        $scope.getquestionPublishingStatus = function(question){
            return questionPublishingStatus;
        }

        efoRestService.questionApi.getByPublishingStatus({"page":0,"size":20, verb2:questionPublishingStatus},
            function(value){
                if(value._embedded) {
                    $scope.questionsList = value._embedded.questions;
                }
            },function(httpResponse){
                console.debug(httpResponse);//logging error messages
            });

        $scope.updateFavorite= function(question, index){
            toastr.clear();
            // save the favorite in backend
            efoRestService.questionApi.toggleFavorite({verb1: question.id}, function (value) {

                // update the client list entry
                $scope.questionsList[index].isfavorite = !question.isfavorite;

                if($scope.questionsList[index].isfavorite) {
                    toastr.success('Frage erfolgreich gemerkt.')
                }
                else{
                    toastr.success('Frage erfolgreich entfernt.')
                }
            }, function (err) {
                toastr.error('Fehler beim entfernen der Frage!');
                console.error(err);
            });
        }

        /*$scope.getLastActivityString = function(date){
            console.log("questionsStatusController")
            var calculatedTime = globalSettings.utitlities.calcTimespan(date)
            return $sce.trustAsHtml(calculatedTime.number + ' <span class="font-xs">' + calculatedTime.string +'</span>');
        }*/
    }]);

forumControllers.controller('questionsQuestionStatusController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {
        //console.log("questionsQuestionStatusController");
        $scope.questionStatus= $routeParams.id==null?1:$routeParams.id;
        $scope.getquestionPublishingStatus = function(question){
            return question.publishingStatus;
        }

        efoRestService.questionApi.getByQuestionStatus({"page":0,"size":20, verb2:$scope.questionStatus},function(value){
            if(value._embedded) {
                $scope.questionsList = value._embedded.questions;
            }
        },function(httpResponse){
            console.debug(httpResponse);//logging error messages
        });
    }]);

// Details page
forumControllers.controller('questionsDetailsController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {

        $scope.model ={
            questionId: $routeParams.id,
        };

        $scope.question = efoRestService.questionApi.get({verb1:$scope.model.questionId},function(value){
           $scope.question=value;
        });
        $scope.answers = efoRestService.questionApi.getAnswers({verb1: $scope.model.questionId}, function(value){
            $scope.answers = {};
            if(value._embedded) {
                $scope.answers = value._embedded.answers;
            }
        })

        $scope.updateFavorite= function(question){
            toastr.clear();
            // save the favorite in backend
            efoRestService.questionApi.toggleFavorite({verb1: question.id}, function (value) {

                // update the client list entry
                $scope.question.isfavorite = !question.isfavorite;

                if($scope.question.isfavorite) {
                    toastr.success('Frage erfolgreich gemerkt.')
                }
                else{
                    toastr.success('Frage erfolgreich entfernt.')
                }
            }, function (err) {
                toastr.error('Fehler beim entfernen der Frage!');
                console.error(err);
            });
        }


        //admin change status of question -start
        $scope.categoriesList = efoRestService.categoryApi.getAll(null,function(value){
            $scope.categoriesList = value;
        });
        $scope.stateChangeHelper = {
            edit: function(question){
                $scope.model.editQuestion=question;
                $scope.toggleEditQuestion(question.id);
            },
            approve: function(question){
                $scope.stateChangeHelper.updateQuestion(question, globalSettings.enums.PublishingStatus.Approve,
                    "Question is now approved and visible.", "Can not approve question!")
            },
            reject: function(question){
                $scope.stateChangeHelper.updateQuestion(question, globalSettings.enums.PublishingStatus.Rejected,
                    "Question is marked as rejected.", "Can not update question as rejected!")
            },
            spam: function(question){
                $scope.stateChangeHelper.updateQuestion(question, globalSettings.enums.PublishingStatus.Spam,
                    "Question is marked as spam.", "Can not update question as spam!")
            },
            updateQuestion: function(question, newPublishingStatus, successmessage, errorMessage) {

                if(question.publishingStatus != newPublishingStatus) {//if the question already is not of same status
                    question.publishingStatus = newPublishingStatus;

                    efoRestService.questionApi.updatePublishingStatus(question, function (value) {
                        question = value;
                        toastr.success(successmessage)
                    }, function (err) {
                        toastr.error(errorMessage);
                        console.error(err);
                    });
                }
            }
        }
        $scope.toggleEditQuestion = function(questionId){
            if($scope.model.idOfEditQuestion === questionId)//close questions shown
            {
                $scope.model.editQuestion=null;
                $scope.model.idOfEditQuestion = -1; return;
            }

            $scope.model.idOfEditQuestion = questionId;
        }
        $scope.showEditQuestion = function(question){
            return $scope.model.idOfEditQuestion == question.id;
        }

        $scope.updateQuestion= function(formObject){
            toastr.clear();
            if(formObject.$invalid === false)
            {
                //step 1: get latest question from db
                efoRestService.questionsApi.getOne({verb1:$scope.model.editQuestion.id}, function(value){
                    var dbQuestion = value;
                    //step 2: update props of db question object from edited question object in angular
                    dbQuestion.questionText = $scope.model.editQuestion.questionText;

                    //step 3: update question object
                    dbQuestion.$save(function(){
                        toastr.success('Question Updated.')
                        $scope.model.editQuestion=null;
                        $scope.model.idOfEditQuestion=-1
                    },function(err){
                        toastr.error('Can not update questions details!');
                        console.error(err);
                    });

                });
            }
            else
            {
                toastr.error('Please fill in question text!');
            }
        }
        //admin change status of question -end
    }]);
forumControllers.controller('questionsAddController', ['$scope', '$filter','$routeParams','efoRestService', '$location',
    function($scope, $filter, $routeParams, efoRestService, $location) {

        $scope.questionModel = new  efoRestService.questionApi();
        $scope.categoriesList = efoRestService.categoryApi.getAll(null,function(value){
            $scope.categoriesList = value;
        });
        $scope.model={submitted:false};

        $scope.saveQuestion= function(formObject){
            $scope.model.submitted=true;
            toastr.clear();
            if(formObject.$invalid === false)
            {
                $scope.questionModel.forumUserId=globalSettings.loggedInUserInfo.forumUserId;//todo review after login impl
                $scope.questionModel.publishingStatus = globalSettings.enums.PublishingStatus.New;
                $scope.questionModel.questionStatus = globalSettings.enums.QuestionStatus.Open;
                $scope.questionModel.$save(function(){
                    toastr.success('Question Saved Successfully.')
                    $scope.questionModel = new efoRestService.questionApi();
                    $scope.model.submitted=false;
                    $location.path("/questions/user");
                },function(err){
                    toastr.error('Can not save questions details!');
                    console.error(err);
                });
            }
            else
            {
                toastr.error('Please fill in all the required fields!');
            }
        }
    }]);

forumControllers.controller('questionsEditController', ['$scope', '$filter','$routeParams','efoRestService', '$location',
    function($scope, $filter, $routeParams, efoRestService, $location) {

        $scope.model={
            submitted:false,
            questionId: $routeParams.id
        };

        //edit question mode
            $scope.questionModel = efoRestService.questionApi.get({verb1:$scope.model.questionId},function(value){
                $scope.questionModel=value;
                console.debug(value);
        });

        $scope.categoriesList = efoRestService.categoryApi.getAll(null,function(value){
            $scope.categoriesList = value;
        });

        $scope.saveQuestion= function(formObject){
            $scope.model.submitted=true;
            toastr.clear();
            if(formObject.$invalid === false)
            {
                $scope.questionModel.$save(function(){
                    toastr.success('Question Updated Successfully.')
                    $scope.model.submitted=false;
                },function(err){
                    toastr.error('Can not update questions details!');
                    console.error(err);
                });
            }
            else
            {
                toastr.error('Please fill in all the required fields!');
            }
        }
    }]);

// Merkliste
// return the marked as favorite questions from a logged-in user
forumControllers.controller('questionsFavoriteController', ['$scope', '$filter','$routeParams','efoRestService',
    function($scope, $filter, $routeParams, efoRestService) {
        //console.log("questionsQuestionStatusController");
        $scope.userId = globalSettings.loggedInUserInfo.forumUserId;
        $scope.headline = "Meine <b>Merkliste</b>";
        // load the first column with the special icons/functionality
        $scope.columnTemplateUrl = globalSettings.applicationPath+'view?viewName=/{0}/{1}'.format("views/questions/actionColumns", "myFavoritesColumn");


        efoRestService.forumUsersApi.getFavoritesQuestions({"page":0,"size":20, verb1:$scope.userId},function(value){
            if(value._embedded) {
                $scope.questionsList = value._embedded.questions;
            }
        },function(httpResponse){
            console.debug(httpResponse);//logging error messages
        });


        $scope.updateFavorite= function(question, index){
            toastr.clear();

            efoRestService.questionApi.toggleFavorite({verb1: question.id}, function (value) {
                // question = value;
                // remove the clicked favorite from list
                /*$scope.questionsList.splice(index, 1);*/

                // update the client list entry
                $scope.questionsList[index].isfavorite = !question.isfavorite;

                if($scope.questionsList[index].isfavorite) {
                    toastr.success('Frage erfolgreich gemerkt.')
                }
                else{
                    toastr.success('Frage erfolgreich entfernt.')
                }
            }, function (err) {
                toastr.error('Fehler beim entfernen der Frage!');
                console.error(err);
            });
        }
    }]);