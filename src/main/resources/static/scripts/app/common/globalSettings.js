/**
 * Created by ajitchahal on 24/09/15.
 */
var globalSettings = {
    form: {validate:null},
    applicationPath: "@app.host@@app.contextPath@/",
    loginPath: "@loginPath@",
    fs24AuthApiPath: "@fs24AuthApiPath@",
    logOutPath: "@logOutPath@",
    getDirectivesTemplatePath: null,
    loggedInUserInfo: {
        "forumUserId": 0,
        "nickName": null,
        "message": null,
        "role": null,
        "authenticated": false
    },//{loginSuccess: true, loggedInForumUserId: 2, role: "USER"},//todo review default values - @fan: CHANGE LOGONSUCCESS TO FALSE AND LOGGEDINFORUMUSERID TO NULL
    getAdminDirectivesTemplatePath: null,
    enums:{
        PublishingStatus: {New:"NEW", Approve:"APPROVED", Spam:"SPAM", Rejected:"REJECTED"},
        QuestionStatus: {Open:"OPEN", Answered:"ANSWERED", HelpfullyAnswered:"ANSWERED_HELPFULLY", Closed:"CLOSED"},
        ExfoRole:{LoggedInUser:"USER", RequestedExpert:"EXPERT_REQUESTED", ApprovedExpert:"EXPERT_APPROVED", Support:"SUPPORT", Admin:"ADMIN"},


        NotificationEventType: {SUPPORT_CONTACT:"SUPPORT_CONTACT",EXPERT_CONTACT:"EXPERT_CONTACT",  QUESTION_CREATED: "QUESTION_CREATED",
            QUESTION_UPDATED: "QUESTION_UPDATED",
            QUESTION_CLOSED: "QUESTION_CLOSED",
            QUESTION_DELETED: "QUESTION_DELETED",
            QUESTION_SPAMMED: "QUESTION_SPAMMED",
            ANSWER_CREATED: "ANSWER_CREATED",
            ANSWER_UPDATED: "ANSWER_UPDATED",
            ANSWER_COMMENTED_BY_EXPERT: "ANSWER_COMMENTED_BY_EXPERT",
            ANSWER_COMMENTED_BY_USER: "ANSWER_COMMENTED_BY_USER",},
        NotificationChannel: {PUSH:"PUSH",EMAIL:"EMAIL",DISPLAY:"DISPLAY" },
        NotificationStatus: {NEW:"NEW",SENT:"SENT",UNSEEN:"UNSEEN",SEEN:"SEEN"},
        RecipientType: {THREAD_INITIATOR: "THREAD_INITIATOR",
            THREAD_COMMENTER: "THREAD_COMMENTER",
            THREAD_FAVORITER: "THREAD_FAVORITER",
            EXPERT_INITIATOR: "EXPERT_INITIATOR",//EXPERT OF ANSWER
            EXPERT: "EXPERT",//ANY OTHER EXPERT IN THE THREAD
            USER: "USER"},
        ObjectType: { QUESTION: "QUESTION",
            ANSWER: "ANSWER",
            COMMENT: "COMMENT",
            USER: "USER",
            EXPERT: "EXPERT",
            NOTICE: "NOTICE"},
    },
    utitlities:{calcTimespan:null},
    //Role:{LoggedInUser:"USER", RequestedExpert:"EXPERT_REQUESTED", ApprovedExpert:"EXPERT_APPROVED", Support:"SUPPORT", Admin:"ADMIN"},
};

