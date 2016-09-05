package de.scout24.financing.service.impl;

import de.scout24.financing.domain.Category;
import de.scout24.financing.domain.enums.ExfoRole;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.resource.ForumUserResource;

import java.util.List;

@Service
public class ForumUserService extends BaseService{

    public Iterable<ForumUser> getForumUsers() {
        return serviceHelper.forumUserRepository.findAll();
    }

    public Resource<ForumUser> getForumUser(long id) {
        return serviceHelper.forumUserResourceAssembler.toResource(serviceHelper.forumUserRepository.findOne(id));
    }

    public PagedResources<ForumUserResource> getForumUsersPaged(Pageable p, PagedResourcesAssembler<ForumUser> assembler) {
        Page<ForumUser> forumUsers = serviceHelper.forumUserRepository.findAll(p);

        return assembler.toResource(forumUsers, serviceHelper.forumUserResourceAssembler);
    }

    public ForumUser findOne(long id) {
        return serviceHelper.forumUserRepository.findOne(id);
    }
    public ForumUser getForumUserByUserId(long userId) {
        return serviceHelper.forumUserRepository.findByUserIdAndDeletedFalse(userId);
    }

    public List<ForumUser> getExperts(Category category, String zipCode) {
        throw new NotImplementedException();
    }
    public List<ForumUser> getAllExperts() {
        return serviceHelper.forumUserRepository.getByRoleAndDeletedFalse(serviceHelper.roleRepository.getByName(ExfoRole.EXPERT_APPROVED));
    }
}
