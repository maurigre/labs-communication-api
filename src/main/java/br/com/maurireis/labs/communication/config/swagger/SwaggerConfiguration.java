package br.com.maurireis.labs.communication.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.AllArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SwaggerConfiguration {

    private final static String EMAIL = "maurigre@gmail.com";
    private final static String SITE = "http://maurireis.com.br";
    private final static String TITLE = "Mauri Reis";


    private final BuildProperties build;

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.of(plugins));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("controller")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title(build.getName())
                        .contact( new Contact().email(EMAIL).url(SITE).name(TITLE))
                        .description(build.getName() + " - " + build.get("description"))
                        .version(build.getVersion())
                        .license(new License().name("Documentation Powered by " + TITLE).url(SITE)));
    }

}
