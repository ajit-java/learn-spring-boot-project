package de.scout24.financing.controller;

import de.scout24.financing.exception.ExpertForumException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.resource.AuthenticatedForumUserResource;
import de.scout24.financing.service.impl.ProfileService;

@RestController
public class UserProfileController {
    
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/profile/updateForumUser", method = RequestMethod.POST)
    public ForumUser updateForumUser(@RequestBody ForumUser forumUser) throws ExpertForumException{
        profileService.updateForumUser(forumUser);
        return null;
    }

//    @RequestMapping(value = "/profile/login", method = RequestMethod.POST)
//    public AuthenticatedForumUserResource login(@RequestBody AuthenticatedForumUserResource login) {
//
//        // todo login user properly with security impl
//
//        return profileService.login(login);
//    }

}
