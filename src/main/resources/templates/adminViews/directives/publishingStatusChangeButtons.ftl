<div id="buttonControl" class="grid float-right font-lightgray font-m " style="margin-bottom: -20px" ng-show12222="ifIsInRole()=='admin/support'"> <!--todo check role of user here-->
    <div class="grid-item margin-right-xs pointer circle border background-lightblue " ng-click="stateChangeHelper.edit(parentObj)">
        <i title="Edit" class="fa fa-pencil padding-xs" ></i>
    </div>
    <div class="grid-item margin-right-xs circle border " ng-class="{'background-confirm-dark':approved, 'background-confirm pointer':!approved}"  ng-click="stateChangeHelper.approve(parentObj)">
        <i title="Approve" class="fa fa-check-circle-o padding-xs"  ></i>
    </div>
    <div class="grid-item margin-right-xs  circle  border" ng-class="{'background-warning-dark':rejected, 'background-warning pointer':!rejected}"  ng-click="stateChangeHelper.reject(parentObj)">
        <i title="Reject" class=" fa fa-ban padding-xs"   ></i>
    </div>
    <div class="grid-item margin-right-xs  circle border " ng-class="{'background-error-dark':spam, 'background-error pointer':!spam}" ng-click="stateChangeHelper.spam(parentObj)">
        <i title="Spam" class=" fa fa-times-circle-o padding-xs"  ></i>
    </div>

</div>