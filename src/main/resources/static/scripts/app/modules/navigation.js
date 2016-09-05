forumApp.config(['$routeProvider', 'routeResolverProvider', '$sceDelegateProvider', function ($routeProvider, routeResolverProvider, $sceDelegateProvider) {
  $sceDelegateProvider.resourceUrlWhitelist([
    // Allow same origin resource loads.
    'self',
    // Allow loading from our assets domain.  Notice the difference between * and **.
    //globalSettings.applicationPath+'**'
      '**'
  ]);


    $routeProvider
        //.when('/', {templateUrl: 'view?viewName=/views/home', controller: 'homeController'})
        .when('/', routeResolverProvider.resolve({module:'',view:'home',controller:'homeController', secure:false}))
//---------question---------------------
      .when('/questions/pstatus/:id', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsStatusController'}))
      .when('/questions/qstatus/:id', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsQuestionStatusController'}))
      .when('/questions/add', routeResolverProvider.resolve({module:'questions',view:'add',controller:'questionsAddController'}))
      .when('/questions/:id/details', routeResolverProvider.resolve({module:'questions',view:'details',controller:'questionsDetailsController'}))
      .when('/questions/:id/edit', routeResolverProvider.resolve({module:'questions',view:'edit',controller:'questionsEditController'}))
      .when('/questions/:id/delete', routeResolverProvider.resolve({module:'questions',view:'delete',controller:'questionsDeleteController'}))
      .when('/questions/user/', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsUserController'}))
      .when('/questions/user/answers', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsExpertUserController'}))
      .when('/questions/edit/:id', routeResolverProvider.resolve({module:'questions',view:'add',controller:'questionsEditController'}))
      .when('/questions/favorites/', routeResolverProvider.resolve({module:'questions',view:'list',controller:'questionsFavoriteController'}))
//---------end question---------------------

//---------profile---------------------
        .when('/login', routeResolverProvider.resolve({module:'',view:'home',controller:'loginController', secure:false}))
        .when('/logout', routeResolverProvider.resolve({module:'',view:'home',controller:'logoutController', secure:false}))
        .when('/profile/user', routeResolverProvider.resolve({module:'profile',view:'user',controller:'profileController'}))
        .when('/profile/expert', routeResolverProvider.resolve({module:'profile',view:'expert',controller:'profileController'}))
        .when('/profile/welcome/expert', routeResolverProvider.resolve({module:'profile',view:'welcomeExpert',controller:'profileController'}))
        .when('/profile/welcome/user', routeResolverProvider.resolve({module:'profile',view:'welcomeUser',controller:'profileController'}))
        .when('/profile/register/expert', routeResolverProvider.resolve({module:'profile',view:'registerExpert',controller:'profileController'}))
        .when('/profile/register/user', routeResolverProvider.resolve({module:'profile',view:'registerUser',controller:'profileController', secure:true}))
//---------end profile---------------------


        //.when('/404', {templateUrl: 'view?viewName=/views/404'})
        .when('/404', routeResolverProvider.resolve({module:'',view:'404',controller:'', secure:false}))
        .otherwise({ redirectTo: '/404' });
}]);




