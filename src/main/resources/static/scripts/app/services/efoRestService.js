forumServices.service('efoRestService',['$resource','$http', function($resource, $http){
    //this.questionCrudApi = $resource(forumAdminApp.applicationPath + "questions");
    //this.questionApix = $resource(forumAdminApp.applicationPath + "questions/:verb1/:verb2",{"verb":"all","verb2":"paged","page":'0',"size":'10'});

    this.questionApi = $resource(globalSettings.applicationPath + "questions/:verb1/:verb2", {},
        {
            getAll: { method:'GET', params:{"verb1":"all"}, "isArray":true},
            getPaged: { method:'GET', params:{"verb1":"all","verb2":"paged","page":'0',"size":'20'}},
            getByQuestionStatus: { method:'GET', params:{"verb1":"QuestionStatus","verb2":null,"page":'0',"size":'20' }},
            getByPublishingStatus: { method:'GET', params:{"verb1":"PublishingStatus","verb2":null,"page":'0',"size":'20'}},
            getAnswers:{ method:'GET', params:{"verb1":"qid","verb2":"answers","page":'0',"size":'20'}},
            updatePublishingStatus: { method:'POST', params:{"verb1":"updatePublishingStatus"}},
            toggleFavorite: { method:'GET', params:{"verb2":"toggleFavorite"}}
        }
    );

    this.categoryApi = $resource(globalSettings.applicationPath + "categories/:verb1/:verb2", {},
        {
            getAll: { method:'GET', params:{"verb1":"all"}, "isArray":true},
            getPaged: { method:'GET', params:{"verb1":"all","verb2":"paged","page":'0',"size":'20'}},
            getOne: { method:'GET', params:{"verb1":"-1"}},
        }
    );
    this.answersApi = $resource(globalSettings.applicationPath + "answers/:verb1/:verb2", {},
        {
            getAll: { method:'GET', params:{"verb1":"all"}, "isArray":true},
            getPaged: { method:'GET', params:{"verb1":"all","verb2":"paged","page":'0',"size":'20'}},
            getOne: { method:'GET', params:{"verb1":"-1"}},
            getComments: { method:'GET', params:{"verb1":"aid","verb2":"comments","page":'0',"size":'20'}}
        });

    this.dashboardApi = $resource(globalSettings.applicationPath + "dashboard/:verb1/:verb2", {},
        {
            getAdmin: { method:'GET', params:{"verb1":"admin"}},
            getQuestion: { method:'GET', params:{"verb1":"question"}},
        });
    this.commentsApi = $resource(globalSettings.applicationPath + "comments/:verb1/:verb2", {},
        {
            getAll: { method:'GET', params:{"verb1":"all"}, "isArray":true},
            getPaged: { method:'GET', params:{"verb1":"all","verb2":"paged","page":'0',"size":'20'}},
            getOne: { method:'GET', params:{"verb1":"-1"}},
            updatePublishingStatus: { method:'POST', params:{"verb1":"updatePublishingStatus"}},
        });
    this.usersApi = $resource(globalSettings.applicationPath + "users/:verb1/:verb2", {},
        {
            getOne: { method:'GET', params:{"verb1":"-1"}},
        });
    this.forumUsersApi = $resource(globalSettings.applicationPath + "forumUsers/:verb1/:verb2", {},
        {
            getOne: { method:'GET', params:{"verb1":"-1"}},
            getQuestions: {method:'GET', params:{"verb1":"-1", verb2:'questions'}},
            getFavoritesQuestions: {method:'GET', params:{"verb1":"-1", verb2:'favoriteQuestions'}},
            getPaged: { method:'GET', params:{"verb1":"all","verb2":"paged","page":'0',"size":'20'}},
            getDisplayNotificationsCount: {method:'GET', params:{"verb1":"displayNotificationsCount"}},
            displayNotificationsAsUnSeen: {method:'POST', params:{"verb1":"displayNotificationsAsUnSeen"}},//means user just has clicked on Bömmel
        });
    this.profileApi = $resource(globalSettings.applicationPath + "profile/:verb1/:verb2", {},
        {
            getOne: { method:'GET', params:{"verb1":"-1"}},
            login: {method:'POST', params:{user:{},"verb1":"login"}},
            //saveForumUser: {method:'POST', params:{"verb1":"saveForumUser"}},
            updateForumUser: {method:'POST', params:{"verb1":"updateForumUser"}},
        });
    this.notificationApi = $resource(globalSettings.applicationPath + "notifications/:verb1/:verb2", {},
        {
            supportContact: {method:'POST', params:{"verb1":"supportContact"}},
            expertContact: {method:'POST', params:{"verb1":"expertContact"}},
            displayNotificationSeen: {method:'POST', params:{"verb2":"displayNotificationSeen"}},//means user has gone to detail page of a  question..??? todo?
        });

    this.notificationRulesApi = $resource(globalSettings.applicationPath + "notificationRules/:verb1/:verb2", {},
        {
            getAll: { method:'GET', params:{"verb1":"all"}, "isArray":true},
        });

    this.enumsApi = $resource(globalSettings.applicationPath + "enums/:verb1/:verb2", {},
        {
            getEnumNames: { method:'GET', params:{"verb1":"recipientType"}, "isArray":true}, //change verb1 for the name of enum
        });
    this.authenticateApi = $resource(globalSettings.applicationPath + ":verb1/:verb2", {},
        {
            authenticate: { method:'GET', params:{"verb1":"authenticate"}}, //change verb1 for the name of enum
            logout: { method:'GET', params:{"verb1":"logout"}}, //change verb1 for the name of enum
        });
}]);
