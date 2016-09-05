<div class="grid-item one-whole margin-top-xl">
    <div class="grid-item pointer font-blue " ng-show="model.parentObject.commentCount>0" ng-click="getComments(model.parentObject.id)">
        <i class="fa fa-chevron-down"></i>
        <span>{{model.parentObject.commentCount}} Kommentar</span>
        <span ng-show="!showComments(model.parentObject.id)"> anzeigen</span>
        <span ng-show="showComments(model.parentObject.id)"> ausblenden</span>
    </div>
    <div class="grid-item pointer font-blue " ng-show="model.parentObject.commentCount<=0" ng-click="toggleSaveComment(model.parentObject.id)">
        <i class="fa fa-chevron-down"></i>
        <span ng-show="!showSaveComment(model.parentObject.id)">Kommentar verfassen</span>
        <span ng-show="showSaveComment(model.parentObject.id)">Ausblenden</span>
    </div>

    <div ng-show="showComments(model.parentObject.id)">
        <div ng-repeat="c in comments track by $index" id="comment" class="grid-item background-white border margin-vertical-m ">
            <div class="grid">
                <div class="grid-item margin-m">
                    <h4 ng-show="!c.isExpertUserComment">Kommentar</h4>
                    <h4 ng-show="c.isExpertUserComment">Experten Kommentar</h4>
                    <p class="font-s font-lightgray">von {{c.forumUserName}}, am {{c.createdAt | date : format : timezone}}</p>
                    <p>{{c.commentText}}</p>
                </div>
                <div class="grid-item">
                    <publishing-status-change-buttons state-change-helper="stateChangeHelper" parent-obj="c"></publishing-status-change-buttons>
                </div>
                <div class="grid-item margin-top-l" ng-show="showEditComment(c)">
                    <form action="#" class="form form-theme-white" name="commentEditForm" id="commentEditForm">
                        <fieldset>
                            <label class="font-regular font-xs margin-top-xs" for="txtCommentText">Bearbeiten Sie Ihr Kommentar
                                <span class="font-lightgray">(Max 3000 Zeichen)</span></label>
                            <span ng-show="commentForm.txtCommentText.$error.required">*</span>
                            <textarea required id="txtEditedCommentText" name="txtCommentText" ng-model="model.editComment.commentText" class="input-text-area border" placeholder="Ihr kommentar" type="text"/>
                            <div class="grid-item one-whole margin-bottom-xs margin-top-xs align-center">
                                <a class="button-secondary one-whole " ng-click="updateComment(commentEditForm)" href="">
                                    <div class=" align-center" >
                                        Kommentar aktualisieren
                                    </div>
                                </a>
                            </div>
                        </fieldset>

                    </form>
                </div>
            </div>
        </div>
    </div>

    <div ng-show="showComments(model.parentObject.id) && !showSaveComment(model.parentObject.id)" class="grid-item one-whole margin-bottom-xs margin-top-m align-center">
        <a class="button-secondary one-whole " ng-click="toggleSaveComment(model.parentObject.id)" href="">
            <div class="align-center" >
                Kommentar schreiben
            </div>
        </a>
    </div>

    <div class="grid-item margin-top-m" ng-show="showSaveComment(model.parentObject.id)">
        <form action="#" class="form form-theme-standard" name="commentForm" id="commentForm" ng-class="{submitted:model.submitted}">
            <fieldset>
                <label class="font-regular font-xs margin-top-xs" for="txtCommentText">Stellen Sie Ihr Kommentar
                    <span class="font-lightgray">(Max 3000 Zeichen)</span></label>
                <span ng-show="commentForm.txtCommentText.$error.required">*</span>
                <textarea required id="txtCommentText" name="txtCommentText" ng-model="commentModel.commentText" class="input-text-area" placeholder="Ihr kommentar" type="text"/>
                <div class="grid-item one-whole margin-bottom-xs margin-top-xs align-center">
                    <a class="button-secondary one-whole " ng-click="saveComment(commentForm)" href="">
                        <div class=" align-center" >
                            Kommentar absenden
                        </div>
                    </a>
                </div>
            </fieldset>

        </form>
    </div>

    <div class="grid-item pointer font-blue " ng-show="showComments(model.parentObject.id)" ng-click="getComments(model.parentObject.id)" >
        <i class="fa fa-chevron-up"></i>
        <span>{{model.parentObject.commentCount}} Kommentar ausblenden </span>
    </div>
    <div class="grid-item pointer font-blue " ng-show="model.parentObject.commentCount<=0 && showSaveComment(model.parentObject.id)" ng-click="toggleSaveComment(model.parentObject.id)">
        <i class="fa fa-chevron-up"></i>
        <span ng-show="!showSaveComment(model.parentObject.id)">Kommentar verfassen</span>
        <span ng-show="showSaveComment(model.parentObject.id)">Ausblenden</span>
    </div>
</div>

