package de.scout24.financing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.resource.ForumUserResource;
import de.scout24.financing.service.impl.ForumUserService;

/**
 * Created by ajitchahal on 10/09/15.
 */
@RestController
public class ForumUserController {

    @Autowired
    private ForumUserService forumUserService;
    
    @RequestMapping(value = "/forumUsers/all", method = RequestMethod.GET)
    public Iterable<ForumUser> getForumUsers() {
        return forumUserService.getForumUsers();
    }

    @RequestMapping(value = "/forumUsers/{id}", method = RequestMethod.GET)
    public Resource<ForumUser> getForumUser(@PathVariable long id) {
        return forumUserService.getForumUser(id);
    }

    @RequestMapping(value = "/forumUsers/all/paged", method = RequestMethod.GET)
    public PagedResources<ForumUserResource> getForumUsersPaged(@PageableDefault Pageable p, PagedResourcesAssembler<ForumUser> assembler) {
        return forumUserService.getForumUsersPaged(p, assembler);
    }

    // @RequestMapping(value="/forumUsers/byUser",method = RequestMethod.GET)
    // public PagedResources<Resource<ForumUser>> getForumUsersByUser(long
    // userId, @PageableDefault Pageable p, PagedResourcesAssembler
    // pagedAssembler){
    // Page<ForumUser> forumUsers =
    // forumUserRepository.findByUser(userRepository.findOne(userId), p);
    //
    // return pagedAssembler.toResource(forumUsers, forumUserResourceAssembler);
    // }

}
