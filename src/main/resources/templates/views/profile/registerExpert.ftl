<div class="grid ">

    <h2 class="margin-xl">Registrierung</h2>
    <p>Es freut uns das Sie sich dazu entschieden haben einen Account zu erstellen.</p>

    <form action="#" ng-class="{submitted:model.submitted}" class="form form-theme-standard" name="userForm" id="questionForm">
        <fieldset>
            <div class="grid gutter-m">
                <div class="grid-item">
                    <input type="text" required ng-model="forumUserModel.firstName" class="input-text " placeholder="Vorname" type="text"/>
                </div>
                <div class="grid-item">
                    <input typeof="text" required ng-model="forumUserModel.lastName" class="input-text " placeholder="Nachname (Wird online NICHT angezeigt!)" type="text"/>
                </div>
                <div class="grid-item">
                    <input type="text" ng-model="forumUserModel.street" class="input-text " placeholder="Strasse" type="text"/>
                </div>
                <div class="grid-item one-fourth">
                    <input type="number" name="plz" required ng-model="forumUserModel.zipCode" maxlength="5" class="input-text  " placeholder="PLZ" type="text"/>
                </div>

                <div class="grid-item three-fourths">
                    <input type="text" required ng-model="forumUserModel.city" class="input-text  " placeholder="Ort" type="text"/>
                </div>
                <div class="grid-item">
                    <input type="email" required ng-model="forumUserModel.email" class="input-text " placeholder="Email" type="text"/>
                </div>
                <div class="grid-item">
                    <input type="password" required class="input-text " minlength="4" id="Password" name="Password" placeholder="Passwort" ng-model="forumUserModel.password"/>
                </div>
                <div class="grid-item one-whole margin-bottom-xl margin-top-xl align-center">
                    <a class="button-secondary one-whole " ng-click="saveForumUser(userForm)" href="">
                        <div class="margin-m align-left" >
                            Account erstellen
                        </div>
                    </a>
                </div>
            </div>
        </fieldset>

    </form>
    <div class="grid-item">
        <p>Sie benötigen Hilfe?</p>
        <a href="mailto:help@financescout24.de">help@financescout24.de</a>
    </div>
    <div class="grid-item">
        <p>Sie sind ein Experte?</p>
        <a href="#/registerexpert">Registrien Sie sich jetzt kostenlos!</a>
    </div>
</div>
