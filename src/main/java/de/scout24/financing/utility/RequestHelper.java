package de.scout24.financing.utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ajitchahal on 11/09/15.
 */
public class RequestHelper {
    public static String getCookie(HttpServletRequest context, String cookieName) {
        String retVal = "";
        try {
            Cookie[] cookies = context.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals(cookieName)) {
                        retVal = cookies[i].getValue();
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }

        return retVal;
    }
}
