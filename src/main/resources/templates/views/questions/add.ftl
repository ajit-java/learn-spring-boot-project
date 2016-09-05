    <h2 class="margin-vertical-xl margin-horizontal-m">Fragen Sie einen <b>Experten</b></h2>

    <form ng-class="{submitted:model.submitted}" action="#" class="form form-theme-standard" name="questionForm" id="questionForm">
        <fieldset class="grid gutter-xl">
            <div class="grid-item">
                <label for="txtQuestionTitle">Stellen Sie Ihre Frage <span class="font-lightgray font-s palm-hide">(Max. 160 Zeichen)</span></label>
                <span ng-show="questionForm.txtQuestionTitle.$error.required">*</span>
                <input required id="txtQuestionTitle" name="txtQuestionTitle" ng-model="questionModel.title" class="input-text" type="text"/>
            </div>
            <div class="grid-item">
                <label for="ddlCategoriesList">Wählen Sie eine Kategorie <span class="font-lightgray font-s palm-hide">(Wichtig für Experten)</span></label>
                <span ng-show="questionForm.ddlCategoriesList.$error.required">*</span>
                <select required class="select" ng-model="questionModel.categoryId" id="ddlCategoriesList" name="ddlCategoriesList" ng-options="c.id as c.categoryName for c in categoriesList" no-dirty-check>
                    <#--<option ng-repeat="c in categoriesList" value="{{c.id}}" >{{c.categoryName}}</option>-->
                </select>
            </div>
            <div class="grid-item">
                <label for="txtQuestionText">Beschreiben Sie Ihre Anliegen <span class="font-lightgray font-s palm-hide">(Max 3000 Zeichen)</span></label>
                <span ng-show="questionForm.txtQuestionText.$error.required">*</span>
                <textarea required class="textarea" id="txtQuestionText" name="txtQuestionText" ng-model="questionModel.text"></textarea>
            </div>
            <div class="grid-item one-whole">
                <a class="button-primary one-whole" ng-click="saveQuestion(questionForm)" href="">Frage absenden</a>
            </div>
        </fieldset>

    </form>