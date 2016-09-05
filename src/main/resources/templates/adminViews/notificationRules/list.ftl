<#--<pre>{{notificationRulesList | json}}</pre>-->

<div class="grid ">
    <h2 class="margin-xl">Notification Rules</h2>
    <div class="background-warning">
        <a class="float-right ng-isolate-scope" href="#/notificationRules/add "><i title="Add a New Rule" class="font-xxl font-lightgray fa fa-plus-circle"></i></a>
    </div>
    <div class="grid-item ">
        <div class="grid " ng-class="{'font-lightgray':nr.deleted}" ng-repeat="nr in notificationRulesList track by $index">
            <div class="grid-item background1-confirm">
                <hr/>
            </div>
            <div class="grid-item background-warning">
                NotificationEventType: <span class="font-bold">{{nr.notificationEventType}}</span>
                <a class="float-right ng-isolate-scope" href="#/notificationRules/edit/{{nr.id}} "><i title="Edit Rule" class="font-xxl font-lightgray fa fa-pencil"></i></a>
            </div>
            <div class="grid-item">
                RecipientType: {{nr.recipientType}}
            </div>
            <div class="grid-item">
                Role: {{nr.exfoRole}}
            </div>
            <div class="grid-item">
                Mail:
                <input type="checkbox" ng-model="nr.mail">
            </div>
            <div class="grid-item">
                Push:
                <input type="checkbox" ng-model="nr.push">
            </div>
            <div class="grid-item">
                Display:
                <input type="checkbox" ng-model="nr.display">
            </div>
            <div class="grid-item">
                Mail Subject: {{nr.mailSubject}}
            </div>
            <div class="grid-item">
                Mail Text: {{nr.mailText}}
            </div>
            <div class="grid-item">
                Display Notification Text: {{nr.displayNotificationText}}
            </div>
            <div class="grid-item">
                Deleted:
                <input type="checkbox" ng-model="nr.deleted">
            </div>
            <div class="grid-item">
                <a class="button-primary" ng-click="updateNotificationRule(nr)">Update</a>
            </div>
        </div>
    </div>
</div>