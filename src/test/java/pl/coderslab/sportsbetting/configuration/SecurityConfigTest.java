package pl.coderslab.sportsbetting.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.sportsbetting.service.SpringDataUserDetailsService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

    SecurityConfig securityConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        securityConfig = new SecurityConfig();
    }


    @Test
    public void passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = securityConfig.passwordEncoder();
        assertNotNull(bCryptPasswordEncoder);
    }

    @Test
    public void customUserDetailsService() {
        SpringDataUserDetailsService userDetailsService = securityConfig.customUserDetailsService();
        assertNotNull(userDetailsService);
    }

    @Test
    public void passwordEncoder1() {
        securityConfig.passwordEncoder();
    }

    @Test
    public void customUserDetailsService1() {
        securityConfig.customUserDetailsService();
    }
}