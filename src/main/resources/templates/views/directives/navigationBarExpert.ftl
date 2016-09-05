
<nav id="navigationBar" class="grid padding-bottom background">

    <!--palm + lap-->
    <div class="grid-item palm-one-fifth lap-one-fifth desk-one-sixth desk-hide padding-top">
        <a href="#" onclick="return false;" class="" ng-click="showSearchBar = !showSearchBar"  ><i class="fa fa-search"></i><span class="palm-hide lap-hide"> Search</span></a>

        <!--searchbar for mobile version, show per click on the search icon -->
        <div ng-hide="showSearchBar" id="searchBar" class="grid-item form">
            <input class="input-text input-unit" type="search" value="" placeholder="Suchbegriff eingeben" autocomplete="off">
            <span id="searchButton" class="unit-icon fa fa-search searchButton"></span>
        </div>
    </div>

    <!--desk only-->
    <div class="grid-item lap-hide palm-hide padding-m searchBarFull">
        <div class="grid gutter-m form">
            <div class="grid-item one-fourth">
                <label class="font-bold" for="selProduct">Produkt wählen</label>
                <select class=" select margin-bottom-s one-whole font-regular" id="selProduct">
                    <option value="">Ratenkredit</option>
                    <option value="">Kfz-Versicherung</option>
                    <option value="">Baufinanzierung</option>
                </select>
            </div>
            <div class="grid-item one-half" style="position: relative;">
                <label class="font-bold" for="btnSearch">Suchbegriff</label>
                <input class="input-text input-unit font-regular" type="search" id="btnSearch" value="" placeholder="z.B. Wohnungskredite" autocomplete="off">
                <span id="searchButton2" class="unit-icon fa fa-search searchButton button"></span>
            </div>
        </div>
    </div>

    <a class="grid-item palm-one-fifth lap-one-fifth desk-one-fifth padding-top " href="#" ><i class="fa fa-filter"></i><span class="palm-hide lap-hide"> Meine Filterliste</span></a>
    <a class="grid-item palm-one-fifth lap-one-fifth desk-one-fifth padding-top" href="#" ><i class="fa fa-star-o "></i><span class="palm-hide lap-hide"> Meine Merkliste</span></a>
    <a class="grid-item palm-one-fifth lap-one-fifth desk-one-fifth padding-top" href="#" ><i class="fa fa-comments"></i><span class="palm-hide lap-hide"> Meine Antworten</span></a>
    <a class="grid-item palm-one-fifth lap-one-fifth desk-one-fifth padding-top" href="#" ><i class="fa fa-clock-o "></i><span class="palm-hide lap-hide"> Neuesten Fragen</span></a>
</nav>