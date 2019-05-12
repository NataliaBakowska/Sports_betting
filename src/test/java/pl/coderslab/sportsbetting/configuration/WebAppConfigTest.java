package pl.coderslab.sportsbetting.configuration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ViewResolver;

import static org.junit.Assert.*;

public class WebAppConfigTest {

    WebAppConfig webAppConfig;

    @Before
    public void setUp() throws Exception {
        webAppConfig = new WebAppConfig();
    }

    @Test
    public void viewResolver() {
        ViewResolver viewResolver = webAppConfig.viewResolver();
        assertNotNull(viewResolver);
    }
}