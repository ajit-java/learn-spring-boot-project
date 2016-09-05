
forumControllers.controller('profileController', ['$scope', '$filter','$routeParams','efoRestService', '$location',
    function($scope, $filter, $routeParams, efoRestService, $location) {

        $scope.userModel = new  efoRestService.usersApi();
        $scope.forumUserModel = new  efoRestService.forumUsersApi();


        $scope.model={submitted:false};

        //$scope.loginUser= function(formObject){
        //    $scope.model.submitted=true;
        //    toastr.clear();
        //    if(formObject.$invalid === false)
        //    {
        //        efoRestService.profileApi.login($scope.userModel,function(value){
        //            if(value.loginSuccess === true) {
        //                globalSettings.loggedInUserInfo = value;
        //                $scope.model.submitted=false;
        //                toastr.success('Login Success.')
        //                $location.path("/profile/welcome/user");
        //            }else{
        //                toastr.error(value.message);
        //            }
        //            //todo create/read cookie etc
        //
        //        },function(err){
        //            toastr.error('Can not login, invalid credentials!');
        //            console.error(err);
        //        });
        //    }
        //    else
        //    {
        //        toastr.error('Please fill in all the required fields!');
        //    }
        //}

        $scope.saveForumUser = function(formObject)
        {
            $scope.model.submitted=true;
            toastr.clear();
            globalSettings.form.validate(formObject);

            if(formObject.$invalid === false)
            {
                efoRestService.profileApi.updateForumUser($scope.forumUserModel,function(value){
                    toastr.success('Information Saved Successfully.')
                    $scope.forumUserModel = new efoRestService.forumUsersApi();
                    $scope.model.submitted=false;
                    $location.path("/profile/welcome/user");
                },function(err){
                    toastr.error('Can not save information, please try again!');
                    console.error(err);
                });
            }
            else
            {
                //toastr.error('Please fill in all the required fields!');
            }
        }

    }]);

