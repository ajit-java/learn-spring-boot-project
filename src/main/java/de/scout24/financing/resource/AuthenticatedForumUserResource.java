package de.scout24.financing.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Permission;
import de.scout24.financing.domain.Role;
import de.scout24.financing.domain.enums.ExfoRole;
import lombok.Data;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajitchahal on 11/09/15.
 */
@Data
public class AuthenticatedForumUserResource extends ResourceSupport implements UserDetails{
    private boolean isAuthenticated;
    private long forumUserId;
    private String nickName;
    private String message;
    private ExfoRole role;

    public long getForumUserId() {
        return forumUser !=null? forumUser.getId():0;
    }

    public String getNickName() {
        return forumUser !=null?forumUser.getNickName():null;
    }

    public ExfoRole getRole() {
        return forumUser !=null?forumUser.getRole().getName():null;
    }

    @JsonIgnore
    private ForumUser forumUser;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(forumUser!=null) {
            Set<Permission> permissions = forumUser.getRole().getPermissions();
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            for (Permission permission:permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermission().name()));
            }
            return authorities;
        }

        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return forumUser !=null?forumUser.getUser().getPassword():null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return forumUser !=null?forumUser.getUser().getEmail():null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
