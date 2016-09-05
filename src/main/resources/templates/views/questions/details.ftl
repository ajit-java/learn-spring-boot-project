<div class="margin-l margin-top-xl ">
    <a href="#" onclick="history.go(-1);" class="pointer float-left desk-hide">
        <i class="fa fa-chevron-left"></i> zurück
    </a>
    <a href class="float-right align-center" ng-click="updateFavorite(question)">
        <span class="margin-s desk-hide pam-hide">Merken</span><i ng-class="question.isfavorite ? 'font-brandorange' : 'font-lightgray'" class="fa fa-star-o fa-2x"></i>
        <div class="margin-s lap-hide">Frage merken</div>
    </a>
</div>


<#--{{question}}-->
<div class="grid">

    <div id="topInfo" class="grid-item margin-top-xl">
        <#--<a class="float-right" href="#/questions/edit/{{question.id}}">-->
            <#--<i title="Edit question" class="font-xxl font-lightgray fa fa-pencil"></i>-->
        <#--</a>-->
        <admin-edit-icon href="#/questions/edit/{{question.id}}"></admin-edit-icon>

        <strong class="font-xl">Frage</strong>
        <span id="topInfo-userName" class="font-lightgray">von {{question.forumUserName}}</span><span id="topInfo-date" class="font-lightgray"><last-activity parent-obj="question" text="vor"></last-activity></span>
        <hr class="palm-hide">
    </div>



    <div id="question">
        <div class="grid-item">
            <h1 class="font-l font-bold margin-vertical-xl">{{question.title}}</h1>
            <p>{{question.text}}</p>
        </div>
    </div>

    <div id="answers" class="grid-item background-info margin-top-xl border" ng-repeat="a in answers track by $index">
        <div class="margin-xl">
            <div class="grid-item one-whole">
                <h4>Antwort</h4>
                <p>von {{a.forumUserName}} am {{a.createdAt | date : format : timezone}}</p>
            </div>
            <div class="grid-item one-whole">
                <div class="grid-item ">{{a.answerText}}</div>
            </div>
            <div class="grid-item one-whole">
                <div class="grid-item ">{{a.answerText}}</div>
            </div>
            <fs24-comments parent-obj="a" comments-list="comments" ></fs24-comments>
        </div>
    </div>

    <#--//todo if role is expert-->
    <div id="expertArea" class="grid-item margin-top-xl">
        <answer-question parent-obj="question"></answer-question>
    </div>

    <#--//todo if role is admin-->
    <div id="adminButtons" class="grid-item margin-top-xl">
        <publishing-status-change-buttons-large state-change-helper="stateChangeHelper" parent-obj="question"></publishing-status-change-buttons-large>
    </div>
</div>
