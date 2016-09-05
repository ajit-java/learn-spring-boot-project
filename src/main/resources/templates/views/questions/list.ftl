<#--get headline from controller-->
<h2 class="margin-vertical-xl margin-horizontal-m"><span ng-bind-html="headline"></span></h2>

<#--<!--todo ng-ifinrole support or admin&ndash;&gt;-->
<h2 class="margin-xl" ng-show="questionPublishingStatus==1">Fragen (warten auf Freigabe)</h2>
<h2 class="margin-xl" ng-show="questionPublishingStatus==2">Alle freigegebenen Fragen</h2>
<h2 class="margin-xl" ng-show="questionPublishingStatus==3">Als Spam markiert Fragen</h2>
<h2 class="margin-xl" ng-show="questionPublishingStatus==4">Alle Zurückgewiesene Fragen</h2>
<#--<!--todo if in role loggedinuser&ndash;&gt;-->
<h2 class="margin-xl" ng-show="isInRole=='loggedInuser'">Die neuesten Fragen</h2>


    <#--<form action="#" class="form form-theme-standard margin-bottom-xxl">
        <fieldset>
            <input class="input-text" placeholder="Fragen Durchsuchen" type="search"/>
        </fieldset>
        <p class="font-lightgray">Keine Suche ausgeführt</p>
        &lt;#&ndash;<!--count AND cat and search string&ndash;&gt;&ndash;&gt;
    </form>-->

    <div class="questionSortBar font-lightgray margin-top-m">
        Sortieren nach
        <a href="#" class="float-right">
            <span>letzte Aktion</span>
            <i class="fa fa-sort-asc font-l"></i>
            <i class="fa fa-sort-desc font-l lastIcon"></i>
        </a>

        <a href="#" class="float-right margin-right-l">
            <span>Antworten</span>
            <i class="fa fa-sort-asc font-l"></i>
            <i class="fa fa-sort-desc font-l lastIcon"></i>
        </a>

        <#--<a href="#" class="fa fa-sort-asc">letzte Aktion</a>-->
        <hr>
    </div>
<div class="questionList">
    <ul class="margin-top-xl">
        <li class="grid" ng-repeat="q in questionsList track by $index">


            <#--{{q}}-->
            <div class="grid-item palm-two-twelfths one-twelfth font-lightgray font-center padding-top-m " ng-include="columnTemplateUrl">
                <#--code here will come from the include template, depending on the page: favorites, questions-->
            </div>



            <div class="grid-item palm-ten-twelfths eleven-twelfths background-white padding-vertical-s padding-horizontal-l" >
                <h4><a href="#/questions/{{q.id}}/details" target="_self">
                    {{q.title}}
                </a></h4>
                <div class="grid">
                    <div class="grid-item two-twelfths font-standard font-lightgray">
                        <i class="fa fa-comments"></i> <span>{{q.answerCount}}</span>
                    </div>

                    <#--if user is un role EXPERT: show distance to the "Fragesteller"-->
                    <#--<div class="grid-item two-twelfths font-standard font-lightgray align-right">
                        <i class="fa fa-map-marker"></i> <span>22</span>
                    </div>-->

                    <div class="grid-item eight-twelfths font-standard font-lightgray align-right">
                        <i class="fa fa-clock-o"></i> <span>
                        <last-activity parent-obj="q" text="Änderung vor"></last-activity>
                    </div>

                </div>
                <div class="palm-hide margin-top-m">{{q.text}}</div>
            </div>
        </li>
    </ul>
</div>

        <#-- <a href="#/questions/{{q.id}}/details" >
             <div class="grid-item one-twelfth align-center">
                 <i title="New Question" class="fa fa-folder-open-o font-lightgray" ng-show="getquestionPublishingStatus(q)=='NEW'"></i>
                 <i title="Approved" class="fa fa-check-circle-o font-confirm" ng-show="getquestionPublishingStatus(q)=='APPROVED'"></i>
                 <i title="Spam" class="fa fa-ban font-lightgray" ng-show="getquestionPublishingStatus(q)=='SPAM'"></i>
                 <i title="Rejected" class="fa fa-ban font-error" ng-show="getquestionPublishingStatus(q)=='REJECTED'"></i>
             </div>
             <div class="grid-item eleven-twelfths background-white font-regular">
                 <div class="grid-item ">{{q.title | limitTo: 500}}</div>
                 <div class="grid-item palm-hide margin-m">{{q.text | limitTo: 5000}}</div>
                 <div class="grid-item font-lightgray ">
                     <div class="grid-item three-twelfths"><i title="Count of answers" class="fa fa-check-circle"></i>{{q.answerCount}}</div>
                     <div class="grid-item three-twelfths"><i title="Count of comments"class="fa fa-comments"></i>{{q.commentCount}}</div>
                     <!--<div class="grid-item three-twelfths"><i class="fa fa-map-marker"></i>50<span class="font-xs">KM</span></div>&ndash;&gt;
                     <!--<div class="grid-item six-twelfths"><i class="fa fa-clock-o"></i><span class="font-xs"> letzte Aktivität von </span>5<span class="font-xs"> Tagen</span></div>&ndash;&gt;
                 </div>
             </div>
         </a>-->


