package de.scout24.financing.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.UserRepository;
import de.scout24.financing.resource.AuthenticatedForumUserResource;
import de.scout24.financing.service.impl.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Role;
import de.scout24.financing.domain.User;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.service.impl.RestService;
import de.scout24.financing.service.impl.auth.ExfoUserDetailsService;

@Controller
public class AuthenticationController {

    private static final String SERVICE_URL_PRINCIPAL = "https://exfo.sandbox-immobilienscout24.de:8444/auth/public/principal";
    private static final String HEADER_NAME_COOKIE = "Cookie";
    private static final String COOKIE_NAME_SSO = "SSO";
    private static final String COOKIE_NAME_SSO_HMAC = "SSO-HMAC";

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ExfoUserDetailsService userDetailsService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestService restService;
    
    @Value("${exfo.sso.baseUrl}" + "${exfo.sso.authenticationPath}")
    private String authenticationRestUrl;

    /**
     * Call this entrypoint by AJAX to receive the logged in user. If there are no authentication cookies
     * available, a direct login via the SSO server will be attempted and the new principal is returned
     *
     * @param request the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @return the (logged in user's) principal
     */
    @RequestMapping(value = "authenticate", method = RequestMethod.GET)
    @ResponseBody
    public AuthenticatedForumUserResource authenticate(HttpServletRequest request, HttpServletResponse response,
           @RequestParam(required = false) String sso, @RequestParam(required = false) String ssoHmac, Principal principal) throws ExpertForumException {
        return profileService.authenticate(request, response, sso, ssoHmac);
    }

//    /**
//     * Call this entrypoint by AJAX to receive the logged in user. If there are no authentication cookies
//     * available, a direct login via the SSO server will be attempted and the new principal is returned
//     *
//     * @param request the {@link HttpServletRequest}
//     * @param response the {@link HttpServletResponse}
//     * @return the (logged in user's) principal
//     */
//    @RequestMapping(value = "authenticate_old", method = RequestMethod.GET)
//    @ResponseBody
//    public ForumUser authenticate_old(HttpServletRequest request, HttpServletResponse response) {
//        HttpHeaders headers = new HttpHeaders();
//        Optional<Cookie[]> cookies = Optional.ofNullable(request.getCookies());
//        if (cookies.isPresent()) {
//            for (Cookie cookie : request.getCookies()) {
//                if (cookie.getName().equals(COOKIE_NAME_SSO)) {
//                    headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO + "=" + cookie.getValue());
//                } else if (cookie.getName().equals(COOKIE_NAME_SSO_HMAC)) {
//                    headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO_HMAC + "=" + cookie.getValue());
//                }
//            }
//        }
//
//        User user = null;
//        ForumUser forumUser = null;
//        try {
//            user = restService.exchange(authenticationRestUrl, HttpMethod.GET, null, headers,
//                    new ParameterizedTypeReference<User>() {}).getBody();
//            if (user != null) {
//                forumUser = (ForumUser) userDetailsService.loadUserByUsername(user.getEmail());
//            }
//        } catch (RestClientException e) {
//            LOGGER.warn(e.getLocalizedMessage());
//            response.setStatus(401);
//        } catch (UsernameNotFoundException e) {
//            LOGGER.warn(e.getLocalizedMessage());
//            forumUser = new ForumUser();
//            Role role = userDetailsService.findByRole(ExfoRole.USER);
//            forumUser.setRole(role);
//            forumUser.setUser(user);
//            forumUser.setEmail(user.getEmail());
//            forumUser = (ForumUser) userDetailsService.save(forumUser);
//        } finally {
//            if (forumUser != null) {
//                Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), forumUser.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            } else {
//                SecurityContextHolder.getContext().setAuthentication(null);
//            }
//        }
//        return forumUser;
//    }
//    
    /**
     * This entrypoint delivers a test page that can be used to test the SSO workflows.
     * 
     * @param request the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @param sso the value of the SSO cookie as returned (optional)
     * @param ssoHmac the value of the SSO-HMAC cookie as returned (optional)
     * @return the test page view
     */
    @RequestMapping(value = "authtest", method = RequestMethod.GET)
    public String showAuthenticationTestPage(HttpServletRequest request, HttpServletResponse response, 
            @RequestParam(required = false) String sso, @RequestParam(required = false) String ssoHmac) {
        Cookie ssoCookie = new Cookie(COOKIE_NAME_SSO, sso);
        Cookie ssoHmacCookie = new Cookie(COOKIE_NAME_SSO_HMAC, ssoHmac);
        if (sso == null) {
            ssoCookie.setMaxAge(0);
        }
        if (ssoHmac == null) {
            ssoHmacCookie.setMaxAge(0);
        }
        response.addCookie(ssoCookie);
        response.addCookie(ssoHmacCookie);
        return "auth-test";
    }

    @RequestMapping(value = "securedTestPage", method = RequestMethod.GET)
    @Secured("hasRole('ROLE_USER')")
    public String showSecuredTestPage() {
        return "securedTestPage";
    }
//
////    @RequestMapping(value = "logout", method = RequestMethod.GET)
////    @ResponseBody
////    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
////        SecurityContextHolder.getContext().setAuthentication(null);
////        return true;
////    }
////
//    /**
//     * This entrypoint delivers a test page that can be used to test the SSO workflows.
//     *
//     * @param request the {@link HttpServletRequest}
//     * @param response the {@link HttpServletResponse}
//     * @param sso the value of the SSO cookie as returned (optional)
//     * @param ssoHmac the value of the SSO-HMAC cookie as returned (optional)
//     * @return the test page view
//     */
//    @RequestMapping(value = "authtest", method = RequestMethod.GET)
//    public String showAuthenticationTestPage(HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(required = false) String sso, @RequestParam(required = false) String ssoHmac) {
//        Cookie ssoCookie = new Cookie(COOKIE_NAME_SSO, sso);
//        Cookie ssoHmacCookie = new Cookie(COOKIE_NAME_SSO_HMAC, ssoHmac);
//        HttpHeaders headers = new HttpHeaders();
//        if (sso == null) {
//            ssoCookie.setMaxAge(0);
//        } else {
//            headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO + "=" + sso);
//        }
//        if (ssoHmac == null) {
//            ssoHmacCookie.setMaxAge(0);
//        } else {
//            headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO_HMAC + "=" + ssoHmac);
//        }
//        response.addCookie(ssoCookie);
//        response.addCookie(ssoHmacCookie);
//
//
//        return "auth-test";
//    }
//
//    @RequestMapping(value = "securedTestPage", method = RequestMethod.GET)
////    @Secured("hasRole('USER_REQUESTED')")
//    public String showSecuredTestPage() {
//        return "securedTestPage";
//    }
    
}
