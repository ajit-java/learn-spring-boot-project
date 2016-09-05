package de.scout24.financing.service.impl.auth;

import java.util.List;

import de.scout24.financing.resource.AuthenticatedForumUserResource;
import de.scout24.financing.service.impl.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Role;
import de.scout24.financing.domain.enums.ExfoRole;

@Service
public class ExfoUserDetailsService extends BaseService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ForumUser forumUser = serviceHelper.forumUserRepository.findByEmail(email);
        if (forumUser == null) {
            throw new UsernameNotFoundException("username/email " + email + " not found!");
        }
        AuthenticatedForumUserResource authenticatedForumUserResource = new AuthenticatedForumUserResource();
        authenticatedForumUserResource.setForumUser(forumUser);
        return authenticatedForumUserResource;
    }

    public Role findByRole(ExfoRole role) {
        List<Role> roles = serviceHelper.roleRepository.findByName(role);
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }

    public boolean canAccessUser(Long forumUserId) {
        ForumUser loggedInForumUser = serviceHelper.profileService.getLoggedInUser();
        return serviceHelper.profileService.getSecurityPrincipal().isAuthenticated() && loggedInForumUser != null
                && (loggedInForumUser.getRole().getName() == ExfoRole.ADMIN || loggedInForumUser.getRole().getName() == ExfoRole.SUPPORT || loggedInForumUser.getId() == forumUserId);
    }
}
