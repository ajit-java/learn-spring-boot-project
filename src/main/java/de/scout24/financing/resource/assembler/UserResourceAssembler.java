package de.scout24.financing.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import de.scout24.financing.resource.UserResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import de.scout24.financing.controller.UserController;
import de.scout24.financing.domain.User;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource> {

    @Override
    public UserResource toResource(User user) {
        UserResource resource = new UserResource(user);

//        getLinks(user, resource);
        return resource;
    }

    private void getLinks(User user, UserResource resource) {
        try {
            resource.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
        } catch (ResourceNotFoundException ex) {
            // do nothing
        }
    }
}
