<div class="{{b.designClass}}" ng-repeat="b in forumButtons track by $index">
    <a class="{{b.buttonClass}}" href="{{b.link}}" ng-click="b.ngClick">
        <div class="align-left">
            {{b.titleText}} <span class="font-s">{{b.subTitle}}</span>
            <p class="float-right">{{b.info}}</p>
            <i ng-show="b.fontAwesomeIconClass" class="{{b.fontAwesomeIconClass}} "></i>
        </div>
    </a>
</div>