package de.scout24.financing.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.Role;
import de.scout24.financing.domain.User;
import de.scout24.financing.domain.enums.ExfoRole;
import de.scout24.financing.repository.ForumUserRepository;
import de.scout24.financing.repository.RoleRepository;
import de.scout24.financing.service.impl.ServiceHelper;
import de.scout24.financing.service.impl.auth.ExfoUserDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @Mock
    private ServiceHelper serviceHelper;
    
    @InjectMocks
    private ExfoUserDetailsService userDetailsService;
    
    @Before
    public void setup() {
        serviceHelper.roleRepository = Mockito.mock(RoleRepository.class);
        serviceHelper.forumUserRepository = Mockito.mock(ForumUserRepository.class);
        List<Role> mockedRoles = new ArrayList<>();
        Role role = new Role();
        role.setName(ExfoRole.SUPPORT);
        mockedRoles.add(role);
        ForumUser mockedUser = new ForumUser();
        User user = new User();
        user.setEmail("a@b.de");
        mockedUser.setUser(user);
        Mockito.when(serviceHelper.roleRepository.findByName(Mockito.any(ExfoRole.class))).thenReturn(mockedRoles);
        Mockito.when(serviceHelper.forumUserRepository.findByEmail(Mockito.anyString())).thenReturn(mockedUser);
    }
    
    @Test
    public void testFindByRole() {
        Assert.assertEquals(ExfoRole.SUPPORT, userDetailsService.findByRole(ExfoRole.SUPPORT).getName());
    }
    
    @Test
    public void testLoadUserByUsername() {
        Assert.assertEquals("a@b.de", userDetailsService.loadUserByUsername("a@b.de").getUsername());
    }

}
