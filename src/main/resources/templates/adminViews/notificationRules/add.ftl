<h2 class="margin-xl">Add/edit Notification Rule</h2>

<form ng-class="{submitted:model.submitted}" action="#" class="form form-theme-standard" name="notificationRulesForm" id="notificationRulesForm">
    <fieldset class="grid gutter-xl">
        <div class="grid-item">
            <label for="ddlNotificationEventTypeList">Notification Event Type</label>
            <span ng-show="notificationRulesForm.ddlNotificationEventTypeList.$error.required">*</span>
            <select required class="select" ng-model="notificationRuleModel.notificationEventType" id="ddlNotificationEventTypeList" name="ddlNotificationEventTypeList" ng-options="c for c in notificationEventTypeNames" no-dirty-check>
            </select>
        </div>
        <div class="grid-item">
            <label for="ddlRecipientsList">Recipient Type</label>
            <span ng-show="notificationRulesForm.ddlRecipientsList.$error.required">*</span>
            <select required class="select" ng-model="notificationRuleModel.recipientType" id="ddlRecipientsList" name="ddlRecipientsList" ng-options="c for c in recipientTypeNames" no-dirty-check>
            </select>
        </div>
        <div class="grid-item">
            <label for="ddlRolesList">Role</label>
            <span ng-show="notificationRulesForm.ddlRolesList.$error.required">*</span>
            <select required class="select" ng-model="notificationRuleModel.exfoRole" id="ddlRolesList" name="ddlRolesList" ng-options="c for c in exfoRoleNames" no-dirty-check>
            </select>
        </div>
        <div class="grid-item">
            <label for="ddlNotificationEventTypeList">Mail</label>
            <input type="checkbox" ng-model="notificationRuleModel.mail">
        </div>
        <div class="grid-item">
            <label for="ddlNotificationEventTypeList">Push</label>
            <input type="checkbox" ng-model="notificationRuleModel.push">
        </div>
        <div class="grid-item">
            <label for="ddlNotificationEventTypeList">Display</label>
            <input type="checkbox" ng-model="notificationRuleModel.display">
        </div>
        <div class="grid-item">
            <label for="txtSubject">Email Subject</label>
            <span ng-show="notificationRulesForm.txtSubject.$error.required">*</span>
            <input required id="txtSubject" name="txtSubject" ng-model="notificationRuleModel.mailSubject" class="input-text" type="text"/>
        </div>
        <div class="grid-item">
            <label for="txtMailText">Email Text</label>
            <span ng-show="notificationRulesForm.txtMailText.$error.required">*</span>
            <textarea required class="textarea" id="txtMailText" name="txtMailText" ng-model="notificationRuleModel.mailText"></textarea>
        </div>
        <div class="grid-item">
            <label for="txtdisplayNotificationText">Display Notification Text</label>
            <span ng-show="notificationRulesForm.txtdisplayNotificationText.$error.required">*</span>
            <input required id="txtdisplayNotificationText" name="txtdisplayNotificationText" ng-model="notificationRuleModel.displayNotificationText" class="input-text" type="text"/>
        </div>
        <div class="grid-item one-whole">
            <a class="button-primary one-half" ng-click="updateNotificationRule(notificationRulesForm)" href="">Save</a>
        </div>
    </fieldset>

</form>


