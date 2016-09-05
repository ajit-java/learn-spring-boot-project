package de.scout24.financing.service.impl;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.User;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.UserRepository;
import de.scout24.financing.resource.ForumUserResource;
import de.scout24.financing.resource.UserResource;
import de.scout24.financing.resource.assembler.ForumUserResourceAssembler;
import de.scout24.financing.resource.assembler.UserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService{

    public Iterable<User> getUsers() {
        return serviceHelper.userRepository.findAll();
    }

    public UserResource getUser(long id) {
        return serviceHelper.userResourceAssembler.toResource(serviceHelper.userRepository.findOne(id));
    }

    public PagedResources<UserResource> getUsersPaged(Pageable p, PagedResourcesAssembler<User> assembler) {
        Page<User> users = serviceHelper.userRepository.findAll(p);

        return assembler.toResource(users, serviceHelper.userResourceAssembler);
    }
}
