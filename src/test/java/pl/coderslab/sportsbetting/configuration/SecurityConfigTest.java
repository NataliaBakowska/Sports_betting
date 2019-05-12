package pl.coderslab.sportsbetting.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.sportsbetting.service.SpringDataUserDetailsService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(HttpSecurity.class)
public class SecurityConfigTest {

    SecurityConfig securityConfig;

    @Before
    public void setUp() throws Exception {
        securityConfig = new SecurityConfig();
    }

//    @Test
//    public void configure() throws Exception {
//        securityConfig.configure(httpSecurity);
//        verify(httpSecurity, times(1)).authorizeRequests();
//    }

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
}