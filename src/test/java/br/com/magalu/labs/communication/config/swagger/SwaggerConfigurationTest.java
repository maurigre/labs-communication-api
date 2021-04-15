package br.com.magalu.labs.communication.config.swagger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class SwaggerConfigurationTest {

    BuildProperties build;

    @BeforeEach
    void setUp() {
        this.build = mock(BuildProperties.class);
    }

    @Test
    void testCreateApi(){
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration(build);
        Docket api = swaggerConfiguration.api();

        assertNotNull(api);
        assertTrue(api.getClass().isAssignableFrom(Docket.class));
    }

}
