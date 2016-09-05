<div id="buttonControlLarge" class="grid gutter-m" style="margin-bottom: -20px"
     ng-show12222="ifIsInRole()=='admin/support'">
    <!--todo check role of user here-->
    <div class="grid-item one-whole align-center" ng-show="!approved">
        <a class="button-primary one-whole height-l" href="" ng-click="stateChangeHelper.approve(parentObj)">
            <div class="align-left font-m">
                Frage freigeben
                <i title="Approve" class="float-right fa fa-check-circle-o padding-xs "></i>
            </div>
        </a>
    </div>

    <div class="grid-item one-whole align-center">
        <a class="button-secondary one-whole height-l" href="" ng-click="showContactUserForm=!showContactUserForm">
            <div class="align-left font-m">
                Benutzer kontaktieren
                <i title="Approve" class="float-right fa fa-envelope padding-xs "></i>
            </div>
        </a>

        <div id="contactUser" ng-show="showContactUserForm">
            <form ng-class="{submitted:model.submitted}" action="#" class="form form-theme-standard" name="contactForm"  id="contactForm">
                <fieldset>
                    <input required id="txtEmailSubject" name="txtEmailSubject" ng-model="notificationModel.notificationSubject"
                           class="input-text" placeholder="Email subject" type="text"/>
                    <textarea required class="input-text-area-xl margin-top-m" id="txtEmailText" name="txtEmailText"
                      placeholder="Email message" ng-model="notificationModel.notificationText"></textarea>

                    <div class=" margin-top-m align-center">
                        <a class="button-primary one-whole " ng-click="sendEmailToUser(contactForm)" href="">
                            <div class="align-center">
                                Email senden
                            </div>
                        </a>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

    <div class="grid-item one-whole align-center" ng-show="!rejected">
        <a class="button-secondary one-whole height-l" href="" ng-click="stateChangeHelper.reject(parentObj)">
            <div class="align-left font-m">
                Abweisen mit Grund
                <i title="Approve" class="float-right fa fa-ban padding-xs "></i>
            </div>
        </a>
    </div>
    <div class="grid-item one-whole align-center" ng-show="!spam">
        <a class="button-secondary one-whole height-l" href="" ng-click="stateChangeHelper.spam(parentObj)">
            <div class="align-left font-m">
                Als SPAM markieren
                <i title="Approve" class="float-right fa fa-times-circle-o padding-xs "></i>
            </div>
        </a>
    </div>

</div>