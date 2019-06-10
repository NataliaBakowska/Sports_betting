package pl.coderslab.sportsbetting.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import pl.coderslab.sportsbetting.entity.Role;
import pl.coderslab.sportsbetting.entity.User;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataUserDetailsServiceTest {

    private SpringDataUserDetailsService userDetailsService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new SpringDataUserDetailsService();
        ReflectionTestUtils.setField(userDetailsService, "userService", userService);
    }

    @Test
    public void setUserRepository() {
        userDetailsService.setUserRepository(userService);
    }

    @Test
    public void loadUserByUsername() {
        User user = new User();
        Role role = new Role();
        role.setName("rola");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setUsername("username");
        user.setPassword("password");
        when(userService.findByUserName("username")).thenReturn(user);

        UserDetails result= userDetailsService.loadUserByUsername("username");
    }
}