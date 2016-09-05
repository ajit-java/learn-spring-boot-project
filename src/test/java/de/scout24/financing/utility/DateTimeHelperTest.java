package de.scout24.financing.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateTimeHelper.class)
public class DateTimeHelperTest {
    private SimpleDateFormat sdf;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
    }

    @Test
    public void testGetUtcTimeAsStringInWinter() throws Exception {
        Date dateWinter = sdf.parse("2015-01-01 06:55:55");
        PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(dateWinter);
        Assert.assertEquals("2015-01-01 05:55:55", DateTimeHelper.getUtcDatetimeAsString());
    }

    @Test
    public void testGetUtcTimeAsStringInSummer() throws Exception {
        Date dateSummer = sdf.parse("2015-05-05 07:55:55");
        PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(dateSummer);
        Assert.assertEquals("2015-05-05 05:55:55", DateTimeHelper.getUtcDatetimeAsString());
    }

    @Test
    public void testInvalidStringDateToDateWithNoDate() {
        String str = "test";
        Assert.assertNull("should be null", DateTimeHelper.stringDateToDate(str));
    }

    @Test
    public void testInvalidStringDateToDateWithInvalidDate() {
        String str = "2050135 307505";
        Assert.assertNull("should be null", DateTimeHelper.stringDateToDate(str));
    }
}
