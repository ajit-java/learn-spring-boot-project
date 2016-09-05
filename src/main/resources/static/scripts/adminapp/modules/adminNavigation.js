forumAdminApp.config(['$routeProvider','$httpProvider','routeResolverProvider', '$sceDelegateProvider',function ($routeProvider, $httpProvider, routeResolverProvider, $sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([
        // Allow same origin resource loads.
        'self',
        // Allow loading from our assets domain.  Notice the difference between * and **.
        globalSettings.applicationPath+'**'
    ]);
    $routeProvider

        //.when('/', {templateUrl: 'html/adminViews/dashboard.html', controller: 'dashboardController'})
        //.when('/questionDashboard', {templateUrl: 'html/adminViews/questions/dashboard.html', controller: 'questionDashboardController'})
        .when('/', routeResolverProvider.resolve({module:'',view:'dashboard',controller:'dashboardController', area:'adminViews'}))
        .when('/questionDashboard', routeResolverProvider.resolve({module:'questions',view:'dashboard',controller:'questionDashboardController', area:'adminViews'}))

        //---------question---------------------
        .when('/questions/pstatus/:id', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsStatusController'}))
        .when('/questions/qstatus/:id', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsQuestionStatusController'}))
        .when('/questions/add', routeResolverProvider.resolve({module:'questions',view:'add',controller:'questionsAddController'}))
        .when('/questions/:id/details', routeResolverProvider.resolve({module:'questions',view:'details',controller:'questionsDetailsController'}))
        //.when('/questions/:id/edit', routeResolverProvider.resolve({module:'questions',view:'edit',controller:'questionsEditController'}))
        .when('/questions/:id/delete', routeResolverProvider.resolve({module:'questions',view:'delete',controller:'questionsDeleteController'}))
        .when('/questions/user/:id', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsUserController'}))
        .when('/questions/user/:id/answers', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsExpertUserController'}))
        .when('/questions/edit/:id', routeResolverProvider.resolve({module:'questions',view:'add',controller:'questionsEditController'}))


//---------end question---------------------

//---------profile---------------------
        .when('/login', routeResolverProvider.resolve({module:'',view:'home',controller:'loginController', secure:false}))
        .when('/logout', routeResolverProvider.resolve({module:'',view:'home',controller:'logoutController', secure:false}))
        .when('/profile/user', routeResolverProvider.resolve({module:'profile',view:'user',controller:'profileController'}))
        .when('/profile/expert', routeResolverProvider.resolve({module:'profile',view:'expert',controller:'profileController'}))
        .when('/profile/welcome/expert', routeResolverProvider.resolve({module:'profile',view:'welcomeExpert',controller:'profileController'}))
        .when('/profile/welcome/user', routeResolverProvider.resolve({module:'profile',view:'welcomeUser',controller:'profileController'}))
        .when('/profile/register/expert', routeResolverProvider.resolve({module:'profile',view:'registerExpert',controller:'profileController'}))
        .when('/profile/register/user', routeResolverProvider.resolve({module:'profile',view:'registerUser',controller:'profileController'}))
//---------end profile---------------------
//---------users---------------------
        .when('/users/role/:id', routeResolverProvider.resolve({module:'users',view:'list',controller:'usersController', area:'adminViews'}))
//---------end users---------------------
//---------category---------------------
        .when('/categories', routeResolverProvider.resolve({module:'categories',view:'list',controller:'categoriesController', area:'adminViews'}))
//---------end category---------------------


        .when('/notificationRules', routeResolverProvider.resolve({module:'notificationRules',view:'list',controller:'notificationRulesController', area:'adminViews'}))
        .when('/notificationRules/edit/:id', routeResolverProvider.resolve({module:'notificationRules',view:'add',controller:'notificationRulesAddEditController', area:'adminViews'}))
        .when('/notificationRules/add', routeResolverProvider.resolve({module:'notificationRules',view:'add',controller:'notificationRulesAddEditController', area:'adminViews'}))


        //.when('/404', {templateUrl: 'html/adminViews/404.html',})
        .when('/404', routeResolverProvider.resolve({module:'',view:'404',controller:'', area:'adminViews'}))
        .otherwise({ redirectTo: '/404' });

}]);
