<#--Meine Merkliste | Neuesten Fragen-->
<#--link to mark/unmark as favorite-->
<a href ng-class="q.isfavorite ? 'font-brandorange' : 'font-lightgray'" ng-click="updateFavorite(q, $index)"><i class="fa fa-star-o fa-2x"></i></a>

<div ng-show="q.publishingStatus=='NEW'" class="background-brandblue font-white padding-horizontal-xs padding-vertical-xxs font-xs font-uppercase margin-top-s">Freigabe</div>
