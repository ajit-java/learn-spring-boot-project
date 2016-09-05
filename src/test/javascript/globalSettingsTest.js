describe("globalSettings", function() {
    it("test the globalSettings object", function() {
        //expect(true).toBe(true);
        expect(globalSettings).toBeDefined();
        expect(globalSettings.applicationPath).toBeDefined();
        expect(globalSettings.applicationPath).toContain("app");
    });

    it("test the calcTimespan function", function(){
        var testDate = new Date();

        var result = globalSettings.utitlities.calcTimespan(testDate);
        expect(result).toBeDefined();
        expect(result["string"]).toBeDefined();
        expect(result.string).toEqual("Sekunden");


        // add 2hours to our date, that the difference is more than 2h
        /*var testDateHour = new Date(testDate.setHours(testDate.getHours()+2));
        //var testDateHour = testDate.setHours(testDate.getHours()+2);
        var resultHours = globalSettings.utitlities.calcTimespan(testDateHour);
        expect(resultHours.string).toEqual("Stunden");*/
    });
});