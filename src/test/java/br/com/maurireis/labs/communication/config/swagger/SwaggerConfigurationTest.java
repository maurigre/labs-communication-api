package br.com.maurireis.labs.communication.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;

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

        OpenAPI openAPI = swaggerConfiguration.openAPI();

        assertNotNull(openAPI);
        assertTrue(openAPI.getClass().isAssignableFrom(OpenAPI.class));
    }

}
