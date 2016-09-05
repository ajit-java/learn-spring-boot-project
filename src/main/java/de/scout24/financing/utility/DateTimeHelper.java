package de.scout24.financing.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Hary on 07/09/15.
 */
public class DateTimeHelper {

    static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date getUtcDatetimeAsDate() {
        // note: doesn't check for null
        return stringDateToDate(getUtcDatetimeAsString());
    }

    public static String getUtcDatetimeAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }

    public static Date stringDateToDate(String strDate) {
        Date dateToReturn = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

        try {
            dateToReturn = (Date) dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateToReturn;
    }

}
