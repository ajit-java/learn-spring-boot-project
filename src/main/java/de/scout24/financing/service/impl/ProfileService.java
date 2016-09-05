package de.scout24.financing.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import de.scout24.financing.domain.*;
import de.scout24.financing.domain.enums.MessageKey;
import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.exception.ExpertForumException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.resource.AuthenticatedForumUserResource;
import org.springframework.web.client.RestClientException;

/**
 * Initially created by Tino on 05.08.15.
 */
@Service
@Transactional
public class ProfileService extends BaseService{

    private static final String SERVICE_URL_PRINCIPAL = "https://exfo.sandbox-immobilienscout24.de:8444/auth/public/principal";
    private static final String HEADER_NAME_COOKIE = "Cookie";
    private static final String COOKIE_NAME_SSO = "SSO";
    private static final String COOKIE_NAME_SSO_HMAC = "SSO-HMAC";
    @Value("${exfo.sso.baseUrl}" + "${exfo.sso.authenticationPath}")
    private String authenticationRestUrl;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class);

    @PreAuthorize("@exfoUserDetailsService.canAccessUser(#id)")
    public ForumUser updateForumUser(ForumUser forumUser) throws ExpertForumException{
        ForumUser dbForumUser = null;
        if(getLoggedInUser().getRole().getName()==ExfoRole.SUPPORT || getLoggedInUser().getRole().getName()==ExfoRole.ADMIN) {
            dbForumUser = serviceHelper.forumUserRepository.findOne(forumUser.getId());
        }
        else {
            dbForumUser = getLoggedInUser();
        }
        if (dbForumUser == null) {
            throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.INVALID_USER_ID, new Exception("Error while updating forum user.!"));
        }

        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());
        dbForumUser.setFirstName(forumUser.getFirstName());

        dbForumUser = serviceHelper.forumUserRepository.save(dbForumUser);

        return dbForumUser;
    }

    public ForumUser saveForumUser(User user) {
        User dbUser = serviceHelper.userRepository.findBySsoIdAndDeletedFalse(user.getSsoId());
        if (dbUser == null) {
            dbUser = serviceHelper.userRepository.save(user);
        }
        ForumUser forumUser = new ForumUser();
        forumUser.setRole(serviceHelper.roleRepository.getByName(ExfoRole.USER_REQUESTED));
        forumUser.setUser(dbUser);
        forumUser.setEmail(user.getEmail());

        forumUser = serviceHelper.forumUserRepository.save(forumUser);

        return forumUser;
    }

    public AuthenticatedForumUserResource authenticate(HttpServletRequest request, HttpServletResponse response, String sso, String ssoHmac) throws ExpertForumException {
        AuthenticatedForumUserResource authenticatedForumUserResource = new AuthenticatedForumUserResource();
        authenticatedForumUserResource.setAuthenticated(false);

        HttpHeaders headers = getHttpHeadersFromExfoSsoCookies(request, response, sso, ssoHmac);
        if(!headers.isEmpty())
        {
            User user = null;
            ForumUser forumUser = null;
            try {

                User ssoUser = getSsoUserFromFs24AuthApi(headers);
                if (ssoUser != null) {
                    user = serviceHelper.userRepository.findBySsoIdAndDeletedFalse(ssoUser.getSsoId()); //try to get local exfo user by his sso id
                    if (user != null) {
                        forumUser = serviceHelper.forumUserService.getForumUserByUserId(user.getId()); //returning expert forum user
                    } else {
                        forumUser = saveForumUser(ssoUser);//this sso user is a new expert fourm user
                    }
                }
            } catch (RestClientException e) {
                throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.AUTHENTICATION_ERROR, e, "Error while connecting to Fs24 Auth API");
            } catch (Exception e) {
                throw serviceHelper.exceptionHandler.handle(this, ExceptionKey.AUTHENTICATION_ERROR, e, "Generic Error while Authenticating user");
            }

            if (forumUser != null) {
                authenticatedForumUserResource.setAuthenticated(true);
                authenticatedForumUserResource.setForumUser(forumUser);
                Authentication auth = new UsernamePasswordAuthenticationToken(authenticatedForumUserResource, forumUser.getUser().getPassword(), authenticatedForumUserResource.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            else {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
        return authenticatedForumUserResource;
    }

    private User getSsoUserFromFs24AuthApi(HttpHeaders headers) {
        return serviceHelper.restService.exchange(authenticationRestUrl, HttpMethod.GET, null, headers,  new ParameterizedTypeReference<User>() {}).getBody();
    }

    //after auth exfo itself creates its own cookies
    private HttpHeaders getHttpHeadersFromExfoSsoCookies(HttpServletRequest request, HttpServletResponse response, String sso, String ssoHmac) {HttpHeaders headers = new HttpHeaders();
        Optional<Cookie[]> cookies = Optional.ofNullable(request.getCookies());
        if (cookies.isPresent()) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(COOKIE_NAME_SSO)) {
                    headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO + "=" + cookie.getValue());
                } else if (cookie.getName().equals(COOKIE_NAME_SSO_HMAC)) {
                    headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO_HMAC + "=" + cookie.getValue());
                }
            }
        }
        if(headers.isEmpty())
        {
            Cookie ssoCookie = new Cookie(COOKIE_NAME_SSO, sso);
            Cookie ssoHmacCookie = new Cookie(COOKIE_NAME_SSO_HMAC, ssoHmac);
            headers = new HttpHeaders();
            if (sso == null) {
                ssoCookie.setMaxAge(0);
            } else {
                headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO + "=" + sso);
            }
            if (ssoHmac == null) {
                ssoHmacCookie.setMaxAge(0);
            } else {
                headers.add(HEADER_NAME_COOKIE, COOKIE_NAME_SSO_HMAC + "=" + ssoHmac);
            }
            response.addCookie(ssoCookie);
            response.addCookie(ssoHmacCookie);
        }
        return headers;
    }

    public ForumUser getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            AuthenticatedForumUserResource authenticatedForumUserResource = (AuthenticatedForumUserResource) authentication.getPrincipal();
            return authenticatedForumUserResource.getForumUser();
        }

        return null;
    }
    public Authentication getSecurityPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    public boolean hasRole(ExfoRole role){
        return getLoggedInUser().getRole().getName().equals(role);
    }
//    public boolean hasPermission(Permission permission){
//        return getLoggedInUser().getAuthorities().getRole().getName().equals(role);
//    }
}
