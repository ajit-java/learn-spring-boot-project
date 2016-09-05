<div class="grid">

    <h2 class="margin-xl">Hallo!</h2>
    <p>Sie haben anscheinend noch keinen Account order Sie sind noch nicht eingelogged.</p>

    <form ng-class="{submitted:model.submitted}" action="#" class="form form-theme-standard" name="loginForm" id="questionForm">
        <fieldset>
            <input required id="txtUserName" name="txtUserName" ng-model="userModel.loginId" class="input-text" placeholder="Benutzername / Email" type="text"/>
            <input type="password" required class="input-text margin-top-m" id="txtPassword" name="txtPassword" placeholder="Passwort" ng-model="userModel.password"/>

            <div class="grid-item one-whole margin-bottom-xl margin-top-xl align-center">
                <a class="button-secondary one-whole " ng-click="loginUser(loginForm)" href="">
                    <div class="margin-m align-left" >
                        Einloggen
                    </div>
                </a>
            </div>


            <div class="grid-item one-whole margin-bottom-xl margin-top-xl align-center">
                <p>Hier können Sie sich kostenlos registieren.</p>
                <a class="button-secondary one-whole " href="#/profile/register/user">
                    <div class="margin-m align-left" >
                        Account erstellen
                    </div>
                </a>
            </div>
        </fieldset>

    </form>

</div>
