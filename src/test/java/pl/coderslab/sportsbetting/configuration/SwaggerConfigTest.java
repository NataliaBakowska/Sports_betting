package pl.coderslab.sportsbetting.configuration;

import org.junit.Before;
import org.junit.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.*;

public class SwaggerConfigTest {

    SwaggerConfig swaggerConfig;

    @Before
    public void setUp() throws Exception {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    public void api() {
        Docket docket = swaggerConfig.api();
        assertNotNull(docket);
    }
}