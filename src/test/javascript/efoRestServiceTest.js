// http://jasmine.github.io/2.0/introduction.html
// https://docs.angularjs.org/guide/unit-testing
// https://angularjs.de/artikel/angularjs-test

describe("efoRestService", function() {

    beforeEach(function() {
        module('forumServices');
    });

    it('should exist', inject(function (efoRestService) {
        expect(efoRestService).toBeDefined();
    }));

/*    it('returns 1', function(){
        var $injector = angular.injector([ 'ExpertForum' ]);
        /!*var myService = $injector.get( 'efoRestService' );
        expect(myService).toBeDefined();*!/
        //expect( myService.one ).toEqual(1);
    })*/

    /*it('should contain a forumServices',
        inject(function(efoRestService) {
            expect(efoRestService).not.to.equal(null);
        }));*/

/*    it("test the categoryApi", function() {

        var categoriesList = forumServices.categoryApi.getAll(null,function(value){
            categoriesList = value;
        });

        //expect(true).toBe(true);
        expect(forumServices).toBeDefined();
        expect(categoriesList).toBeDefined();*/
        /*expect(globalSettings.applicationPath).toBeDefined();
        expect(globalSettings.applicationPath).toContain("app");*/
    /*});*/
});
