package de.scout24.financing.controller;

import de.scout24.financing.domain.Question;
import de.scout24.financing.resource.UserResource;
import de.scout24.financing.service.impl.QuestionService;
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

import de.scout24.financing.domain.User;
import de.scout24.financing.domain.User;
import de.scout24.financing.resource.UserResource;
import de.scout24.financing.service.impl.UserService;

/**
 * Created by ajitchahal on 10/09/15.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Resource<User> getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/users/all/paged", method = RequestMethod.GET)
    public PagedResources<UserResource> getUsersPaged(@PageableDefault Pageable p, PagedResourcesAssembler<User> assembler) {
        return userService.getUsersPaged(p, assembler);
    }
}