// calc the difference from a given date to date-Now
globalSettings.utitlities.calcTimespan = function(date){
    var dateNow = Date.now();
    var dateActivity = Date.parse(date);
    var difference = (dateNow - dateActivity) / 1000;
    var diffNumber, diffSring;
    // console.log("Diff in seconds" + difference);

    if(difference<60)
    {
        diffNumber = difference;
        diffSring = "Sekunden";
    }
    else if(difference < 3600) // <60min
    {
        diffNumber = Math.round(difference/60);
        diffSring = "Minuten";
        if(diffNumber === 1) {
            diffSring = "Minute";
        }
    }
    else if(difference < 86400) // <24h
    {
        diffNumber = Math.round(difference/3600);
        diffSring = "Stunden";
        if(diffNumber === 1) {
            diffSring = "Stunde";
        }
    }
    else if(difference < 604800) // <7days
    {
        diffNumber = Math.round(difference/86400);
        diffSring = "Tage";
        if(diffNumber === 1) {
            diffSring = "Tag";
        }
    }
    else if(difference < 2419200) // <4weeks
    {
        diffNumber = Math.round(difference/604800);
        diffSring = "Wochen";
        if(diffNumber === 1) {
            diffSring = "Woche";
        }
    }
    else if(difference < 29030400) // <12months
    {
        diffNumber = Math.round(difference/2419200);
        diffSring = "Monate";
        if(diffNumber === 1) {
            diffSring = "Monat";
        }
    }
    else if(difference > 29030399) // >12months
    {
        diffNumber = Math.round(difference/29030400);
        diffSring = "Jahre";
        if(diffNumber === 1) {
            diffSring = "Jahr";
        }
    }

    return {number: diffNumber, string: diffSring};
}

globalSettings.getDirectivesTemplatePath = function(templateName){
    var path = globalSettings.applicationPath+'view?viewName=/{0}/{1}'.format("views/directives", templateName);

    return path;
}
globalSettings.getAdminDirectivesTemplatePath = function(templateName){
    var path = globalSettings.applicationPath+'view?viewName=/{0}/{1}'.format("adminViews/directives", templateName);

    return path;
}

globalSettings.form.validate = function(formObject)
{
    if(formObject && formObject.$invalid)
    {
        if(formObject.$error.required)
        {
            toastr.error('Please fill in all the required fields!');
        }
        else if(formObject.$error.maxlength)
        {
            var fieldName = formObject.$error.maxlength[0].$name?formObject.$error.maxlength[0].$name:'';
            toastr.error('Field ' + fieldName +' is too large.');
        }
        else if(formObject.$error.minlength) {
            var fieldName = formObject.$error.minlength[0].$name ? formObject.$error.minlength[0].$name : '';
            toastr.error('Field ' + fieldName + ' is too small.');
        }
        else if(formObject.$error.email)
        {
            toastr.error('Invalid email.');
        }
        else{
            toastr.error('Please fill in all the required fields!');
        }
    }

    return true;
}
var setToasterOptions = function() {
    if (toastr) {
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
    }
}
setToasterOptions();

String.prototype.capitalizeFirstLetter = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

String.prototype.format = function() {
    // The string containing the format items (e.g. "{0}")
    // will and always has to be the first argument.
    var theString = this;

    // start with the 1st argument (i = 0)
    for (var i = 0; i < arguments.length; i++) {
        // "gm" = RegEx options for Global search (more than one instance)
        // and for Multiline search
        var regEx = new RegExp("\\{" + (i) + "\\}", "gm");
        theString = theString.replace(regEx, arguments[i]);
    }

    //remove double / in path if any
    theString = theString.replace("//", "/");

    return theString;
}

function routeResolverProvider(){

    this.$get= function(){
        return this;
    }

    this.resolve = function (options) {
        var route = {};

        route.templateUrl = function (params) {
            var view = options.view;
            var module = options.module;
            var area = options && options.area?options.area:'views';

            var path = globalSettings.applicationPath+'view?viewName=/{0}/{1}/{2}'.format(area, module, view);
console.debug(path);
            return path;
        };

        route.controller = function ($rootScope, $scope, $route, $controller) {
            var params = $route.current.params;//parameters such as :nav1 if needed
            var controller = options.controller;

            var controllerName = '{0}'.format(controller);

            $controller(controllerName, {$scope: $scope});
        }

        if(typeof(options.secure) !== 'undefined' ) {
            route.secure = options.secure;
        }
        else {
            route.secure = true;
        }

        return route;
    }}



