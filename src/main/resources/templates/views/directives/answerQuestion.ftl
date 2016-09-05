<div class="grid">
    <div class="grid-item one-whole align-center">
        <a class="button-secondary one-whole height-l" href="" ng-click="model.showSaveAnswer=!model.showSaveAnswer">
            <div class="align-center ">
                <i title="Approve" class="fa fa-chevron-down padding-xs "></i>
                Frage beantworten
            </div>
        </a>
    </div>


    <div class="grid-item margin-top-m" ng-show="model.showSaveAnswer">
        <form action="#" class="form form-theme-standard" name="answerForm" id="answerForm" ng-class="{submitted:model.submitted}">
            <fieldset>
                <textarea required id="txtAnswerText" name="txtAnswerText" ng-model="answerModel.answerText"
                          class="input-text-area" placeholder="Ihre Antwort" type="text"/>

                <div class="grid-item one-whole margin-bottom-xs margin-top-xs align-center">
                    <a class="button-secondary one-whole " ng-click="saveAnswer(answerForm)" href="">
                        <div class=" align-center">
                            Antwort absenden
                        </div>
                    </a>
                </div>
            </fieldset>

        </form>
    </div>

</div>